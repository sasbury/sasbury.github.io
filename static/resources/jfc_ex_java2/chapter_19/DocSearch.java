//Copyright 1998 John Wiley and Sons, Inc.


import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;

public class DocSearch extends JFrame
implements ActionListener, ListSelectionListener, HyperlinkListener
{
	protected JList results;
	protected JPanel top;
	protected JTextField searchField;
	protected JLabel dirLabel;
	protected JLabel statusBar;
	protected JEditorPane display;
	protected HTMLIndex index;
	protected String curFileName;
	protected Thread pageThread;
	protected boolean watchAnchor;
	
	public final static String SEARCH="search";
	
    public DocSearch()
    {
        JSplitPane split;
        JPanel tmp,tmp2;
        Font font = new Font("SanSerif",Font.PLAIN,16);
    
        setTitle("Doc Search");
    
        top = new JPanel();
        top.setLayout(new BorderLayout());
    
        results = new JList();
        results.addListSelectionListener(this);
        results.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        top.add(new JScrollPane(results),"Center");
    
        tmp = new JPanel();
        tmp.setLayout(new GridLayout(2,1,5,5));
    
        dirLabel = new JLabel("No Directory Selected");
        dirLabel.setForeground(Color.darkGray);
        dirLabel.setFont(font);
    
        tmp.add(dirLabel);
    
        tmp2 =new JPanel();
        tmp2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
    
        JLabel tmpLabel =new JLabel("Search:");
        tmpLabel.setFont(font);
    
        tmp2.add(tmpLabel);
    
        searchField = new JTextField(25);
        searchField.setActionCommand(SEARCH);
        searchField.addActionListener(this);
        searchField.setFont(font);
    
        tmp2.add(searchField);
    
        tmp.add(tmp2);
    
        top.add(tmp,"North");
    
        display = new JEditorPane();
        display.setEditable(false);
        display.setContentType("text/html");
        display.addHyperlinkListener(this);
    
        split = new JSplitPane(JSplitPane.VERTICAL_SPLIT
                                ,top
                                ,new JScrollPane(display));
                            
        statusBar = new JLabel("Loaded",JLabel.RIGHT);
        statusBar.setFont(font);
    
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
    
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(split,"Center");
        getContentPane().add(statusBar,"South");
    
        buildMenu();
    
        setDirectory(null);
    }
	
    protected void buildMenu()
    {
        Action action;
        JMenuBar bar;
        JMenu menu;
    
        bar = new JMenuBar();
        menu = new JMenu("File");
    
        action = new SetDirAction(this);
        menu.add(action);
        menu.addSeparator();
    
        action = new QuitAction(this);
        this.addWindowListener((QuitAction)action);
        menu.add(action);
    
        bar.add(menu);
    
        setJMenuBar(bar);
    }
	
    public void setIndex(HTMLIndex i)
    {
        index = i;
        statusBar.setText("Index Loaded.");
        getToolkit().beep();
    }
	
    public void setDirectory(String dirName)
    {
        if(dirName == null)
        {
            index = null;
            dirLabel.setText("No Directory Selected.");
            statusBar.setText("Loaded.");
            setFile(null);
        }
        else
        {
            File dir = new File(dirName);
        
            if(!dir.exists())
            {
                index = null;
                dirLabel.setText("No Directory Selected.");
                statusBar.setText("");
                setFile(null);
    
                String msg[] = new String[2];
    
                msg[0] = "No such directory:";
                msg[1] = dirName;
    
                JOptionPane.showMessageDialog(this
                                      ,msg
                                      ,"Error"
                                      ,JOptionPane.ERROR_MESSAGE);
            }
            else
            {  
                int result;
                String msg[] = new String[2];
    
                msg[0] = "Are you sure you want to index:";
                msg[1] = dirName;
            
                result = JOptionPane.showConfirmDialog(this
                                      ,msg
                                      ,"Confirm Selection"
                                      ,JOptionPane.YES_NO_OPTION);
            
                if(result == JOptionPane.YES_OPTION)
                {
    	            statusBar.setText("Loading Index...");
    	            dirLabel.setText(dirName);
        
    	            Thread kicker = new Thread(new IndexLoader(this,dirName));
    	            kicker.start();
                }
            }
        }
    
        results.setListData(new Object[0]);
    }
	
    public void setFile(File file)
    {
        URL url = null;
    
        if(file != null)
        {
            try
            {
                url = new URL("file",null,file.getAbsolutePath());
            }
            catch(Exception exp)
            {
                fileError(file.getAbsolutePath());
            }
        }
    
        setURL(url);
    }

    public void setFileByName(String file)
    {
        URL url = null;
    
        if(file != null)
        {
            try
            {
                url = new URL("file",null,file);
            }
            catch(Exception exp)
            {
                fileError(file);
            }
        }
    
        setURL(url);
    }
	
    public void setURL(URL url)
    {
        try
        {
            if(pageThread != null)
            {
                pageThread.interrupt();
                pageThread = null;
            }

            if(url==null)
            {
                curFileName = null;
                display.setText("");
            }
            else if(!url.getFile().equals(curFileName)
                    ||((url.getRef() != null)&&watchAnchor))
            {
                curFileName = url.getFile();
    
                statusBar.setText("Loading page...");
    
                Cursor c = display.getCursor();
                Cursor waitCursor 
                    = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);

                display.setCursor(waitCursor);
    
                if(!curFileName.equals(results.getSelectedValue()))
                {
                    results.clearSelection();
                    results.setSelectedValue(curFileName,true);
                }
    
                Thread pageThread = new Thread(new PageLoader(display,url, c,statusBar));
                pageThread.start();
    
                watchAnchor=false;
            }
        }
        catch(Exception exp)
        {
            if(url != null) fileError(url.getFile());
            else fileError("No File Available");
        }
    }
	
    protected void fileError(String file)
    {
        String msg[] = new String[2];
    
        msg[0] = "Error loading file:";
        msg[1] = file;
    
        JOptionPane.showMessageDialog(this
                                      ,msg
                                      ,"Error"
                                      ,JOptionPane.ERROR_MESSAGE);
    }

    public void hyperlinkUpdate(HyperlinkEvent evt)
    {
        watchAnchor = true;
        setURL(evt.getURL());
        searchField.selectAll();
        searchField.requestFocus();
    }
    
    public void valueChanged(ListSelectionEvent evt)
    {
        setFileByName((String) results.getSelectedValue());
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        
        if(SEARCH.equals(cmd))
        {
            Vector files;
	        Cursor c = getCursor();
	        Cursor waitCursor 
	                = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
            
            if(index != null)
            {
                setCursor(waitCursor);
                
                setURL(null);
                
                files = index.filesForWord(searchField.getText());
                
                results.setListData(files);
                results.ensureIndexIsVisible(0);
                
                searchField.selectAll();
                searchField.requestFocus();
                
                top.validate();
                
                setCursor(c);
            }
        }
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
	
    		curSize.height = Math.max(550,curSize.height);
    		curSize.width = Math.max(600,curSize.width);
	
    		x = (screenSize.width-curSize.width)/2;
    		y = (screenSize.height-curSize.height)/2;
	
    		setBounds(x,y,curSize.width,curSize.height);
    	}
    	super.setVisible(tf);
    }
	
    public static void main(String s[])
    {
        DocSearch frame = new DocSearch();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        
        frame.setVisible(true);
    }
    
}

