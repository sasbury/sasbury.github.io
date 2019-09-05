//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Changes extends JPanel 
implements ChangeListener
{
    JTextField field;
        
    public Changes()
    {
        JSlider slider;
        
        setBackground(Color.lightGray);
        setLayout(new GridLayout(1,2,10,10));
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        field = new JTextField("50",3);
        add(field);
        
        /*
         * Create a slider that is 5 wide and goes from 0 to 100.
         * The actual maximum is 105 to account for the sliders
         * extent/width.
         */
        slider = new JSlider(JSlider.HORIZONTAL,0,105,50);
        slider.setExtent(5);
        slider.addChangeListener(this);
        add(slider);
    }
    
    public void stateChanged(ChangeEvent e)
    {
        Object src;
        
        src = e.getSource();
        
        if(src instanceof JSlider)
        {
            JSlider slide = (JSlider) src;
            field.setText(Integer.toString(slide.getValue()));
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 50);
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
        JFrame frame = new JFrame("Changes");
        Changes panel = new Changes();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

class WindowCloser extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}
