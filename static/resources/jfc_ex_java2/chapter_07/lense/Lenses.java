//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

 
class Lense extends JComponent 
implements MouseListener, FocusListener
{
    boolean hasFocus;
    
    public Lense()
    {
        addMouseListener(this);
        addFocusListener(this);
    }

    public void paint(Graphics g)
    {    
        Rectangle bounds = getBounds();
        g.setColor(Color.gray);
        g.fillRect(0,0,bounds.width,bounds.height);
        
        if(hasFocus)
        {
            g.setColor(Color.green);
            g.drawRect(0,0,bounds.width-1,bounds.height-1);
        }
        else
        {
            g.setColor(Color.black);
            g.drawRect(0,0,bounds.width-1,bounds.height-1);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(16,16);
    }
    
    public Dimension getMinimumSize()
    {
        return new Dimension(16,16);
    }
    
    public boolean isFocusTraversable()
    {
        return true;
    }
    
    public void focusGained(FocusEvent e)
    {
        hasFocus = true;
        repaint();
    }
    
    public void focusLost(FocusEvent e)
    {
        hasFocus = false;
        repaint();
    }
    
    public void mouseClicked(MouseEvent evt)
    {
        requestFocus();
    }
    
    public void mouseEntered(MouseEvent evt)
    {
    }
    
    public void mouseExited(MouseEvent evt)
    {
    }
    
    public void mousePressed(MouseEvent evt)
    {
    }
    
    public void mouseReleased(MouseEvent evt)
    {
    }
}

public class Lenses extends JPanel
{
    public Lenses()
    {
        setBackground(Color.lightGray);
        
        setLayout(new GridLayout(1,4,5,5));
        add(new Lense());
        add(new Lense());
        add(new Lense());
        add(new Lense());
    }
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Focus Example");
        Lenses panel = new Lenses();
        
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
