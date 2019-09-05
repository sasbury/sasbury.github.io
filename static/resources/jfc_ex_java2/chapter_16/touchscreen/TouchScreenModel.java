//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public interface TouchScreenModel
{
    public void setImage(Image i);
    public Image getImage();
    
    public void addRegion(Shape s);
    public void removeRegion(Shape s);
    public void selectRegion(Shape s);
    public void selectRegionForPoint(Point p);
    public Shape getRegionForPoint(Point p);
    public Shape getSelectedRegion();
    public Enumeration regions();
    
    public void addChangeListener(ChangeListener x);
    public void removeChangeListener(ChangeListener x);
}
