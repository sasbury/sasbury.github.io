//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Windows2Motif extends JPanel 
implements ActionListener
{
    public Windows2Motif()
    {
        JTextField field;
        AbstractButton button;
        JSlider slider;
        JRadioButton radioButton;
        ButtonGroup grp;
        JPanel topPanel, bottomPanel;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3,2,5,5));
        
        field = new JTextField("TextField",10);
        topPanel.add(field);
        
        button = new JButton("Button");
        topPanel.add(button);
        
        button = new JCheckBox("Checkbox");
        topPanel.add(button);
        
        button = new JRadioButton("RadioButton");
        topPanel.add(button);
        
        slider = new JSlider(JSlider.HORIZONTAL,0,100,50);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        topPanel.add(slider);
        
        add(topPanel,"Center");
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        
        grp = new ButtonGroup();
        
        radioButton =  new JRadioButton("Windows",true);
        radioButton.addActionListener(this);
        radioButton.setActionCommand("Windows");
        grp.add(radioButton);
        bottomPanel.add(radioButton);
        
        radioButton =  new JRadioButton("Motif",false);
        radioButton.addActionListener(this);
        radioButton.setActionCommand("Motif");
        grp.add(radioButton);
        bottomPanel.add(radioButton);
        
        add(bottomPanel,"South");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd;
        
        cmd = e.getActionCommand();
        
        if(cmd.equals("Windows")) setUI2Windows();
        else if(cmd.equals("Motif")) setUI2Motif();
    }
    
    public void setUI2Motif()
    {
        try
        {
            Container parent;
            String cn = "com.sun.java.swing.plaf.";
           
            cn+="motif.MotifLookAndFeel";
            
            parent = getParent();
            
            if(parent != null)
            {
                UIManager.setLookAndFeel(cn);
            
                SwingUtilities.updateComponentTreeUI(this);
                parent.validate();
                parent.repaint();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }

    public void setUI2Windows()
    {
        try
        {
            Container parent;
            String cn = "com.sun.java.swing.plaf.";
            
            cn+="windows.WindowsLookAndFeel";
        
            parent = getParent();
            
            if(parent != null)
            {
                UIManager.setLookAndFeel(cn);
            
                SwingUtilities.updateComponentTreeUI(this);
                parent.validate();
                parent.repaint();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Windows2Motif");
        Windows2Motif panel = new Windows2Motif();
        String cn = "javax.swing.";
            
        cn+="windows.WindowsLookAndFeel";
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        try
        {
            UIManager.setLookAndFeel(cn);
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch(Exception exp)
        {
            System.out.println(exp);
        }
        
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
