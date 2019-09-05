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

public class QuitAction extends AbstractAction
implements WindowListener
{
    JFrame frame;
    
	public QuitAction(JFrame f)
	{
	    frame = f;
		putValue(Action.NAME,"Quit");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        doQuit();
    }
    
    public void doQuit()
    {
        frame.setVisible(false);
        System.exit(0);
    }
    
    public void windowOpened(WindowEvent e){}
    
    public void windowClosing(WindowEvent e)
    {
        doQuit();
    }
    
    public void windowClosed(WindowEvent e){}
    
    public void windowIconified(WindowEvent e){}
    
    public void windowDeiconified(WindowEvent e){}
    
    public void windowActivated(WindowEvent e){}
    
    public void windowDeactivated(WindowEvent e){}
}
