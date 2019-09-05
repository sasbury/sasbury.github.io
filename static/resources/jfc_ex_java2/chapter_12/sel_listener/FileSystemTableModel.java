//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class FileSystemTableModel extends AbstractTableModel
{
	protected Vector files;
	
	public FileSystemTableModel(File r)
	{
	    String fileNames[];
	    File curFile;
	    int i,max;
	    
	    files = new Vector();
		
	    fileNames = r.list();
	    
	    max = fileNames.length;
	    
	    for(i=0;i<max;i++)
	    {
	        curFile = new File(r,fileNames[i]);
	        files.addElement(curFile);
	    }
	}
	
	public int getRowCount()
	{
	    return files.size();
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
	            retVal = "Last Modified";
	            break;
	        case 2:
	            retVal = "Size";
	            break;
	        case 3:
	            retVal = "Directory";
	            break;
	        default:
	            retVal = "default";
	            break;
	    }
	    
	    return retVal;
	}
	
	public Class getColumnClass(int columnIndex)
	{
	    Class retVal = String.class;
	    
	    if(columnIndex==3) retVal = Boolean.class;
	    
	    return retVal;
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
        File file=null;
        
        if(rowIndex < files.size())
        {
            file = (File)files.elementAt(rowIndex);
        }
        
        if(file == null) return "";
        
        if(columnIndex == 0)
        {
            retVal = file.getName();
        }
        else if(columnIndex == 1)
        {
            Date d = new Date(file.lastModified());
            java.text.DateFormat df = java.text.DateFormat.getDateInstance();
            
            
            
            retVal = df.format(d);
        }
        else if(columnIndex == 2)
        {
            long sizeInBytes = file.length();
            long sizeInKb = sizeInBytes/1024;
            
            if(sizeInKb < 1) sizeInKb = 1;
            
            retVal = String.valueOf(sizeInKb) + " kb";
        }
        else if(columnIndex == 3)
        {
            retVal = new Boolean(file.isDirectory());
        }
        else
        {
            retVal = file;
        }
        
        return retVal;
    }
	
	public void setValueAt(Object aValue,
                                 int rowIndex,
                                 int columnIndex)
    {
        return;
    }
}
