//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class EBorderExample extends JPanel
{
    public EBorderExample()
    {
        EtchedBorder border;
        JLabel label;
        
        setLayout(new GridLayout(1,3,20,20));

        label = new JLabel("Without a border",JLabel.CENTER);
        add(label);

        label = new JLabel("Raised etched border",JLabel.CENTER);
        border = new EtchedBorder(EtchedBorder.RAISED);
        label.setBorder(border);
        add(label);

        label = new JLabel("Lowered etched border",JLabel.CENTER);
        border = new EtchedBorder(EtchedBorder.LOWERED);
        label.setBorder(border);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 100);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Etched Border Example");
        EBorderExample panel = new EBorderExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

