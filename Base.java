/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class Base implements Serializable{
    
    private int id;
    
    private String name;
   private String adress;
   private int blackCount;
   private int greenCount;
   private int redCount;
   private int blueCount;
   private int hourPrice;
   private int nightPrice;
    
   private boolean hasLightedSloves;
   private boolean hasTrainers;
   private boolean hasParking;
   private boolean hasCaffe;
   private boolean hasHotel;
    
   private ArrayList<Ski> skis;
   private ArrayList<Ski> snows;
   private ArrayList<Food> menuList;
   
   
   public int getId(){
       return id;
      
   }
   
   public void setId(int idi){
       this.id = idi;
   }
   
   public String getName(){
       return name;
   }
        
   public String getAdress(){
       return adress;
   }
   
   public ArrayList<Ski> getSkis(){
       if(skis==null) 
        {
          skis=new ArrayList<Ski>();
          
        }
      return skis;
   }
   
   public ArrayList<Ski> getSnows(){
       if(snows==null) 
        {
         snows = new ArrayList<Ski>();
       
        }
      return snows;
   }
   
   public ArrayList<Food> getMenu(){
       if(menuList==null) 
        {
          menuList = new ArrayList<Food>();
          
        }
      return menuList;
   }
   
   public int getBlackC(){
       return blackCount;
   }
   
   public int getBlueC(){
       return blueCount;
   }
   
   public int getGreenC(){
       return greenCount;
   }
   
   public int getRedC(){
       return redCount;
   }
   
   public int getHourPrice(){
       return hourPrice;
   }
   
   public int getNightPrice(){
       return nightPrice;
   }
   
   public boolean hasLight(){
       return hasLightedSloves;
   }
   
   public boolean hasTrainers(){
       return hasTrainers;
   }
   
   public boolean hasParking(){
       return hasParking;
   }
   
   public boolean hasCaffe(){
       return hasCaffe;
   }
   
   public boolean hasHotel(){
       return hasHotel;
   }
   
   public void setName(String name) {
        this.name = name;
    }
   
   public void setAdress(String adress) {
        this.adress = adress;
    }
   
  public void setBlackCount(int count){
      this.blackCount = count;
  }
  
   public void setBlueCount(int count){
      this.blueCount = count;
  }
  
   public void setGreenCount(int count){
      this.greenCount = count;
  }
  
   public void setRedCount(int count){
      this.redCount = count;
  }
   
  public void setHourPrice(int price){
      this.hourPrice = price;
  }
  
  public void setNightPrice(int price){
      this.nightPrice = price;
  }
  
  public void setHasTrainers(boolean has){
      this.hasTrainers = has;
  }
  
  public void setHasLight(boolean has){
      this.hasLightedSloves = has;
  }
  
  public void setHasCaffe(boolean has){
      this.hasCaffe = has;
  }
   public void setHasHotel(boolean has){
      this.hasHotel = has;
  }
   
  public void setHasParking(boolean has){
      this.hasParking = has;
  }

    
}
