//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class ConstructExample extends JPanel
implements ActionListener
{
    public ConstructExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String msg[] = {"A file error has occured."
                        ,"Continue?"};
                        
        JOptionPane pane= new JOptionPane(msg
                                     ,JOptionPane.ERROR_MESSAGE
                                     ,JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog;
        Object result;
        
        dialog = pane.createDialog(this,"Error");
        
        dialog.show();
        
        result = pane.getValue();
        
        if(result instanceof Integer)
        {
            int intResult=((Integer)result).intValue();
            
            if(intResult == JOptionPane.OK_OPTION)
            {
                System.out.println("Got OK");
            }
            else
            {
                System.out.println("Cancelled/Closed");
            }
        }
        else if(result == null)
        {
            System.out.println("Cancelled/Closed");
        }
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Constructor");
        ConstructExample panel = new ConstructExample();
        
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

