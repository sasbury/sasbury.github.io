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

class WindingCanvas extends JComponent
{
	int rule;
	boolean reverse;
	
	public WindingCanvas(int b,boolean yesno)
	{
		rule = b;
		reverse = yesno;
	}
	
	public void paint(Graphics g)
	{
		Rectangle bounds = getBounds();
		Graphics2D g2d;
		GeneralPath outer = new GeneralPath(rule);
		GeneralPath inner = new GeneralPath(rule);
		GeneralPath total = new GeneralPath(rule);
		BasicStroke strk;
		
		g2d = (Graphics2D)g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(0,0,bounds.width,bounds.height);
		
		strk = new BasicStroke(4
		                        ,BasicStroke.CAP_SQUARE
		                        ,BasicStroke.JOIN_MITER);
		g2d.setStroke(strk);
		
		//Build outer square
		outer.moveTo(20,20);
		outer.lineTo(20,bounds.height-20);
		outer.lineTo(bounds.width-20,bounds.height-20);
		outer.lineTo(bounds.width-20,20);
		outer.closePath();
		
		//Build inner square, reverse if approp.
		
		inner.moveTo(40,40);
		
		if(reverse)
		{
    		inner.lineTo(bounds.width-40,40);
    		inner.lineTo(bounds.width-40,bounds.height-40);
    		inner.lineTo(40,bounds.height-40);
		}
		else
		{
    		inner.lineTo(40,bounds.height-40);
    		inner.lineTo(bounds.width-40,bounds.height-40);
    		inner.lineTo(bounds.width-40,40);
		}
		
		inner.closePath();
		
		//build the total path
		total.append(outer,false);
		total.append(inner,false);
		
		//fill the path
		g2d.setColor(Color.lightGray);
		g2d.fill(total);
		
		//Draw the path w/arrows
		g2d.setColor(Color.black);
		g2d.draw(total);
		
		g2d.drawLine(bounds.width/2,20
		                ,(bounds.width/2) + 4,16);
		g2d.drawLine(bounds.width/2,20
		                ,(bounds.width/2) + 6,24);
		
		if(reverse)
		{
		    g2d.drawLine(bounds.width/2,40
		                ,(bounds.width/2) - 4,36);
	    	g2d.drawLine(bounds.width/2,40
		                ,(bounds.width/2) - 4,44);
		}
		else
		{
		    g2d.drawLine(bounds.width/2,40
		                ,(bounds.width/2) + 4,36);
	    	g2d.drawLine(bounds.width/2,40
		                ,(bounds.width/2) + 4,44);
		}
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(150,150);
	}
}

public class Winding extends JPanel
{
    public Winding()
    {
    	JPanel tmpPanel;
    	JLabel tmpLabel;
    	WindingCanvas tmpWCanvas;
	
    	this.setLayout(new GridLayout(1,4,10,5));
    	this.setDoubleBuffered(true);
	
    	// Create a even-odd Canvas and label
	
    	tmpPanel = new JPanel();
    	tmpPanel.setLayout(new BorderLayout());
	
    	tmpLabel = new JLabel("EvenOdd");
    	tmpWCanvas = new WindingCanvas(GeneralPath.WIND_EVEN_ODD,false);
	
    	tmpPanel.add(tmpWCanvas,"Center");
    	tmpPanel.add(tmpLabel,"South");
    	add(tmpPanel);
	
    	// Create a even-odd Canvas and label, reversed
	
    	tmpPanel = new JPanel();
    	tmpPanel.setLayout(new BorderLayout());
	
    	tmpLabel = new JLabel("EvenOdd, with reverse");
    	tmpWCanvas = new WindingCanvas(GeneralPath.WIND_EVEN_ODD,true);
	
    	tmpPanel.add(tmpWCanvas,"Center");
    	tmpPanel.add(tmpLabel,"South");
    	add(tmpPanel);
	
    	// Create an nonzero Canvas and label
	
    	tmpPanel = new JPanel();
    	tmpPanel.setLayout(new BorderLayout());
	
    	tmpLabel = new JLabel("None Zero");
    	tmpWCanvas = new WindingCanvas(GeneralPath.WIND_NON_ZERO,false);
	
    	tmpPanel.add(tmpWCanvas,"Center");
    	tmpPanel.add(tmpLabel,"South");
    	add(tmpPanel);
	
    	// Create an nonzero Canvas and label,reversed
	
    	tmpPanel = new JPanel();
    	tmpPanel.setLayout(new BorderLayout());
	
    	tmpLabel = new JLabel("None Zero with reverse");
    	tmpWCanvas = new WindingCanvas(GeneralPath.WIND_NON_ZERO,true);
	
    	tmpPanel.add(tmpWCanvas,"Center");
    	tmpPanel.add(tmpLabel,"South");
    	add(tmpPanel);
	}
	
    public Dimension getPreferredSize()
    {
        return new Dimension(620, 170);
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
        JFrame frame = new JFrame("WindingRule Example");
        Winding panel = new Winding();
        
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

