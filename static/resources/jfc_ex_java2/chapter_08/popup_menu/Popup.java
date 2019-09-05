//Copyright 1998 John Wiley and Sons, Inc.

import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
 
public class Popup extends JComponent
implements ActionListener,PopupMenuListener
{
    JPopupMenu popup;

    public Popup()
    {        
        JMenuItem mi;

        popup = new JPopupMenu();

        mi = new JMenuItem("This");
        mi.addActionListener(this);
        mi.setActionCommand("This");
        popup.add(mi);

        mi = new JMenuItem("That");
        mi.addActionListener(this);
        mi.setActionCommand("That");
        popup.add(mi);

        mi = new JMenuItem("The Other");
        mi.addActionListener(this);
        mi.setActionCommand("The Other");
        popup.add(mi);

        popup.addPopupMenuListener(this);
        popup.setOpaque(true);
        popup.setLightWeightPopupEnabled(true);
        
        addMouseListener(new PopupMouser(popup));
    }
    
    public void paint(Graphics g)
    {
        Dimension size = getSize();
        g.setColor(Color.darkGray);
        g.fillRect(0,0,size.width,size.height);
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        System.out.println(command);
    }

    /* Popup Listener Methods*/
    public void popupMenuWillBecomeVisible(PopupMenuEvent e)
    {
        System.out.println("Popup will become visible.");
    }
    
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
    {
        System.out.println("Popup will become invisible.");
    }
    
    public void popupMenuCanceled(PopupMenuEvent e)
    {
        System.out.println("Popup cancelled.");
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(300, 300);
    }
    
    public static void main(String s[])
    {
        JFrame frame = new JFrame("Popup Example");
        Popup pup = new Popup();
        
        frame.setForeground(Color.black);
        frame.setBackground(Color.lightGray);
        frame.addWindowListener(new WindowCloser());
        frame.getContentPane().add(pup,"Center");
        
        frame.setSize(pup.getPreferredSize());
        frame.setVisible(true);
    }
}

class PopupMouser extends MouseAdapter
{
    JPopupMenu popup;
    
    public PopupMouser(JPopupMenu p)
    {
        popup = p;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger())
        { 
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public void mousePressed(MouseEvent e)
    {
        if (e.isPopupTrigger())
        { 
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
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
