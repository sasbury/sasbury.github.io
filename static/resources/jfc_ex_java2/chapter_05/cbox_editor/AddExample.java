//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddExample extends JPanel 
implements ActionListener
{
    JComboBox addBox;
    AddFieldEditor addEditor;

    public AddExample() 
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

        //Create the new editor
        addEditor = new AddFieldEditor();
        addEditor.getEditorComponent().setFont(f);

        // Set the combo box as an ActionListener for the editor
        addEditor.addActionListener(this);

        addBox.setEditor(addEditor);
        addBox.setEditable(true);
        
        addBox.addItem("Brad");
        addBox.addItem("Scott");
        addBox.addItem("Stephen");

        add(addBox,"Center");
    }

    public void actionPerformed(ActionEvent e)
    {
        String value;
        JTextField editorComp;
        
        editorComp = ((JTextField)addEditor.getEditorComponent());
        value = editorComp.getText();
        
	    addBox.addItem(value);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(100,60);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Adding to ComboBox Example");
        AddExample panel = new AddExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}
