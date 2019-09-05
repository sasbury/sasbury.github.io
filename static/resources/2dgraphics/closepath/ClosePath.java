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

class CloseCanvas extends JComponent
{
    boolean closeit;
    
    public CloseCanvas(boolean yesno)
    {
        closeit = yesno;
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
        
        strk = new BasicStroke(16
                                ,BasicStroke.CAP_SQUARE
                                ,BasicStroke.JOIN_MITER);
        g2d.setStroke(strk);
        
        //Build a triangle,inset by 20 on
        //each side
        
        path.moveTo(20,bounds.height-20);
        path.lineTo(bounds.width/2,20);
        path.lineTo(bounds.width-20,bounds.height-20);
        
        //close, or just line to
        if(closeit)
        {
            path.closePath();
        }
        else
        {
            path.lineTo(20,bounds.height-20);
        }
        
        g2d.setColor(Color.black);
        g2d.draw(path);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class ClosePath extends JPanel
{
    public ClosePath()
    {
        JPanel tmpPanel;
        JLabel tmpLabel;
        CloseCanvas tmpCCanvas;
    
        this.setLayout(new GridLayout(1,2,10,5));
        this.setDoubleBuffered(true);
    
        // Create a closed Canvas and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Closed");
        tmpCCanvas = new CloseCanvas(true);
    
        tmpPanel.add(tmpCCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create an open Canvas and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Not Closed");
        tmpCCanvas = new CloseCanvas(false);
    
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
        JFrame frame = new JFrame("ClosePath Example");
        ClosePath panel = new ClosePath();
        
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

