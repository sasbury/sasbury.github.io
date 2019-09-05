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

class ClipCanvas extends JComponent
{
    boolean clipCircle;
    
    public ClipCanvas(boolean yesno)
    {
        clipCircle = yesno;
    }
    
    public void paint(Graphics g)
    {
        Rectangle bounds = getBounds();
        Graphics2D g2d;
        Ellipse2D circle;
        Rectangle2D rect;
        Shape oldClip;
        
        g2d = (Graphics2D)g;
        
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,bounds.width,bounds.height);
        
        circle = new Ellipse2D.Float(10.0f,10.0f
                                    ,(float)(2*bounds.width/3) - 20.0f
                                    ,(float)bounds.height - 20.0f);
        
        rect = new Rectangle2D.Float((float)(bounds.width/3)+10.0f,10.0f
                                    ,(float)(2*bounds.width/3) - 20.0f
                                    ,(float)bounds.height - 20.0f);
        
        oldClip = g2d.getClip();
        
        if(clipCircle) g2d.clip(circle);
        else g2d.clip(rect.getBounds());//doesn't work with rect alone
        
        g2d.setColor(Color.red);
        g2d.fill(circle);
        
        g2d.setColor(Color.blue);
        g2d.fill(rect);
        
        g2d.setClip(oldClip);
        g2d.setColor(Color.lightGray);
        g2d.draw(circle);
        g2d.draw(rect);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class Clipping extends JPanel
{
    public Clipping()
    {
        JPanel tmpPanel;
        JLabel tmpLabel;
        ClipCanvas tmpCCanvas;
    
        this.setLayout(new GridLayout(1,2,10,5));
        this.setDoubleBuffered(true);
    
        // Create a circle clip Canvas and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Clip Circle");
        tmpCCanvas = new ClipCanvas(true);
    
        tmpPanel.add(tmpCCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create an rect clip Canvas and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Clip Rectangle");
        tmpCCanvas = new ClipCanvas(false);
    
        tmpPanel.add(tmpCCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(420, 220);
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
        JFrame frame = new JFrame("Clipping Example");
        Clipping panel = new Clipping();
        
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

