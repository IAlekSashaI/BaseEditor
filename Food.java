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
public class Food implements Serializable{
    
    private String name;
    private String price;
    
    public String getName(){
        return name;
    }
    
    public String getPrice(){
        return price;
    }
    
    public void setName(String nme){
        this.name = nme;
    }
    
    public void setPrice(String prce){
        this.price = prce;
    }
    
}
