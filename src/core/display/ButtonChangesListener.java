package core.display;

import core.currencies.CurrenciesManager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonChangesListener implements ItemListener {
    public boolean changed;
    private String newCurrencyCode;
    private ButtonsManager manager;
    public ButtonChangesListener(ButtonsManager manager){
        this.manager=manager;
    }
    //First change -> current selected item
    //Second change -> new selected item
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(changed){
            newCurrencyCode=(String) e.getItem();
            System.out.println(newCurrencyCode);
            changed=false;
            manager.buttonsChanged();
        }
        else
            changed=true;
    }
}
