package core;

import core.currencies.CurrenciesManager;
import core.display.CreateFrame;
import core.display.MainPanel;

public class Main {
    public static void main(String[] args) {
        CreateFrame window=new CreateFrame();
        CurrenciesManager manager=new CurrenciesManager();
        MainPanel panel=new MainPanel(manager);
        manager.setPanel(panel);
        window.add(panel);
        window.pack();
    }
}
