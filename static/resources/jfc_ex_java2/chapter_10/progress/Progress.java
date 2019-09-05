//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class Progress extends JPanel
{
    JProgressBar progBar;
    Label status;
    
    public Progress()
    {
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        progBar = new JProgressBar();
        progBar.setMinimum(0);
        progBar.setMaximum(100);
        progBar.setValue(0);
        
        status = new Label("");
        
        add(progBar,"Center");
        add(status,"South");
    }
    
    public void go()
    {
        FileInputStream fileIn=null;
        int curByte;
        int i,max;
        File file;
        
        status.setText("Loading File");
        
        try
        {
            file = new File("Progress.java");
            fileIn = new FileInputStream("Progress.java");
            
            if(file.exists()) max = (int) file.length();
            else max = 0;
            
            for(i=0;i<max;i++)
            {
                curByte = fileIn.read();
                
                //Show status as a percent
                progBar.setValue((i*100)/max);
                
                //Make sure to takes a few seconds
                Thread.sleep(5);
            }
            
            status.setText("Load completed");
            progBar.setValue(100);
        }
        catch(Exception exp)
        {
            status.setText("Read failed");
        }
        finally
        {
            try
            {
                if(fileIn != null) fileIn.close();
            }
            catch(Exception exp2)
            {
            }
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("ProgressBar Example");
        Progress panel = new Progress();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        
        panel.go();
    }
}

class WindowCloser extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}
