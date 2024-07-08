package core.currencies;

import core.display.ButtonsManager;
import core.display.CurrencyCalculator;
import core.display.CurrencyPainter;
import core.display.MainPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrenciesManager {
    private static String currencyValuesDate;       //Currencies update date
    private static ArrayList<Currency> currencies;  //All currencies
    private final CurrencyCalculator calculator;    //Converter between currencies
    private CurrencyRandomizer currencyRandomizer;  //2 Random currencies picker
    public final CurrencyPainter currencyPainter;   //Draw Currencies on screen
    public final ButtonsManager buttonsManager;     //Create and manage ComboBox with JTextField
    private BigDecimal calculatedAnswer;            //Converted currency
    private MainPanel panel;                        //JPanel on JFrame to draw on
    public CurrenciesManager(){
        createCurrencies();
        currencyRandomizer=new CurrencyRandomizer();
        currencyPainter=new CurrencyPainter();
        currencyPainter.setRandomCurrencies(currencyRandomizer.getRandomCurrencies()); //set random currencies to draw
        addDefaultCurrenciesToDraw();                                                  //set currencies to draw chosen by default on upper panel (PLN and EUR)
        calculator=new CurrencyCalculator(currencies);                                 //
        calculatedAnswer=BigDecimal.valueOf(0);                                        //Default converted number is 0
        buttonsManager=new ButtonsManager(this);                                       //Create buttons
    }

    /**
     * Create individual currency classes with data obtained from API
     */
    private void createCurrencies(){
        //Get currencies from API
        CurrencyDownloader currencyDownloader=new CurrencyDownloader();
        currencyDownloader.startDownload();
        currencyValuesDate=currencyDownloader.getCurrencyValuesDate();
        JSONArray rates=currencyDownloader.getRates();
        //Create currencies array list and add PLN
        currencies=new ArrayList<>(); //All currency classes stored
        currencies.add(new Currency("PLN","polski zloty",1.0));
        //loop through all JSONObjects and create Currency objects from them
        Currency currency;
        for(Object obj: rates){
            JSONObject jsonObject=(JSONObject) obj; //cast Object as JSONObject
            currency=new Currency();
            currency.setCode((String)jsonObject.get("code"));
            currency.setCurrency((String)jsonObject.get("currency"));
            currency.setValueRelativeToPLN((Double)jsonObject.get("mid"));
            currencies.add(currency);
            //System.out.println(currency.getCurrency()+"\n"+currency.getCode()+"\n"+currency.getValueRelativeToPLN());
        }
    }

    /**
     * Method what gets called everytime ComboBox or JTextField in ButtonManager gets changed
     * @param codeInput Original currency code
     * @param codeAnswer Converted currency code
     * @param inputText Number put in to convert
     */
    public void initiateCalculation(String codeInput, String codeAnswer,String inputText){
        //Currency conversion, pass arguments from ButtonsManager
        calculator.giveCodes(codeInput, codeAnswer,inputText);
        calculatedAnswer=calculator.getRoundedAnswer();
        //Prepare currency values in the upper panel (lower part) to display
        ArrayList<Currency> currenciesToDisplay=new ArrayList<>();
        for(Currency currency: currencies){
            if(currency.getCode().equals(codeInput))
                currenciesToDisplay.add(currency);
            if(currency.getCode().equals(codeAnswer))
                currenciesToDisplay.add(currency);
        }
        currencyPainter.updateTwoDisplayedCurrencies(currenciesToDisplay);
        //Force panel to redraw everything
        panel.repaint();
    }
    private void addDefaultCurrenciesToDraw(){
        ArrayList<Currency> defaultCurrencies=new ArrayList<>();
        defaultCurrencies.add(currencies.get(0));
        defaultCurrencies.add(currencies.get(8));
        currencyPainter.updateTwoDisplayedCurrencies(defaultCurrencies);
    }

    public static ArrayList<Currency> getCurrencies(){
        return currencies;
    }
    public static String getCurrencyValuesDate() {
        return currencyValuesDate;
    }
    public BigDecimal getCalculatedAnswer(){
        return calculatedAnswer;
    }
    public void setPanel(MainPanel panel){
        this.panel=panel;
    }
}
