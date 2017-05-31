/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseeditor;

import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebFormattedTextField;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import layout.TableLayout;

/**
 *
 * @author Alexander
 */
public class BaseEditing extends JPanel {

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
    private JButton delete;
    private JButton close;
    private Base base;
    private BaseEditor baseEditor;
    private boolean isNew = true;

    private JButton skis;
    private JButton snowboards;
    private JButton menu;

    public BaseEditing() {
        double size[][]
                = {{0.25, 0.25, 0.25, 0.25},
                {50, 40, 40, 40, 40, 40, 40, 40, 70, 50}};

        this.setLayout(new TableLayout(size));

        nameField = new JTextField("Новая база");
        nameField.setBorder(BorderFactory.createTitledBorder("Название"));
        adressField = new JTextField();
        adressField.setBorder(BorderFactory.createTitledBorder("Адрес"));

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
        hasTrainersCheckbox = new JCheckBox("Есть квалифицированные тренеры");
        hasParkingCheckbox = new JCheckBox("Парковка");
        hasCafeCheckbox = new JCheckBox("Можно купить покушать");
        hasHotelcheCheckbox = new JCheckBox("Можно переночевать");

        skis = new JButton("Лыжи");
        snowboards = new JButton("Сноуборды");
        menu = new JButton("Меню кафе");

        save = new JButton("Сохранить");
        delete = new JButton("Удалить");
        close = new JButton("Закрыть");

        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNew) {
                    base = new Base();
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
                    Random rn = new Random();
                    int nextInt = rn.nextInt(99999999 - 1 +1);
                    base.setId(nextInt);
                    isNew = false;
                    baseEditor.setNewAll(base);

                } else {

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
                    baseEditor.setUpdate(nameField.getText());
                }

            }
        });

        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseEditor.deliting(base);

            }
        });

        close.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseEditor.closing();

            }
        });

        skis.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNew) {
                    base = new Base();
                    base.setName(nameField.getText());
                    base.setAdress(adressField.getText());
                    isNew = false;
                    baseEditor.setNewAll(base);
                }

                SkisEditingDialog skisDialog = new SkisEditingDialog(baseEditor, "Редактировать список лыж", false, base, true);
             //   skisDialog.setBase(base, true);
                skisDialog.setVisible(true);
            }
        });

        snowboards.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isNew) {
                    base = new Base();
                    base.setName(nameField.getText());
                    base.setAdress(adressField.getText());
                    isNew = false;
                    baseEditor.setNewAll(base);
                }

                SkisEditingDialog snowsDialog = new SkisEditingDialog(baseEditor, "Редактировать сноуборды", false, base, false);
               // snowsDialog.setBase(base, false);
                snowsDialog.setVisible(true);
            }
        });

        menu.setVisible(false);
        nightPricePerson.setVisible(false);

        menu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNew) {
                    base = new Base();
                    base.setName(nameField.getText());
                    base.setAdress(adressField.getText());
                    isNew = false;
                    baseEditor.setNewAll(base);
                }

                FoodEditingDialog foodDialog = new FoodEditingDialog(baseEditor, "Редактировать сноуборды", false, base);
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
        this.add(save, "0,9,1,9");
        this.add(delete, "2,9");
        this.add(close, "3,9");
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        isNew = false;
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

        this.base = base;

    }

    public void setEditor(BaseEditor baseEditor) {

        this.baseEditor = baseEditor;

    }

}
