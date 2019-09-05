//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.io.*;

public class HTMLIndex
{
	Vector files;
	Hashtable theIndex;
	boolean indexLoaded;
	
	public static final String INDEX_FILE_NAME=".htmlindex";
	
	/**
	 * Looks for an index file and creates one if necessary.
	 * doesn't log the build process.
	 */
	public HTMLIndex(File dir)
	{
		this(dir,false);
	}
	
	/**
	 * Looks for an index file and creates one if necessary.
	 */
	public HTMLIndex(File dir,boolean log)
	{
		File indexFile;
		
		theIndex = new Hashtable();
		files = new Vector();
		
		indexFile = new File(dir,INDEX_FILE_NAME);
		
		if(!indexFile.exists())
		{
			HTMLIndex.buildIndex(dir,log);
		}
		
		loadIndexFile(indexFile);
	}
	
	/**
	 * Private method that loads the index file.
	 */
	protected void loadIndexFile(File indexFile)
	{
		FileReader fileIn;
		LineNumberReader lineIn;
		String curLine;
		StringTokenizer cursor;
		String word,curId;
		Vector entryForWord;
		Integer newEntry;
		
		try
		{
			fileIn = new FileReader(indexFile);
			lineIn = new LineNumberReader(fileIn);
			
			//Read the file names
			
			while(((curLine = lineIn.readLine()) != null)
					&&(curLine.length()>0))
			{
				files.addElement(curLine);
			}
			
			//Read the words
			
			while(((curLine = lineIn.readLine()) != null)
					&&(curLine.length()>0))
			{
				cursor= new StringTokenizer(curLine,"|");
			
				word = cursor.nextToken();
				entryForWord = new Vector();
			
				while(cursor.hasMoreTokens())
				{
					curId = cursor.nextToken();
					newEntry = new Integer(curId);
					
					if(!entryForWord.contains(newEntry))
						entryForWord.addElement(newEntry);
				}
				
				theIndex.put(word,entryForWord);
			}
			
			lineIn.close();
			fileIn.close();
			
			indexLoaded = true;
		}
		catch(Exception exp)
		{
			indexLoaded = false;
		}
	}
	
	/**
	 * Return true if the index is loaded.
	 */
	public boolean getIndexLoaded()
	{
		return indexLoaded;
	}
	
	public Enumeration files()
	{
		return files.elements();
	}
	
	public Enumeration words()
	{
		return theIndex.keys();	
	}
	
	public Vector filesForWord(String word)
	{
		Vector dataForWord;
		Vector results = new Vector();		
		
		dataForWord = (Vector) theIndex.get(word.toLowerCase());
		
		if(dataForWord != null)
		{
			int i,max;
			int fileIndex;
			
			max = dataForWord.size();
			
			for(i=0;i<max;i++)
			{
				fileIndex = ((Integer)dataForWord.elementAt(i)).intValue();
				results.addElement(files.elementAt(fileIndex));
			}
		}
		
		return results;
	}
	
	/**
	 * Private method to index a directory
	 */
	public static void buildIndexForDir(File dir
										,Vector files
										,Hashtable entries
										,boolean log)
	{
		String[] list;
		File curFile;
		int i,max;
		
		list = dir.list();
		
		if(log) System.out.println("Indexing Directory: "+dir);
		
		max = list.length;
		
		for(i=0;i<max;i++)
		{
			curFile = new File(dir,list[i]);
			
			if(curFile.isDirectory())
			{
				buildIndexForDir(curFile,files,entries,log);
			}
			else if(list[i].endsWith(".html")
					||list[i].endsWith(".htm"))
			{
				if(log) System.out.println("Indexing file: "+curFile);
				
				buildIndexForFile(curFile,files,entries);
			}
		}
	}
	
