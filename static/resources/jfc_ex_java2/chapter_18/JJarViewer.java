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

public class JJarViewer extends JFrame
{
    protected FileDialog sharedChooser;
    protected JTable table;
    protected JarTableModel tmodel;
    JProgressBar progBar;
    JLabel statusField;
    Action extractAction;
    
    public JJarViewer()
    {
        JMenuBar bar = new JMenuBar();
        JMenu fileM,actionM;
        JMenuItem tmp;
        Action action;
        JToolBar tools;
        JPanel status;
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        table = new JTable();
        table.setBackground(Color.white);
        
        getContentPane().add(new JScrollPane(table),"Center");
        
        tools = new JToolBar();
        
        fileM = new JMenu("File");
        fileM.setMnemonic('F');
        
        //Add the open action to the menu
        //and toolbar
        action = new OpenAction(this);
        tmp = fileM.add(action);
        tmp.setMnemonic('o');
        tools.add(action);
        
        //Add the close action to the menu
        action = new CloseAction(this);
        tmp = fileM.add(action);
        tmp.setMnemonic('c');
        
        fileM.addSeparator();
        
        //Add the quit action to the menu
        //and as a window listener
        action = new QuitAction(this);
        tmp = fileM.add(action);
        tmp.setMnemonic('q');
        
        addWindowListener((QuitAction)action);
        
        bar.add(fileM);
        
        tools.addSeparator();
        
        //Build the actions menu
        actionM = new JMenu("Actions");
        actionM.setMnemonic('A');
        
        extractAction = new ExtractAction(this);
        tmp = actionM.add(extractAction);
        tmp.setMnemonic('x');
        tools.add(extractAction);
        
        bar.add(actionM);
        
        setJMenuBar(bar);
        getContentPane().add(tools,"North");
    
        status = new JPanel();
        status.setBorder(new BevelBorder(BevelBorder.LOWERED));
        status.setLayout(new FlowLayout(FlowLayout.RIGHT));

        statusField = new JLabel("Select a File to View.");
        status.add(statusField);
        
        progBar = new JProgressBar();
        status.add(progBar);
        
        getContentPane().add(status,"South");
        
        //initialize the file to null
        
        setFile(null);
    }
    
    public JProgressBar getProgressBar()
    {
        return progBar;
    }
    
    public JLabel getStatus()
    {
        return statusField;
    }
    
    public FileDialog getFileChooser()
    {
        if(sharedChooser == null)
        {
            sharedChooser = new java.awt.FileDialog(this);
        }
        
        return sharedChooser;
    }
    
    public JarTableModel getTableModel()
    {
        return tmodel;
    }
    
    public JTable getTable()
    {
        return table;
    }
    
    public void setFile(File f)
    {
        tmodel = new JarTableModel(f);
        table.clearSelection();
        table.setModel(tmodel);
        table.createDefaultColumnsFromModel();
        
        if(f != null)
        {
            setTitle(f.getName());
            statusField.setText("File Loaded.");
            extractAction.setEnabled(true);
        }
        else
        {
            setTitle("JJar");
            statusField.setText("Select a file to load.");
            extractAction.setEnabled(false);
        }
    }
    
    public File getFile()
    {
        return tmodel.getFile();
    }
    
    public void closeFile()
    {
        setFile(null);
    }
    
	public void setVisible(boolean tf)
	{
	    if(tf)
	    {
    		Dimension screenSize =
    			getToolkit().getScreenSize();
    		Dimension curSize;
    		int x,y;
		
    		this.pack();
		
    		curSize = getSize();
		
    		curSize.width = Math.max(400,curSize.width);
    		curSize.height = Math.max(300,curSize.height);
		
    		x = (screenSize.width-curSize.width)/2;
    		y = (screenSize.height-curSize.height)/2;
		
    		setBounds(x,y,curSize.width,curSize.height);
		}
		super.setVisible(tf);
	}
	
    public static void main(String s[])
    {
        JJarViewer frame = new JJarViewer();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        
        frame.setVisible(true);
    }
}
