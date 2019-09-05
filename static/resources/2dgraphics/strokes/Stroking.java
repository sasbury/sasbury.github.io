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
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

class StrokeCanvas extends JComponent
{
    int cap;
    int join;
    float dashes[];
    float phase;
    float width;
    
    public StrokeCanvas()
    {
        cap = BasicStroke.CAP_SQUARE;
        join = BasicStroke.JOIN_MITER;
        width = 1;
    }
    
    public void setWidth(float op)
    {
        width = op;
        repaint();
    }
    
    public void setCap(int op)
    {
        cap = op;
        repaint();
    }
    
    public void setJoin(int op)
    {
        join = op;
        repaint();
    }
    
    public void setPhase(float op)
    {
        phase = op;
        repaint();
    }
    
    public void setDashArray(String dashString)
    {
        //Parse string to create array
        if((dashString == null)||dashString.equals(""))
        {
            dashes = null;
        }
        else
        {
            Vector dashStrings = new Vector();
            int max,i,index=0,lastIndex = 0;
            String tmp;
            
            try
            {
                max = dashString.length();
            
                index = dashString.indexOf(",",lastIndex);
            
                while(index > 0)
                {
                    tmp = dashString.substring(lastIndex,index);
                    dashStrings.addElement(tmp);
                    lastIndex = index +1;
                    index = dashString.indexOf(",",lastIndex);                    
                }
            
                //Add the last string, or the whole thing if no commas
                if(dashStrings.size() > 0)
                {
                    dashStrings.addElement(dashString.substring(lastIndex,max));
                }
                else
                {
                    dashStrings.addElement(dashString);
                }
            
                max = dashStrings.size();
                
                dashes = new float[max];
            
                for(i=0;i<max;i++)
                {
                    tmp = (String)dashStrings.elementAt(i);
                    dashes[i] = Float.valueOf(tmp).floatValue();
                }
            }
            catch(Exception exp)
            {
                dashes = null;
                System.out.println("Couldn't set dashes");
            }
        }
        
        repaint();
    }
    
    public void paint(Graphics g)
    {
        Rectangle bounds = getBounds();
        Graphics2D g2d;
        BasicStroke strk;
        
        g2d = (Graphics2D) g;
        
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,bounds.width,bounds.height);
        
        if(dashes != null)
        {
            strk = new BasicStroke(width,cap,join,10.0f,dashes,phase);
        }
        else
        {
            strk = new BasicStroke(width,cap,join);
        }
        
        //Draw the line
        g2d.setStroke(strk);
        g2d.setColor(Color.black);
        g2d.drawLine(20,bounds.height/2
                        ,bounds.width- 20,bounds.height/2);
        
        //show the end points
        g2d.setStroke(new BasicStroke());
        g2d.setColor(Color.lightGray);
        g2d.drawLine(20,0
                    ,20,bounds.height);
        g2d.drawLine(bounds.width- 20,0
                    ,bounds.width- 20,bounds.height);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,200);
    }
}

public class Stroking extends JPanel
implements ActionListener
{
    StrokeCanvas sCanvas;
    JTextField dashField;
    JTextField phaseField;
    JTextField widthField;
        
    public Stroking()
    {
        ButtonGroup grp;
        JRadioButton tmpButton;
        JPanel tmpPanel,bottomPanel;
        JLabel tmpLabel;
        
        this.setLayout(new BorderLayout());
        this.setDoubleBuffered(true);
        
        sCanvas = new StrokeCanvas();
    
        bottomPanel = new JPanel();
    
        tmpPanel = new JPanel();
        tmpPanel.setBorder(new TitledBorder("Caps"));
        tmpPanel.setLayout(new GridLayout(3,1,5,5));
        
        grp = new ButtonGroup();
        
        tmpButton = new JRadioButton("BUTT");
        tmpButton.setActionCommand("BUTT");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("SQUARE");
        tmpButton.setActionCommand("SQUARE");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpButton.setSelected(true);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("ROUND");
        tmpButton.setActionCommand("ROUNDC");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        bottomPanel.add(tmpPanel);
        
        tmpPanel = new JPanel();
        tmpPanel.setLayout(new GridLayout(3,1,5,5));
        tmpPanel.setBorder(new TitledBorder("Joins"));
        
        grp = new ButtonGroup();
        
        tmpButton = new JRadioButton("MITER");
        tmpButton.setActionCommand("MITER");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpButton.setSelected(true);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("BEVEL");
        tmpButton.setActionCommand("BEVEL");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        tmpButton = new JRadioButton("ROUND");
        tmpButton.setActionCommand("ROUNDJ");
        tmpButton.addActionListener(this);
        grp.add(tmpButton);
        tmpPanel.add(tmpButton);
        
        bottomPanel.add(tmpPanel);
        
        tmpPanel = new JPanel();
        
        tmpLabel = new JLabel("Width");
        tmpPanel.setBorder(new TitledBorder("Line Width"));
        tmpPanel.add(tmpLabel);
        
        widthField = new JTextField(4);
        widthField.setActionCommand("WIDTH");
        widthField.addActionListener(this);
        widthField.setText("1");
        tmpPanel.add(widthField);
        
        bottomPanel.add(tmpPanel);
        
        tmpPanel = new JPanel();
        tmpPanel.setBorder(new TitledBorder("Dashes"));
        
        tmpLabel = new JLabel("Phase");
        tmpPanel.add(tmpLabel);
        
        phaseField = new JTextField(4);
        phaseField.setActionCommand("PHASE");
        phaseField.addActionListener(this);
        phaseField.setText("0");
        tmpPanel.add(phaseField);
        
        tmpLabel =  new JLabel("Dashes");
        tmpPanel.add(tmpLabel);
        
        dashField = new JTextField(10);
        dashField.setActionCommand("DASH");
        dashField.addActionListener(this);
        tmpPanel.add(dashField);
        
        bottomPanel.add(tmpPanel);
    
        add(sCanvas,"Center");
        add(bottomPanel,"South");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        
        if("BUTT".equals(cmd))
        {
            sCanvas.setCap(BasicStroke.CAP_BUTT);
        }
        else if("ROUNDC".equals(cmd))
        {
            sCanvas.setCap(BasicStroke.CAP_ROUND);
        }
        else if("SQUARE".equals(cmd))
        {
            sCanvas.setCap(BasicStroke.CAP_SQUARE);
        }
        else if("MITER".equals(cmd))
        {
            sCanvas.setJoin(BasicStroke.JOIN_MITER);
        }
        else if("BEVEL".equals(cmd))
        {
            sCanvas.setJoin(BasicStroke.JOIN_BEVEL);
        }
        else if("ROUNDJ".equals(cmd))
        {
            sCanvas.setJoin(BasicStroke.JOIN_ROUND);
        }
        else if("WIDTH".equals(cmd))
        {
            float width;
            String tmp;
            
            try
            {
                tmp = widthField.getText();
                width = Float.valueOf(tmp).floatValue();   
                sCanvas.setWidth(width);
            }
            catch(Exception exp)
            {
                System.out.println("Couldn't set width");
            }
        }
        else if("PHASE".equals(cmd))
        {
            float phase;
            String tmp;
            
            try
            {
                tmp = phaseField.getText();
                phase = Float.valueOf(tmp).floatValue();   
                sCanvas.setPhase(phase);
            }
            catch(Exception exp)
            {
                System.out.println("Couldn't set phase");
            }
        }
        else if("DASH".equals(cmd))
        {
            sCanvas.setDashArray(dashField.getText());
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 220);
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
        JFrame frame = new JFrame("Basic Stroke Example");
        Stroking panel = new Stroking();
        
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

