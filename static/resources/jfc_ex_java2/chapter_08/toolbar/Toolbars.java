//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class Toolbars extends JPanel
{
    public Toolbars()
    {
        JToolBar bar;
        JComboBox combo;
        
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        
        bar = new JToolBar();
        
        bar.add(new JButton("Left"));
        bar.add(new JButton("Right"));
        bar.add(new JButton("Center"));
        bar.addSeparator();
        bar.add(new JButton("Top"));
        bar.add(new JButton("Bottom"));
        bar.add(new JButton("Center"));
        
        add(bar,"North");
        
        bar = new JToolBar();
        bar.setBorderPainted(false);
        
        bar.add(new CheckAction());
        bar.add(new ExAction());
        bar.add(new ShapesAction());
        
        bar.addSeparator();
        
        combo = new JComboBox();
        combo.addItem("One");
        combo.addItem("Two");
        combo.addItem("Three");
        
        bar.add(combo);
        
        add(bar,"South");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 150);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Toolbar Example");
        Toolbars panel = new Toolbars();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

class CheckAction extends AbstractAction
{
    public CheckAction()
    {
        Icon img = new ImageIcon("images/check.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,"Check");
    }
    
    
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Check");
    }
}

class ExAction extends AbstractAction
{
    public ExAction()
    {
        Icon img = new ImageIcon("images/ex.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,"Ex");
    }
    
    
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Ex");
    }
}

class ShapesAction extends AbstractAction
{
    public ShapesAction()
    {
        Icon img = new ImageIcon("images/shapes.gif");
        putValue(Action.SMALL_ICON,img);
        putValue(Action.DEFAULT,img);
        putValue(Action.NAME,"Shapes");
    }
    
    
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Shapes");
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
