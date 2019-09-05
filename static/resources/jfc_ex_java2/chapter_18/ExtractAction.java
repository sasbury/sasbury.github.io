//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class ExtractAction extends AbstractAction
implements Runnable
{
	protected JJarViewer jjar;
	Thread kicker;
	File dest;
	int rows[];

	public ExtractAction(JJarViewer j)
	{
		jjar = j;
		
        Icon img = new ImageIcon("images/extract.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,"Extract Files");
	}
	
    public void actionPerformed(ActionEvent evt)
    {
        if((kicker == null)
            && isEnabled())
        {
            JTable table = jjar.getTable();
            String result=null;
            
            result = JOptionPane.showInputDialog(jjar
                                                 ,"Where do you want to extract to?"
                                                 ,"Destination"
                                                 ,JOptionPane.QUESTION_MESSAGE);
            
            if(result != null) dest = new File(result);
    
            if(dest == null)
            {
                //do nothing;
            }
            if(!dest.exists())
            {
                JOptionPane.showMessageDialog(jjar
                                     ,"That is not a valid directory."
                                     ,"File Error"
                                     ,JOptionPane.ERROR_MESSAGE);
            
            }
            else
            {
                rows = table.getSelectedRows();
            
                kicker = new Thread(this);
                kicker.start();
            }
        }
    }
    
    public synchronized void run()
    {
        int i,max;
        ZipEntry entry;
        File realDest;
        FileOutputStream fileOut;
        BufferedOutputStream bufOut;
        BufferedInputStream bufIn;
        InputStream zipIn;
        String entryPath;
        int cur;
        JarTableModel tmodel = jjar.getTableModel();    
        File myFile = tmodel.getFile();
        Vector entries = tmodel.getEntries();
        JProgressBar progBar = jjar.getProgressBar();
        JLabel status = jjar.getStatus();
        
        if(!dest.isDirectory())
        {
            dest = new File(dest.getParent());
        }
        
        max = rows.length;
        
        progBar.setMinimum(0);
        progBar.setMaximum(max);
        progBar.setValue(0);
        
        for(i=0;i<max;i++)
        {
            entry = (ZipEntry)entries.elementAt(rows[i]);
            
            entryPath = entry.getName();
            entryPath = entryPath.replace('/',File.separatorChar);
            entryPath = entryPath.replace('\\',File.separatorChar);
            
            realDest = new File(dest,entryPath);
            
            status.setText("Extracting: "+entryPath);
            
            if(status.getParent() != null)
            {
                status.getParent().validate();
            }
            
            if(entry.isDirectory())
            {
                realDest.mkdirs();
            }
            else
            {
                new File(realDest.getParent()).mkdirs();
                
                try
                {
                    zipIn = tmodel.getZipFile().getInputStream(entry);
                    bufIn = new BufferedInputStream(zipIn);
                
                    fileOut = new FileOutputStream(realDest);
                    bufOut = new BufferedOutputStream(fileOut);
                
                    while((cur = bufIn.read()) != -1)
                    {
                        bufOut.write(cur);
                    }
                
                    bufOut.close();
                    bufIn.close();
                }
                catch(Exception exp)
                {
                }
            }
            
            progBar.setValue(i);
        }
        
        //reset on completion
        status.setText("");
        progBar.setValue(0);
        kicker = null;
    }
}
