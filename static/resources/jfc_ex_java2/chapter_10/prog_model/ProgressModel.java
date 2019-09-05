//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class ProgressModel extends JPanel
{
    public ProgressModel()
    {
        JProgressBar progBar;
        JSlider slider;
        BoundedRangeModel model;
        
        setBackground(Color.lightGray);
        setLayout(new GridLayout(2,1,10,10));
        
        progBar = new JProgressBar();
        progBar.setMinimum(0);
        progBar.setMaximum(100);
        progBar.setValue(0);
        
        slider = new JSlider();
        
        model = progBar.getModel();
        slider.setModel(model);
        
        add(progBar);
        add(slider);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("ProgressModel Example");
        ProgressModel panel = new ProgressModel();
        
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
