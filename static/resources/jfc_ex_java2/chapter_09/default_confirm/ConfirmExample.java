//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class ConfirmExample extends JPanel
implements ActionListener
{
    public ConfirmExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        int result;
        
        result = JOptionPane.showConfirmDialog(this
                                     ,"Continue Processing.");
                                     
        if(result == JOptionPane.YES_OPTION)
        {
            System.out.println("Got Yes!");
        }
        else if(result == JOptionPane.NO_OPTION)
        {
            System.out.println("Got No.");
        }
        else if(result == JOptionPane.CANCEL_OPTION)
        {
            System.out.println("Got Cancel :-(");
        }
        else if(result == JOptionPane.CLOSED_OPTION)
        {
            System.out.println("Dialog Closed");
        }
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Confirm");
        ConfirmExample panel = new ConfirmExample();
        
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

