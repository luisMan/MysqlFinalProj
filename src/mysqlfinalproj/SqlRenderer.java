/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author luism
 */
public class SqlRenderer extends Canvas{
    private SqlPane view;
    private MysqlWorldScene scene;
    private sqlThread mainThread;
    
    public SqlRenderer(SqlPane view, MysqlWorldScene scene)
    {this.view =view; this.scene =  scene; 
     this.setWidth(view.getViewPort().getViewPortMaxWidth()); 
     this.setHeight(view.getViewPort().getViewPortMaxHeight());
     
     mainThread = new sqlThread(view,this);
     mainThread.start();
     
    }
    
    public GraphicsContext getGraphic2D()
    {return this.getGraphicsContext2D();}
    
    
    
}
