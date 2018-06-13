package cryptofthejavadancer.Model.Carte;

import java.util.Random;

/**
 * Générateur alétoire unifié
 * @author Matthieu
 */
public class Aleatoire {

//---------- CONSTRUCTEURS -----------------------------------------------------

    private static Random instance;                                             //singleton
    
//------------------------------------------------------------------------------

//---------- GETEUR/SETEUR -----------------------------------------------------

    private Aleatoire(){};
    
    public static Random get() {
        if(instance == null) {
            instance = new Random();
        }
        return instance;
    }
    
    public static int getInt(int borneMin, int borneMax) {
        return get().nextInt(borneMax-borneMin)+borneMin;
    }
    
//------------------------------------------------------------------------------

}
