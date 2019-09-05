//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Menus
{
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Menus");
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Levels");
        JMenuItem tmp;
        Icon anIcon;
        
        tmp = new JMenuItem("Primary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Secondary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Tertiary");
        menu.add(tmp);
        
        bar.add(menu);
        
        menu = new JMenu("Colors");
        
        tmp = new JMenuItem("Red");
        anIcon = new Swatch(Color.red);
        tmp.setIcon(anIcon);
        tmp.setHorizontalTextPosition(tmp.RIGHT);
        menu.add(tmp);
        
        tmp = new JMenuItem("Green");
        anIcon = new Swatch(Color.green);
        tmp.setIcon(anIcon);
        tmp.setHorizontalTextPosition(tmp.RIGHT);
        menu.add(tmp);
        
        tmp = new JMenuItem("Blue");
        anIcon = new Swatch(Color.blue);
        tmp.setIcon(anIcon);
        tmp.setHorizontalTextPosition(tmp.RIGHT);
        menu.add(tmp);
        
        bar.add(menu);
        
        menu = new JMenu("Actions");
        
        tmp = new JMenuItem("Shapes");
        anIcon = new ImageIcon("images/shapes.gif");
        tmp.setIcon(anIcon);
        menu.add(tmp);
        
        tmp = new JMenuItem("X");
        anIcon = new ImageIcon("images/ex.gif");
        tmp.setIcon(anIcon);
        menu.add(tmp);
        
        tmp = new JMenuItem("Check");
        anIcon = new ImageIcon("images/check.gif");
        tmp.setEnabled(false);
        menu.add(tmp);
        
        bar.add(menu);
        
        menu = new JMenu("Checkboxes");
        
        tmp = new JCheckBoxMenuItem("Save");
        menu.add(tmp);
        
        menu.addSeparator();
        
        tmp = new JCheckBoxMenuItem("Delete");
        menu.add(tmp);
        
        bar.add(menu);
        
        menu = new JMenu("Radios");
        
        ButtonGroup grp = new ButtonGroup();
        
        tmp = new JRadioButtonMenuItem("Yes");
        grp.add(tmp);
        menu.add(tmp);
        
        tmp = new JRadioButtonMenuItem("No");
        grp.add(tmp);
        menu.add(tmp);
        
        bar.add(menu);
        
        //Create a menu with a tree component in it
        menu = new JMenu("Tree");
        
        Vector data = new Vector();
        data.addElement("one");
        data.addElement("two");
        data.addElement("three");
        
        menu.add(new JTree(data));
        bar.add(menu);
        
        frame.setJMenuBar(bar);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.pack();
        frame.setVisible(true);
    }
}

//Simple Icon that displays a colored square
class Swatch implements Icon
{
    Color color;
    
    public Swatch(Color c)
    {
        this.color = c;
    }

    public void paintIcon(Component c,Graphics g, int x, int y)
    {
        //Save the current color, don't stomp on it
        Color oldColor = g.getColor();
        g.setColor(color);
        g.fill3DRect(x,y,getIconWidth(), getIconHeight(), true);
        g.setColor(oldColor);
    }
    
    public int getIconWidth()
    {
        return 12;
    }
    
    public int getIconHeight()
    {
        return 12;
    }
}
