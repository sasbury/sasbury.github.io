//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

public class BasicTouchScreenUI extends TouchScreenUI 
implements MouseListener, MouseMotionListener
{
    Shape hilightRegion;
    
    /*
     * Method for creating ui objects
     */
    public static BasicTouchScreenUI createUI(TouchScreen ts)
    {
        return new BasicTouchScreenUI();
    }
    
    /*
     * Mouse listener method.
     */
    public void mouseClicked(MouseEvent evt)
    {
    }
    
    public void mousePressed(MouseEvent evt)
    {
    }
    
    public void mouseReleased(MouseEvent evt)
    {
        TouchScreenModel mod;
        TouchScreen screen;
        
        screen = (TouchScreen) evt.getSource();
        mod = screen.getModel();
        
        mod.selectRegionForPoint(evt.getPoint());
    }
    
    public void mouseEntered(MouseEvent evt)
    {
    }
    
    public void mouseExited(MouseEvent evt)
    {
        TouchScreen screen;
        
        screen = (TouchScreen) evt.getSource();
        hilightRegion = null;
        screen.repaint();
    }
    
    /*
     * Mouse motion methods.
     */
     
    public void mouseDragged(MouseEvent evt)
    {
    }
    
    public void mouseMoved(MouseEvent evt)
    {
        TouchScreenModel mod;
        TouchScreen screen;
        
        screen = (TouchScreen) evt.getSource();
        mod = screen.getModel();
        
        hilightRegion = mod.getRegionForPoint(evt.getPoint());
        screen.repaint();
    }
    
    /*
     * Overriden ComponentUI methods.
     */
    public void paint(Graphics g,JComponent c)
    {
        Image img;
        TouchScreenModel mod;
        TouchScreen screen;
        Shape selRegion;
        Color clr;
        Dimension size = c.getSize();
        Rectangle bounds;
        Rectangle imageBounds;
	    Insets border = getBorderInsets(c);
        
        screen = (TouchScreen) c;
        mod = screen.getModel();
        img = mod.getImage();
        
        imageBounds = new Rectangle();
        imageBounds.width = size.width-border.left-border.right;
        imageBounds.height = size.height-border.top-border.bottom;
        imageBounds.x = border.left;
        imageBounds.y = border.top;
        
        /*
         * This is a simple version that assumes the
         * component is big enough, and there is no border.
         */
        if(img != null)
        {
            g.drawImage(img,imageBounds.x
                            ,imageBounds.y
                            ,imageBounds.width
                            ,imageBounds.height
                            ,c);
        }
        
        if(hilightRegion != null)
        {
            g.setColor(((TouchScreen)c).getHilightColor());
            
            if(hilightRegion instanceof Polygon)
            {
                g.drawPolygon((Polygon)hilightRegion);
            }
            else
            {
                bounds = hilightRegion.getBounds();
                
                bounds = bounds.intersection(imageBounds);
                
                //Subtract 1 for round off
                g.drawRect(bounds.x,bounds.y
                            ,bounds.width-1,bounds.height-1);
            }
        }
        
        selRegion= mod.getSelectedRegion();
        
        if(selRegion != null)
        {
            clr = c.getForeground();
            g.setColor(clr);
            
            if(selRegion instanceof Polygon)
            {
                g.drawPolygon((Polygon)selRegion);
            }
            else
            {
                bounds = selRegion.getBounds();
                
                bounds = bounds.intersection(imageBounds);
                
                //Subtract 1 for round off
                g.drawRect(bounds.x,bounds.y
                            ,bounds.width-1,bounds.height-1);
            }
        }

    }
    
    public Dimension getPreferredSize(JComponent c)
    {
        Dimension retVal = new Dimension();
        Image img;
        TouchScreenModel mod;
        TouchScreen screen;
	    Insets border = getBorderInsets(c);
        
        screen = (TouchScreen) c;
        mod = screen.getModel();
        img = mod.getImage();
        
        if(img != null)
        {
            retVal.width = img.getWidth(c);
            retVal.height = img.getHeight(c);
        }
        
        if(border != null)
        {
            retVal.width += border.left + border.right;
	        retVal.height += border.top + border.bottom;
        }
        
        return retVal;
    }

    public Dimension getMinimumSize(JComponent c)
    {
        return getPreferredSize(c);
    }
    
    public Dimension getMaximumSize(JComponent c)
    {
        return getPreferredSize(c);
    }
    
    protected Insets getBorderInsets(JComponent c)
    {
        Border b = c.getBorder();
        Insets retVal;
        
        if(b != null)
        {
            retVal = b.getBorderInsets(c);
        }
        else
        {
            retVal = new Insets(0,0,0,0);
        }
        
        return retVal;
    }
    
    /*
     * Methods for adding and removing the ui
     */
    public void installUI(JComponent c)
    {
        TouchScreen screen;
        
        screen = (TouchScreen)c;
        screen.addMouseListener(this);
        screen.addMouseMotionListener(this);
    }

    public void uninstallUI(JComponent c)
    {
        TouchScreen screen;
        
        screen = (TouchScreen)c;
        screen.removeMouseListener(this);
        screen.removeMouseMotionListener(this);
    }
}

