//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

class KeyCatcher extends KeyAdapter
{
    Target target;
    
    public KeyCatcher(Target tp)
    {
        target = tp;
    }
    
    public void keyTyped(KeyEvent e)
    {
        target.setChar(e.getKeyChar());
    }
}
 
class MouseCatcher extends MouseAdapter
{
    Target target;
    
    public MouseCatcher(Target tp)
    {
        target = tp;
    }
    
    public void mouseClicked(MouseEvent e)
    {
        target.setLoc(e.getPoint());
        target.requestFocus();
    }
}
 
class Target extends JComponent
{
    char curChar[];
    Point curLoc;
    
    public Target()
    {
        curChar = new char[1];
        curChar[0] = 'x';
    
        addKeyListener(new KeyCatcher(this));
        addMouseListener(new MouseCatcher(this));
    }
    
    public void setLoc(Point p)
    {
        curLoc = p;
        repaint();
    }
    
    public void setChar(char c)
    {
        curChar[0] = c;
    }
    
    public char curChar()
    {
        return curChar[0];
    }
    
    public void paint(Graphics g)
    {
        Rectangle bounds = getBounds();
        Font bigFont = new Font("Times-Roman",Font.PLAIN,24);
        
        g.setFont(bigFont);
        
        g.setColor(Color.white);
        g.fillRect(0,0,bounds.width,bounds.height);
            
        if(curLoc != null)
        {
            g.setColor(Color.black);
            g.drawChars(curChar,0,1,curLoc.x,curLoc.y);
        }
    }
}

public class TargetPractice extends JPanel
{
    public TargetPractice()
    {
        Target target = new Target();
        
        setLayout(new BorderLayout());
        add("Center",target);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("TargetPractice");
        TargetPractice panel = new TargetPractice();
        
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
