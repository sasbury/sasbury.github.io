//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class VRuler extends JComponent
implements MouseMotionListener
{
    int curY;
    
    public void paint(Graphics g)
    {
        Dimension curSize=getSize();
        int i,max;
        
        g.setColor(getBackground());
        g.fillRect(0,0,curSize.width,curSize.height);
        
        g.setColor(Color.black);
        
        max = curSize.height/72;
        
        for(i=0;i<max;i++)
        {
            g.drawLine(0,i*72,curSize.width,i*72);
            g.drawLine(0,i*36,curSize.width/2,i*36);
            g.drawLine(0,i*18,curSize.width/4,i*18);
            g.drawLine(0,i*54,curSize.width/4,i*54);
        }
        
        if(curY > 0)
        {
            g.setColor(Color.blue);
            g.drawLine(0,curY,curSize.width,curY);
        }
    }
    
    public void mouseDragged(MouseEvent evt)
    {
        curY = evt.getY();
        repaint();
    }
    
    public void mouseMoved(MouseEvent evt)
    {
        curY = evt.getY();
        repaint();
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(16,72*12);
    }
}
