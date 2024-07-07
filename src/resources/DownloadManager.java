package resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class DownloadManager {
    public static Image frameIcon;
    public static Image redChart,blueChart;
    public DownloadManager(){
        downloadImages();
        NewFonts newfonts=new NewFonts();
    }
    private void downloadImages(){
        final String imagePath="/resources/images/";
        try {
            frameIcon= ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath + "frame title.png")));
            redChart= ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath + "wykresCzerwony.png")));
            blueChart= ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath + "wykresNiebieski.png")));
        } catch (IOException e) {
            System.out.println("BLAD W POBRANIU ZDJEC");
            throw new RuntimeException(e);
        }
    }
}
