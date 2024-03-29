//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

class ListItem
{
    protected Icon icon;
    protected String title;
    
    public ListItem(String t,Icon i)
    {
        icon = i;
        title = t;
    }
    
    public Icon getIcon()
    {
        return icon;
    }
    
    public String getTitle()
    {
        return title;
    }
}
