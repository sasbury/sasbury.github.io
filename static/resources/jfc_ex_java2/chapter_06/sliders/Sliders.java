//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Sliders extends JPanel
{
    public Sliders()
    {
        JPanel left, right;
        JSlider slider;
        
        setBackground(Color.lightGray);
        setLayout(new GridLayout(1,2,5,5));
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        left = new JPanel();
        left.setLayout(new GridLayout(5,1,5,5));
        
        //Plain slider
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        slider.setToolTipText("0-100");
        left.add(slider);
        
        //Slider with major ticks
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        slider.setToolTipText("Major ticks");
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        left.add(slider);
        
        //Slider with major and minor ticks
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        slider.setToolTipText("Major & minor ticks");
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        left.add(slider);
        
        //Slider with big extent
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        slider.setToolTipText("0-100, extent = 20, snaps");
        slider.setMajorTickSpacing(10);
        slider.setExtent(20);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        left.add(slider);
        
        //Disabled,bordered slider
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 40);
        slider.setToolTipText("Disabled, bordered");
        slider.setEnabled(false);
        slider.setBorder(new TitledBorder("Disabled"));
        left.add(slider);
        
        add(left);
        
        right = new JPanel();
        right.setLayout(new GridLayout(1,5,5,5));
        
        //Plain slider
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 40);
        slider.setToolTipText("0-100");
        right.add(slider);
        
        //Slider with major ticks
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 40);
        slider.setToolTipText("Major ticks");
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        right.add(slider);
        
        //Slider with major and minor ticks
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 40);
        slider.setToolTipText("Major & minor ticks");
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        right.add(slider);
        
        //Slider with big extent
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 40);
        slider.setToolTipText("0-100, extent = 20, snaps");
        slider.setMajorTickSpacing(10);
        slider.setExtent(20);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        right.add(slider);
        
        //Disabled,bordered slider
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 40);
        slider.setToolTipText("Disabled, bordered");
        slider.setEnabled(false);
        slider.setBorder(new TitledBorder("Disabled"));
        right.add(slider);
        
        add(right);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(650, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Sliders");
        Sliders panel = new Sliders();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

