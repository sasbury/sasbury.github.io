//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class ProgExample extends JPanel
implements ActionListener
{
    JProgressBar bar;
    JButton go;
    Timer ticker;
    
    public ProgExample()
    {
        go = new JButton("Go");
        go.addActionListener(this);
        add(go);
    
        bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(0);
        
        ticker = new Timer(20,this);
        ticker.setRepeats(true);
        ticker.stop();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == go)
        {
            int result;
            Object msg[];
            Object options[];
            
            msg = new Object[2];
            
            msg[0] = "Current Load Progress";
            msg[1] = bar;
            
            options = new Object[1];
            
            options[0] = "Cancel Load";
            
            bar.setValue(0);
            ticker.start();
            
            result = JOptionPane.showOptionDialog(this
                                         ,msg
                                         ,"Progress"
                                         ,JOptionPane.OK_CANCEL_OPTION
                                         ,JOptionPane.PLAIN_MESSAGE
                                         ,null
                                         ,options
                                         ,options[0]); 
            
            ticker.stop();
        }
        else if(e.getSource() == ticker)
        {
            int curProg = bar.getValue();
            
            if(curProg >= 99)
            {
                bar.setValue(100);
                ticker.stop();
                getToolkit().beep();
            }
            else
            {
                bar.setValue(curProg+1);
            }
        }
    }
   
    public Dimension getPreferredSize()
    {
        return new Dimension(100, 60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Progress");
        ProgExample panel = new ProgExample();
        
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

