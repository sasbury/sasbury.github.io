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

public class CloseAction extends AbstractAction
{
	protected JJarViewer jjar;

	public CloseAction(JJarViewer j)
	{
		jjar = j;
		
        putValue(Action.NAME,"Close Archive");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        jjar.closeFile();
    }
}
