/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;

import javax.swing.JTable;

/**
 *
 * @author Alexander
 */
public class BaseTable extends JTable{
    
    public BaseTable(){
        super(new BaseTableModel());
        BaseTableCellRender renderer = new BaseTableCellRender();
        setDefaultRenderer(Boolean.class, renderer);
        setDefaultRenderer(Integer.class, renderer);
        setDefaultRenderer(String.class, renderer);
    }
    
}
