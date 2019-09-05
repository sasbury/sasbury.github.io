//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class TBorderExample extends JPanel
{
    public TBorderExample()
    {
        TitledBorder border;
        JLabel label;
        
        setLayout(new GridLayout(6,3,5,5));
        
        //AboveTop left
        
        label = new JLabel("Above Top, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_TOP);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //AboveTop center
        
        label = new JLabel("Above Top, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_TOP);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //AboveTop right
        
        label = new JLabel("Above Top, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_TOP);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
       //Top left
        
        label = new JLabel("Top, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //Top center
        
        label = new JLabel("Top, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //Top right
        
        label = new JLabel("Top, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
        //BelowTop left
        
        label = new JLabel("Below Top, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_TOP);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //BelowTop center
        
        label = new JLabel("Below Top, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_TOP);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //BelowTop right
        
        label = new JLabel("Below Top, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_TOP);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
        //AboveBottom left
        
        label = new JLabel("Above Bottom, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //AboveBottom center
        
        label = new JLabel("Above Bottom, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //AboveBottom right
        
        label = new JLabel("Above Bottom, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.ABOVE_BOTTOM);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
       //Bottom left
        
        label = new JLabel("Bottom, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BOTTOM);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //Bottom center
        
        label = new JLabel("Bottom, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BOTTOM);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //Bottom right
        
        label = new JLabel("Bottom, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BOTTOM);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
        
        //BelowBottom left
        
        label = new JLabel("Below Bottom, Left",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_BOTTOM);
        border.setTitleJustification(TitledBorder.LEFT);
        label.setBorder(border);
        add(label);
        
        //BelowBottom center
        
        label = new JLabel("Below Bottom, Center",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_BOTTOM);
        border.setTitleJustification(TitledBorder.CENTER);
        label.setBorder(border);
        add(label);
        
        //BelowBottom right
        
        label = new JLabel("Below Bottom, Right",JLabel.CENTER);
        border = new TitledBorder("Title");
        border.setTitlePosition(TitledBorder.BELOW_BOTTOM);
        border.setTitleJustification(TitledBorder.RIGHT);
        label.setBorder(border);
        add(label);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(450, 500);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Titled Border Example");
        TBorderExample panel = new TBorderExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

