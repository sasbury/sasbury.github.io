//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.applet.*;
import java.awt.*;

public class GridLayoutExample extends JPanel
{
	public GridLayoutExample()
	{
		JButton tmp;
		
		this.setLayout(new GridLayout(2,2,5,5));

		tmp = new JButton("One");
		add(tmp);
		
		tmp = new JButton("Two");
		add(tmp);
		
		tmp = new JButton("Three");
		add(tmp);			
		
		tmp = new JButton("Four");
		add(tmp);						
	}
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Grid Layout Example");
        GridLayoutExample panel = new GridLayoutExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

