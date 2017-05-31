/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;

import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebFormattedTextField;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import layout.TableLayout;

/**
 *
 * @author Alexander
 */
public class Baseclient extends JFrame {
private static Baseclient baseClient;
    

private JTextField nameField;
private JTextField adressField;

private WebSpinner blackSpiner;
private WebSpinner greenSpiner;
private WebSpinner blueSpiner;
private WebSpinner redSpiner;

private WebFormattedTextField hourPriceField;
private WebFormattedTextField nightPricePerson;

private JCheckBox isLightedCheckbox;
private JCheckBox hasTrainersCheckbox;
private JCheckBox hasParkingCheckbox;
private JCheckBox hasCafeCheckbox;
private JCheckBox hasHotelcheCheckbox;
    
private JButton save;

private JTextField skiId;
private JTextField snowId;
private JTextField skiOwner;
private JTextField snowOwner;

private JButton selSki;
private JButton selSnow;

private JButton prSki;
private JButton prSnow;

private JButton skis;
private JButton snowboards;
private JButton menu;

private Base base;

private JTextField id;

private JButton connect;

public ArrayList<Base> list;

/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         baseClient = new Baseclient();
        baseClient.setSize(700, 650);
        baseClient.setLocationRelativeTo(null);
        baseClient.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        baseClient.setVisible(true);
        
    }
    
    public Baseclient() throws HeadlessException{
        super("Редактор базы");
        
        double size[][]
                = {{0.25, 0.25, 0.25, 0.25},
                {50, 40, 40, 40, 40, 40, 40, 40, 70, 50, 50,50,50}};

        this.setLayout(new TableLayout(size));
         list = new ArrayList<Base>();
        updateBasesList();
         
        nameField = new JTextField("Новая база");
        nameField.setBorder(BorderFactory.createTitledBorder("Название"));
        adressField = new JTextField();
        adressField.setBorder(BorderFactory.createTitledBorder("Адрес"));

        id = new JTextField();
        id.setBorder(BorderFactory.createTitledBorder("id"));
        connect = new JButton("Подключиться");
        
        blackSpiner = new WebSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        blackSpiner.setBorder(BorderFactory.createTitledBorder("Кол-во черных трасс"));
        greenSpiner = new WebSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        greenSpiner.setBorder(BorderFactory.createTitledBorder("Кол-во зеленых трасс"));
        blueSpiner = new WebSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        blueSpiner.setBorder(BorderFactory.createTitledBorder("Кол-во синих трасс"));
        redSpiner = new WebSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
        redSpiner.setBorder(BorderFactory.createTitledBorder("Кол-во красных трасс"));

        hourPriceField = new WebFormattedTextField(0);
        hourPriceField.setBorder(BorderFactory.createTitledBorder("Цена за час"));
        nightPricePerson = new WebFormattedTextField(0);
        nightPricePerson.setBorder(BorderFactory.createTitledBorder("Цена за ночь за человека"));

        isLightedCheckbox = new JCheckBox("Есть искусственное освещение");
        hasTrainersCheckbox = new JCheckBox("Есть обученные тренеры");
        hasParkingCheckbox = new JCheckBox("Парковка");
        hasCafeCheckbox = new JCheckBox("Можно купить покушать");
        hasHotelcheCheckbox = new JCheckBox("Можно переночевать");

        skis = new JButton("Лыжи");
        snowboards = new JButton("Сноуборды");
        menu = new JButton("Меню кафе");

        save = new JButton("Сохранить");
        
        selSki = new JButton("Сдать лыжи");
        selSnow = new JButton("Сдать сноуборд");
        
        skiId = new JTextField();
        skiId.setBorder(BorderFactory.createTitledBorder("id лыж"));
        
        snowId = new JTextField();
        snowId.setBorder(BorderFactory.createTitledBorder("id сноуборда"));
        
        skiOwner = new JTextField();
        skiOwner.setBorder(BorderFactory.createTitledBorder("Арендователь"));
        
        snowOwner = new JTextField();
        snowOwner.setBorder(BorderFactory.createTitledBorder("Арендователь"));
               
       selSki.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i< base.getSkis().size();i++){
                    if (skiId.getText().equals(base.getSkis().get(i).getId())){
                     base.getSkis().get(i).wasOwned(skiOwner.getText());
                     skiId.setText("");
                     skiOwner.setText("");
                     break;
                    }
                    
                }
                
                System.out.println("seled");
                
            }
        });
       
       selSnow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i< base.getSkis().size();i++){
                    if (snowId.getText().equals(base.getSnows().get(i).getId())){
                     base.getSnows().get(i).wasOwned(snowOwner.getText());
                     snowId.setText("");
                     snowOwner.setText("");
                     break;
                    }
                    
                }
                
                System.out.println("seled");
                
                
            }
        });
       
       prSki = new JButton("Принять");
       prSnow = new JButton("Принять");
       
       prSki.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                for (int i = 0; i< base.getSkis().size();i++){
                    if (skiId.getText().equals(base.getSnows().get(i).getId())){
                     base.getSkis().get(i).setFree(true);
                     base.getSkis().get(i).setOwner("");
                     snowId.setText("");
                     snowOwner.setText("");
                     break;
                    }
                    
                }
                
            }
        });
       
       prSnow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                for (int i = 0; i< base.getSkis().size();i++){
                    if (snowId.getText().equals(base.getSnows().get(i).getId())){
                     base.getSnows().get(i).setFree(true);
                     base.getSnows().get(i).setOwner("");
                     snowId.setText("");
                     snowOwner.setText("");
                     break;
                    }
                    
                }
                
            }
        });
       
        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                  
                    base.setName(nameField.getText());
                    base.setAdress(adressField.getText());
                    base.setHourPrice(Integer.parseInt(hourPriceField.getText()));
                    base.setNightPrice(Integer.parseInt(nightPricePerson.getText()));
                    base.setBlackCount(Integer.parseInt(blackSpiner.getValue().toString()));
                    base.setBlueCount(Integer.parseInt(blueSpiner.getValue().toString()));
                    base.setGreenCount(Integer.parseInt(greenSpiner.getValue().toString()));
                    base.setRedCount(Integer.parseInt(redSpiner.getValue().toString()));
                    base.setHasCaffe(hasCafeCheckbox.isSelected());
                    base.setHasHotel(hasHotelcheCheckbox.isSelected());
                    base.setHasLight(isLightedCheckbox.isSelected());
                    base.setHasParking(hasParkingCheckbox.isSelected());
                    base.setHasTrainers(hasTrainersCheckbox.isSelected());

                   updateBasesList();

                

            }
        });
        
        skis.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    
                    
                 

                SkisEditingDialog skisDialog = new SkisEditingDialog(baseClient, "Редактировать список лыж", false, base, true);
             //   skisDialog.setBase(base, true);
                skisDialog.setVisible(true);
            }
        });
        
        snowboards.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                
                    
                    
                    

                SkisEditingDialog snowsDialog = new SkisEditingDialog(baseClient, "Редактировать сноуборды", false, base, false);
              //  snowsDialog.setBase(base, false);
                snowsDialog.setVisible(true);
            }
        });
        
        menu.setVisible(false);
        nightPricePerson.setVisible(false);

        menu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    base = new Base();
                    base.setName(nameField.getText());
                    base.setAdress(adressField.getText());
                    

                FoodEditingDialog foodDialog = new FoodEditingDialog(baseClient, "Редактировать сноуборды", false, base);
              //  foodDialog.setBase(base);
                foodDialog.setVisible(true);

            }
        });
      
        
        hasCafeCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (hasCafeCheckbox.isSelected()) {
                    menu.setVisible(true);
                } else {
                    menu.setVisible(false);
                }

            }
        });
        
        hasHotelcheCheckbox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (hasHotelcheCheckbox.isSelected()) {
                    nightPricePerson.setVisible(true);
                } else {
                    nightPricePerson.setVisible(false);
                }

            }
        });
        
        
        connect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i< list.size();i++){
                  
                if (id.getText().equals(""+list.get(i).getId())){
                
                base=list.get(i);
                nameField.setText(base.getName());
        adressField.setText(base.getAdress());
        hourPriceField.setText(Integer.toString(base.getHourPrice()));
        isLightedCheckbox.setSelected(base.hasLight());
        hasTrainersCheckbox.setSelected(base.hasTrainers());
        hasCafeCheckbox.setSelected(base.hasCaffe());
        hasHotelcheCheckbox.setSelected(base.hasHotel());
        hasParkingCheckbox.setSelected(base.hasParking());
        greenSpiner.setValue(base.getGreenC());
        blueSpiner.setValue(base.getBlueC());
        redSpiner.setValue(base.getRedC());
        blackSpiner.setValue(base.getBlackC());
        nightPricePerson.setText(Integer.toString(base.getNightPrice()));
        break;
                }
            }
            }
        });
        
        
        this.add(nameField, "0,0,1,0");
        this.add(adressField, "2,0,3,0");
        this.add(hourPriceField, "3,1");
        this.add(isLightedCheckbox, "0,2,1,2");
        this.add(hasTrainersCheckbox, "0,3,1,3");
        this.add(hasCafeCheckbox, "0,4,1,4");
        this.add(menu, "2,4,3,4");
        this.add(hasHotelcheCheckbox, "0,5,1,5");
        this.add(nightPricePerson, "2,5,3,5");
        this.add(hasParkingCheckbox, "0,6,1,6");
        this.add(skis, "0,7,1,7");
        this.add(snowboards, "2,7,3,7");
        this.add(greenSpiner, "0,8");
        this.add(blueSpiner, "1,8");
        this.add(redSpiner, "2,8");
        this.add(blackSpiner, "3,8");
        this.add(save, "0,9,3,9");
        this.add(id, "2,10");
        this.add(connect, "3,10");
        this.add(skiId, "0,11");
        this.add(skiOwner, "1,11");
        this.add(selSki, "2,11");
        this.add(snowId, "0,12");
        this.add(snowOwner, "1,12");
        this.add(selSnow, "2, 12");
        this.add(prSki,"3,11");
        this.add(prSnow,"3,12");
        
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
