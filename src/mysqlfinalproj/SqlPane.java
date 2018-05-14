/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author luis manon student at lehman college extra credit project for professor Jwong
 */
public class SqlPane extends StackPane {
    private final MysqlWorldScene mySqlScene;
    private final Stage myPrimaryStage;
    private MouseEventHandler mouseSingleton;
    private WidgetComponentEventHandler widgetComponentEventHandler;
    private SqlRenderKeyHandler sqlRenderKeyEventHandler;
    private SqlRenderer sqlRender;
    private BorderPane  mainUI;
    public  BorderPane  tablesUI;
    public  BorderPane tableContraints;
    private Pane top;
    private Pane left;
    private Pane center;
    private ScrollPane right;
    private Pane bottom;
    private TextField schemaName;
    private Button setName;
    private Button saveSchema;
    private Button loadSchema;
    private Button constraints;
    private Button Tables;
    private Button closeTables;
    private Button closeConstraints;
    private HBox tableBoxGrid;
    private SqlViewPort viewport;
    private HashMap<String,SqlTable> tables;
    private HashMap<String,ArrayList<SqlTupple>>tupples;
    private HashMap<String,BorderPane> tableComponents;
    private HashMap<String,ArrayList<GridPane>> tupleComponents;
    private int TABLE_COUNTER = 0;
    public boolean showTables = false;
    public boolean showConstraints = false;
    
    
    public SqlPane(Stage mStage, long w, long h)
    {
        myPrimaryStage  =  mStage;
        //instantiate world scene
        mySqlScene = new MysqlWorldScene(this,w,h,Color.BLACK);
       
        initEventHandler();
        initLayoutComponent();
        initUITextComponent();
        initUIWidgetComponent();
        initUILayoutParams();
        initUIObjects();
        
        //after all data is loaded lets start our thread and renderer
        initRenderer();
       
        
        
        
        
    }
      
    public void initLayoutComponent()
    { 
        top = new Pane();
        top.setPrefSize(mySqlScene.getCanvasWidth(), 50);
        top.setStyle("-fx-background-color:#0a0f0f;");
        top.getChildren().add(new Label("texting the colors lol"));
        left =  new Pane();
        Label tree = new Label("Schema Tree");
        tree.setStyle("-fx-font-family: \"Segoe UI Semibold\";\n" +"    -fx-text-fill: white;");
        
        left.getChildren().add(tree);
        left.setPrefSize(200, mySqlScene.getCanvasHeight());
        left.setStyle("-fx-background-color: #33334d;");
        right = new ScrollPane();
        right.setStyle("-fx-background-color: #33334d;");
        center =  new Pane();
        center.setPrefSize(1300, 900);
        viewport = new SqlViewPort(0,0,0,0,1300,900,25f);
        center.setStyle("-fx-background-color:black;");
        bottom = new Pane();
        bottom.setPrefSize(mySqlScene.getCanvasWidth(), 50);
        bottom.setStyle("-fx-background-color:#33334d;");
        
        
        tableBoxGrid = new HBox();
        
        
        
        
        
        //intantiate pur main ui pane to add to the stackpane
        mainUI = new BorderPane();
        //this is the pane for the table ui
        
        closeTables = new Button("closeTable");
        closeTables.setOnAction(widgetComponentEventHandler);
        closeConstraints = new Button("closeConstraint");
        closeConstraints.setOnAction(widgetComponentEventHandler);
            
        tablesUI = new BorderPane();
        tablesUI.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        tablesUI.setTranslateY(mySqlScene.getCanvasHeight());
        tablesUI.setPrefSize(mySqlScene.getCanvasWidth(),mySqlScene.getCanvasHeight());
        tablesUI.setTop(closeTables);
        tablesUI.setCenter(right);
        
        
        //this is the pane for the constraint data
        tableContraints = new BorderPane();
        tableContraints.setStyle("-fx-background-color: rgba(255,255,255,0.4);");
        tableContraints.setTranslateY(mySqlScene.getCanvasHeight());
        tableContraints.setPrefSize(mySqlScene.getCanvasWidth(),mySqlScene.getCanvasHeight());
        tableContraints.setTop(closeConstraints);
        tableContraints.setCenter(tableBoxGrid);
        
        mainUI.setTop(top);
        mainUI.setLeft(left);
        mainUI.setBottom(bottom);
        mainUI.setCenter(center);
        
        
        this.getChildren().add(mainUI);
        this.getChildren().add(tablesUI);
        this.getChildren().add(tableContraints);
    }
     
    
    
