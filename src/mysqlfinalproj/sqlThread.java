/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author luism
 */
public class sqlThread extends AnimationTimer{
    private SqlPane view;
    private SqlRenderer canvas;
    private GraphicsContext context;
    private final int FramePerSecond = 60; 
    
    public sqlThread(SqlPane v, SqlRenderer canvas)
    {this.view = v; this.canvas =  canvas;}
     
    
    @Override
    public void handle(long now) {
        try{
            //we will render good thins here
            context =  ((Canvas)canvas).getGraphicsContext2D();
            //lets refresh screen 
            context.setFill(Color.BLACK);
            context.fillRect(0, 0, view.getViewPort().getViewPortMaxWidth(), view.getViewPort().getViewPortMaxHeight());
            
            
            context.setFill(Color.YELLOW);
            context.fillText("Use W=up, A=left, D=Right, S=down to move Scene ViewPort", 0, 50);
            context.fillText("Viewport x = "+view.getViewPort().getViewPortX()+" y = "+view.getViewPort().getViewPortY(),0,70);
            context.fillText("Left Click create new Table",0,90);
            context.fillText("C + Right Click creates constraints",0,110);
            context.fillText("Mouse X = "+view.getMouseEvent().getMouseX()+" Y = "+view.getMouseEvent().getMouseY(), 0, 130);
            
            
            //render sqlTables
            renderSqlTables();
            renderSqlConstraints();
            animateUIPane();
            
            //log("our thread is running!");
            Thread.sleep(FramePerSecond);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean isOnViewPortToRender(double x , double y)
    {
        if((x >= view.getViewPort().getViewPortX()-100 && x < view.getViewPort().getViewPortX()+ view.getViewPort().getViewPortMaxWidth())
                && (y>=view.getViewPort().getViewPortY()-100 && y < view.getViewPort().getViewPortMaxHeight()))
        {
            return true;
        }
       return false;
    }
    
   public void renderSqlTables()
   {
       if(view.getSqlTableMap().size()>0)
       {
           Set tableSet =  view.getSqlTableMap().entrySet();
           Iterator it  = tableSet.iterator();
           while(it.hasNext())
           {
               Map.Entry k =  (Map.Entry)it.next();
               String tableName =  k.getKey().toString();
               SqlTable table = (SqlTable)k.getValue();
               double x =  table.getNodeX() + view.getViewPort().getViewPortX();
               double y = table.getNodeY() + view.getViewPort().getViewPortY();
               
               if(isOnViewPortToRender(x,y)){
               
               context.setFill(Color.AQUA);
               context.fillText("Table Name : "+table.getTableName(), x,y);
               context.setFill(Color.TRANSPARENT);
               context.setStroke(Color.RED);
               context.strokeRoundRect(x, y,table.getRectangle().width, table.getRectangle().height,10,10);
               
               //lets draw all tupples now
                if(null!=view.getSqlTupples().get(tableName))
                { 
                    int counter = 20;
                    ArrayList<SqlTupple> data =  view.getSqlTupples().get(tableName);
                    for(int i=0; i<data.size(); i++)
                    {
                         context.setFill(Color.AQUA);
                         context.fillText(data.get(i).toString(), x,y+counter);
                         counter+=20;
                    }
                    
                }//close if
               
               }//close viewport
           }//close while
           
           
       }//close if
   }
   
   public void renderSqlConstraints()
   {
       if(view.getSqlTableMap().size()>0)
       {
           Set tableSet =  view.getSqlTableMap().entrySet();
           Iterator it  = tableSet.iterator();
           while(it.hasNext())
           {
               Map.Entry k =  (Map.Entry)it.next();
               String tableName =  k.getKey().toString();
               SqlTable table = (SqlTable)k.getValue();
               double x =  table.getNodeX() + view.getViewPort().getViewPortX();
               double y = table.getNodeY() + view.getViewPort().getViewPortY();
               
               
               if(isOnViewPortToRender(x,y)){
                 
                    //lets render the connected line 
                    if(table.getTableConstraints().size() > 0)
                    {
                        for(SqlTable t: table.getTableConstraints()){
                        double tox = t.getNodeX() + view.getViewPort().getViewPortX();
                        double toy =  t.getNodeY() + view.getViewPort().getViewPortY();
                            
                        context.setFill(Color.AQUA);
                        context.setLineWidth(10);
                        context.strokeLine(x, y, tox, toy);
                        }
                    }
               
               }//close viewport
           }//close while
           
           
       }//close if
   }
    
   
   
    public void animateUIPane()
    {
        if(view.showConstraints)
        {
            if(view.tableContraints.getTranslateY() > 0)
            {
                view.tableContraints.setTranslateY(view.tableContraints.getTranslateY()-100);
            }else{
                 view.tableContraints.setTranslateY(0);
            }
        }else{
            
             if(view.tableContraints.getTranslateY() <= view.getMainScene().getHeight())
            {
                view.tableContraints.setTranslateY(view.tableContraints.getTranslateY()+100);
            }else{
                 view.tableContraints.setTranslateY(view.getMainScene().getHeight()+100);
            }
        }
        
        if(view.showTables)
        {
            if(view.tablesUI.getTranslateY() > 0)
            {
                view.tablesUI.setTranslateY(view.tablesUI.getTranslateY()-100);
            }else{
                 view.tablesUI.setTranslateY(0);
            }
        }else{
            
             if(view.tablesUI.getTranslateY() <= view.getMainScene().getHeight())
            {
                view.tablesUI.setTranslateY(view.tablesUI.getTranslateY()+100);
            }else{
                 view.tablesUI.setTranslateY(view.getMainScene().getHeight()+100);
            }
        }
    }
    public void log(String str)
    {System.out.println(str);}
    
}
