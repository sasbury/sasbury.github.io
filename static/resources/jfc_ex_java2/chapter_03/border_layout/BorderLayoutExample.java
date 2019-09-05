//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.applet.*;
import java.awt.*;

public class BorderLayoutExample extends JPanel
{
	public BorderLayoutExample()
	{
		JButton tmp;
		Font font;
		
		font = new Font("Times-Roman",Font.BOLD,18);
		
		this.setLayout(new BorderLayout());

		tmp = new JButton("North");
		tmp.setFont(font);
		add(tmp,"North");
		
		tmp = new JButton("South");
		tmp.setFont(font);
		add(tmp,"South");
		
		tmp = new JButton("East");
		tmp.setFont(font);
		add(tmp,"East");
		
		tmp = new JButton("West");
		tmp.setFont(font);
		add(tmp,"West");	
		
		tmp = new JButton("Center");
		tmp.setFont(font);
		add(tmp,"Center");	
	}
	
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400, 150);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Border Layout Example");
        BorderLayoutExample panel = new BorderLayoutExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

