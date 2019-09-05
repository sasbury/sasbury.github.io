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

class CompositeCanvas extends JComponent
{
    int operator;
    float alpha;
    
    public CompositeCanvas()
    {
        operator = AlphaComposite.SRC_OVER;
        alpha = 1.0f;
    }
    
    public void setAlpha(float f)
    {
        alpha = f;
        repaint();
    }
    
    public void setOp(int op)
    {
        operator = op;
        repaint();
    }
    
    public void paint(Graphics g)
    {
        Rectangle bounds = getBounds();
        Graphics2D g2d;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        BasicStroke strk;
        AlphaComposite comp;
        int i,max;
        
        g2d = (Graphics2D)g;
        g2d.setBackground(Color.white);
        
        //fill the background with white
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,bounds.width,bounds.height);
        
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
        g2d.setComposite(comp);
        
        strk = new BasicStroke(2
                                ,BasicStroke.CAP_SQUARE
                                ,BasicStroke.JOIN_MITER);
        g2d.setStroke(strk);
        
        //Draw triangle
        path.moveTo(0,bounds.height);
        path.lineTo(0,0);
        path.lineTo(bounds.width,0);
        path.closePath();
        
        g2d.setColor(Color.red);
        g2d.fill(path);
        
        //Create the composite
        comp = AlphaComposite.getInstance(operator,alpha);
        g2d.setComposite(comp);
        
        //Draw horizontal lines
        path.reset();
        path.moveTo(0,0);
        path.lineTo(bounds.width,0);
        path.lineTo(bounds.width,bounds.height);
        path.closePath();
        
        g2d.setColor(Color.blue);
        g2d.fill(path);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class Compositing extends JPanel
implements ActionListener
{
    CompositeCanvas cCanvas;
    JTextField alphaField;
        
    public Compositing()
    {
        ButtonGroup grp;
        JRadioButton tmpButton;
        JPanel tmpPanel;
        
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(true);
        
        cCanvas = new CompositeCanvas();
    
        tmpPanel = new JPanel();
        grp = new ButtonGroup();
        
        tmpButton = new JRadioButton("CLEAR");
        tmpButton.setActionCommand("CLEAR");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("DST_IN");
        tmpButton.setActionCommand("DST_IN");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("DST_OUT");
        tmpButton.setActionCommand("DST_OUT");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("DST_OVER");
        tmpButton.setActionCommand("DST_OVER");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("SRC");
        tmpButton.setActionCommand("SRC");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("SRC_IN");
        tmpButton.setActionCommand("SRC_IN");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("SRC_OUT");
        tmpButton.setActionCommand("SRC_OUT");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("SRC_OVER");
        tmpButton.setActionCommand("SRC_OVER");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        alphaField = new JTextField("1.0");
        alphaField.setActionCommand("ALPHA");
        alphaField.addActionListener(this);
        
        tmpPanel.add(new JLabel("Alpha:"));
        tmpPanel.add(alphaField);
        
        //Select the SRC_OVER button
        tmpButton.setSelected(true);
        cCanvas.setOp(AlphaComposite.SRC_OVER);
    
        add(cCanvas,"Center");
        add(tmpPanel,"South");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        
        if("ALPHA".equals(cmd))
        {
            float a;
            String tmp;
            
            tmp = alphaField.getText();
            a = Float.valueOf(tmp).floatValue();
            cCanvas.setAlpha(a);
        }
        else if("CLEAR".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.CLEAR);
        }
        else if("DST_IN".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.DST_IN);
        }
        else if("DST_OUT".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.DST_OUT);
        }
        else if("DST_OVER".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.DST_OVER);
        }
        else if("SRC".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.SRC);
        }
        else if("SRC_IN".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.SRC_IN);
        }
        else if("SRC_OUT".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.SRC_OUT);
        }
        else if("SRC_OVER".equals(cmd))
        {
            cCanvas.setOp(AlphaComposite.SRC_OVER);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(700, 220);
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
        JFrame frame = new JFrame("Compositing Example");
        Compositing panel = new Compositing();
        
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

