package core.display;

import resources.DownloadManager;

import javax.swing.*;
import java.awt.*;

public class CreateFrame extends JFrame {
    public CreateFrame(){
        DownloadManager download=new DownloadManager();
        setMinimumSize(new Dimension(466,789));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Currency Conventer");
        setIconImage(DownloadManager.frameIcon);
        setVisible(true);
    }
}
