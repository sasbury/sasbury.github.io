//Copyright 1998 John Wiley and Sons, Inc.

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class JDBCTable extends JPanel
implements ActionListener
{
    JTextField queryField;
    JDBCTableModel model;
    
    public JDBCTable() 
    {
        JTable table;
        File root;
        Font f;
        JPanel tmpPanel;
        
        setLayout(new BorderLayout());
        
        model = new JDBCTableModel("jdbc:odbc:northwind"
                                    ,"sun.jdbc.odbc.JdbcOdbcDriver"
                                    ,""
                                    ,"");
        
        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        
        add(new JScrollPane(table)
            ,"Center");
            
        tmpPanel = new JPanel();
        tmpPanel.add(new JLabel("Query: "));
        
        queryField = new JTextField("Enter your query here",30);
        queryField.addActionListener(this);
        queryField.selectAll();
        
        tmpPanel.add(queryField);
        
        add(tmpPanel,"South");
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String query = queryField.getText();
        
        model.executeQuery(query);
        queryField.selectAll();
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(450, 350);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("JDBC Table Example");
        JDBCTable panel = new JDBCTable();
        
        System.setErr(System.out);
        
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

