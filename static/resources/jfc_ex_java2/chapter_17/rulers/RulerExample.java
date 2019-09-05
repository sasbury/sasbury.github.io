//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class RulerExample extends JPanel
{
    public RulerExample()
    {
        JScrollPane scroller;
        JLabel mainView;
        HRuler hRuler;
        VRuler vRuler;
        String pos;
        
        setLayout(new BorderLayout());
        
        mainView = new JLabel(new ImageIcon("logo.gif"));
        
        hRuler = new HRuler();
        mainView.addMouseMotionListener(hRuler);
        
        vRuler = new VRuler();
        mainView.addMouseMotionListener(vRuler);
        
        scroller = new JScrollPane(mainView);
        
        scroller.setColumnHeaderView(hRuler);
        scroller.setRowHeaderView(vRuler);
        
        pos = JScrollPane.UPPER_LEFT_CORNER;
        scroller.setCorner(pos,new Corner(mainView,pos));
        
        pos = JScrollPane.LOWER_LEFT_CORNER;
        scroller.setCorner(pos,new Corner(mainView,pos));
        
        pos = JScrollPane.UPPER_RIGHT_CORNER;
        scroller.setCorner(pos,new Corner(mainView,pos));
        
        pos = JScrollPane.LOWER_RIGHT_CORNER;
        scroller.setCorner(pos,new Corner(mainView,pos));
        
        add(scroller,"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 600);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Ruler Example");
        RulerExample rulers = new RulerExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(rulers,"Center");
        
        frame.setSize(new Dimension(300,300));
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


