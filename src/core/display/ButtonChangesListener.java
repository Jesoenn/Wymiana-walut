package core.display;

import core.currencies.CurrenciesManager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonChangesListener implements ItemListener {
    public boolean changed;
    private ButtonsManager manager;
    public ButtonChangesListener(ButtonsManager manager){
        this.manager=manager;
    }
    //First change -> current selected item
    //Second change -> new selected item
    @Override
    public void itemStateChanged(ItemEvent e) {
        //Second change
        if(changed){
            changed=false;
            manager.buttonsChanged();
        }
        //First change
        else
            changed=true;
    }
}
