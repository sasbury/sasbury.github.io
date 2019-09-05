//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class BorderExample extends JPanel
{
    public BorderExample()
    {
        Border border;
        JLabel label;
        ImageIcon icon = new ImageIcon("images/shapes.gif");
        
        setLayout(new GridLayout(6,2,10,10));
        
        //Raised bevel border
        
        label = new JLabel("Raised Bevel",JLabel.CENTER);
        border = new BevelBorder(BevelBorder.RAISED);
        label.setBorder(border);
        add(label);
        
        //Lowered bevel border
        
        label = new JLabel("Lowered Bevel",JLabel.CENTER);
        border = new BevelBorder(BevelBorder.LOWERED);
        label.setBorder(border);
        add(label);
        
        //Raised soft bevel border
        
        label = new JLabel("Raised Soft Bevel",JLabel.CENTER);
        border = new SoftBevelBorder(BevelBorder.RAISED);
        label.setBorder(border);
        add(label);
        
        //Lowered soft bevel border
        
        label = new JLabel("Lowered Soft Bevel",JLabel.CENTER);
        border = new SoftBevelBorder(BevelBorder.LOWERED);
        label.setBorder(border);
        add(label);
        
        //line border
        
        label = new JLabel("Blue Line",JLabel.CENTER);
        border = new LineBorder(Color.blue);
        label.setBorder(border);
        add(label);
        
        //etched border
        
        label = new JLabel("Etched",JLabel.CENTER);
        border = new EtchedBorder();
        label.setBorder(border);
        add(label);
        
        //titled border
        
        label = new JLabel("Titled",JLabel.CENTER);
        border = new TitledBorder("Title");
        label.setBorder(border);
        add(label);
        
        //title with bevel
        
        label = new JLabel("Titled with Bevel",JLabel.CENTER);
        border = new TitledBorder(new BevelBorder(BevelBorder.RAISED),"Title");
        ((TitledBorder)border).setTitlePosition(TitledBorder.ABOVE_TOP);
        ((TitledBorder)border).setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
        //title with title
        
        label = new JLabel("Titled with Titled",JLabel.CENTER);
        border = new TitledBorder(new TitledBorder("Title"),"Subtitle");
        ((TitledBorder)border).setTitlePosition(TitledBorder.BOTTOM);
        ((TitledBorder)border).setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
        //empty border
        
        label = new JLabel("Empty",JLabel.CENTER);
        border = new EmptyBorder(10,10,10,10);
        label.setBorder(border);
        add(label);
        
        //matte border with color
        
        label = new JLabel("Green Matte",JLabel.CENTER);
        border = new MatteBorder(10,5,10,5,Color.green);
        label.setBorder(border);
        add(label);
        
        //matte border with image
        
        label = new JLabel("Image Matte",JLabel.CENTER);
        border = new MatteBorder(10,10,10,10,icon);
        label.setBorder(border);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(500, 400);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Border Example");
        BorderExample panel = new BorderExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

