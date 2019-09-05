//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class SBorderExample extends JPanel
{
    public SBorderExample()
    {
        BevelBorder border;
        SoftBevelBorder sborder;
        JLabel label;
        
        setLayout(new GridLayout(1,3,20,20));

        label = new JLabel("Without a border",JLabel.CENTER);
        add(label);

        label = new JLabel("With a beveled border",JLabel.CENTER);
        border = new BevelBorder(BevelBorder.RAISED);
        label.setBorder(border);
        add(label);  

        label = new JLabel("With a soft beveled border",JLabel.CENTER);
        sborder = new SoftBevelBorder(BevelBorder.RAISED);
        label.setBorder(sborder);
        add(label); 
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(500, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Bevel Border Example");
        SBorderExample panel = new SBorderExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

