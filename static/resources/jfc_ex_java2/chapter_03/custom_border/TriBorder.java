//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class TriBorder extends AbstractBorder
{
    Insets ins;
    
	private TriBorder()
	{
	}
	
	public TriBorder(int top,int left,int bottom,int right)
	{
	    ins = new Insets(top,left,bottom,right);
	}
	
	public TriBorder(Insets i)
	{
	    ins=i;
	}
	
	public Insets getBorderInsets(Component c)
	{
	    return ins;
	}
	
	public void paintBorder(Component c, Graphics g,
                         int x, int y, int width, int height)
    {
        int triW,triH;
        int i,max;
        int xp[],yp[];
        
        xp = new int[4];
        yp = new int[4];
        
        g.setColor(Color.black);
        
        //Draw the triangles on the top
        
        triW = ins.left;
        triH = ins.top;
        max = triW + x + width;
        
        for(i=x;i<max;i+=triW)
        {
            xp[0] = i;
            xp[1] = i+(triW/2);
            xp[2] = i+triW;
            xp[3] = i;
            
            yp[0] = y+triH;
            yp[1] = y;
            yp[2] = y+triH;
            yp[3] = y+triH;
            
            g.fillPolygon(xp,yp,4);
        }
        
        //Draw the triangles on the bottom
        
        triW = ins.left;
        triH = ins.bottom;
        max = triW + x + width;
        
        for(i=x;i<max;i+=triW)
        {
            xp[0] = i;
            xp[1] = i+(triW/2);
            xp[2] = i+triW;
            xp[3] = i;
            
            yp[0] = y+height;
            yp[1] = y+height-triH;
            yp[2] = y+height;
            yp[3] = y+height;
            
            g.fillPolygon(xp,yp,4);
        }
        
        //Draw the triangles on the left
        
        triW = ins.left;
        triH = ins.top;
        max = y + height - triH;
        
        for(i=y+triH;i<max;i+=triH)
        {
            xp[0] = x;
            xp[1] = x+(triW/2);
            xp[2] = x+triW;
            xp[3] = x;
            
            yp[0] = i+triH;
            yp[1] = i;
            yp[2] = i+triH;
            yp[3] = i+triH;
            
            g.fillPolygon(xp,yp,4);
        }
        
        //Draw the triangles on the left
        
        triW = ins.right;
        triH = ins.top;
        max = y + height - triH;
        
        for(i=y+triH;i<max;i+=triH)
        {
            xp[0] = x+width-triW;
            xp[1] = x+width-(triW/2);
            xp[2] = x+width;
            xp[3] = x+width-triW;
            
            yp[0] = i+triH;
            yp[1] = i;
            yp[2] = i+triH;
            yp[3] = i+triH;
            
            g.fillPolygon(xp,yp,4);
        }
    }
}
