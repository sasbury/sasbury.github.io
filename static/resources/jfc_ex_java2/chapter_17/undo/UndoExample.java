//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.undo.*;
import java.awt.event.*;

import java.awt.*;
import java.lang.*;

public class UndoExample extends JPanel
implements KeyListener,ActionListener
{
    JTextField field;
    UndoManager manager;
        
    public UndoExample()
    {
        JButton undo,redo;
        
        setBackground(Color.white);
        
        manager = new UndoManager();
        
        field = new JTextField(15);
        field.addKeyListener(this);
        
        undo = new JButton("Undo");
        undo.setActionCommand("undo");
        undo.addActionListener(this);
        
        redo = new JButton("Redo");
        redo.setActionCommand("redo");
        redo.addActionListener(this);
        
        add(field);
        add(undo);
        add(redo);
    }
   
    public void keyTyped(KeyEvent evt)
    {
    }
    
    public void keyPressed(KeyEvent evt)
    {
    }
    
    public void keyReleased(KeyEvent evt)
    {
        KeyEdit edit;
        int ind;
        
        ind = field.getCaretPosition();
        
        edit = new KeyEdit(field,evt.getKeyChar(),ind-1);
        
        manager.addEdit(edit);
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String cmd = evt.getActionCommand();
        
        try
        {
            if("undo".equals(cmd))
            {
                manager.undo();
            }
            else
            {
                manager.redo();
            }
        }
        catch(Exception exp)
        {
            System.out.println("Error during undo: "+exp);
        }
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(400,70);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Undo");
        UndoExample panel = new UndoExample();
        
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

