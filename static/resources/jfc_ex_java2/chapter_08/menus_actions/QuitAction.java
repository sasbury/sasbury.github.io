//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class QuitAction extends AbstractAction
{
    JFrame frame;
    
	public QuitAction(JFrame f)
	{
	    frame = f;
		putValue(Action.NAME,"Quit");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        frame.setVisible(false);
        System.exit(0);
    }
}
