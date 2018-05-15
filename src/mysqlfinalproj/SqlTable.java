/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 *
 * @author luism
 */

public class SqlTable implements Serializable{
   
    //well since it is a 2d game the z axis will be the zoom location with respect to the windows screen and size of object node.
    //the bigger the z axis is the bigger the node becomes this is just an illution
    private double x,y,z;
    private String name;
     transient private Color color ;
    private ArrayList<SqlTupple>sqlTupples;
    private ArrayList<SqlTable>constraints;
   
    public SqlTable(String name ,double x, double y, double z)
    {
        this.name=name;
        this.x =x;
        this.y=y;
        this.z=z;
        sqlTupples = new ArrayList<SqlTupple>();
        constraints = new ArrayList<SqlTable>();
             
    }
    public Rectangle getRectangle()
    {
        return new Rectangle((int)x, (int)y+5, 250,(int)(sqlTupples.size()*20)+120);
    }
    
    public ArrayList<SqlTupple> getTableTupples()
    {return this.sqlTupples;}
    public ArrayList<SqlTable> getTableConstraints()
    {return this.constraints;}
   
    public void addTuple(String key, SqlTupple val)
    {
        sqlTupples.add(val);
    }
    public void addSqlTableConstraints(SqlTable constraint)
    {
        constraints.add(constraint);
    }
    
    public void addTuppleObject(ArrayList<SqlTupple>sqlT)
    {
        this.sqlTupples = sqlT;
    }
    public void setX(double x)
    {this.x=x;}
    public void setY(double y)
    {this.y=y;}
    public void setCircleColor(Color text)
    {this.color=text;}
    public void setName(String v)
    {this.name = v;}
    
   
    public String getTableName()
    {return this.name;}
    public double getNodeX()
    {return this.x;}
    public double getNodeY()
    {return this.y;}
    public double getNodeZ()
    {return this.z;}
    public Color getNodeColor()
    {return this.color;}
   
    
   
    public void upDateOnDrag(double x, double y)
    {
       this.x = x;
       this.y=y;
    }

    
    public String toString()
    {
        return " x = "+x+" y = "+y;
    }
      
}
