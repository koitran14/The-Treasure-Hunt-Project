package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {

    public static BufferedImage GetPlayerAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.getResourceAsStream("/AnimationSheet_Character.png");
        
        try{
             img = ImageIO.read(is);
          

        } catch (IOException e){
            //Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    private static InputStream getResourceAsStream(String string) {
        return null;
    }
}
