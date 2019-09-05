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

class CapCanvas extends JComponent
{
    int cap_type;
    
    public CapCanvas(int j)
    {
        cap_type = j;
    }
    
    public void paint(Graphics g)
    {
        Rectangle bounds = getBounds();
        Graphics2D g2d;
        BasicStroke strk;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        
        
        g2d = (Graphics2D)g;
        
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,bounds.width,bounds.height);
        
        //Draw a line from the bottom, up
        
        strk = new BasicStroke(20,cap_type,BasicStroke.JOIN_MITER);
        g2d.setStroke(strk);
        
        path.moveTo(bounds.width/2,bounds.height);
        path.lineTo(bounds.width/2,bounds.height/3);
        
        g2d.setColor(Color.black);
        g2d.draw(path);
        
        //draw a line at y = bounds.height/3
        strk = new BasicStroke();
        g2d.setStroke(strk);
        
        g2d.setColor(Color.lightGray);
        g2d.drawLine(0,bounds.height/3
                    ,bounds.width,bounds.height/3);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class Caps extends JPanel
{
    public Caps()
    {
        JPanel tmpPanel;
        JLabel tmpLabel;
        CapCanvas tmpJCanvas;
    
        this.setLayout(new GridLayout(1,3,10,5));
        this.setDoubleBuffered(true);
    
        // Create a BUTT cap and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Butt");
        tmpJCanvas = new CapCanvas(BasicStroke.CAP_BUTT);
    
        tmpPanel.add(tmpJCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create a SQUARE cap and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Square");
        tmpJCanvas = new CapCanvas(BasicStroke.CAP_SQUARE);
    
        tmpPanel.add(tmpJCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create a ROUND cap and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Round");
        tmpJCanvas = new CapCanvas(BasicStroke.CAP_ROUND);
    
        tmpPanel.add(tmpJCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(620, 220);
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
        JFrame frame = new JFrame("Line Cap Example");
        Caps panel = new Caps ();
        
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

