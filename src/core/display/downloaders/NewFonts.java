package core.display.downloaders;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class NewFonts {
    public static Font manrope;
    public NewFonts(){
        downloadManrope();
    }
    private void downloadManrope(){
        try{
            InputStream inputStream=getClass().getResourceAsStream("/fonts/Manrope-Regular.ttf");
            manrope = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            manrope=manrope.deriveFont(30f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
