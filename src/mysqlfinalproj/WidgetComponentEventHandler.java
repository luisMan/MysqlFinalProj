/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author luism
 */
public class WidgetComponentEventHandler implements EventHandler<ActionEvent>{
    private SqlPane view;
    private HashMap<String,Label> treeComponents;
    
    public WidgetComponentEventHandler(SqlPane view)
    {
        this.view  = view;
        treeComponents = new HashMap<String,Label>();
   
    }

    @Override
    public void handle(ActionEvent event) {
       //lets check for action event to widget and text and many more ui components
       if(event.getSource() instanceof Button){
       String text = ((Button)event.getSource()).getText();
       //log("the event name = "+text);
       if(text.toLowerCase().equals("setschemaname"))
       {
           log("setSchemaName");
           //lets add a new component to the Schema Tree
           if(!treeComponents.containsKey("schemaName") ||treeComponents.containsKey("schemaName") )
           {
               Label schema = new Label(view.getSchemaName().getText().toString());
               treeComponents.put("schemaName", schema);
               populateTree();
           }
       }else if(text.toLowerCase().equals("savesqlschema"))
       {
           log("saveSqlSchema");
           
       }else if(text.toLowerCase().equals("loadsqlschema"))
       {
           log("loadSqlSchema");
       }else if(text.toLowerCase().equals("constraints"))
       {
           if(!view.showConstraints){
               view.showConstraints = true;
           }else{
               view.showConstraints = false;
           }
       }else if(text.toLowerCase().equals("tables"))
       {
           if(!view.showTables)
           {
               view.showTables = true;
           }else{
               view.showTables  = false;
           }
       }else if(text.toLowerCase().equals("closetable"))
       {
           view.showTables =false;
       }else if(text.toLowerCase().equals("closeconstraint"))
       {
           view.showConstraints =false;
       }else{
           //we are listening to the buttons for tables 
          //String [] action = ((Button)event.getSource()).getId().toString().split("\\d+");
          String [] key =  ((Button)event.getSource()).getId().toString().split("(?<=\\D)(?=\\d)");
          if(key[0].toLowerCase().equals("addtuple"))
          {
              //System.out.println(key[0]);
             // System.out.println(key[1]);
              view.addSqlTupplePaneComponents(key[1]);
              
              
          }else if(key[0].toLowerCase().equals("deletetable"))
          {
              view.deleteTuppleFromTable(key[1]);
              
          }else if(key[0].toLowerCase().equals("save")){
              //save table data to renders
              view.saveAllDataComponentsForTableWithKey(key[1]);
          }
       }
       
       }else if(event.getSource() instanceof CheckBox){
              
           String text = ((CheckBox)event.getSource()).getId();
           
           if(text.toLowerCase().equals("constraintcreation"))
           {
              if(!view.onConstraintCreationActive){
                  view.onConstraintCreationActive = true;
              }else{
                  view.onConstraintCreationActive = false;
              }
             
          
           }
           
       }
       
       
    }
    
    public void addNewTreeComponent(String key, String value)
    {
               Label desc = new Label(value);
               treeComponents.put(key, desc);
               populateTree();
    }
    
    public void populateTree()
    {
        //lets clear the three
        view.getLeft().getChildren().clear();
        Set list = treeComponents.entrySet();
        GridPane grid = new GridPane();
        Iterator it = list.iterator();
        int counter = 0;
        while(it.hasNext())
        { 
              Map.Entry k = (Map.Entry)it.next();
              String name = k.getKey().toString();
              Label obj = (Label)k.getValue();
              obj.setStyle("-fx-font-family: \"Segoe UI Semibold\";\n" +" -fx-font-size:16px;   -fx-text-fill: white;");
              Label key = new Label(name);
              key.setStyle("-fx-font-family: \"Segoe UI Semibold\";\n" +" -fx-font-size:16px;   -fx-text-fill: white;");
           
              grid.add(key, 0, counter);
              grid.add(obj, 1, counter+1);
              
             
            counter+=2;
        }
         view.getLeft().getChildren().add(grid);
      
    }
    public void log(String str)
    {
        System.out.println(str);
    }
    
    public HashMap<String, Label> getTreeComponentsGraph()
    {return this.treeComponents;}
    
}
