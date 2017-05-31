package baseeditor;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alexander
 */
public class BaseTableModel extends AbstractTableModel{
    
   
    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Base> fullBases= new ArrayList<>();
    
    @Override
    public int getRowCount() {
        
        return bases.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
     switch (column){
            case 0: return "Название";
            case 1: return "Адрес";
            case 2: return "id";
         
        }
        return "";
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
Base base = bases.get(rowIndex);
        
        switch (columnIndex){
            case 0:
                
                return base.getName();
            
            case 1:
                
                return base.getAdress();
            case 2:
                return base.getId();
            
        }
        
        return null;
    }
    
    
    public void addBase(Base base){
        bases.add(base);
        fullBases.add(base);
        fireTableDataChanged();
        
        
    }
    
    public void deleteBase (Base base){
      
        bases.remove(base);
        fullBases.remove(base);
        fireTableDataChanged();
    }
    
    public Base getBase (int rowIndex){
        return bases.get(rowIndex);
    }
    
    public void setBase(int rowIndex, Base base){
        bases.set(rowIndex, base);
        fireTableDataChanged();
    }
    
    public void setSerch(String serch){
        
        bases.clear();
        for(int i=0; i<fullBases.size();i++){
          String name = fullBases.get(i).getName();
          
          
         if (name.startsWith(serch)){ 
             bases.add(fullBases.get(i));
        
    }
        fireTableDataChanged();
        
    }      
    }
    
    
    public void setLists(ArrayList<Base> list){
        bases.clear();
        fullBases.clear();
        for (int i = 0; i<list.size();i++){
            bases.add(list.get(i));
            fullBases.add(list.get(i));
        }
        
        
        fireTableDataChanged();
        
    }
    
    public ArrayList<Base> getListOfBases(){
        
        return fullBases;
    }
    
}
