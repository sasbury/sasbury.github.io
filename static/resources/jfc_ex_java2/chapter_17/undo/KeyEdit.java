//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.undo.*;

public class KeyEdit extends AbstractUndoableEdit
{
    char key;
    JTextField field;
    int index;
    
    public KeyEdit(JTextField t,char c,int i)
    {
        key = c;
        field = t;
        index = i;
    }
    
	public void undo() throws CannotUndoException
	{
	    super.undo();
	    
	    field.select(index,index+1);
	    field.replaceSelection("");
	    field.setCaretPosition(index);
	}
	
	public void redo() throws CannotRedoException
	{
	    super.redo();
	    field.setCaretPosition(index);
	    field.replaceSelection(""+key);
	}
}
