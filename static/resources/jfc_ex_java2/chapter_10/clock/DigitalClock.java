//Copyright 1998 John Wiley and Sons, Inc.


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.text.*;

public class DigitalClock extends JComponent
 implements ActionListener
{
	Timer ticker;
	boolean showSeconds;
	
	public DigitalClock()
	{
		showSeconds = false;
		ticker = new Timer(200,this);
		
		setDoubleBuffered(true);
		resume();
	}
	
	public void resume()
	{
		ticker.start();
	}
	
	public void suspend()
	{
		ticker.stop();	
	}
	
	public boolean running()
	{
		return ticker.isRunning();	
	}
	
	public void setShowSeconds(boolean tf)
	{
		showSeconds = tf;
		repaint();
	}
	
	public Dimension getPreferredSize()
	{
		Font theFont;
		Rectangle2D stringBounds=null;
		Dimension retVal;
        FontRenderContext frc = 
            new FontRenderContext(null, false, false);
		
		theFont = getFont();
		
		if(theFont != null)
		{
		    stringBounds = theFont.getStringBounds("00:00:00", frc);
	    }
	    
		if(stringBounds != null)
		{
			retVal = new Dimension((int) stringBounds.getWidth(),(int) stringBounds.getHeight());
		}
		else
		{
			retVal = new Dimension(75,25);	
		}
		
		return retVal;
	}
	
	public void paint(Graphics g)
	{
		String timeString;
		GregorianCalendar realDate;
		Font theFont;
		Dimension curSize;
		int h,m,s;
		Rectangle2D stringBounds=null;
		Dimension retVal;
        FontRenderContext frc = 
            new FontRenderContext(null, false, false);
		
		theFont = getFont();
	
		if((theFont != null) && isEnabled() && running())
		{
			realDate = new GregorianCalendar();
			h = realDate.get(Calendar.HOUR_OF_DAY);
			h = h%12;
			m = realDate.get(Calendar.MINUTE);
			s = realDate.get(Calendar.SECOND);
			
			timeString = ""+h;
			
			if(s%2==0) timeString+=":";
			else timeString+=".";
			
			if((m>0)&&(m<10)) timeString+="0";
			timeString += m;
			if(m==0) timeString+="0";
			
			if(showSeconds)
			{
				if(s%2==0) timeString+=":";
				else timeString+=".";
			
				if((s>0)&&(s<10)) timeString+="0";
				timeString += s;
				if(s==0) timeString+="0";
			}
			
			stringBounds = theFont.getStringBounds(timeString.replace(' ',':'), frc);
			
			curSize = getSize();
			
			g.setColor(getBackground());
			g.fillRect(0,0,curSize.width,curSize.height);
			
			g.setColor(getForeground());
			g.setFont(theFont);
			
			g.drawString(timeString,
				(curSize.width-((int)stringBounds.getWidth()))/2,
				(curSize.height + ((int)stringBounds.getHeight()))/2);
		}
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==ticker) repaint();
	}
}
