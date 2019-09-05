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
		FileHolder holder;
		File file;
		File newFile;
		
		try
		{
    		holder= (FileHolder) path.getLastPathComponent();
    		file = holder.getFile();
		
    		//Create a file for the new path
    		newFile = new File(file.getParent(),newVal.toString());
            
    		if(file.renameTo(newFile))
    		{
    		    Object objPath[];
    		    Object pobjPath[];
    		    TreePath parentPath;
    		    FileHolder parentHolder;
    		    Vector files;
    		    int i,max;
    		    int childInd[];
    		    Object child[];
    		    
    		    //Change the file for the holder
    		    holder.setFile(newFile);
    		    
    		    //Notify listeners
    		    objPath = path.getPath();
    		    
    		    max = objPath.length - 1;
    		    pobjPath = new Object[max];
    		    
    		    for(i=0;i<max;i++)
    		    {
    		        pobjPath[i] = objPath[i];
    		    }
    		    
    		    parentPath = new TreePath(pobjPath);
    		    
    		    parentHolder = (FileHolder) parentPath.getLastPathComponent();
    		    
    		    files = parentHolder.getChildren();
    		    
    		    childInd = new int[1];
    		    childInd[0] = files.indexOf(holder);
    		    
    		    child = new Object[1];
    		    child[0] = holder;
    		    
    		    fireValueChanged(parentPath,childInd,child);
    		}
		}
		catch(Exception exp)
		{
		    //ignore the change
		}
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
	
	protected void fireValueChanged(TreePath path,int[] ind,
	                                    Object[] children)
	{
	    TreeModelEvent evt;
	    int i,max;
	    TreeModelListener curL;
	    
	    evt = new TreeModelEvent(this,path,ind,children);
	    
	    max = listeners.size();
	    
	    for(i=0;i<max;i++)
	    {
	        curL = (TreeModelListener) listeners.elementAt(i);
	        
	        curL.treeNodesChanged(evt);
	    }
	}
}

class FileHolder
{
    File myFile;
    Vector children;
    
    public FileHolder(File f)
    {
        myFile = f;
    }
    
    public File getFile()
    {
        return myFile;
    }
    
    public void setFile(File f)
    {
        myFile = f;
        children = null;
    }
    
    public Vector getChildren()
    {
        if(myFile.isDirectory()
            &&(children == null))
        {
            int i,max=0;
            String list[];
            File curFile;
            FileHolder curHolder;
            
            children = new Vector();
            
            list = myFile.list();
            
            if(list != null) max = list.length;
            
            for(i=0;i<max;i++)
            {
                curFile = new File(myFile,list[i]);
                curHolder = new FileHolder(curFile);
                children.addElement(curHolder);
            }
        }
        
        return children;
    }
    
    public String toString()
    {
        return myFile.getName();
    }
}
