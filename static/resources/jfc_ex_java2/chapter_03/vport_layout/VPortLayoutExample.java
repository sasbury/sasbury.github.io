//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.applet.*;
import java.awt.*;

public class VPortLayoutExample extends JPanel
{
	public VPortLayoutExample()
	{
		IconComp ic;
		JViewport subPanel;
		
		this.setLayout(new GridLayout(1,2,10,10));
		
		ic = new IconComp(new ImageIcon("images/shapes.gif"));
		subPanel = new JViewport();
		subPanel.setView(ic);
		add(subPanel);
		
		ic = new IconComp(new ImageIcon("images/logo.gif"));
		subPanel = new JViewport();
		subPanel.setView(ic);
		add(subPanel);
	}
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 150);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("View Port Layout Example");
        VPortLayoutExample panel = new VPortLayoutExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

class IconComp extends JComponent
{
    Icon icon;
    
    public IconComp(Icon i)
    {
        icon = i;
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(icon.getIconWidth(),icon.getIconHeight());
    }
    
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
    
    public void paint(Graphics g)
    {
        icon.paintIcon(this,g,0,0);
    }
}
