import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class SetDirAction extends AbstractAction
{
    DocSearch searcher;
    
	public SetDirAction(DocSearch s)
	{
	    searcher = s;
		putValue(Action.NAME,"Set Directory");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        String result;
        
        result = JOptionPane.showInputDialog(searcher
                                             ,"Enter a directory"
                                             ,"Search Directory..."
                                             ,JOptionPane.QUESTION_MESSAGE);
        
        if(result != null) searcher.setDirectory(result);
    }
}
