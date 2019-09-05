//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class ComboExample extends JPanel
{
    public ComboExample() 
    {
        JComboBox glyphComboBox;
        Font f;
        
        f = new Font("Serif",Font.PLAIN,18);
        setFont(f);
        setLayout(new BorderLayout());

        glyphComboBox = new JComboBox();
        glyphComboBox.setFont(f);
        
        glyphComboBox.setRenderer(new MyRenderer());
        
        glyphComboBox.addItem("Gold");
        glyphComboBox.addItem("Silver");
        glyphComboBox.addItem("Bronze");

        add(glyphComboBox,"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(140, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("ComboBox Example");
        ComboExample panel = new ComboExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

