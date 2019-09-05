//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TouchScreenModelImp implements TouchScreenModel
{
    protected Image img;
    protected Vector regions;
    protected Vector changeList;
    protected Shape selectedRegion;
    
    public TouchScreenModelImp()
    {
        regions = new Vector();
        changeList = new Vector();
    }
    
    public void setImage(Image i)
    {
        if(img != i)
        {
            img = i;
            notifyListeners();
        }
    }
    
    public Image getImage()
    {
        return img;
    }
    
    public void addRegion(Shape s)
    {
        if(! regions.contains(s)) regions.addElement(s);
    }
    
    public void removeRegion(Shape s)
    {
        regions.removeElement(s);
    }
    
    public void selectRegion(Shape s)
    {
        if((s == null) || regions.contains(s))
        {
            selectedRegion = s;
            notifyListeners();
        }
    }
    
    public void selectRegionForPoint(Point p)
    {
        selectRegion(getRegionForPoint(p));
    }
    
    public Shape getRegionForPoint(Point p)
    {
        Enumeration regions = regions();
        Shape curRegion=null;
        Rectangle bounds;
        
        if(p == null) return null;
        
        while(regions.hasMoreElements())
        {
            curRegion = (Shape) regions.nextElement();
            
            if(curRegion instanceof Polygon)
            {
                if(((Polygon)curRegion).contains(p))
                {
                    break;
                }
            }
            else
            {
                bounds = curRegion.getBounds();
                if(bounds.contains(p))
                {
                    break;
                }
                else
                {
                    curRegion = null;
                }
            }
        }
        
        return curRegion;
    }
    
    public Shape getSelectedRegion()
    {
        return selectedRegion;
    }
    
    public Enumeration regions()
    {
        return regions.elements();
    }
    
    /*
     * use a vector to support multiple listeners
     */
    public void addChangeListener(ChangeListener cl)
	{
		if(!changeList.contains(cl)) changeList.addElement(cl);
	}
	
    public void removeChangeListener(ChangeListener cl)
	{
		changeList.removeElement(cl);
	}
	
    protected void notifyListeners()
    {
        ChangeEvent evt;
        int i,max;
        ChangeListener curListener;
        
        evt = new ChangeEvent(this);
        
        max= changeList.size();
        
        for(i=0;i<max;i++)
        {
            curListener = (ChangeListener) changeList.elementAt(i);
            curListener.stateChanged(evt);
        }
    }
}
