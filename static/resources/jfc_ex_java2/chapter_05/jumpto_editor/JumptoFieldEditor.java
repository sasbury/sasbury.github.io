//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JumptoFieldEditor extends KeyAdapter 
implements ComboBoxEditor, ActionListener
{
    JTextField theField;
    Object curItem;
    JComboBox box;
    
    public JumptoFieldEditor(JComboBox c)
    {
        box = c;
        theField = new JTextField();
        theField.addKeyListener(this);
        theField.addActionListener(this);
    }

    public  void setItem(Object anObject)
    {
        curItem = anObject;
        
        if(curItem != null)
        {
            String str;
            
            str= curItem.toString();
            theField.setText(str);
        }
        else
        {
            theField.setText("");
        }
    }

    public Component getEditorComponent()
    {
        return theField;
    }

    public  Object getItem()
    {
        Object retVal;
        
        if((curItem == null)||(curItem instanceof String)) 
        {
            retVal = theField.getText();
        }
        else
        {
            retVal = curItem;
        }
        
        return retVal;
    }

    public  void selectAll()
    {
        theField.selectAll();
    }

    // Pass the ActionListener on to the field
    public void addActionListener(ActionListener l)
    {
        theField.addActionListener(l);
    }
    
    public  void removeActionListener(ActionListener l)
    {
        theField.removeActionListener(l);
    }
    
    public void keyReleased(KeyEvent evt)
    {
        int curLen;
        String curVal;
        
        curVal = theField.getText();
        
        if(curVal != null) curLen = curVal.length();
        else curLen = 0;
       
        //ignore arrow keys, delete,
        //and the shift key
        if((evt.getKeyCode() == KeyEvent.VK_UP)
            ||(evt.getKeyCode() == KeyEvent.VK_DOWN)
            ||(evt.getKeyCode() == KeyEvent.VK_LEFT)
            ||(evt.getKeyCode() == KeyEvent.VK_RIGHT)
            ||(evt.getKeyCode() == KeyEvent.VK_SHIFT)
            ||(evt.getKeyCode() == KeyEvent.VK_DELETE)
            ||(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE))
        {
            return;
        }
        
        if(curLen > 0)
        {
            int i,max;
            String item;
            
            max = box.getItemCount();
            
            for(i=0;i<max;i++)
            {
                item = box.getItemAt(i).toString();
                
                if(item.startsWith(curVal))
                {
                    box.setSelectedIndex(i);
                    setItem(box.getSelectedItem());
                    theField.select(curLen,item.length());
                    break;
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String value;
        boolean shouldAdd=true;
        int i,max;
        String item;
        
        value = theField.getText();
        
        //Make sure the new value is not already
        //in the list **Updated for Java 2 version**
        
        max = box.getItemCount();
        
        for(i=0;i<max;i++)
        {
            item = box.getItemAt(i).toString();
            
            if(item.equals(value))
            {
                shouldAdd=false;
                break;
            }
        }
        
        if(shouldAdd)
        {
            box.addItem(value);
        }
    }
}

