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

public class PageLoader implements Runnable
{
	URL url;
	Cursor cursor;
	JEditorPane pane;
	JLabel status;
	
	PageLoader(JEditorPane p,URL u, Cursor c,JLabel s)
	{
	    pane = p;
	    url = u;
	    cursor = c;
	    status = s;
	}

    public void run()
    {
        if (url == null)
        {
	        // restore the original cursor
        	pane.setCursor(cursor);
        	
        	if(status != null) status.setText("Load Failed.");
        }
        else
        {
	        Document doc = pane.getDocument();
	        try
	        {
	            pane.setPage(url);
	            pane.scrollRectToVisible(new Rectangle(0,0,10,10));
	        }
	        catch(Exception exp)
	        {
	            pane.setDocument(doc);
	        }
	        finally
	        {
	            status.setText(url.getFile());
	            url = null;
	            status = null;
	            SwingUtilities.invokeLater(this);
	        }
        }
    }
}
