//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class TwoSplit extends JPanel
{
    public TwoSplit()
    {
        JSplitPane pane;
        JSplitPane topPane,bottomPane;
        JPanel left2,right2;
        JPanel left,right;
        JLabel label;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        left2 = new JPanel();
        left2.setOpaque(true);
        left2.setBackground(Color.cyan);
        left2.setLayout(new BorderLayout());
        label = new JLabel("left2",JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.black);
        left2.add(label,"Center");
        
        right2 = new JPanel();
        right2.setOpaque(true);
        right2.setBackground(Color.magenta);
        right2.setLayout(new BorderLayout());
        label = new JLabel("right2",JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        right2.add(label,"Center");
        
        left = new JPanel();
        left.setOpaque(true);
        left.setBackground(Color.yellow);
        left.setLayout(new BorderLayout());
        label = new JLabel("Left",JLabel.CENTER);
        label.setForeground(Color.black);
        left.add(label,"Center");
        
        right = new JPanel();
        right.setOpaque(true);
        right.setBackground(Color.black);
        right.setLayout(new BorderLayout());
        label = new JLabel("Right",JLabel.CENTER);
        label.setForeground(Color.white);
        right.add(label,"Center");
        
        topPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT
                            ,left,right);
        topPane.setContinuousLayout(true);
        
        bottomPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT
                            ,left2,right2);
        bottomPane.setContinuousLayout(true);
        
        
        pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT
                            ,topPane,bottomPane);
        pane.setContinuousLayout(true);
        pane.setDividerSize(2*bottomPane.getDividerSize());
        
        add(pane,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Multiple Split Example");
        TwoSplit panel = new TwoSplit();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.show();
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
