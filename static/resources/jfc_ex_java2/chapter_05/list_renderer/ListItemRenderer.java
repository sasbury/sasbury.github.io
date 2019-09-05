//Copyright 1998 John Wiley and Sons, Inc.

import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class ListItemRenderer extends Object 
implements ListCellRenderer
{ 
    JLabel theLabel;
    
    public ListItemRenderer()
    {
        theLabel = new JLabel();
        theLabel.setOpaque(true);
    }
    
    public Component getListCellRendererComponent(JList list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus)

    {
        ListItem li=null;
        
        if(value instanceof ListItem) li = (ListItem) value;
        
        theLabel.setFont(list.getFont());
        
        if(isSelected)
        {
            theLabel.setBackground(list.getSelectionBackground());
            theLabel.setForeground(list.getSelectionForeground());
        }
        else
        {
            theLabel.setBackground(list.getBackground());
            theLabel.setForeground(list.getForeground());
        }
        
        if(li != null)
        {
            theLabel.setText(li.getTitle());
            theLabel.setIcon(li.getIcon());
        }
        else
        {
            theLabel.setText(value.toString());
        }
        
        return theLabel;
    }
}

