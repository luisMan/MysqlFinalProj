/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

/**
 *
 * @author luism
 */
public class SqlTupple {
    String tuppleName;
    String tuppleDataType;
    boolean tuppleNullidity;
    boolean tupplePrimaryKey;
    boolean hasConstraint = false;
    
    public SqlTupple(String tn, String tdt,boolean tnull, boolean tp)
    {
        this.tuppleName=tn;
        this.tuppleDataType=tdt;
        this.tuppleNullidity=tnull;
        this.tupplePrimaryKey = tp;
    }
    
    
    //setters 
    public void setTuppleName(String n)
    {this.tuppleName = n;}
    public void setTuppleDataType(String dt)
    {this.tuppleDataType = dt;}
    public void setTuppleNullidity(boolean tn)
    {this.tuppleNullidity=tn;}
    public void setHasConstraint(boolean c)
    {this.hasConstraint = c;}
    public void setIsPrimaryKey(boolean p)
    {this.tupplePrimaryKey = p;}
    
    //getters
    public String getTuppleName()
    {return this.tuppleName;}
    public String getTuppleDataType()
    {return this.tuppleDataType;}
    public boolean getTuppleNullidity()
    {return this.tuppleNullidity;}
    public boolean getHasConstraint()
    {return this.hasConstraint==true;}
    public boolean getIsPrimaryKey()
    {return this.tupplePrimaryKey==true;}
    
    public String toString()
    {
        String nullidity = "";
        String pkey ="";
        if(tuppleNullidity)
            nullidity = "NULL";
        else
            nullidity = "NOT NULL";
        
        if(tupplePrimaryKey)
            pkey ="PRIMARY KEY";
        else
            pkey="";
        
        return tuppleName+" "+tuppleDataType+" "+nullidity+" "+pkey;
       
               
    }
    
    
}
