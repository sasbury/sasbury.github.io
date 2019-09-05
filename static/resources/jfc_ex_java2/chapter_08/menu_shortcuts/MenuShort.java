//Copyright 1998 John Wiley and Sons, Inc.


import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MenuShort extends JPanel
implements ActionListener
{
    JTextField status;
    
    public MenuShort(JFrame frm)
    {
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Levels");
        JMenuItem item;
        
        menu.setMnemonic('l');
        
        item = new JMenuItem("Primary");
        item.setMnemonic('p');
        item.addActionListener(this);
        item.setActionCommand("Primary");
        menu.add(item);
        
        item = new JMenuItem("Secondary");
        item.setMnemonic('s');
        item.addActionListener(this);
        item.setActionCommand("Secondary");
        menu.add(item);
        
        item = new JMenuItem("Tertiary");
        item.setMnemonic('t');
        item.addActionListener(this);
        item.setActionCommand("Tertiary");
        menu.add(item);
        
        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        item.addActionListener(this);
        item.setActionCommand("Quit");
        menu.add(item);
        
        bar.add(menu);
        
        frm.setJMenuBar(bar);
        
        status = new JTextField(20);
        status.requestFocus();
        add(status);
        
        setStatus("Hello World");
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        setStatus(evt.getActionCommand());
        
        if(evt.getActionCommand().equals("Quit"))
        {
            System.exit(0);
        }
    }
    
    public void setStatus(String str)
    {
        status.setText(str);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 200);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Shortcut Example");
        MenuShort panel = new MenuShort(frame);
        
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
