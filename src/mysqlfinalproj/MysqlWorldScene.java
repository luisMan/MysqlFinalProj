/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author luism
 */
public class MysqlWorldScene extends Scene {
 
    public MysqlWorldScene(Parent root,long w, long h, Color color) {
        super(root,w,h,color);
        
        
    }
    
    public double getCanvasWidth()
    {return this.getWidth();}
    public double getCanvasHeight()
    {return this.getHeight();}
    
    public Paint getCanvasColor()
    {return this.getFill();}
    
    public double getCanvasX()
    {return this.getX();}
    public double getCanvasY()
    {return this.getY();}
}
