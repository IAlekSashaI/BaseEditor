/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;

import java.io.Serializable;

/**
 *
 * @author Alexander
 */
public class Ski implements Serializable{
    
    private String name;
    private String high;
    private int usedCount = 0;
    public String id;
    private boolean isFree;
    private String owner;
    
    public void wasOwned(String owne){
        usedCount+=1;
        owner = owne;
        isFree = false;
        
    }
    
    public String getName(){
       return name; 
    }
    
    public String getHigh(){
       return high; 
    }
    
    public int getUsedCount(){
       return usedCount; 
    }
    
    public String getId(){
        return id;
    }
    
    public boolean isFree(){
       return isFree; 
    }
    
    public String getOwner(){
       return owner; 
    }
    
    public void setName(String nme){
       this.name = nme; 
    }
    
    public void setHigh(String h){
       this.high = h; 
    }
    
    public void setFree(boolean is){
        this.isFree = is;
    }
    
    public void setOwner(String own){
       this.owner = own; 
    }
    
    
    
}
