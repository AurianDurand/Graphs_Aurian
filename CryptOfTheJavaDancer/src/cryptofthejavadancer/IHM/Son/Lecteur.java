package cryptofthejavadancer.IHM.Son;

import java.net.URL;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Gestionnaire de son
 * @author Matthieu
 */
public class Lecteur {

    private static Lecteur instance;                                            //Singleton
    private HashMap<String,MediaPlayer> listeMediaPlayer;                       //Multiton
    
//---------- CONSTRUCTEURS -----------------------------------------------------

    private Lecteur() {
        this.listeMediaPlayer = new HashMap<>();                                   
    }
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    //Geteur du singleton
    public static Lecteur get() {
        if(instance == null) {
            instance = new Lecteur();
        }
        return instance;
    }
    
//------------------------------------------------------------------------------

    //Lance une musique (sans la recharger si elle est déjà en mémoire)
    public static void play(String nomFichier,boolean boucle,double volume) {
        if(get().listeMediaPlayer.get(nomFichier) == null) {
            URL url = get().getClass().getResource("/cryptofthejavadancer/IHM/Son/Musiques/"+nomFichier);
            Media media = new Media(url.toExternalForm());
            get().listeMediaPlayer.put(nomFichier, new MediaPlayer(media));
        }
        get().listeMediaPlayer.get(nomFichier).setVolume(volume);
        if(boucle) {
            get().listeMediaPlayer.get(nomFichier).setCycleCount(MediaPlayer.INDEFINITE);
        }
        else {
            get().listeMediaPlayer.get(nomFichier).setCycleCount(1);
        }
        get().listeMediaPlayer.get(nomFichier).stop();
        get().listeMediaPlayer.get(nomFichier).play();
    }
    
    //Arrete toutes les musiques
    public static void stopAll() {
        for(MediaPlayer media : get().listeMediaPlayer.values()) {
            media.stop();
        }
    }
    
}
