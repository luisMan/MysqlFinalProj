/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;



/**
 *
 * @author luism this class will serve as viewport focus point on character movements
 * this will help us render images on an unique way to reduce memory usage
 * 
 */
public class SqlViewPort {
    private float viewPortX;
    private float viewPortY;
    private float viewPortVelocity;
    private long viewPort_minWidth;
    private long viewPort_minHeight;
    private long viewPort_maxWidth;
    private long viewPort_maxHeight;
    
    public SqlViewPort(float x, float y, long minw, long minh, long maxw, long maxh, float velocity)
    { 
        this.viewPortX=x; this.viewPortY=y;
        this.viewPortVelocity=velocity;
        this.viewPort_minWidth = minw;
        this.viewPort_minHeight = minh;
        this.viewPort_maxWidth=maxw;
        this.viewPort_maxHeight=maxh;

    }
    
    
    //setters
    public void setViewPortX(float x)
    {this.viewPortX = x;}
    public void setViewPortY(float y)
    {this.viewPortY = y;}
    public void setViewPortVelocity(float v)
    {this.viewPortVelocity=v;}
    public void setMinViewPortWidth(long w)
    {this.viewPort_minWidth=w;}
    public void setMinViewPortHeight(long h)
    {this.viewPort_minHeight=h;}
    public void setMaxViewPortWidth(long w)
    {this.viewPort_maxWidth=w;}
    public void setMaxViewPortHeight(long h)
    {this.viewPort_maxHeight=h;}
    
    
    //getters
    public float getViewPortX()
    {return this.viewPortX;}
    public float getViewPortY()
    {return this.viewPortY;}
    public float getViewPortVelocity()
    {return this.viewPortVelocity;}
    public long getViewPortMinWidth()
    {return this.viewPort_minWidth;}
    public long getViewPortMinHeight()
    {return this.viewPort_maxHeight;}
    public long getViewPortMaxWidth()
    {return this.viewPort_maxWidth;}
    public long getViewPortMaxHeight()
    {return  this.viewPort_maxHeight;}
    
    public float getMidDistanceOfX()
    { return (viewPortX+this.viewPort_minWidth+this.viewPort_maxWidth) / 2;}
    public float getMidDistanceOfY()
    {return (this.viewPortY+this.viewPort_minHeight+this.viewPort_maxHeight)/2;}
    
    public void upDateViewPortToFollowXY(float x, float y)
    {
        //in this method we look at the view port and min-width max-width and min-height and max-width will change according to x and y update
        if(x>(getMidDistanceOfX()/2)+100)
        {
            //since we see that character is greater than the mid distance of viewport lets move it to follow character
            viewPortX+=viewPortVelocity;
        }
        if(x<(getMidDistanceOfX()/2)-100)
        {
            viewPortX-=viewPortVelocity;
        }
        
        if(y>((getMidDistanceOfY())/2)-100)
        {
            viewPortY += viewPortVelocity;
        }
        
        if(y<(getMidDistanceOfY()/2)+100)
        {
            viewPortY -=viewPortVelocity;
        }
        
        if(viewPortX<0)
        {viewPortX=0;}
        if(viewPortY<0)
        {viewPortY=0;}
    }
    
    public void upDateViewPortXY(float x, float y)
    {
        if(viewPortX>(this.viewPort_maxWidth*-1) && viewPortX <= 0){
          viewPortX+=x;
        }else{
            viewPortX=0;
        }
        if(viewPortY>(this.viewPort_maxHeight*-1) && viewPortY<=0 ){
          viewPortY+=y;
        }else{
            viewPortY=0;
        }
    }
    
    
}
