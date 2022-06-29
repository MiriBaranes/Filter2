

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GetImageFromFacebook implements Runnable {
    public static final String ERROR = "Error! this image/profile not exist!";
    public static final String URL_FACEBOOK = "https://facebook.com/";
    public static final String EMAIL_OR_PASSWORD = "Naamabbitan@gmail.com";
    public static final String PASSWORD = "Abcd12";
    private boolean running;
    private final MainPanel panel;

    public GetImageFromFacebook(MainPanel basicJPanel) {
        this.panel = basicJPanel;
    }

    public void startAgain() {
        this.panel.setMessage("");
        this.running = true;
        run();
    }

    @Override
    public void run() {
        while (running) {
            Util.sleep();
            run_();
        }
    }

    public void stop() {
        this.running = false;
    }

    public void run_() {
        List<WebElement> images = panel.getDriver().findElements(By.cssSelector("div > a > div > svg > g > image"));
        if (images.size() > 0) {
            try {
                URL imageURL = new URL(images.get(0).getAttribute("xlink:href"));
                boolean change = panel.isSetOriginalImage(imageURL);
                if (change) {
                    stop();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            panel.setMessage(ERROR);
            stop();
        }
    }

}

