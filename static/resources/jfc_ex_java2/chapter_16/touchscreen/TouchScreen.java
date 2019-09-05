//Copyright 1998 John Wiley and Sons, Inc.

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TouchScreen extends JComponent
{
    protected Vector actList;
    protected ChangeListener changeList;
    protected String actionCmd;
    TouchScreenModel model;
    TouchScreenUI ui;
    Color hilightColor;

    public TouchScreen()
    {
        changeList = createChangeListener();
        actList = new Vector();
        hilightColor = Color.green;
    
        setModel(createDefaultModel());
    
        updateUI();
    }

    /**
     * Notification from the UIFactory that the L&F
     * has changed.
     */
    public void updateUI()
    {
        setUI(createDefaultUI());
    }


    /**
     * Returns a string that specifies the name of the L&F class
     * that renders this component.
     */
    public String getUIClassID()
    {
        return "TouchScreenUI";
    }

    public TouchScreenUI getUI()
    {
        return ui;
    }

    public void setUI(TouchScreenUI newUI)
    {
        TouchScreenUI oldUI = getUI();
    
        if(oldUI == newUI) return;//don't do anything
    
        if(oldUI != null) oldUI.uninstallUI(this);
    
        ui = newUI;
    
        if(ui != null) ui.installUI(this);
    
        invalidate();
    }

    public TouchScreenModel getModel()
    {
        return model;
    }

    public void setModel(TouchScreenModel newModel)
    {
        TouchScreenModel oldModel = getModel();
    
        if (oldModel != null)
        {
            oldModel.removeChangeListener(changeList);
        }
    
        model = newModel;
    
        if (newModel != null)
        {
            newModel.addChangeListener(changeList);
        }
    }

    public void setActionCommand(String cmd)
    {
        actionCmd = cmd;
    }

    public String getActionCommand()
    {
        return actionCmd;
    }

    //Use a vector to support multiple listeners
    public void addActionListener(ActionListener al)
    {
        if(!actList.contains(al)) actList.addElement(al);
    }

    public void removeActionListener(ActionListener al)
    {
        actList.addElement(al);
    }

    //Method to notify action listeners
    void notifyListeners()
    {
        int i,max;
        ActionEvent evt;
        ActionListener curListener;
    
        evt = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,actionCmd);
    
        max = actList.size();
    
        for(i=0;i<max;i++)
        {
            curListener = (ActionListener) actList.elementAt(i);
        
            curListener.actionPerformed(evt);
        }
    }

    /*
     * Hilight color accessors
     */
    public void setHilightColor(Color c)
    {
        hilightColor = c;
    }

    public Color getHilightColor()
    {
        return hilightColor;
    }

    /*
     * Methods delegated to the model
     */

    public void setImage(Image i)
    {
        if(model != null) model.setImage(i);
    }

    public Image getImage()
    {
        Image retVal = null;
    
        if(model != null) retVal = model.getImage();
    
        return retVal;
    }

    public void addRegion(Shape s)
    {
        if(model != null) model.addRegion(s);
    }

    public void removeRegion(Shape s)
    {
        if(model != null) model.removeRegion(s);
    }

    public void selectRegion(Shape s)
    {
        if(model!=null) model.selectRegion(s);
    }

    public void selectRegionForPoint(Point p)
    {
        if(model!=null) model.selectRegionForPoint(p);
    }

    public Shape getSelectedRegion()
    {
        Shape retVal = null;
    
        if(model != null) retVal = model.getSelectedRegion();
    
        return retVal;
    }

    public Enumeration regions()
    {
        Enumeration retVal = null;
    
        if(model != null) retVal = model.regions();
    
        return retVal;
    }

    /*
     * Methods to delegate to the ui object
     */
    public void paint(Graphics g)
    {
        if (ui != null)
        {
            ui.paint(g, this);
        }
    
        super.paint(g);  // paint children if any
    }

    public Dimension getPreferredSize()
    {
        Dimension retVal = null;
    
        if(ui != null) retVal = ui.getPreferredSize(this);
        else retVal = new Dimension();
    
        return retVal;
    }

    public Dimension getMinimumSize()
    {
        Dimension retVal = null;
    
        if(ui != null) retVal = ui.getMinimumSize(this);
        else retVal = new Dimension();
    
        return retVal;
    }

    public Dimension getMaximumSize()
    {
        Dimension retVal = null;
    
        if(ui != null) retVal = ui.getMaximumSize(this);
        else retVal = new Dimension();
    
        return retVal;
    }

    /*
     * Methods to help subclasses.
     */
    protected ChangeListener createChangeListener()
    {
        return new DefTSChangeListener(this);
    }

    protected TouchScreenModel createDefaultModel()
    {
        return new TouchScreenModelImp();
    }

    protected TouchScreenUI createDefaultUI()
    {
        return BasicTouchScreenUI.createUI(this);
    }
}

class DefTSChangeListener implements ChangeListener
{
    TouchScreen screen;

    public DefTSChangeListener(TouchScreen ts)
    {
        screen = ts;
    }

    public void stateChanged(ChangeEvent e)
    {
        if(screen != null)
        {
            TouchScreenModel mod;
            mod = screen.getModel();

            screen.repaint();
        
            if(mod.getSelectedRegion() != null)
            {
                screen.notifyListeners();
            }
        }
    }
}

