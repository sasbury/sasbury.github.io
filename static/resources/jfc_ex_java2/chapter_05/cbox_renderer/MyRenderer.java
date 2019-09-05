//Copyright 1998 John Wiley and Sons, Inc.

import java.lang.*;
import javax.swing.*;
import java.awt.*;

public class MyRenderer extends Object 
implements ListCellRenderer
{ 
    ImageIcon checkIcon;
    ImageIcon unCheckIcon;
    JLabel theLabel;
    
    public MyRenderer()
    {
        checkIcon = new ImageIcon("images/check.gif");
        unCheckIcon = new ImageIcon("images/ex.gif");
        theLabel = new JLabel();
        theLabel.setOpaque(true);
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
            theLabel.setIcon(checkIcon);
        }
        else
        {
            theLabel.setForeground(Color.black);
            theLabel.setIcon(unCheckIcon);
        }
        
        theLabel.setText(value.toString());
        
        return theLabel;
    }
}

