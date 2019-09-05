
import java.io.*;

public class FileCopier
{
	private static boolean overwrite;
	
	static
	{
		overwrite = false;
	}
	
	/**
	 * By default a file will not be copied if 
	 * the destination has a file with the same
	 * length and modification time.
	 */
	public static void setOverwrite(boolean tf)
	{
		overwrite = tf;
	}
	
	/**
	 * Copy the src to the destination path.
	 * Works for file or directory, depending on the
	 * type of src.
	 * Creates the destination path if necessary.
	 */
	public static void copy(String src, String dest) throws IOException
	{
		File srcF,destF;
		
		srcF = new File(src);
		destF = new File(dest);
		copy(srcF,destF);
	}
	
	/**
	 * Copy the src to the destination path.
	 * Works for file or directory, depending on the
	 * type of src.
	 * Creates the destination path if necessary.
	 */
	public static void copy(File src, File dest) throws IOException
	{
		try
		{
			if(src.isFile())
			{
				copyFile(src,dest);
			}
			else if (src.isDirectory())
			{
				copyDirectory(src,dest);
			}
		}
		catch(Exception exp)
		{
			throw new IOException("Error copying files.");
		}
	}
	
	/**
	 * Copy the src into the destination dir.
	 * Uses the same name for the dest as the src.
	 * Creates the destination path if necessary.
	 */
	public static void copyTo(String src,String destDir) throws IOException
	{
		File srcF,destF;
		
		srcF = new File(src);
		destF = new File(destDir);
		copy(srcF,destF);
	}
	
	/**
	 * Copy the src into the destination dir.
	 * Uses the same name for the dest as the src.
	 * Creates the destination path if necessary.
	 */
	public static void copyTo(File src,File destDir) throws IOException
	{
		File realDest;
		String realDestPath;
		
		realDestPath = src.getName();
		realDestPath = destDir.getAbsolutePath()
						+ File.separator
						+ realDestPath;
		
		realDest = new File(realDestPath);
		copy(src,realDest);
	}
	
	/**
	 * Copy the src to the destination path.
	 * Creates the destination path if necessary.
	 */
	public static void copyDirectory(File src,File dest) throws IOException
	{
		copyDirectoryWithFilter(src,dest,null);
	}
	
	/**
	 * Copy the src to the destination path.
	 * Using a file filter on all of the files.
	 * Creates the destination path if necessary.
	 */
	public static void copyDirectoryWithFilter(File src,File dest,FilenameFilter filter) throws IOException
	{
		copyDirectoryWithFilter(src,dest,filter,true);
	}
	
	/**
	 * Copy the src to the destination path.
	 * Using a file filter on all of the files.
	 * Creates the destination path if necessary.
	 * Recursion can be turned on or off
	 */
	public static void  copyDirectoryWithFilter(File src,File dest,FilenameFilter filter,boolean recurse) throws IOException
	{
		try
		{
			String files[];
			File cur;
			int i,max;
			
			if(!src.exists()) return;
			
			insureDirectory(dest);
			
			files = src.list();
		
			max = files.length;
			
			for(i=0;i<max;i++)
			{
				cur = new File(src,files[i]);
				
				if(cur.isDirectory() && recurse)
				{
					copyTo(cur,dest);
				}
				else if(cur.isFile()
						&& ((filter ==
						null)||(filter.accept(src,files[i]))))
				{
					copyTo(cur,dest);
				}
			}
		}
		catch(Exception exp)
		{
			throw new IOException("Error copying files.");
		}
	}
	
	public static void insureDirectory(String dir) throws IOException
	{
		insureDirectory(new File(dir));
	}
	
	public static void insureDirectory(File dir) throws IOException
	{
		try
		{
			if((dir != null)&&(!dir.exists())) dir.mkdirs();
		}
		catch(Exception exp)
		{
			throw new IOException();
		}
	}
	
	protected static void copyFile(File src,File dest) throws IOException
	{
		FileInputStream fileIn;
		BufferedInputStream bufIn;
		BufferedOutputStream bufOut;
		FileOutputStream fileOut;
		int cur;
		
		try
		{
			insureDirectory(dest.getParent());
		
			if(!overwrite && dest.exists() && src.exists()
				&&(dest.length()==src.length())
				&&(dest.lastModified()==src.lastModified()))
			{
				return;//do nothing if the file is unchanged
			}
			
			if(!src.exists()) return;
			
			fileIn = new FileInputStream(src);
			bufIn = new BufferedInputStream(fileIn);
			fileOut = new FileOutputStream(dest);
			bufOut = new BufferedOutputStream(fileOut);
		
			while((cur = bufIn.read()) != -1)
			{
				bufOut.write(cur);
			}
		
			bufIn.close();
			bufOut.close();
		}
		catch(Exception exp)
		{
			throw new IOException("Error copying file: "+src.getAbsolutePath());
		}
	
	}

    public static boolean deleteDirectory(String path)
    {
        File f = new File(path);
        return deleteDirectory(f);
    }

    /**
     * Returns true if the delete was completely successfull.
     * Because all of the files in a directory have to be deleted to 
	* delete the directory, it is possible
     * that this method will fail after performing a partial delete.
     */
    public static boolean deleteDirectory(File f)
    {
        boolean retVal = true;

        try
        {
            if(f.isDirectory())
            {
                File[] files = f.listFiles();

                if(files!=null)
                {
                    for(int i=0,max=files.length;i<max;i++)
                    {
                        if(files[i].isDirectory())
                        {
                            retVal = retVal && deleteDirectory(files[i]);
                        }
                        else
                        {
                            retVal = retVal && files[i].delete();
                        }
                    }
                }

                retVal = retVal && f.delete();
            }
        }
        catch(Exception exp)
        {
            retVal = false;
        }

        return retVal;
    }
}
