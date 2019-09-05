//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class AutoScrollExample extends JComponent
{
    Image img;
    
    public void paint(Graphics g)
    {
        if(img == null)
        {
            img = getToolkit().getImage("logo.gif");
        }
        
        g.drawImage(img,0,0,600,600,this);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 600);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("AutoScroll Example");
        AutoScrollExample comp = new AutoScrollExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(new JScrollPane(comp),"Center");
        
        frame.setSize(new Dimension(300,300));
        frame.setVisible(true);
        
        comp.setAutoscrolls(true);
        
        comp.scrollRectToVisible(new Rectangle(150,150,300,300));
    }
}

