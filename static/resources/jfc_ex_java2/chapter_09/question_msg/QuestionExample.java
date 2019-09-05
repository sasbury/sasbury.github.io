//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class QuestionExample extends JPanel
implements ActionListener
{
    public QuestionExample()
    {
        JButton go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JOptionPane.showMessageDialog(this
                                     ,"A Question For You."
                                     ,"?????"
                                     ,JOptionPane.QUESTION_MESSAGE);
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Question");
        QuestionExample panel = new QuestionExample();
        
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