       public void initUIObjects()
       {  
           //lets instantiate our ArrayList for table under specific schema
           tables = new HashMap<String,SqlTable>();
           tupples = new HashMap<String,ArrayList<SqlTupple>>();
           tableComponents = new HashMap<String,BorderPane>();
           tupleComponents = new HashMap<String,ArrayList<GridPane>>();
                   
       }
       
        public void initEventHandler()
        {
          mouseSingleton = new MouseEventHandler(this);
          sqlRenderKeyEventHandler = new SqlRenderKeyHandler(this);
          widgetComponentEventHandler = new WidgetComponentEventHandler(this);
        
        }
      
        public void initUITextComponent(){
            GridPane grid = new GridPane();
            GridPane footer = new GridPane();
            schemaName = new TextField();
            Label schemaLevel =  new Label("Schema Name : ");    
            schemaLevel.setStyle("-fx-font-family: \"Segoe UI Semibold\";\n" +" -fx-font-size:28px;   -fx-text-fill: white;");
            setName = new Button("SetSchemaName");
            setName.setOnAction(widgetComponentEventHandler);
            saveSchema = new Button("SaveSqlSchema");
            saveSchema.setOnAction(widgetComponentEventHandler);
            loadSchema =  new Button("loadSqlSchema");
            loadSchema.setOnAction(widgetComponentEventHandler);
            constraints = new Button("constraints");
            constraints.setOnAction(widgetComponentEventHandler);
            Tables = new Button("tables");
            Tables.setOnAction(widgetComponentEventHandler);
         
            
            
            
            grid.add(schemaLevel, 0, 0);
            grid.add(setName,2,0);
            grid.add(schemaName, 1, 0);
            grid.add(saveSchema, 3, 0);
            grid.add(loadSchema, 4, 0);
            top.getChildren().add(grid);
          
            footer.add(constraints,0,0);
            footer.add(Tables, 1,0);
            bottom.getChildren().add(footer);
            
        }
        public void initUIWidgetComponent(){
            
        }
        public void initUILayoutParams()
        {
                
        }
        
        
        
        public void ConstructNewTableComponent(double x , double y)
        {
            
            String key = ""+TABLE_COUNTER;
            
            //construct the table object to be able to render 
            SqlTable table = new SqlTable("noName",x,y,0);
            
            
            GridPane tableTop = new GridPane();
            tableTop.setStyle("-fx-background-color:#0a0f0f;");
            BorderPane mainPane = new BorderPane();
            mainPane.setId(key);
            mainPane.setPrefSize(750, 200);
            mainPane.setStyle("-fx-background-color:#0a0f0f;");
            Label tname = new Label("   Table Name : ");
            tname.setStyle(" -fx-font-family: \"Segoe UI Semibold\";\n" +" -fx-font-size:16px;   -fx-text-fill: white;");
            TextField nameField = new TextField();
            nameField.setId("tableName"+key);
            Button addTupple = new Button("+");
            addTupple.setId("addTuple"+key);
            addTupple.setOnAction(widgetComponentEventHandler);
            Button deleteTable = new Button("-");
            deleteTable.setId("deleteTable"+key);
            deleteTable.setOnAction(widgetComponentEventHandler);
            Button saveTable =  new Button("Save");
            saveTable.setId("save"+key);
            saveTable.setOnAction(widgetComponentEventHandler);
            
            
            tableTop.add(tname, 0, 0);
            tableTop.add(nameField,0,1);
            tableTop.add(addTupple,1,1);
            tableTop.add(deleteTable,2,1);
            mainPane.setTop(tableTop);
            mainPane.setBottom(saveTable);
            
            tableComponents.put(key, mainPane);
            tables.put(key, table);
            geWidgetComponentEventHandler().addNewTreeComponent("table"+key, table.getTableName());
            
            
            refreshTableUIComponents();
            TABLE_COUNTER++;
        }
        
        
        
        
        
        
        public void initRenderer()
        {
            //init the renderer and this will instantiate the thread as well
          sqlRender = new SqlRenderer(this,mySqlScene);
            //lets add mouse listener to this renderer
          sqlRender.setOnMousePressed(mouseEvent ->{
          mouseSingleton.handle(mouseEvent);
          });
          sqlRender.setOnMouseDragged(mouseEvent ->{
          mouseSingleton.handle(mouseEvent);
          });
          sqlRender.setOnMouseReleased(mouseEvent ->{
          mouseSingleton.handle(mouseEvent);
          });
          
          sqlRender.setOnMouseMoved(mouseEvent->{
          mouseSingleton.handle(mouseEvent);
          });
          
          sqlRender.setOnMouseEntered(mouseEvent->{
          mouseSingleton.handle(mouseEvent);
          });
          
          sqlRender.setOnMouseExited(mouseEvent->{
          mouseSingleton.handle(mouseEvent);
          });
          
          
          //lets add keyEvent to our scene
          mySqlScene.setOnKeyPressed(keyEvent->{
              sqlRenderKeyEventHandler.handle(keyEvent);
          });
         
          
          center.getChildren().add(sqlRender);
          center.setFocusTraversable(true);
        }
       
        
      public void refreshTableUIComponents()
      { 
          right.setContent(null);
          int col = 0;
          int row = 0;
          Set table =  tableComponents.entrySet();
          Iterator it = table.iterator();
         
          GridPane grid = new GridPane();
          grid.setStyle("-fx-background-color:transparent;");
         
          while(it.hasNext())
          {
              Map.Entry k = (Map.Entry)it.next();
              String t =  (String)k.getKey().toString();
              BorderPane pane =  (BorderPane)k.getValue();
              pane.setStyle("-fx-background-color:transparent;");
              grid.add(pane,col,row);
              col+=1;
              if(col!=0 && col%2==0)
              {
                  col = 0; 
                  row++;
              }
              
            
             System.out.println("row = "+row+" col = "+col);
          }
         
          right.setContent(grid);
          this.geWidgetComponentEventHandler().populateTree();
      }
      
      
      public void RefreshTuplesForTables()
      {
          Set tables =  tableComponents.entrySet();
          Iterator it = tables.iterator();
         
          while(it.hasNext())
          {
              Map.Entry key =  (Map.Entry)it.next();
              String id =  key.getKey().toString();
              BorderPane pane = (BorderPane)key.getValue();
              pane.setStyle("-fx-background-color:lightGray;");
              ScrollPane scroll =  new ScrollPane();
              VBox vertical = new VBox();
              ArrayList<GridPane> tupples = tupleComponents.get(id);
              if(tupples!=null){
              System.out.println("the size of tupple for table with key = "+id+" = "+tupples.size());
              for(int i=0; i<tupples.size(); i++)
              {
                  vertical.getChildren().add(tupples.get(i));
              }
              scroll.setContent(vertical);
              pane.setCenter(scroll);
              }
          }
      }
    
