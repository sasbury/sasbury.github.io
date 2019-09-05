//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class HRuler extends JComponent
implements MouseMotionListener
{
    int curX;
    
    public void paint(Graphics g)
    {
        Dimension curSize=getSize();
        int i,max;
        
        g.setColor(getBackground());
        g.fillRect(0,0,curSize.width,curSize.height);
        
        g.setColor(Color.black);
        
        max = curSize.width/72;
        
        for(i=0;i<max;i++)
        {
            g.drawLine(i*72,curSize.height,i*72,0);
            g.drawLine(i*36,curSize.height/2,i*36,0);
            g.drawLine(i*18,curSize.height/4,i*18,0);
            g.drawLine(i*54,curSize.height/4,i*54,0);
        }
        
        if(curX > 0)
        {
            g.setColor(Color.blue);
            g.drawLine(curX,curSize.height,curX,0);
        }
    }
    
    public void mouseDragged(MouseEvent evt)
    {
        curX = evt.getX();
        repaint();
    }
    
    public void mouseMoved(MouseEvent evt)
    {
        curX = evt.getX();
        repaint();
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(72*12, 16);
    }
}
