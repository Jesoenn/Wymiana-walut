package core.currencies;

import core.Main;
import core.display.ButtonsManager;
import core.display.CurrencyCalculator;
import core.display.CurrencyPainter;
import core.display.MainPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrenciesManager {
    private static String currencyValuesDate;
    private static ArrayList<Currency> currencies;
    private final CurrencyCalculator calculator;
    private CurrencyRandomizer currencyRandomizer;
    public final CurrencyPainter currencyPainter;
    public final ButtonsManager buttonsManager;
    private BigDecimal calculatedAnswer;
    private MainPanel panel;
    public CurrenciesManager(){
        createCurrencies();
        currencyRandomizer=new CurrencyRandomizer();
        currencyPainter=new CurrencyPainter();
        currencyPainter.setRandomCurrencies(currencyRandomizer.getRandomCurrencies());
        addDefaultCurrenciesToDraw();
        calculator=new CurrencyCalculator(currencies);
        calculatedAnswer=BigDecimal.valueOf(0);
        buttonsManager=new ButtonsManager(this);
    }
    //Create individual currency classes with data obtained from API
    private void createCurrencies(){
        CurrencyDownloader currencyDownloader=new CurrencyDownloader();
        currencyDownloader.startDownload();
        currencyValuesDate=currencyDownloader.getCurrencyValuesDate();
        JSONArray rates=currencyDownloader.getRates();
        currencies=new ArrayList<>(); //All currency classes stored
        currencies.add(new Currency("PLN","polski zloty",1.0));
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

    public void initiateCalculation(String codeInput, String codeAnswer,String inputText){
        //Currency conversion, pass arguments from ButtonsManager
        calculator.giveCodes(codeInput, codeAnswer,inputText);
        calculatedAnswer=calculator.getRoundedAnswer();
        //Draw current currency values in upper panel
        ArrayList<Currency> currenciesToDisplay=new ArrayList<>();
        for(Currency currency: currencies){
            if(currency.getCode().equals(codeInput))
                currenciesToDisplay.add(currency);
            if(currency.getCode().equals(codeAnswer))
                currenciesToDisplay.add(currency);
        }
        currencyPainter.updateTwoDisplayedCurrencies(currenciesToDisplay);
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
    public void setCalculatedAnswer(BigDecimal calculatedAnswer){
        this.calculatedAnswer=calculatedAnswer;
    }
    public void setPanel(MainPanel panel){
        this.panel=panel;
    }
}
