package core.currencies;

import java.util.ArrayList;
import java.util.Random;

public class CurrencyRandomizer {
    private ArrayList<Currency> currencies;
    private ArrayList<Currency> randomCurrencies;
    public CurrencyRandomizer(){
        currencies=CurrenciesManager.getCurrencies();
        pickRandomCurrencies();
    }
    private void pickRandomCurrencies(){
        Random randomNumber=new Random();
        int howManyCurrencies=currencies.size();
        randomCurrencies=new ArrayList<>();
        int generatedNumber;
        for(int i=0; i<2; i++){
            generatedNumber=randomNumber.nextInt(howManyCurrencies);
            randomCurrencies.add(currencies.get(generatedNumber));
        }
    }

    public ArrayList<Currency> getRandomCurrencies() {
        return randomCurrencies;
    }
}
