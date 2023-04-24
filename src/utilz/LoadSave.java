package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Crabby;
import static utilz.constants.EnemyConstants.CRABBY;

import java.awt.Color;

import main.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "MCharacter.png";
    public static final String LEVEL_ATLAS = "dm7.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String CRAPPY_SPRITE = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";


    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

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

    public static ArrayList<Crabby> getCrabs(){
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();
        for(int j = 0; j <img.getHeight(); j++)
            for (int i = 0; i < img. getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                // lvlData[j][i] = color.getRed();
                int value = color.getGreen();
                if(value ==CRABBY)
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
        }
        return list;
    
    }

    public static int[][] GetLevelData() {
        int[][] lvlData = new int [Game.TILES_IN_HEIGHT] [Game.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j <img.getHeight(); j++)
            for (int i = 0; i < img. getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                lvlData[j][i] = color.getRed();
                int value = color.getRed();
                if(value >=48)
                value = 0;
                lvlData[j][i]= value;
        }
        return lvlData;
    }
}
