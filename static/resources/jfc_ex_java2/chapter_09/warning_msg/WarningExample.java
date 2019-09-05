//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class WarningExample extends JPanel
implements ActionListener
{
    public WarningExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(this
                                     ,"Watch Out, Don't Go There."
                                     ,"Warning"
                                     ,JOptionPane.WARNING_MESSAGE);
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Warning");
        WarningExample panel = new WarningExample();
        
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

