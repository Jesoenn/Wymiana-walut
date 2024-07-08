package core.display;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import core.currencies.CurrenciesManager;
import core.currencies.Currency;
import core.display.downloaders.NewFonts;

import javax.swing.*;

import static core.display.MainPanel.width;

public class ButtonsManager {
    private JComboBox editableChooser,answerChooser;
    private JTextField inputText; // user input for amount of specified currency
    private CurrenciesManager manager;
    private ArrayList<String> currencyCodes;
    private ButtonChangesListener itemListener;
    public ButtonsManager(CurrenciesManager manager){
        this.manager=manager;
        createCurrencyCodes(CurrenciesManager.getCurrencies());

        //TEXT INPUT SETTINGS
        inputText=new JTextField();
        setInputTextParameters(inputText);

        //COMBOBOX settings
        itemListener=new ButtonChangesListener(this);
        editableChooser=new JComboBox(currencyCodes.toArray());
        answerChooser=new JComboBox(currencyCodes.toArray());
        setComboBoxParameters(editableChooser,1,24);
        setComboBoxParameters(answerChooser,2,9);
    }

    /**
     * Extracting currency codes from Currency objects
     */
    private void createCurrencyCodes(ArrayList<Currency> currencies){
        currencyCodes=new ArrayList<>();
        for(Currency currency: currencies){
            currencyCodes.add(currency.getCode());
        }
        currencyCodes.sort(String::compareToIgnoreCase);
    }
    private void setComboBoxParameters(JComboBox comboBox,int row,int index){
        comboBox.setSelectedIndex(index);
        comboBox.setBounds(width-110,85+(row-1)*80,70,40);
        comboBox.addItemListener(itemListener);
        comboBox.setVisible(true);
    }
    private void setInputTextParameters(JTextField textField){
        textField.setBounds(30,80,width-160,50);
        textField.setFont(NewFonts.manrope.deriveFont(Font.BOLD,21f));
        textField.setSelectionColor(new Color(190,190,190));
        textField.setSelectedTextColor(new Color(138,142,165));
        textField.setForeground(new Color(138,142,165));
        textField.setText("Enter number to convert");
        textField.setBorder(null);
        textField.setOpaque(false);
        //After pressing the text, default tip disappears
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
                textField.removeFocusListener(this);
            }
            @Override
            public void focusLost(FocusEvent e) {}
        });
        //Listener that checks if text format is correct - only numbers, etc
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //If typed character isn't a number or .
                if(e.getKeyChar()!='.' && !(e.getKeyChar()>='0' && e.getKeyChar()<='9')){
                    e.consume();
                    return;
                }
                //if typed character is dot for the second time
                else if(e.getKeyChar()=='.' && textField.getText().contains(".")){
                    e.consume();
                    return;
                }
                //if text is longer than 12 characters
                if(textField.getText().length()>12)
                    e.consume();
                //if there is more than 2 numbers after .
                if(textField.getText().contains(".") && textField.getText().length()-textField.getText().indexOf(".")>2)
                    e.consume();
                //if the first number is zero and user types any other than .
                if(textField.getText().length()==1 && textField.getText().indexOf("0") == 0 && e.getKeyChar() != '0' && e.getKeyChar()!='.')
                    textField.setText("");
                //If user tries to press . as first number
                if(textField.getText().length()==0 && e.getKeyChar()=='.')
                    textField.setText("0");
            }
            @Override
            public void keyReleased(KeyEvent e) {
                buttonsChanged();
            }
        });
        textField.setEditable(true);
        textField.setVisible(true);
    }

    /**
     * When comboBox is changed this method gets called by ButtonChangeListener
     * which initiates currency conversion in CurrencyManager
     */
    public void buttonsChanged(){
        manager.initiateCalculation((String)editableChooser.getSelectedItem(),(String)answerChooser.getSelectedItem(),inputText.getText());
    }
    public JComboBox getEditableChooser() {
        return editableChooser;
    }
    public JComboBox getAnswerChooser() {
        return answerChooser;
    }
    public JTextField getInputText(){
        return inputText;
    }
}
