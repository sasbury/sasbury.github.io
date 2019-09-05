//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Echo extends JPanel 
implements KeyListener
{
    public Echo()
    {
        JTextField field;
        
        setBackground(Color.lightGray);
        
        field = new JTextField(25);
        field.addKeyListener(this);
        add(field);
    }
    
    public void keyPressed(KeyEvent e)
    {
        char character;
        int mods;
        String modText;
        
        character = e.getKeyChar();
        
        System.out.print(character+" - ");
        System.out.print(KeyEvent.getKeyText(e.getKeyCode()));
        System.out.print(" ");
        
        mods = e.getModifiers();
        
        if(mods != 0)
        {
            modText = KeyEvent.getKeyModifiersText(mods);
            System.out.print(modText);
            System.out.print(" ");
        }
        
        System.out.println("pressed.");
    }
    
    public void keyReleased(KeyEvent e)
    {
        char character;
        int mods;
        String modText;
        
        character = e.getKeyChar();
        
        System.out.print(character+" - ");
        System.out.print(KeyEvent.getKeyText(e.getKeyCode()));
        System.out.print(" ");
        
        mods = e.getModifiers();
        
        if(mods != 0)
        {
            modText = KeyEvent.getKeyModifiersText(mods);
            System.out.print(modText);
            System.out.print(" ");
        }
        
        System.out.println("released.");
    }
    
    public void keyTyped(KeyEvent e)
    {
        char character;
        int mods;
        String modText;
        
        character = e.getKeyChar();
        
        System.out.print(character+" - ");
        
        mods = e.getModifiers();
        
        if(mods != 0)
        {
            modText = KeyEvent.getKeyModifiersText(mods);
            System.out.print(modText);
            System.out.print(" ");
        }
        
        System.out.println("typed.");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 50);
    }

    public Dimension getMinimumSize()
    {
        return new Dimension(25, 25);
    }

    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Echo");
        Echo panel = new Echo();
        
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
