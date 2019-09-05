//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Timers extends JPanel 
implements ActionListener
{
    JTextField delayField;
    JCheckBox repeat;
    Timer clock;
    
    final static String SET="set";
    final static String ALARM="alarm";
    
    public Timers()
    {
        JLabel label;
        GridLayout grid;
        
        setBackground(Color.lightGray);
        
        grid = new GridLayout(1,3,10,10);
        setLayout(grid);

        label = new JLabel("Delay (ms)");
        add(label);

        delayField = new JTextField();
        delayField.addActionListener(this);
        delayField.setActionCommand(SET);
        add(delayField);

        repeat = new JCheckBox("Repeat");
        repeat.addActionListener(this);
        repeat.setActionCommand(SET);
        add(repeat);
        
        clock = new Timer(1000,this);
        clock.setRepeats(false);
        clock.stop();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String command;
        
        command = e.getActionCommand();
        
        if (SET.equals(command))
        {
            int delayTime;
            
            delayTime = Integer.parseInt(delayField.getText());
            
            clock.stop();
            clock.setDelay(delayTime);
            clock.setInitialDelay(delayTime);
            
            if(repeat.isSelected())
            {
                clock.setRepeats(true);
            }
            else
            {
                clock.setRepeats(false);
            }
            
            clock.start();
            
            delayField.selectAll();
        }
        else if(e.getSource() == clock)
        {
            System.out.println("ALARM!");
            getToolkit().beep();
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 50);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Timers");
        Timers panel = new Timers();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
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
