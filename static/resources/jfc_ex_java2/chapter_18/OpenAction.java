//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class OpenAction extends AbstractAction
{
	protected JJarViewer jjar;

	public OpenAction(JJarViewer j)
	{
		jjar = j;
		
        Icon img = new ImageIcon("images/open.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,"Open Archive");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        FileDialog chooser;
        
        chooser = jjar.getFileChooser();
        
        chooser.show();
            
        File fileToOpen = new File(chooser.getDirectory(),
                                chooser.getFile());
        
        jjar.setFile(fileToOpen);
    }
}
