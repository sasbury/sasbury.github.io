//Copyright 1998 John Wiley and Sons, Inc.

import java.lang.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class SimpleTreeRenderer extends Object 
implements TreeCellRenderer
{ 
    JLabel theLabel;
    Icon open;
    Icon closed;
    Icon leaf;
    
    public SimpleTreeRenderer()
    {
        theLabel = new JLabel();
        theLabel.setOpaque(true);
        
        open = new ImageIcon("images/check.gif");
        closed = new ImageIcon("images/ex.gif");
        leaf = new ImageIcon("images/shapes.gif");
    }
    
    public Component getTreeCellRendererComponent(JTree tree,
                            Object value,
                            boolean selected,
                            boolean expanded,
                            boolean isleaf,
                            int row,
                            boolean hasFocus)

    {
        theLabel.setFont(tree.getFont());
        
        if(selected)
        {
            theLabel.setForeground(tree.getBackground());
            theLabel.setBackground(tree.getForeground());
        }
        else
        {
            theLabel.setBackground(tree.getBackground());
            theLabel.setForeground(tree.getForeground());
        }
        
        if(isleaf)
        {
            theLabel.setIcon(leaf);
        }
        else if(expanded)
        {
            theLabel.setIcon(open);
        }
        else
        {
            theLabel.setIcon(closed);
        }
        
        theLabel.setText(value.toString());
        
        return theLabel;
    }
}

