package core.display;

import core.currencies.Currency;

import java.lang.reflect.AnnotatedArrayType;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrencyCalculator {
    private Double valueRelativeToPLNInput,valueRelativeToPLNAnswer,givenInputToCalculate;
    private ArrayList<Currency> currencies; //All currencies
    private BigDecimal roundedAnswer=BigDecimal.valueOf(0); //Default value is 0
    public CurrencyCalculator(ArrayList<Currency> currencies){
        this.currencies=currencies;
    }

    /**
     * Get current displayed currency codes from ButtonsManager passed through CurrenciesManager
     */
    public void giveCodes(String codeInput, String codeAnswer,String inputText){
        //If number isn't typed in
        if(inputText.contains("Enter number to convert") || inputText.equals(""))
            return;
        givenInputToCalculate= Double.valueOf(inputText);
        for(Currency currency: currencies){
            if(currency.getCode().equals(codeInput))
                valueRelativeToPLNInput=currency.getValueRelativeToPLN();
            if(currency.getCode().equals(codeAnswer))
                valueRelativeToPLNAnswer=currency.getValueRelativeToPLN();
        }
        calculate();
        //System.out.println(codeInput+": "+valueRelativeToPLNInput+"\n"+codeAnswer+": "+valueRelativeToPLNAnswer);
    }
    private void calculate(){
        Double input=givenInputToCalculate*valueRelativeToPLNInput;
        double answer=input/valueRelativeToPLNAnswer;
        roundedAnswer=BigDecimal.valueOf(answer).setScale(2,BigDecimal.ROUND_HALF_UP);
    }
    public BigDecimal getRoundedAnswer(){
        return roundedAnswer;
    }
}
