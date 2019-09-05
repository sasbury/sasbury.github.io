//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.sql.*;
import javax.swing.table.*; 
import javax.swing.event.*; 

public class JDBCTableModel extends AbstractTableModel
{
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    Vector names; 
    Vector types; 
    Vector data;
    ResultSetMetaData metaData;

    public JDBCTableModel(String url, String driverName,
                       String user, String passwd)
    {
        data = new Vector();
        types = new Vector();
        names = new Vector();
            
        try
        {
            //load the driver
            Class.forName(driverName);

            connection = 
                    DriverManager.getConnection(url, user, passwd);
                    
            statement = connection.createStatement();
        }
        catch (Exception exp)
        {
            System.out.println("Error connecting: "+exp);
        }    
     }

    public void executeQuery(String query)
    {
        int i,max;
        Vector rowData;
        int curType;
        
        try
        {
            data = new Vector();
            types = new Vector();
            names = new Vector();
        
            resultSet = statement.executeQuery(query);

            metaData = resultSet.getMetaData();

            max =  metaData.getColumnCount(); 
            
            //get the column names
            for(i=0;i<max;i++)
            { 
                //adjust for meta data index start at 1
                names.addElement(metaData.getColumnLabel(i+1));
            }
            
            //get the column types
            for(i=0;i<max;i++)
            {
                try
                {
                    curType = metaData.getColumnType(i+1);
                }
                catch (SQLException e)
                {
                    //make it a string if we can't get it
                    curType = -1;
                }

                switch(curType)
                {
                    case Types.CHAR:
                    case Types.VARCHAR:
                    case Types.LONGVARCHAR:
                        types.addElement(String.class);
                        break;
                        
                    case Types.TINYINT:
                    case Types.SMALLINT:
                    case Types.INTEGER:
                        types.addElement(Integer.class);
                        break;

                    case Types.BIGINT:
                        types.addElement(Long.class);
                        break;

                    case Types.FLOAT:
                    case Types.DOUBLE:
                        types.addElement(Double.class);
                        break;

                    case Types.DATE:
                        types.addElement(java.sql.Date.class);
                        break;

                    default:
                        types.addElement(Object.class);
                        break;
                }
            }

            //load the data
            while (resultSet.next())
            {
                rowData = new Vector();
                
                for (i=0;i<max;i++)
                {
	                rowData.addElement(resultSet.getObject(i+1));
                }
                
                data.addElement(rowData);
            }
            
            fireTableChanged(null);
        }
        catch (Exception exp)
        {
            System.out.println("Error performing query: "+exp);
            exp.printStackTrace();
        }    
    }

    public void close() throws SQLException
    {
        resultSet.close();
        statement.close();
        connection.close();
    }

    protected void finalize() throws Throwable
    {
        close();
        super.finalize();
    }

    public String getColumnName(int col)
    {
        String retVal;
        
        retVal = (String) names.elementAt(col);
        
        if(retVal == null) retVal = "";
        
        return retVal;
    }

    public Class getColumnClass(int col)
    {
        Class retVal;
        
        retVal = (Class) types.elementAt(col);
        
        if(retVal == null) retVal = Object.class;
        
        return retVal;
    }

    public boolean isCellEditable(int row, int col)
    { 
        return false;
    }

    public int getColumnCount()
    {
        return names.size(); 
    }

    public int getRowCount()
    {
        return data.size();
    }

    public Object getValueAt(int row, int col)
    {
        Vector rowData = (Vector)data.elementAt(row);
        
        return rowData.elementAt(col);
    }

    public void setValueAt(Object value, int row, int col)
    {
        //This model is not editable.
    }
}
