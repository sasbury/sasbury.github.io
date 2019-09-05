//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

public class TouchScreenTest extends JPanel 
implements ActionListener
{
    TouchScreen screen;
        
    public TouchScreenTest()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img;
        TouchScreenModel mod;
        
        setBackground(Color.lightGray);
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        setLayout(new BorderLayout());
        
        img = kit.getImage("images/logo.gif");
        
        screen = new TouchScreen();
        screen.setForeground(Color.blue);
        screen.addActionListener(this);
        screen.setActionCommand("Screen Action");
        
        screen.setBorder(new TitledBorder("Touch Screen"));
        
        add(screen,"Center");
        
        mod = screen.getModel();
        
        mod.setImage(img);
        mod.addRegion(new Rectangle(0,0,100,80));
        mod.addRegion(new Rectangle(0,80,100,80));
        mod.addRegion(new Rectangle(100,0,100,80));
        mod.addRegion(new Rectangle(100,80,100,80));
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String cmd;
        TouchScreen source;
        TouchScreenModel mod;
        Shape curReg;
        Rectangle bounds=null;
        
        source = (TouchScreen) e.getSource();
        cmd = e.getActionCommand();
        mod = screen.getModel();
        
        curReg = mod.getSelectedRegion();
        if(curReg != null)
        {
            bounds = curReg.getBounds();
        }
        
        System.out.println("Action: "+cmd);
        if(bounds != null) System.out.println("\t Sel region: "+bounds);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200,160);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("TouchScreenTest");
        TouchScreenTest panel = new TouchScreenTest();
        
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
