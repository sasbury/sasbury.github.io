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

class JoinCanvas extends JComponent
{
    int join_type;
    
    public JoinCanvas(int j)
    {
        join_type = j;
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
        
        //Draw a line from the bottom left to the middle,
        // then to the bottom right
        
        strk = new BasicStroke(20,BasicStroke.CAP_SQUARE,join_type);
        g2d.setStroke(strk);
        
        path.moveTo(0,bounds.height);
        path.lineTo(bounds.width/2,bounds.height/3);
        path.lineTo(bounds.width,bounds.height);
        
        g2d.setColor(Color.black);
        g2d.draw(path);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class Joins extends JPanel
{
    public Joins()
    {
        JPanel tmpPanel;
        JLabel tmpLabel;
        JoinCanvas tmpJCanvas;
    
        this.setLayout(new GridLayout(1,3,10,5));
        this.setDoubleBuffered(true);
    
        // Create a BEVEL Join and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Bevel");
        tmpJCanvas = new JoinCanvas(BasicStroke.JOIN_BEVEL);
    
        tmpPanel.add(tmpJCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create a MITER Join and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Miter");
        tmpJCanvas = new JoinCanvas(BasicStroke.JOIN_MITER);
    
        tmpPanel.add(tmpJCanvas,"Center");
        tmpPanel.add(tmpLabel,"South");
        add(tmpPanel);
    
        // Create a ROUND Join and label
    
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
    
        tmpLabel = new JLabel("Round");
        tmpJCanvas = new JoinCanvas(BasicStroke.JOIN_ROUND);
    
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
        JFrame frame = new JFrame("Line Join Example");
        Joins panel = new Joins();
        
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

