//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class SelTable extends JPanel
implements ListSelectionListener
{
    JTable table;
        
    public SelTable(String startPath) 
    {
        FileSystemTableModel model;
        File root;
        Font f;
        ListSelectionModel listMod;
        
        setLayout(new BorderLayout());
        
        root = new File(startPath);
        model = new FileSystemTableModel(root);
        
        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        
        table.setCellSelectionEnabled(true);
        
        table.setIntercellSpacing(new Dimension(5,3));
        table.setGridColor(Color.green);
        
        add(new JScrollPane(table)
            ,"Center");
            
        listMod = table.getSelectionModel();
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMod.addListSelectionListener(this);
        
        listMod = table.getColumnModel().getSelectionModel();
        listMod.addListSelectionListener(this);
    }
    
    public void valueChanged(ListSelectionEvent e)
    {
        int i,maxRows;
        int j,maxCols;
        int[] selRows;
        int[] selCols;
        Object value;
        
        selRows = table.getSelectedRows();
        selCols = table.getSelectedColumns();
        
        maxRows = selRows.length;
        maxCols = selCols.length;
        
        for(i=0;i<maxRows;i++)
        {
            for(j=0;j<maxCols;j++)
            {
                if(j!=0) System.out.print(" ");
                
                value = table.getValueAt(selRows[i],selCols[j]);
                
                System.out.print(value);
            }
            
            System.out.println();
        }
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Table Selection Example");
        SelTable panel;
        
        if(s.length > 0)
            panel = new SelTable(s[0]);
        else
            panel = new SelTable("/");
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        frame.addWindowListener(new WindowCloser());
    }
}

class WindowCloser extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        Window win = e.getWindow();
        win.setVisible(false);
        System.exit(0);
    }
}

