package com.testautomation.framework.utils;

import com.testautomation.framework.base.DataConfig;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotGenarator {

    DataConfig dataConfig=null;
    public ScreenshotGenarator(DataConfig dataConfig){
        this.dataConfig=dataConfig;
    }
    public String getScreenshot(String path) throws FileNotFoundException {
        String screenshotPath = null;

        if(path == null) {
            String screenshotFileName = dataConfig.testMethodName + "_[" + DateAndTime.getTime() + "]_" + DateAndTime.getDate();
            screenshotPath = Prop.getProperty("extent.screenshots.path") + screenshotFileName + ".png";
            path = DataConfig.workDir+Prop.getProperty("extent.htmlreports.file") + screenshotPath;

        }

        File file = new File(path);
        path = screenshotPath;

        byte[] screenshot = new byte[0];

        AShot shot = new AShot();
        shot = shot.shootingStrategy(ShootingStrategies.viewportPasting(100));
        Screenshot screen = shot.takeScreenshot(dataConfig.driver);
        BufferedImage originalImage = screen.getImage();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();
            screenshot = baos.toByteArray();
            baos.close();
            InputStream in = new ByteArrayInputStream(screenshot);
            BufferedImage image = ImageIO.read(in);

            ImageIO.write(image, "png", file);
        } catch (Exception noScreenshot) {
        }
        return path;
    }
}
