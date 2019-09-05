//Copyright 1998 John Wiley and Sons, Inc.

import java.io.*;
import java.util.*;

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
