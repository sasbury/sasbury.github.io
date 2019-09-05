//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.*;
import java.applet.*;

public class NoLayout extends JPanel
{
	public NoLayout()
	{
		Button tmp;
		
		setLayout(null);
		
		tmp = new Button("North West");
		tmp.setBounds(0,0,100,100);
		add(tmp);
		
		tmp = new Button("North East");
		tmp.setBounds(100,0,100,100);
		add(tmp);
		
		tmp = new Button("South West");
		tmp.setBounds(0,100,100,100);
		add(tmp);
		
		tmp = new Button("South East");
		tmp.setBounds(100,100,100,100);
		add(tmp);
	}
	
    public Dimension getPreferredSize()
    {
        return new Dimension(250, 250);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("No Layout Example");
        NoLayout panel = new NoLayout();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}
