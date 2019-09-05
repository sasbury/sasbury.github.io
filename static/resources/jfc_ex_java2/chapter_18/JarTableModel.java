//Copyright 1998 John Wiley and Sons, Inc.

import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class JarTableModel extends AbstractTableModel
{
	protected File myFile;
	protected ZipFile myZipFile;
	protected Vector entries;
	
	public JarTableModel(File f)
	{
		myFile = f;
		entries = new Vector();
		
		try
		{
		    if(myFile != null) myZipFile=new ZipFile(myFile);
		}
		catch(Exception exp)
		{
		    myZipFile = null;
		}
		
		updateEntries();
	}
	
	protected void updateEntries()
	{
	    Enumeration enum;
	    
	    if(myZipFile != null)
	    {
	        enum = myZipFile.entries();
	        
	        entries.removeAllElements();
	        
	        while(enum.hasMoreElements())
	        {
	            entries.addElement(enum.nextElement());
	        }
	    }
	}
	
	public int getRowCount()
	{
	    return entries.size();
	}
	
	public int getColumnCount()
	{
	    return 4;
	}
	
	public String getColumnName(int columnIndex)
	{
	    String retVal = "";
	    
	    switch(columnIndex)
	    {
	        case 0:
	            retVal = "Name";
	            break;
	        case 1:
	            retVal = "Time";
	            break;
	        case 2:
	            retVal = "Size";
	            break;
	        case 3:
	            retVal = "Comment";
	            break;
	        default:
	            retVal = "default";
	            break;
	    }
	    
	    return retVal;
	}
	
	public Class getColumnClass(int columnIndex)
	{
	    return String.class;
	}
	
	public boolean isCellEditable(int rowIndex,
                                        int columnIndex)
    {
        boolean retVal = false;
        
        return retVal;
    }
    
	public Object getValueAt(int rowIndex,
                                   int columnIndex)
    {
        Object retVal=null;
        ZipEntry entry=null;
        
        if(rowIndex < entries.size())
        {
            entry = (ZipEntry)entries.elementAt(rowIndex);
        }
        
        if(entry == null) return "";
        
        if(columnIndex == 0)
        {
            retVal = entry.getName();
        }
        else if(columnIndex == 1)
        {
            Date d = new Date(entry.getTime());
            java.text.DateFormat df = java.text.DateFormat.getDateInstance();
            
            
            
            retVal = df.format(d);
        }
        else if(columnIndex == 2)
        {
            long sizeInBytes = entry.getSize();
            long sizeInKb = sizeInBytes/1024;
            
            if(sizeInKb < 1) sizeInKb = 1;
            
            retVal = String.valueOf(sizeInKb) + " kb";
        }
        else if(columnIndex == 3)
        {
            retVal = entry.getComment();
        }
        
        return retVal;
    }
	
	public void setValueAt(Object aValue,
                                 int rowIndex,
                                 int columnIndex)
    {
        return;
    }
    
    public ZipFile getZipFile()
    {
        return myZipFile;
    }
    
    public File getFile()
    {
        return myFile;
    }
    
    public Vector getEntries()
    {
        return entries;
    }
}
