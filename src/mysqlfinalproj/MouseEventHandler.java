/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author luism
 */
public class MouseEventHandler implements EventHandler<MouseEvent>{
    private SqlPane view;
    private double x,y;
    public MouseEventHandler(SqlPane view)
    {
        this.view  = view;
    }


    @Override
    public void handle(MouseEvent event) {
         x =  event.getX() - view.getViewPort().getViewPortX();
         y =  event.getY() - view.getViewPort().getViewPortY();
        //lets detech event type
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
        {
            
            if(event.getButton() == MouseButton.PRIMARY && view.onConstraintCreationActive)
            {
                if(view.fromNode==null)
                {
                    
                }
            }
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==2)
            {
                if(view.getSchemaName().getText().length()==0)
                {
                    javax.swing.JOptionPane.showMessageDialog(null,"Please input an Schema name to continue host!");
                    return;
                }
                //log("add new table");
               view.ConstructNewTableComponent(x,y);
            }
            if(event.getButton() == MouseButton.SECONDARY && event.getClickCount() ==2)
            {
                //log("add new table");
           Set tableSet =  view.getSqlTableMap().entrySet();
           Iterator it  = tableSet.iterator();
           String key = null; boolean isfound =false;
           while(it.hasNext()&& !isfound)
           {
               Map.Entry k =  (Map.Entry)it.next();
               String tableNameKey =  k.getKey().toString();
               SqlTable table = (SqlTable)k.getValue();
               if((x >= table.getNodeX()- 100 && x<= table.getNodeX() + 100)
                       && (y >= table.getNodeY()-100 && y<= table.getNodeY()+100))
               {
                   key = tableNameKey;
                   isfound = true;
               }

           }
             if(key.length()> 0 && isfound){
              view.deleteTableFromKey(key);
             }
               
            }
            
        }
        if(event.getEventType() == MouseEvent.MOUSE_RELEASED)
        {
            log("mouse RELEASED");
        }
        if(event.getEventType() == MouseEvent.MOUSE_DRAGGED)
        {
           Set tableSet =  view.getSqlTableMap().entrySet();
           Iterator it  = tableSet.iterator();
           SqlTable toMove = null; boolean isfound =false;
           while(it.hasNext()&& !isfound)
           {
               Map.Entry k =  (Map.Entry)it.next();
               String tableNameKey =  k.getKey().toString();
               SqlTable table = (SqlTable)k.getValue();
               if((x >= table.getNodeX()- 100 && x<= table.getNodeX() + 200)
                       && (y >= table.getNodeY()-100 && y<= table.getNodeY()+100))
               {
                   toMove = table;
                   isfound = true;
               }

           }
             if(null!=toMove && isfound){
              view.upDateTableComponent(toMove,x,y);
             }
        }
        if(event.getEventType() == MouseEvent.MOUSE_MOVED)
        {
            log("mouse MOVED");
        }
         if(event.getEventType() == MouseEvent.MOUSE_ENTERED)
        {
            log("mouse ENTERED");
            this.view.requestFocus();
        }
          if(event.getEventType() == MouseEvent.MOUSE_EXITED)
        {
            log("mouse EXITED");
            this.view.setFocusTraversable(false);
             
        }
        
            
         
        
    }
    
    public double getMouseX()
    {return this.x;}
    public double getMouseY()
    {return this.y;}
    
    public void log(String str)
    {System.out.println(str);}
    
}
