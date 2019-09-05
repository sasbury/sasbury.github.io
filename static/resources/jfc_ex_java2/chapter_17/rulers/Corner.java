//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class Corner extends JComponent
implements MouseListener
{
    JComponent toScroll;
    String position;
    
    public Corner(JComponent ts,String pos)
    {
        position = pos;
        toScroll = ts;
        addMouseListener(this);
    }
    
    public void paint(Graphics g)
    {
        Dimension curSize=getSize();
        int i,max;
        
        g.setColor(getBackground());
        g.fillRect(0,0,curSize.width,curSize.height);
        
        g.setColor(Color.blue);
        g.fillOval(0,0,curSize.width,curSize.height);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(16,16);
    }
    
    public void mouseClicked(MouseEvent e)
    {
        Rectangle rect= new Rectangle();
        Dimension size = toScroll.getSize();
        
        if(position.equals(JScrollPane.UPPER_LEFT_CORNER))
        {
            rect.x = 0;
            rect.y = 0;
            rect.width = 1;
            rect.height = 1;
        }
        else if(position.equals(JScrollPane.LOWER_LEFT_CORNER))
        {
            rect.x = 0;
            rect.y = size.height-1;
            rect.width = 1;
            rect.height = 1;
        }
        else if(position.equals(JScrollPane.UPPER_RIGHT_CORNER))
        {
            rect.x = size.width-1;
            rect.y = 0;
            rect.width = 1;
            rect.height = 1;
        }
        else if(position.equals(JScrollPane.LOWER_RIGHT_CORNER))
        {
            rect.x = size.width-1;
            rect.y = size.height-1;
            rect.width = 1;
            rect.height = 1;
        }
        
        toScroll.scrollRectToVisible(rect);
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }
    
    public void mouseEntered(MouseEvent e)
    {
    }
    
    public void mouseExited(MouseEvent e)
    {
    }
}
