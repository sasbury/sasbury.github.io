//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class ProgStream extends JPanel
implements ActionListener, Runnable
{
    Thread kicker;
        
    public ProgStream()
    {
        JButton go;
        
        setBackground(Color.lightGray);
        setLayout(new FlowLayout());
        
        go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        kicker = new Thread(this);
        kicker.start();
    }
    
    public void run()
    {
        FileInputStream fileIn=null;
        ProgressMonitorInputStream progIn=null;
        int curByte;
        ProgressMonitor progMon;
        
        try
        {
            fileIn = new FileInputStream("ProgStream.java");
            progIn = new ProgressMonitorInputStream(this,"Loading",fileIn);
            
            progMon = progIn.getProgressMonitor();
            progMon.setMillisToDecideToPopup(10);
            
            while((curByte = progIn.read()) != -1)
            {
                Thread.sleep(5);
                
                if(progMon.isCanceled())
                {
                    getToolkit().beep();
                    Thread.sleep(200);//pause for next beep
                    break;
                }
            }
            
            getToolkit().beep();
        }
        catch(Exception exp)
        {
        }
        finally
        {
            try
            {
                if(progIn != null) progIn.close();
            }
            catch(Exception exp2)
            {
            }
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(100,60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Progress Stream Example");
        ProgStream panel = new ProgStream();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
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
