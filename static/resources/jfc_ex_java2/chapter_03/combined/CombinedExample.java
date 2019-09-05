//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.applet.*;
import java.awt.*;
import java.net.*;

public class CombinedExample extends JPanel
{
	public CombinedExample()
	{
		JToolBar toolbar;
		JEditorPane editor = null;
		JButton tmp;
		
		setLayout(new BorderLayout());
		
		try
		{
		    editor = new JEditorPane(new URL("file",null,"CombinedExample.java"));
		}
		catch(Exception exp)
		{
		    editor = new JEditorPane();
		    System.out.println("Error loading file. "+exp);
		}
		
		add(new JScrollPane(editor),"Center");
		
		toolbar = new JToolBar();
		
		tmp = new JButton(new ImageIcon("images/new.gif"));
		toolbar.add(tmp);
		
		tmp = new JButton(new ImageIcon("images/open.gif"));
		toolbar.add(tmp);
		
		tmp = new JButton(new ImageIcon("images/save.gif"));
		toolbar.add(tmp);
		
		toolbar.addSeparator();
		
		tmp = new JButton(new ImageIcon("images/cut.gif"));
		toolbar.add(tmp);
		
		tmp = new JButton(new ImageIcon("images/copy.gif"));
		toolbar.add(tmp);
		
		tmp = new JButton(new ImageIcon("images/paste.gif"));
		toolbar.add(tmp);
		
		add(toolbar,"North");
	}
	
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 450);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Combined Layout Example");
        CombinedExample panel = new CombinedExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
       
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

