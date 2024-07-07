package core.display;

import core.currencies.CurrenciesManager;
import core.currencies.Currency;
import resources.DownloadManager;
import resources.NewFonts;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import static core.display.MainPanel.height;
import static core.display.MainPanel.width;

public class CurrencyPainter {
    private ArrayList<Currency> randomCurrencies;
    private ArrayList<Currency> displayedCurrencies;
    public void drawDate(Graphics2D g2d){
        //Middle text: Actual date
        g2d.setColor(new Color(149, 149, 149));
        String displayText= CurrenciesManager.getCurrencyValuesDate();
        g2d.setFont(NewFonts.manrope.deriveFont(13f));
        int textWidth=g2d.getFontMetrics().stringWidth(displayText);
        g2d.drawString(displayText,width-130+(120-textWidth)/2,height/2+g2d.getFontMetrics().getHeight()+2);
    }
    public void drawCalculatedAnswer(Graphics2D g2d, BigDecimal answer){
        g2d.setFont(NewFonts.manrope.deriveFont(Font.BOLD,21f));
        g2d.setColor(new Color(138,142,165));
        g2d.drawString(answer.toString(),30,193);
    }
    public void drawCurrentCurrencies(Graphics2D g2d){
        int y=255;
        String text;
        int textwidth;
        for(Currency currency: displayedCurrencies){
            g2d.setFont(NewFonts.manrope.deriveFont(Font.PLAIN,16f));
            g2d.setColor(new Color(22, 223, 204));
            g2d.drawString("1",30,y);
            //currency full name and code
            g2d.setColor(Color.WHITE);
            text=currency.getCode()+" ("+currency.getCurrency()+")"+" = ";
            textwidth=g2d.getFontMetrics().stringWidth(text);
            g2d.drawString(currency.getCode()+" ("+currency.getCurrency()+")"+" = ",42,y);
            //value relative to PLN
            text=Double.toString(currency.getValueRelativeToPLN());
            g2d.setColor(new Color(22, 223, 204));
            g2d.drawString(text,43+textwidth,y);
            //postfix PLN
            g2d.setColor(Color.WHITE);
            textwidth+=g2d.getFontMetrics().stringWidth(text);
            g2d.drawString("PLN",48+textwidth,y);
            y+=30;
        }
    }
    public void drawRandomCurrencies(Graphics2D g2d){
        g2d.setColor(new Color(149, 149, 149));
        int y=460;
        for(Currency currency: randomCurrencies){
            //Code
            g2d.setFont(NewFonts.manrope.deriveFont(Font.BOLD,19f));
            g2d.drawString("1",30,y);
            g2d.drawString(currency.getCode(),43,y);
            //Full name
            g2d.setFont(NewFonts.manrope.deriveFont(Font.PLAIN,12f));
            g2d.drawString(currency.getCurrency(),30,y+20);
            //PLN
            g2d.setFont(NewFonts.manrope.deriveFont(Font.BOLD,19f));
            g2d.drawString(currency.getValueRelativeToPLN()+" PLN",width-150,y);
            g2d.setFont(NewFonts.manrope.deriveFont(Font.PLAIN,12f));
            g2d.drawString("polski zloty",width-150,y+20);
            if(currency.getValueRelativeToPLN()>=1)
                g2d.drawImage(DownloadManager.blueChart,width-280,y-20,100,40,null);
            else
                g2d.drawImage(DownloadManager.redChart,width-280,y-20,100,40,null);

            y+=80;
        }
        customLine(g2d);
    }
    public void updateTwoDisplayedCurrencies(ArrayList<Currency> currencies){
        displayedCurrencies=currencies;
    }
    public void setRandomCurrencies(ArrayList<Currency> randomCurrencies) {
        this.randomCurrencies = randomCurrencies;
    }
    private void customLine(Graphics2D g2d){

    }
}
