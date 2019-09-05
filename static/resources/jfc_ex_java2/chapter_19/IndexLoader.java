//Copyright 1998 John Wiley and Sons, Inc.


import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.text.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;

public class IndexLoader implements Runnable
{
	DocSearch searcher;
	String dir;
	
	IndexLoader(DocSearch ds,String d)
	{
	    searcher = ds;
	    dir = d;
	}

    public void run()
    {
        HTMLIndex index;
        Cursor c = searcher.getCursor();
        Cursor waitCursor 
            = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        
        searcher.setCursor(waitCursor);
        
        index=new HTMLIndex(new File(dir),true);
        
        searcher.setIndex(index);
        searcher.setCursor(c);
    }
}
