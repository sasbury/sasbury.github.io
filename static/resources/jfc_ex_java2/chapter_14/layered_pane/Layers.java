//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class Layers extends JFrame
{
    public Layers()
    {
        JInternalFrame frame;
        JPanel panel;
        JLayeredPane layers;
        int i;
        Dimension prefSize;
        
        setTitle("Layered Pane Example");
        
        layers = getLayeredPane();

        
        //Add a frame to the 0th layers
        frame = new JInternalFrame();
    
        panel = new JPanel();
        panel.add(new JLabel("0th Layer"));
        frame.getContentPane().add(panel,"Center");
    
        frame.setLocation(50,10);
        frame.pack();
        
        layers.add(frame,new Integer(0));
        
        //Make a few internal frames at various layers
        
        for(i=0;i<10;i+=2)
        {
            frame = new JInternalFrame();
        
            panel = new JPanel();
            panel.add(new JLabel("Layer "+(i*40)));
            frame.getContentPane().add(panel,"Center");
        
            frame.setLocation(i*20,i*20);
            frame.pack();
            
            layers.add(frame,new Integer(i*40));
        }
        
        //Add a frame to the Pallette layers
        frame = new JInternalFrame();
    
        panel = new JPanel();
        panel.add(new JLabel("Palette Layer"));
        frame.getContentPane().add(panel,"Center");
    
        frame.setLocation((i++)*20,(i++)*20);
        frame.pack();
        
        layers.add(frame,JLayeredPane.PALETTE_LAYER);
        
        //Add a frame to the popup layers
        frame = new JInternalFrame();
    
        panel = new JPanel();
        panel.add(new JLabel("Popup Layer"));
        frame.getContentPane().add(panel,"Center");
    
        frame.setLocation((i++)*20,(i++)*20);
        frame.pack();
        
        layers.add(frame,JLayeredPane.POPUP_LAYER);
        
        //Add a frame to the drag layers
        frame = new JInternalFrame();
    
        panel = new JPanel();
        panel.add(new JLabel("Drag Layer"));
        frame.getContentPane().add(panel,"Center");
    
        frame.setLocation((i++)*20,(i++)*20);
        frame.pack();
        
        layers.add(frame,JLayeredPane.DRAG_LAYER);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 400);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new Layers();
        
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
