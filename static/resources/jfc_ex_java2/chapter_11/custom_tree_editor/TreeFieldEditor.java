//Copyright 1998 John Wiley and Sons, Inc.


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

public class TreeFieldEditor 
implements TreeCellEditor, ActionListener
{
    JTextField theField;
    JTree theTree;
    Vector listeners;
    
    public TreeFieldEditor()
    {
        listeners = new Vector();
        theField = new JTextField();
        theField.addActionListener(this);
    }

    public Component getTreeCellEditorComponent(JTree tree,
                                              Object value,
                                              boolean isSelected,
                                              boolean expanded,
                                              boolean leaf,
                                              int row)
    {
        theTree = tree;
    
        theField.setFont(tree.getFont());
        theField.setText(value.toString());
        theField.selectAll();
        
        return theField;
    }
    
    public Object getCellEditorValue()
    {
		return theField.getText();
    }

    public boolean shouldSelectCell(EventObject anEvent)
    {
        theField.selectAll();
        theField.requestFocus();
        
        return true;
    }
    
    public boolean isCellEditable(EventObject anEvent)
    {
        boolean retVal = false;
        
        if(anEvent instanceof MouseEvent)
        {
            MouseEvent me = (MouseEvent) anEvent;
            
            if(me.getClickCount()==2)
            {
                retVal = true;
            }
        }
        
        return retVal;
    }

    public boolean stopCellEditing()
    {
        fireEditingStopped();

        return true;
    }
    
    public void cancelCellEditing()
    {
	    fireEditingCanceled();
    }
    
    public void addCellEditorListener(CellEditorListener l)
    {
        if(!listeners.contains(l))
            listeners.addElement(l);
    }

    public void removeCellEditorListener(CellEditorListener l)
    {
        listeners.removeElement(l);
    }

    protected void fireEditingStopped()
    {
        int i,max;
        CellEditorListener curL;
        ChangeEvent evt;
        
        max = listeners.size();
	    
	    evt = new ChangeEvent(this);
        
	    for (i = 0;i < max; i++)
        {
            curL = (CellEditorListener)listeners.elementAt(i);
            curL.editingStopped(evt);
	    }
    }

    protected void fireEditingCanceled()
    {
        int i,max;
        CellEditorListener curL;
        ChangeEvent evt;
        
        max = listeners.size();
	    
	    evt = new ChangeEvent(this);
        
	    for (i = 0;i < max; i++)
        {
            curL = (CellEditorListener)listeners.elementAt(i);
            curL.editingCanceled(evt);
	    }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String value;
        DefaultMutableTreeNode node=null;
        Object selItem;
        TreePath path;
        
        value = theField.getText();
        path = theTree.getSelectionPath();
        selItem = path.getLastPathComponent();
        
        if(selItem instanceof DefaultMutableTreeNode)
            node = (DefaultMutableTreeNode)selItem;
            
        if(node != null)
        {
            node.setUserObject(value);
        }
        
        fireEditingStopped();
    }
}