      public WidgetComponentEventHandler geWidgetComponentEventHandler()
      {return this.widgetComponentEventHandler;}
      public Stage getPrimaryStage()
      {return this.myPrimaryStage;}
      public Scene getPrimaryScene()
      {return this.mySqlScene;}
      //buttons 
      public Button getLoadButton()
      {return this.loadSchema;}
      public Button getSavedButton()
      {return this.saveSchema;}
      public Scene getMainScene()
      {return this.mySqlScene;}
      public SqlViewPort getViewPort()
      {return this.viewport;}
      public MouseEventHandler getMouseEvent()
      {return this.mouseSingleton;}
      public boolean showTableValue()
      {return this.showTables;}
      public boolean showConstraintValue()
      {return this.showConstraints;}
      //get sql table Map
      public HashMap<String,SqlTable> getSqlTableMap()
      {return this.tables;}
      public HashMap<String, ArrayList<SqlTupple>> getSqlTupples()
      {return this.tupples;}
      public void addNewSqlTableToMap(String tName,SqlTable table)
      {this.tables.put(tName,table);}
      /*public void addNewSqlTupleToTable(String tName,SqlTupple tupple)
      {
          
      }*/
      
      public void addSqlTupplePaneComponents(String key)
      {
          if(!tupleComponents.containsKey(key))
          {
         
          tupleComponents.put(key, new ArrayList<GridPane>()); 
          
          ArrayList<GridPane> list = tupleComponents.get(key);
          //now lets construct the tupple data to be added
          Label tName = new Label("Name : ");
          TextField tupleName = new TextField();
          Label tDType = new Label("Data Type : ");
          TextField tupleDType = new TextField();
          Label isNull = new Label("Null");
          CheckBox nullidity = new CheckBox();
          Label nNull = new Label("Not Null");
          CheckBox notNull = new CheckBox();
          Label pk = new Label("PK");
          CheckBox  pkey = new CheckBox();
          
          GridPane pane = new GridPane();
          pane.add(tName, 0, 0);
          pane.add(tupleName, 1, 0);
          pane.add(tDType,2,0);
          pane.add(tupleDType,3,0);
          pane.add(isNull,4,0);
          pane.add(nullidity,5,0);
          pane.add(nNull,6,0);
          pane.add(notNull,7,0);
          pane.add(pk,8,0);
          pane.add(pkey,9,0);
          
         
          list.add(pane);
          
         
          tupleComponents.put(key,list);
              
          }else{
           
          ArrayList<GridPane> list = tupleComponents.get(key);
          //now lets construct the tupple data to be added
          Label tName = new Label("Name : ");
          TextField tupleName = new TextField();
          Label tDType = new Label("Data Type : ");
          TextField tupleDType = new TextField();
          Label isNull = new Label("Null");
          CheckBox nullidity = new CheckBox();
          Label nNull = new Label("Not Null");
          CheckBox notNull = new CheckBox();
          Label pk = new Label("PK");
          CheckBox  pkey = new CheckBox();
          
          GridPane pane = new GridPane();
          pane.add(tName, 0, 0);
          pane.add(tupleName, 1, 0);
          pane.add(tDType,2,0);
          pane.add(tupleDType,3,0);
          pane.add(isNull,4,0);
          pane.add(nullidity,5,0);
          pane.add(nNull,6,0);
          pane.add(notNull,7,0);
          pane.add(pk,8,0);
          pane.add(pkey,9,0);
          
         
          list.add(pane);
          
         
          tupleComponents.put(key,list);
              
          }
          
         
          //lets refresh 
          RefreshTuplesForTables();
      }
      
     
      
      
      
      
      //lets get ui components layout pane form Main BorderPane
      public Pane getTop()
      {return this.top;}
      public Pane getLeft()
      {return this.left;}
      public Pane getBottom()
      {return this.bottom;}
      public ScrollPane getRight()
      {return this.right;}
      public Pane getCenter()
      {return this.center;}
      
