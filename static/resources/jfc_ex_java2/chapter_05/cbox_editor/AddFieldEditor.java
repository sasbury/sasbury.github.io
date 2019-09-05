//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFieldEditor extends Object 
implements ComboBoxEditor
{
    JTextField theField;
	Object curItem;

    public AddFieldEditor()
    {
        theField = new JTextField();
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

    public Object getItem()
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
}

