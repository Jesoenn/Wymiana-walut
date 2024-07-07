package resources;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NewFonts {
    public static Font manrope;
    public NewFonts(){
        downloadManrope();
    }
    private void downloadManrope(){
        String mainPath="src/resources/fonts/";
        try{
            manrope = Font.createFont(Font.TRUETYPE_FONT, new File(mainPath+"Manrope-Regular.ttf"));
            manrope=manrope.deriveFont(30f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
