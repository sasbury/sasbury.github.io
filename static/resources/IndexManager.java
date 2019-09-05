package index;

import index.*;
import java.util.*;
import java.io.*;

import DebugLog;

public class IndexManager
{
	protected static Hashtable indices;
	protected static Hashtable loadtimes;
	
	public static long updateInterval = 0;//seconds

	static
	{
	    indices = new Hashtable();
	    loadtimes = new Hashtable();
	}
	
	public static HTMLIndex indexForDirectory(String dir,String rel)
	{
		HTMLIndex retVal = null;
		Object test = null;
		IndexLoader loader=null;
		
		if(dir == null) return null;
		
		synchronized(indices)
		{
		    test = indices.get(dir);
		
		    if(test == null)
		    {
		        loader = new IndexLoader(dir,rel,indices);
		        
		        indices.put(dir,loader);
		        loadtimes.put(dir,new Date());
  		    }
  		    else if(test instanceof HTMLIndex)
  		    {
	            retVal = (HTMLIndex) test;
  		        
  		        if(updateInterval>0)
  		        {
      		        try
      		        {
      		            Date now = new Date(),load;
      		            long nw,ld;
  		        
      		            load = (Date) loadtimes.get(dir);
  		        
      		            nw = now.getTime();
      		            ld = load.getTime();
  		            
  		                if(nw > (ld+(updateInterval*1000)))
      		            {
      		                if(retVal.indexNeedsRebuilding())
      		                {
          		                //reload
        		                loader = new IndexLoader(dir,rel,indices);
		        
        		                indices.put(dir,loader);
        		                loadtimes.put(dir,new Date());
          		                
          		                retVal = null;
      		                }
      		                else
      		                {
      		                    //update load time
      		                    loadtimes.put(dir,new Date());
      		                }
      		            }
      		        }
      		        catch(Exception exp)
      		        {
      		            retVal = (HTMLIndex) test;
      		            indices.put(dir,retVal);//just in case
      		            loadtimes.put(dir,new Date());
      		        }
  		        }
  		    }
  		    //else it is the index loader working...
		}
		        
		if(loader != null)
		{
		    if(loader.needsBuild())
		    {
		        //loader.start();
                loader.run();
  		        retVal = (HTMLIndex) indices.get(dir);
		    }
		    else
		    {
		        loader.load();
  		        retVal = (HTMLIndex) indices.get(dir);
		    }
		}    
		
		return retVal;
	}
}

class IndexLoader extends Object
{
    protected String dir;
    protected String rel;
    protected Hashtable indices;
    protected HTMLIndex newIndex;
    protected boolean buildIfNec;
    
    public IndexLoader(String d,String r,Hashtable holder)
    {
        dir = d;
        rel = r;
        indices = holder;
        buildIfNec = false;
        
        newIndex = new HTMLIndex(new File(dir));
    }
    
    public boolean needsBuild()
    {
        return newIndex.indexNeedsRebuilding();
    }
    
    public void run()
    {
        buildIfNec = true;
        load();
    }
    
    public void load()
    {
        if(dir == null) return;
        
        if(rel!=null) newIndex.setRelativePath(rel);
        
        newIndex.loadIndex(buildIfNec);
        
        synchronized(indices)
        {
            indices.put(dir,newIndex);
        }
    }
}
