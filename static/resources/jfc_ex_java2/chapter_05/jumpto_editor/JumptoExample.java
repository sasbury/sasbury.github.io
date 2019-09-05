//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JumptoExample extends JPanel
{
    JComboBox addBox;
    JumptoFieldEditor addEditor;

    public JumptoExample() 
    {
        Font f;
        
        f = new Font("Serif",Font.PLAIN,18);
        setFont(f);
        setLayout(new BorderLayout());
        
        addBox = new JComboBox();
        addBox.setFont(f);
 
        // Sets the maximum row count.  
        // If there are more than ten rows 
        // then the JComboBox will add a scroll bar.
        addBox.setMaximumRowCount(10);
        
        addBox.setRenderer(new SimpleRenderer());

        //Create the new editor
        addEditor = new JumptoFieldEditor(addBox);
        addEditor.getEditorComponent().setFont(f);

        addBox.setEditor(addEditor);
        addBox.setEditable(true);
        
        addBox.addItem("Brad");
        addBox.addItem("Scott");
        addBox.addItem("Stephen");

        addBox.setSelectedIndex(0);
        add(addBox,"Center");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(150,60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Jumping ComboBox Example");
        JumptoExample panel = new JumptoExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}
