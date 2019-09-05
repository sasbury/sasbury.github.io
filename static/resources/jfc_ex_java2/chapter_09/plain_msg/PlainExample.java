//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class PlainExample extends JPanel
implements ActionListener
{
    public PlainExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(this
                                     ,"Something plain has occured."
                                     ,"Plain Stuff"
                                     ,JOptionPane.PLAIN_MESSAGE);
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Plain");
        PlainExample panel = new PlainExample();
        
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

