package core.display;

import core.currencies.CurrenciesManager;
import resources.NewFonts;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public static final int width=450,height=750;
    private final CurrenciesManager manager;
    private JComboBox editableChooser,answerChooser;
    private JTextField inputText;
    public MainPanel(CurrenciesManager manager){
        setBackground(new Color(245,246,249));
        setPreferredSize(new Dimension(width,height));
        setMinimumSize(new Dimension(450,750));
        setLayout(null);
        this.manager=manager;
        //Adding Comboboxes
        editableChooser=manager.buttonsManager.getEditableChooser();
        answerChooser=manager.buttonsManager.getAnswerChooser();
        add(editableChooser);
        add(answerChooser);
        //Adding text fields
        inputText=manager.buttonsManager.getInputText();
        add(inputText);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(g2d);
        drawUpperPanel(g2d);
        drawLowerPanels(g2d);
        drawDatePanel(g2d);
        drawTexts(g2d);
        manager.currencyPainter.drawDate(g2d);
        manager.currencyPainter.drawCalculatedAnswer(g2d,manager.getCalculatedAnswer());
        manager.currencyPainter.drawRandomCurrencies(g2d);
        manager.currencyPainter.drawCurrentCurrencies(g2d);
    }
    private void drawBackground(Graphics2D g2d){
        //Background
        g2d.setPaint(new GradientPaint(0,0,new Color(149, 237, 221),200,400,new Color(200,246,249),true));
        g2d.fillRect(0,0,width,height);
    }
    private void drawUpperPanel(Graphics2D g2d){
        //upper background
        g2d.setPaint(new GradientPaint(0,0,new Color(138,142,165),220,200,new Color(100,115,140),true));
        g2d.fillRoundRect(0,-70,width,height/2,70,70);
        //2 smaller panels
        g2d.setPaint(new GradientPaint(0,0,new Color(255,255,255),250,50,new Color(235, 239, 239),true));
        g2d.fillRoundRect(10,70,width-20,70,40,40); //Enter text
        g2d.fillRoundRect(10,150,width-20,70,40,40); //Calculated answer
    }
    private void drawLowerPanels(Graphics2D g2d){
        //2 Smaller panels
        g2d.fillRoundRect(10,height/2+50,width-20,70,40,40);
        g2d.fillRoundRect(10,height/2+130,width-20,70,40,40);
    }
    private void drawDatePanel(Graphics2D g2d){
        g2d.fillRoundRect(width-130,height/2,120,30,20,20);
    }
    private void drawTexts(Graphics2D g2d){
        //Upper panel text
        g2d.setFont(NewFonts.manrope.deriveFont(Font.PLAIN,30f));
        g2d.setColor(Color.WHITE);
        String displayText="Wymiana walut";
        int textWidth=g2d.getFontMetrics().stringWidth(displayText);
        g2d.drawString(displayText,width/2-textWidth/2,35+NewFonts.manrope.getSize()/2);
        //Lower panel text
        g2d.setColor(new Color(149, 149, 149));
        displayText="Obecne kursy";
        textWidth=g2d.getFontMetrics().stringWidth(displayText);
        g2d.drawString(displayText,(width-120-textWidth)/2,height/2+20);
        //Middle text: Date
        displayText="data aktualizacji";
        g2d.setFont(NewFonts.manrope.deriveFont(13f));
        textWidth=g2d.getFontMetrics().stringWidth(displayText);
        g2d.drawString(displayText,width-(140+textWidth)/2,height/2-5);
    }
}
