//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Monitor extends JPanel 
implements ActionListener
{
    JTextField delayField;
    JTextField popupField;
    Timer clock;
    int ticks;
    ProgressMonitor monitor;
    
    final static String GO="go";
    
    public Monitor()
    {
        JLabel label;
        JPanel tmpPanel;
        JButton go;

        setBackground(Color.lightGray);
        
        tmpPanel = new JPanel();        
        tmpPanel.setLayout(new GridLayout(2,2,10,10));

        label = new JLabel("Popup (ms)");
        tmpPanel.add(label);

        popupField = new JTextField();
        tmpPanel.add(popupField);

        label = new JLabel("Work Time (ms)");
        tmpPanel.add(label);

        delayField = new JTextField();
        tmpPanel.add(delayField);
        
        setLayout(new BorderLayout());
        add(tmpPanel,"North");
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        go = new JButton("Go");
        go .addActionListener(this);
        go .setActionCommand(GO);
        
        tmpPanel.add(go);
        
        add(tmpPanel,"South");
        
        clock = new Timer(1000,this);
        clock.setRepeats(false);
        clock.stop();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String command;
        
        command = e.getActionCommand();
        
        if (GO.equals(command))
        {
            int delayTime;
            int popupTime;
           
            clock.stop();
            if(monitor != null) monitor.close();
            
            delayTime = Integer.parseInt(delayField.getText());
            popupTime = Integer.parseInt(popupField.getText());
           
            ticks = 0;
            
            clock.setDelay(delayTime/10);
            clock.setRepeats(true);
            
            monitor = new ProgressMonitor(this,"Processing...","Started",0,100);
            monitor.setMillisToDecideToPopup(popupTime);
            clock.start();
        }
        else if(e.getSource() == clock)
        {
            if(monitor.isCanceled())
            {
                clock.stop();
                System.out.println("Canceled");
            }
            if(ticks < 100)
            {
                ticks+=10;
                monitor.setProgress(ticks);
                monitor.setNote(String.valueOf(ticks));
                getToolkit().beep();
            }
            else
            {
                clock.stop();
                monitor.close();
                
                //Three beeps to end
                try
                {
                    getToolkit().beep();
                    Thread.sleep(200);
                    getToolkit().beep();
                    Thread.sleep(200);
                    getToolkit().beep();
                }
                catch(Exception exp){}
            }
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 120);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Progress Monitors");
        Monitor panel = new Monitor();
        
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