      //get attributes from main ui to populate tree
      public TextField getSchemaName()
      {return this.schemaName;}
      
      
      //update table component
      public void upDateTableComponent(SqlTable table,double x, double y)
      {
          table.upDateOnDrag(x, y);
      }
      
      public void deleteTableFromKey(String key)
      {
          tables.remove(key);
          tableComponents.remove(key);
          this.geWidgetComponentEventHandler().getTreeComponentsGraph().remove("table"+key);
          refreshTableUIComponents();
          
          
       
      }
      
      public void deleteTuppleFromTable(String key)
      {
          ArrayList<GridPane> tup = tupleComponents.get(key);
          ArrayList<SqlTupple> tableTupples = tupples.get(key);
          
          if(tup!=null && tup.size()> 0)
          {
              tup.remove(tup.get(tup.size()-1));
              
          }
          if(tableTupples!=null && tableTupples.size()> 0)
          {
              tableTupples.remove(tableTupples.get(tableTupples.size()-1));
             
          }
          
          RefreshTuplesForTables();
      }
      
      
      
      //save all data components method
      public void saveAllDataComponentsForTableWithKey(String key)
      {
          BorderPane component = tableComponents.get(key);
          GridPane top = (GridPane)component.getTop();
          //get the name of the table
          String name = ((TextField)top.getChildren().get(1)).getText().toString();
          
          //lets update the name for this table
          if(name.length()>0){
              if(null!=tables.get(key))
              {
                  tables.get(key).setName(name);
              }
          }else{
              javax.swing.JOptionPane.showMessageDialog(null, "Please input a name for this table before saving!");
          }
        
          //noe lets get all the tupples data for this amazing table
          ArrayList<GridPane> list = tupleComponents.get(key);
          if(null!=list && list.size()>0)
          {
              if(null==tupples.get(key))
              {
                  tupples.put(key, new ArrayList<SqlTupple>());
              }else{
                  //lets clear all since we will reupdate the tupple data
                  tupples.get(key).clear();
              }
              
              ArrayList<SqlTupple> TuppleToAdd =  tupples.get(key);
              for(int i= 0; i<list.size(); i++)
              {
                  String tuppleName  = ((TextField)(list.get(i).getChildren().get(1))).getText().toString();
                  String dataType = ((TextField)(list.get(i).getChildren().get(3))).getText().toString();
                  boolean isNull =  ((CheckBox)(list.get(i).getChildren().get(5))).isSelected();
                  boolean isNotNull = ((CheckBox)(list.get(i).getChildren().get(7))).isSelected();
                  boolean isPrimaryKey = ((CheckBox)(list.get(i).getChildren().get(9))).isSelected();
                  
                 if(tuppleName.length() > 0)
                 {
                     SqlTupple tupple = new SqlTupple(tuppleName,dataType,isNull,isPrimaryKey);
                     TuppleToAdd.add(tupple);
                     
                 }else{
                     javax.swing.JOptionPane.showMessageDialog(null, "Please input tupple name for row = "+i+" on table "+name);
                 }
              }//close thhe for loop
              
              //lets set the tupples to this table now that we construct all of them
                tables.get(key).addTuppleObject(TuppleToAdd);
          }
          
      }
    
}
