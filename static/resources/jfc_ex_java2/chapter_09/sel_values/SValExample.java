//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class SValExample extends JPanel
implements ActionListener
{
    public SValExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object result;
        String[] selValues = {"Java"
                               ,"C"
                               ,"C++"
                               ,"Perl"
                               ,"JavaScript"
                               ,"COBOL"
                               ,"Visual Basic"};
        
        String initSel = "Java";
        
        result = JOptionPane.showInputDialog(this
                         ,"What is your favorite prog. language?"
                         ,"Pick a language"
                         ,JOptionPane.QUESTION_MESSAGE
                         ,null
                         ,selValues
                         ,initSel);
                                     
        if(result == null)
        {
            System.out.println("Cancelled");
        }
        else
        {
            System.out.println("Got result "+result);
        }
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Sel. Values");
        SValExample panel = new SValExample();
        
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

