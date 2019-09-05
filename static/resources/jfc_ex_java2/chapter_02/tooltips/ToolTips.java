//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class ToolTips extends JPanel
{
    public ToolTips()
    {
        setToolTipText("ToolTips");
        setBackground(Color.white);
    }
    
    public String getToolTipText(MouseEvent event)
    {
        return "("+event.getX()+","+event.getY()+")";
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Tooltip Example");
        ToolTips panel = new ToolTips();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

