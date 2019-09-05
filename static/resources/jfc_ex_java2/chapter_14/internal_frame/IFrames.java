//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class IFrames extends JFrame
{
    public IFrames()
    {
        JInternalFrame frame;
        JMenuBar bar;
        JMenu menu;
        JMenuItem tmp;
        JPanel panel;
        int x,y;
        Dimension prefSize;
        JLayeredPane layers;
        
        setTitle("Internal Frame Example");
        
        layers = new JDesktopPane();
        setLayeredPane(layers);
        
        //Keep a running origin, to tile iframes
        x = 0;
        y = 0;
        
        //Create a default internal Frame
        frame = new JInternalFrame();
        
        panel = new JPanel();
        panel.add(new JLabel("Label"));
        panel.add(new JButton("Button"));
        frame.getContentPane().add(panel,"Center");
        
        frame.setLocation(x,y);
        frame.pack();
        layers.add(frame);
        
        x+=50;
        y+=50;
        
        //Create an internal Frame with a title
        //and a menu
        frame = new JInternalFrame("Frame 2");
        
        panel = new JPanel();
        panel.add(new JLabel("Label"));
        panel.add(new JButton("Button"));
        frame.getContentPane().add(panel,"Center");
        
        bar = new JMenuBar();
        menu = new JMenu("Levels");
        
        tmp = new JMenuItem("Primary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Secondary");
        menu.add(tmp);
        
        tmp = new JMenuItem("Tertiary");
        menu.add(tmp);
        
        bar.add(menu);
        frame.setJMenuBar(bar);
        
        frame.setLocation(x,y);
        frame.pack();
        layers.add(frame);
        
        x+=50;
        y+=50;
        
        //Create an internal Frame with a title
        //that is resizable and closable
        frame = new JInternalFrame("Frame 3",true,true);
        
        panel = new JPanel();
        panel.add(new JLabel("Label"));
        panel.add(new JButton("Button"));
        frame.getContentPane().add(panel,"Center");
        
        frame.setLocation(x,y);
        frame.pack();
        
        layers.add(frame);
        
        x+=50;
        y+=50;
        
        //Create an internal Frame with a title
        //that is resizable and closable
        //maximizable and iconizable
        frame = new JInternalFrame("Frame 4",true,true,true,true);
        
        panel = new JPanel();
        panel.add(new JLabel("Label"));
        panel.add(new JButton("Button"));
        frame.getContentPane().add(panel,"Center");
        
        frame.setLocation(x,y);
        frame.pack();
        
        layers.add(frame);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 400);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new IFrames();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        
        frame.setSize(frame.getPreferredSize());
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
