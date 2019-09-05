//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class TripleClicker extends MouseAdapter
{
    public void mouseClicked(MouseEvent e)
    {
        if(e.getClickCount() == 3)
        {
            JTable table = (JTable) e.getSource();
            FileSystemTableModel mod;
            FileSystemTableModel newMod;
            File curFile;
            int row;
            
            mod = (FileSystemTableModel) table.getModel();
            
            row = table.rowAtPoint(e.getPoint());
            
            curFile = (File) mod.getValueAt(row,-1);
            
            if(curFile.isDirectory())
            {
                newMod = new FileSystemTableModel(curFile);
                
                table.setModel(newMod);
                table.createDefaultColumnsFromModel();
            }
        }
    }
}

