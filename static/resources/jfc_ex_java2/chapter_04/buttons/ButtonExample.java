//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ButtonExample extends JPanel
{
    public ButtonExample()
    {
        ImageIcon buttonImage=new ImageIcon("images/b.gif");
        ImageIcon pressImage=new ImageIcon("images/p.gif");
        ImageIcon selImage=new ImageIcon("images/p.gif");
        ImageIcon disabledImage=new ImageIcon("images/d.gif");
        ImageIcon rolloverImage=new ImageIcon("images/r.gif");
        ImageIcon selrolloverImage=new ImageIcon("images/l.gif");
        AbstractButton button;
        Font font;
        
        //Make the default font bigger, and buffer the example
        font = new Font("Serif",Font.PLAIN,16);
        setFont(font);
        setDoubleBuffered(true);
        
        setLayout(new GridLayout(1,3,5,5));

        button = new JButton();
        button.setIcon(buttonImage);
        button.setPressedIcon(pressImage);
        button.setRolloverIcon(rolloverImage);
        add(button);
        
        button = new JButton();
        button.setIcon(buttonImage);
        button.setDisabledIcon(disabledImage);
        button.setEnabled(false);
        add(button);
        

        button = new JToggleButton();
        button.setIcon(buttonImage);
        button.setSelectedIcon(selImage);
        button.setRolloverIcon(rolloverImage);
        button.setRolloverSelectedIcon(selrolloverImage);
        add(button);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 70);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Button Example");
        ButtonExample panel = new ButtonExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

