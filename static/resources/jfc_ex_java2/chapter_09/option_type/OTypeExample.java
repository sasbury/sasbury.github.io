//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class OTypeExample extends JPanel
implements ActionListener
{
    public OTypeExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        int result;
        
        result = JOptionPane.showConfirmDialog(this
                                     ,"Continue Processing."
                                     ,"Query"
                                     ,JOptionPane.YES_NO_OPTION);
                                     
        if(result == JOptionPane.YES_OPTION)
        {
            System.out.println("Got Yes!");
        }
        else //No or closed
        {
            System.out.println("Got No.");
        }
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Option Type");
        OTypeExample panel = new OTypeExample();
        
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

