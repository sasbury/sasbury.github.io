//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ButtonModels extends JPanel 
implements ActionListener, ChangeListener
{
    ButtonModel theModel;
    JCheckBox disableSwitch;
    JCheckBox armedSwitch;
    JCheckBox selectedSwitch;
    JCheckBox pressedSwitch;
    JButton exampleButton;
    
    public ButtonModels()
    {
        JPanel tmpPanel = new JPanel();
        GridLayout grid = new GridLayout(4,1,10,5);
        
        setLayout(new BorderLayout());
        /*
         * Turn on buffering to reduce flicker.
         */
        setDoubleBuffered(true);
        
        tmpPanel.setLayout(grid);
        
        armedSwitch = new JCheckBox("Armed");
        armedSwitch.addActionListener(this);
        tmpPanel.add(armedSwitch);
        
        disableSwitch = new JCheckBox("Disabled");
        disableSwitch.addActionListener(this);
        tmpPanel.add(disableSwitch);
        
        selectedSwitch = new JCheckBox("Selected");
        selectedSwitch.addActionListener(this);
        tmpPanel.add(selectedSwitch);
        
        pressedSwitch = new JCheckBox("Pressed");
        pressedSwitch.addActionListener(this);
        tmpPanel.add(pressedSwitch);
        
        add(tmpPanel,"West");
        
        tmpPanel = new JPanel();
        
        exampleButton = new JButton("Example");
        theModel = exampleButton.getModel();
        theModel.addChangeListener(this);
        tmpPanel.add(exampleButton);
        
        tmpPanel.add(new JButton("PlaceHolder"));

        add(tmpPanel,"Center");
    }
    
    //Handle model changes
    public void stateChanged(ChangeEvent e)
    {
        disableSwitch.setSelected(!theModel.isEnabled());
        armedSwitch.setSelected(theModel.isArmed());
        selectedSwitch.setSelected(theModel.isSelected());
        pressedSwitch.setSelected(theModel.isPressed());
    }
    
    //Handle changes to a checkbox
    public void actionPerformed(ActionEvent e)
    {
        theModel.setEnabled(!disableSwitch.isSelected());
        theModel.setArmed(armedSwitch.isSelected());
        theModel.setSelected(selectedSwitch.isSelected());
        theModel.setPressed(pressedSwitch.isSelected());
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(200, 120);
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
        JFrame frame = new JFrame("Button Models");
        ButtonModels panel = new ButtonModels();
        
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
