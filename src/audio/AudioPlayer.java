package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    public static int MENU_1 = 0;
    public static int LEVEL_1 = 1;
    public static int LEVEL_2 = 2;

    public static int DIE = 0;
    public static int JUMP = 1;
    public static int GAMEOVER = 2;
    public static int LVL_COMPLETED = 3;
    public static int ATTACK_ONE = 4;
    public static int ATTACK_TWO = 5;
    public static int ATTACK_THREE = 6;

    private Clip[] songs, effects;
    private int currentSongId; // What kinds of song we have
    private float volume = 0.5f; //koi fixed this volume to make it louder because it's still small
    private boolean songMute, effectMute;
    private Random rand = new Random(); // Attack sounds, co 3 cai

    public AudioPlayer() {
        //Clips is ca music players, co audio, open in clip, play audio whenever we want
        loadSongs();
        loadEffects();
        playSong(MENU_1);//THE SONG WE HEAR EVERYTIME WE START THE GAME
    }

    private void loadSongs()  {//all the name and array of all the songs
        String[] names = {"menu","level1","level2"};
        songs = new Clip[names.length]; // add more song, the clip also increase in size
        for (int i =0; i < songs.length;i++)
            songs[i] = getClip(names[i]);
    }

    private void loadEffects() {
        String[] effectNames = {"die","jump","gameover","lvlcompleted","attack1","attack2","attack3"}; //match the index on top
        effects = new Clip[effectNames.length]; // add more song, the clip also increase in size
        for (int i =0; i < effects.length;i++)
            effects[i] = getClip(effectNames[i]);

        updateEffectsVolume(); // update to 0.5f

    }
    private Clip getClip(String name) {
        URL url = getClass().getResource("/audio/" + name + ".wav"); //tim trong file audio
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);  // Open clip and put audio in
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {// Diffrent type of errors it can return
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }

    public void stopSong(){
        if (songs[currentSongId].isActive())
            songs[currentSongId].stop();
    }

    public void setLevelSong (int lvlIndex){
        //switch level have different songs
        if (lvlIndex % 2 ==0)
            playSong(LEVEL_1);
        else
            playSong(LEVEL_2);
    }
    public void lvlCompleted(){
        stopSong();
        playEffect(LVL_COMPLETED);
    }

    public void playAttackSound() {
        int start = 4; //4 là ATTACK ONE
        start += rand.nextInt(3);
        playEffect(start);
    }

    //playeffect
    public void playEffect(int effect){
        effects[effect].setMicrosecondPosition(0);//thoi gian nghe nhac
        effects[effect].start(); // playeffect
    }
    public void playSong(int song){
        //stop the previous song to play the new one
            stopSong();
            currentSongId = song;
            updateSongVolume(); //apply the volume before change song
            songs[currentSongId].setMicrosecondPosition(0);
            songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);//loop background song until the end of time
    }

    // mute
    public void toggleSongMute() {
        this.songMute = !songMute;
        for (Clip c: songs){
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);//MUTE có the false hoac true
         booleanControl.setValue(songMute);
        }
    }

    public void toggleEffectMute(){
        this.effectMute = !effectMute;
        for (Clip c: effects){
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);//MUTE có the false hoac true
         booleanControl.setValue(effectMute);
        }
        //How loud that it
        if(!effectMute)
            playEffect(JUMP);
    }

    //updating volume for songs or effects
    private void updateSongVolume() {
        //All the songs is in the background
        FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);//get control for changing value,.. of the clips
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain); //Change the value
    }
    private void updateEffectsVolume(){
        //Update 1 effect not all effects
        for (Clip c: effects){
            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);//get control for changing value,.. of the clips
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain); //Change the value
        }
    }
}