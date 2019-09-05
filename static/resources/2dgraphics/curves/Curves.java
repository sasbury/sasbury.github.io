/*
 * Copyright (c)  1998 Paradigm Research, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute portions of
 * this software and its documentation for NON-COMMERCIAL purposes and
 * without fee is hereby granted provided that this copyright notice
 * appears in all copies. 
 *
 * PARADIGM RESEARCH MAKES NO REPRESENTATIONS OR WARRANTIES WITH RESPECT TO
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. PARADIGM RESEARCH SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES OR ANY RELATED
 * DOCUMENTATION.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;

class ControlPoint extends MouseAdapter
implements MouseMotionListener 
{
	Point2D.Float p1;
	CurveCanvas owner;
	boolean gotPress;
	
	public ControlPoint(CurveCanvas canv)
	{
	    owner = canv;
	    p1 = new Point2D.Float();
	}
	
	public void moveTo(float x,float y)
	{
	    p1.x = x;
	    p1.y = y;
	}
	
	public int getX()
	{
	    return (int)p1.getX();
	}
	
	public int getY()
	{
	    return (int)p1.getY();
	}
	
	protected void update(MouseEvent evt)
	{
	    if(gotPress)
	    {
    	    p1.x = (float)evt.getX();
    	    p1.y = (float)evt.getY();
    	    owner.repaint();
	    }
	}
	
	public void mouseDragged(MouseEvent evt)
	{
	    update(evt);
	}
	
    public void mouseMoved(MouseEvent evt)
    {
    }
    
    public void mousePressed(MouseEvent evt) 
    {
        if((Math.abs(evt.getX()-p1.x) < 4)&&
	        (Math.abs(evt.getY()-p1.y) < 4))
	    {
	        gotPress = true;
	        update(evt);
	    }    
    }
    
    public void mouseReleased(MouseEvent evt) 
    {
	    update(evt);
	    gotPress = false;
    }
}

class CurveCanvas extends JComponent
{
    ControlPoint quadcp;
    ControlPoint cubcp1,cubcp2;
    
	public CurveCanvas()
	{
	    quadcp = new ControlPoint(this);
	    quadcp.moveTo(100,100);
	    addMouseListener(quadcp);
	    addMouseMotionListener(quadcp);
	    
	    cubcp1 = new ControlPoint(this);
	    cubcp1.moveTo(50,100);
	    addMouseListener(cubcp1);
	    addMouseMotionListener(cubcp1);
	    
	    cubcp2 = new ControlPoint(this);
	    cubcp2.moveTo(150,100);
	    addMouseListener(cubcp2);
	    addMouseMotionListener(cubcp2);
	}
	
	public void paint(Graphics g)
	{
		Rectangle bounds = getBounds();
		Graphics2D g2d;
		GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
		BasicStroke strk;
		
		g2d = (Graphics2D)g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,bounds.width,bounds.height);
		
		strk = new BasicStroke(4
		                        ,BasicStroke.CAP_SQUARE
		                        ,BasicStroke.JOIN_MITER);
		g2d.setStroke(strk);
		
		//Draw quad line
		
		path.moveTo(0,bounds.height);
		path.quadTo(quadcp.getX(),quadcp.getY()
		            ,bounds.width,bounds.height);
		
		g2d.setColor(Color.lightGray);
		g2d.draw(path);
		
		//Draw Quad control point
		g2d.fillOval(quadcp.getX()-4,quadcp.getY()-4
		            ,8,8);
		
		//Draw cubic line
		path.reset();
		path.moveTo(0,bounds.height);
		path.curveTo(cubcp1.getX(),cubcp1.getY()
		            ,cubcp2.getX(),cubcp2.getY()
		            ,bounds.width,bounds.height);
		
		g2d.setColor(Color.black);
		g2d.draw(path);
		
		//draw cubic control points
		g2d.fillOval(cubcp1.getX()-4,cubcp1.getY()-4
		            ,8,8);
		            
		g2d.fillOval(cubcp2.getX()-4,cubcp2.getY()-4
		            ,8,8);
		
		//Connect cubic control points
		g2d.setStroke(new BasicStroke());
	    g2d.drawLine(cubcp1.getX(),cubcp1.getY()
		            ,cubcp2.getX(),cubcp2.getY());
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(200,200);
	}
}

public class Curves extends JPanel
{
    public Curves()
    {
    	JLabel tmpLabel;
    	CurveCanvas tmpCCanvas;
	
    	this.setLayout(new BorderLayout());
    	this.setDoubleBuffered(true);
	
    	tmpLabel = new JLabel("Quad in Gray, Cubic in black, "
    	                        +"drag circles to change control points");
    	tmpCCanvas = new CurveCanvas();
	
    	add(tmpCCanvas,"Center");
        add(tmpLabel,"South");
    	
	}
	
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 420);
    }

    public Dimension getMinimumSize()
    {
        return new Dimension(25, 25);
    }

    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Curve Example");
        Curves panel = new Curves();
        
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
        win.dispose();
        System.exit(0);
    }
}

