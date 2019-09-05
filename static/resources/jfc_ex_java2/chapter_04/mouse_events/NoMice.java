//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

 
class NoMouseCanvas extends JComponent
{
	public NoMouseCanvas()
	{
		enableMouse();
	}

	public void paint(Graphics g)
	{	
		Rectangle bounds = getBounds();
		g.setColor(Color.blue);
		g.fillRect(0,0,bounds.width,bounds.height);
	}
	
	public void processMouseEvent(MouseEvent evt)
	{
		System.out.println("Got mouse event");
		System.out.println("\tx="+evt.getX());
		System.out.println("\ty="+evt.getY());
		System.out.println("\tclicks="+evt.getClickCount());
		super.processMouseEvent(evt);
	}
	
	public void processMouseMotionEvent(MouseEvent evt)
	{
		System.out.println("Got mouse motion event");
		System.out.println("\tx="+evt.getX());
		System.out.println("\ty="+evt.getY());
		super.processMouseEvent(evt);
	}
	
	public void enableMouse()
	{
		System.out.println("\t*** Enabling Mouse ***");
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}
	
	public void disableMouse()
	{
		System.out.println("\t*** Disabling Mouse ***");
		disableEvents(AWTEvent.MOUSE_EVENT_MASK);
		disableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}
}

class NoMouseListenerCanvas extends JComponent
implements MouseListener, MouseMotionListener
{
	public NoMouseListenerCanvas()
	{
		enableMouse();
	}

	public void paint(Graphics g)
	{	
		Rectangle bounds = getBounds();
		g.setColor(Color.red);
		g.fillRect(0,0,bounds.width,bounds.height);
	}
	
	public void mouseClicked(MouseEvent evt)
	{
		System.out.println("\tMouse clicked");
	}
	
	public void mouseEntered(MouseEvent evt)
	{
		System.out.println("\tMouse entered");
	}
	
	public void mouseExited(MouseEvent evt)
	{
		System.out.println("\tMouse exited");
	}
	
	public void mousePressed(MouseEvent evt)
	{
		System.out.println("\tMouse pressed");
	}
	
	public void mouseReleased(MouseEvent evt)
	{
		System.out.println("\tMouse released");
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		System.out.println("\tMouse dragged");
	}
	
	public void mouseMoved(MouseEvent evt)
	{
		System.out.println("\tMouse moved");
	}
  
	public void enableMouse()
	{
		System.out.println("\t*** Enabling Mouse Listener***");
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void disableMouse()
	{
		System.out.println("\t*** Disabling Mouse Listener***");
		removeMouseListener(this);
		removeMouseMotionListener(this);
	}
}

public class NoMice extends JPanel
implements ItemListener
{
	NoMouseCanvas target;
	NoMouseListenerCanvas target2;
	
	public NoMice()
	{
		JPanel tmpPanel = new JPanel();
		JCheckBox onOff = new JCheckBox("Enabled",true);
		
		target = new NoMouseCanvas();
		target2 = new NoMouseListenerCanvas();
		
		onOff.addItemListener(this);
		
		tmpPanel.add(onOff);
		
		setLayout(new BorderLayout());
		add(tmpPanel,"South");
		
		tmpPanel = new JPanel();
		tmpPanel.setLayout(new GridLayout(1,2));
		tmpPanel.add(target);
		tmpPanel.add(target2);
		add(tmpPanel,"Center");
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			target.enableMouse();
			target2.enableMouse();
		}
		else
		{
			target.disableMouse();
			target2.disableMouse();
		}
	}
	
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(200, 100);
	}
	
	public static void main(String s[])
	{
		JFrame frame = new JFrame("Mouse Events");
		NoMice panel = new NoMice();
		
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
