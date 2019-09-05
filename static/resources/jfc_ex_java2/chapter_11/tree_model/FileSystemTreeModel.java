//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

class FileSystemTreeModel implements TreeModel
{
	protected FileHolder root;
	protected Vector listeners;
	
	public FileSystemTreeModel(File r)
	{
		root = new FileHolder(r);
		listeners = new Vector();
	}
	
	public Object getRoot()
	{
		return root;
	}
	
	public Object getChild(Object parent,
                                 int index)
    {
		Object retVal = null;
		Vector children;
		
		if(parent instanceof FileHolder)
		{
		    children = ((FileHolder)parent).getChildren();
		    
		    if(children != null)
		    {
		        if(index < children.size())
		        {
		            retVal = children.elementAt(index);
		        }
		    }
		}
		
		return retVal;
	}
	
	public int getChildCount(Object parent)
    {
    	int retVal = 0;
    	Vector children;
		
		if(parent instanceof FileHolder)
		{
		    children = ((FileHolder)parent).getChildren();
		    
		    if(children != null)
		    {
		        retVal = children.size();
		    }
		}
    	
    	return retVal;
	}
	
	public boolean isLeaf(Object node)
    {
    	boolean retVal = true;
		File file;
		
		if(node instanceof FileHolder)
		{
		    file = ((FileHolder)node).getFile();
		    
		    retVal = file.isFile();
		}
    	
    	return retVal;
	}
	
	public void valueForPathChanged(TreePath path,Object newVal)
	{
		//Do Nothing
	}
	
	public int getIndexOfChild(Object parent,
                                     Object child)
	{
		int retVal = -1;
    	Vector children;
		
		if(parent instanceof FileHolder)
		{
		    children = ((FileHolder)parent).getChildren();
		    
		    if(children != null)
		    {
		        retVal = children.indexOf(child);
		    }
		}
		
		return retVal;
	}
	
	public void addTreeModelListener(TreeModelListener l)
	{
		if((l != null)&&!listeners.contains(l)) listeners.addElement(l);
	}
	
	public void removeTreeModelListener(TreeModelListener l)
	{
		listeners.removeElement(l);
	}
}
