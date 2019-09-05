//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class TextPaneExample extends JPanel
{
    public TextPaneExample()
    {
        JTextPane area;
        MutableAttributeSet normalStyle;
        MutableAttributeSet boldStyle;
        MutableAttributeSet smallStyle;
        MutableAttributeSet centerStyle;
        Document doc;
        
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
        setDoubleBuffered(true);
        
        area = new JTextPane();
        
        doc = area.getDocument();
        
        normalStyle = new SimpleAttributeSet();
        
        StyleConstants.setFontFamily(normalStyle,"SanSerif");
        StyleConstants.setFontSize(normalStyle,14);
        
        boldStyle = new SimpleAttributeSet(normalStyle);
        StyleConstants.setBold(boldStyle,true);
        StyleConstants.setFontSize(boldStyle,20);
        
        smallStyle = new SimpleAttributeSet(normalStyle);
        StyleConstants.setFontSize(smallStyle,10);
        
        centerStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerStyle
                                    ,StyleConstants.ALIGN_CENTER);
        
        area.setCaretPosition(0);
        area.setParagraphAttributes(centerStyle,true);
        area.insertIcon(new ImageIcon("images/logo.gif"));
        area.replaceSelection("\n");
        
        area.setCaretPosition(doc.getLength());
        area.setParagraphAttributes(centerStyle,true);
        area.setCharacterAttributes(boldStyle,false);
        area.replaceSelection("Attention\n\n");
        
        area.setCaretPosition(doc.getLength());
        area.setCharacterAttributes(normalStyle,true);
        area.replaceSelection("Free Stuff Available\n\n");
        
        area.setCaretPosition(doc.getLength());
        area.setCharacterAttributes(smallStyle,true);
        area.replaceSelection("One at this price.\n\n");
        
        area.setCaretPosition(doc.getLength());
        area.setParagraphAttributes(centerStyle,true);
        area.insertComponent(new JButton("Go There"));
        
        add(new JScrollPane(area),"Center");
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(300, 150);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("JTextPane Example");
        TextPaneExample panel = new TextPaneExample();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(panel,"Center");
        
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
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

