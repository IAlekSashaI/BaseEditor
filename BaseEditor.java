/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;



import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

/**
 *
 * @author Alexander
 */
public class BaseEditor extends JFrame{

    
    private BaseTable baseTable;
    private JButton create;
    private JTabbedPane tabbedPane;
    private static BaseEditor baseEditor;
    private WebTextField serchField;
    
    public ArrayList<Base> list;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        baseEditor = new BaseEditor();
        baseEditor.setSize(1100, 520);
        baseEditor.setLocationRelativeTo(null);
        baseEditor.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseEditor.setVisible(true);
        
        
    }
    
    public BaseEditor() throws HeadlessException{
        super("Редактор баз");
         
         
         

        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BorderLayout());
        
        
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitpane.setLeftComponent(leftpanel);
        
        
        
        initTable();
        initCreateButton();
        initTabbedPane();
        initSerch();

        leftpanel.add(serchField,BorderLayout.NORTH);
        leftpanel.add(new JScrollPane(baseTable), BorderLayout.CENTER);
        leftpanel.add(create, BorderLayout.SOUTH);
        splitpane.setRightComponent(tabbedPane);
        
        getContentPane().add(splitpane);
    }
    
    private void initSerch(){
        
         serchField = new WebTextField ( 15 );
        serchField.setInputPrompt ( "Введите базу..." );
        serchField.setHideInputPromptOnFocus ( false );

         
        
                 
        serchField.addKeyListener(new KeyListener() {
             @Override
             public void keyTyped(KeyEvent e) {
                 
             }

             @Override
             public void keyPressed(KeyEvent e) {
                 
             }

             @Override
             public void keyReleased(KeyEvent e) {
                 ((BaseTableModel) baseTable.getModel()).setSerch(serchField.getText());
                 updateBasesList();
                 if (serchField.getText().equals("")){
                     ((BaseTableModel) baseTable.getModel()).setLists(list);
                 }
                 System.out.println("opa");
                 
             }
         });

        
        
    }
    
    
    private void initTabbedPane(){
        tabbedPane = new JTabbedPane();
    }
    
    
    private void initTable(){
        baseTable = new BaseTable();
        list = new ArrayList<Base>();
        updateBasesList();
        
        ((BaseTableModel) baseTable.getModel()).setLists(list);
        
        baseTable.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
               int t = e.getClickCount();
               if (t==2){
                  
                   
                   int i = baseTable.getSelectedRow();
                   Base base = ((BaseTableModel) baseTable.getModel()).getBase(i);
                  int ind = tabbedPane.indexOfTab(base.getName());   
                  if (ind>-1){
                      tabbedPane.setSelectedIndex(ind);
                      ((BaseEditing) tabbedPane.getComponentAt(ind)).setBase(base);
                  }else{
                   BaseEditing baseEditing = new BaseEditing();
     
                   baseEditing.setBase(base);
                baseEditing.setEditor(baseEditor);
                tabbedPane.addTab(base.getName(), baseEditing);
                  }  
               }
               
               
               }
});
        
        
        
        
        
        
    }
    
    private void initCreateButton(){
        create = new JButton();
        
        create.setText("Добавить новую");
        
        create.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseEditing baseEditing = new BaseEditing();
                baseEditing.setEditor(baseEditor);
                tabbedPane.addTab("Новая база", baseEditing);
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
                
                
            }
        });
        
        
        
    }
    
    public void setNewAll(Base base){
      int i = tabbedPane.getSelectedIndex();
      tabbedPane.setTitleAt(i, base.getName());
       ((BaseTableModel) baseTable.getModel()).addBase(base);
       list = ((BaseTableModel) baseTable.getModel()).getListOfBases();
       updateBasesList();
       
    }
    
    public void setUpdate(String name){
        int i = tabbedPane.getSelectedIndex();
      tabbedPane.setTitleAt(i, name);
      ((BaseTableModel) baseTable.getModel()).fireTableDataChanged();
      list = ((BaseTableModel) baseTable.getModel()).getListOfBases();
      
       updateBasesList();
    }
    
    public void deliting(Base base){
        int i = tabbedPane.getSelectedIndex();
        tabbedPane.remove(i);
        ((BaseTableModel) baseTable.getModel()).deleteBase(base);
        list = ((BaseTableModel) baseTable.getModel()).getListOfBases();
        updateBasesList();
    }
    
 public void closing(){
     int i = tabbedPane.getSelectedIndex();
        tabbedPane.remove(i);
 }
 
 public ArrayList<Base> getFullList(){
     
     return list;
 }
 
 
 public void updateBasesList(){
     try{
     
     
     
     Socket socket = new Socket("127.0.0.1",9090);
     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeUnshared(list);
     ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
           Object basi = ois.readUnshared();
           list = (ArrayList<Base>) basi; 
            System.out.println("prishlo");
           
             
           
           System.out.println(list.size());
           
           oos.close();
            ois.close();
            socket.close();
            
     }
     catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
 }
    
    
}
