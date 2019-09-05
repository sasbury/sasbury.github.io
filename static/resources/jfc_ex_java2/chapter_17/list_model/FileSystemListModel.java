//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class FileSystemListModel extends AbstractListModel
{
	protected String[] fileNames;
	
	public FileSystemListModel(File r)
	{
	   fileNames = r.list();
	}
	
	public int getSize()
	{
	    int retVal = 0;
	    
	    if(fileNames != null) retVal = fileNames.length;
	    
	    return retVal;
	}
	
	public Object getElementAt(int i)
	{
	    Object retVal = null;
	    
	    if(fileNames != null) retVal = fileNames[i];
	    
	    return retVal;
	}
	
}
