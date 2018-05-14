/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author luism
 */
public class SqlRenderKeyHandler implements EventHandler<KeyEvent> {
    private SqlPane view;
    
    public SqlRenderKeyHandler(SqlPane v)
    {this.view = v;}

    @Override
    public void handle(KeyEvent event) {
        //lets check for this 4 different type of keys
        //W forward 
        //A left
        //D right
        //S down
        //this will update view port 
        if(event.getCode() == KeyCode.W)
        {
            log("viewport will go up");
            this.view.getViewPort().upDateViewPortXY(0, -25);
        }
        if(event.getCode() == KeyCode.S)
        {
            log("viewport will go down");
            
            this.view.getViewPort().upDateViewPortXY(0, +25);
        }
        
        if(event.getCode() == KeyCode.A)
        {
            log("viewport will go left");
            
            this.view.getViewPort().upDateViewPortXY(-25, 0);
            
        }
        
        if(event.getCode() == KeyCode.D)
        {
            log("viewport will go right");
            
            this.view.getViewPort().upDateViewPortXY(25, 0);
        }
    }
    
    
    public void log(String str)
    {System.out.println(str);}
    
}
