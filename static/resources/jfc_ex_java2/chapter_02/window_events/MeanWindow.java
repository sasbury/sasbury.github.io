//Copyright 1998 John Wiley and Sons, Inc.

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

class MeanWindowCloser extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        Window win;
        Random gen = new Random();
        
        if((gen.nextInt()%5) == 0)
        {
            win = e.getWindow();
            win.setVisible(false);
            System.exit(0);
        }
    }
}


public class MeanWindow extends JFrame
{
    public static void main(String[] args)
    {
        MeanWindow win;
        
        win = new MeanWindow();
        win.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        win.addWindowListener(new MeanWindowCloser());
        win.getContentPane().add(new Label("Hello World"),"Center");
        
        win.pack();
        win.setVisible(true);
    }
}