	/**
	 * Private method to index a file
	 */
	public static void buildIndexForFile(File file
										,Vector files
										,Hashtable entries)
	{
		FileReader fileIn;
		BufferedReader bufIn;
		int cur;
		int fileInd;
		boolean inTag;
		StringBuffer curWord;
		String curString;
		Vector entryForWord;
		Integer newEntry;
		
		try
		{
			files.addElement(file);
		
			fileInd = files.indexOf(file);
			
			fileIn = new FileReader(file);
			bufIn = new BufferedReader(fileIn);
			
			inTag = false;
		
			curWord = new StringBuffer();
			
			while((cur = bufIn.read()) != -1)
			{
				if(cur == '<')
				{
					inTag = true;
				}
				else if(cur == '>')
				{
					inTag = false;
				}
				else if((Character.isWhitespace((char)cur)
							||(cur == '.')
							||(cur == ',')
							||(cur == '[')
							||(cur == ']')
							||(cur == '{')
							||(cur == '}')
							||(cur == '(')
							||(cur == ')')
							||(cur == '=')
							||(cur == ';')
							||(cur == ':')
							||(cur == '=')
							||(cur == '-')
							||(cur == '/')
							||(cur == '\\'))
							&& !inTag)
				{
					if(curWord.length()>0)
					{
						curString = curWord.toString().toLowerCase();
						curWord.setLength(0);
						
						entryForWord = (Vector) entries.get(curString);
						
						if(entryForWord == null)
						{
							entryForWord = new Vector();
							
							entries.put(curString,entryForWord);
						}
						
						newEntry = new Integer(fileInd);
					
						if(!entryForWord.contains(newEntry))
							entryForWord.addElement(newEntry);
					}
				}
				else if(!inTag)
				{
					//Don't add the separator
					//or quotes
					if(cur == '|')
					{
						curWord.append('-');
					}
					else if((cur != '\"')
							&&(cur != '\''))
					{
						curWord.append((char)cur);
					}
				}
			}
			
			if(curWord.length()>0)
			{
				curString = curWord.toString().toLowerCase();
				curWord.setLength(0);
				
				entryForWord = (Vector) entries.get(curString);
				
				if(entryForWord == null)
				{
					entryForWord = new Vector();
					
					entries.put(curString,entryForWord);
				}
				
				newEntry = new Integer(fileInd);
					
				if(!entryForWord.contains(newEntry))
					entryForWord.addElement(newEntry);
			}
		
			bufIn.close();
			fileIn.close();
		}
		catch(Exception exp)
		{
			//skip this file
		}
	}
	
	/**
	 * Private method write the index file
	 */
	public static void writeIndex(File dir
										,Vector files
										,Hashtable entries)
	{
		FileWriter fileOut;
		BufferedWriter bufOut;
		PrintWriter printOut;
		File curFile;
		int i,max;
		Enumeration words;
		String curWord;
		Vector entryForWord;
		File file;
		
		try
		{
			file = new File(dir,INDEX_FILE_NAME);
			fileOut = new FileWriter(file);
			bufOut = new BufferedWriter(fileOut);
			printOut = new PrintWriter(bufOut);
		
			//Print files in order
			
			max = files.size();
			
			for(i=0;i<max;i++)
			{
				curFile = (File) files.elementAt(i);
				printOut.println(curFile.getAbsolutePath());
			}
			
			//blank line to break files from words
			
			printOut.println();
			
			//print words
			
			words = entries.keys();
			
			while(words.hasMoreElements())
			{
				curWord = (String) words.nextElement();
				
				entryForWord = (Vector) entries.get(curWord);
				
				max = entryForWord.size();

				printOut.print(curWord);
				
				for(i=0;i<max;i++)
				{
					printOut.print('|');
					printOut.print(entryForWord.elementAt(i));
				}
				//add new line char
				printOut.println();
			}
			
			printOut.close();
			bufOut.close();
			fileOut.close();
		}
		catch(Exception exp)
		{
			//failed to write index
		}
	}
	
	/**
	 * Builds an index file for a directory
	 */
	public static void buildIndex(File dir,boolean log)
	{
		Vector files = new Vector();
		Hashtable entries = new Hashtable();
		
		buildIndexForDir(dir,files,entries,log);
		
		if(log) System.out.println("Writing Index");
		writeIndex(dir,files,entries);
	}
	
	public static void main(String args[])
	{
		if(args.length <= 1)
		{
			System.out.println("usage: java HTMLIndex dir word");
			return;
		}
		
		HTMLIndex index = new HTMLIndex(new File(args[0]),true);
		Vector files = index.filesForWord(args[1]);
		int i,max;
		
		max = files.size();
		
		if(max == 0) System.out.println("No Files Match Query");
		else System.out.println("Matching Files---------\n");
		
		for(i=0;i<max;i++)
		{
			System.out.println(files.elementAt(i));
		}
	}
}
