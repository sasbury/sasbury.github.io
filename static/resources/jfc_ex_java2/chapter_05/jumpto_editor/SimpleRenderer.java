//Copyright 1998 John Wiley and Sons, Inc.

import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class SimpleRenderer extends Object 
implements ListCellRenderer
{ 
    ImageIcon checkIcon;
    ImageIcon unCheckIcon;
    JLabel theLabel;
    
    public SimpleRenderer()
    {
        theLabel = new JLabel();
    }
    
    public Component getListCellRendererComponent(JList list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus)

    {
        theLabel.setFont(list.getFont());
        
        if(isSelected)
        {
            theLabel.setForeground(Color.blue);
        }
        else
        {
            theLabel.setForeground(Color.black);
        }
        
        theLabel.setText(value.toString());
        
        return theLabel;
    }
}

