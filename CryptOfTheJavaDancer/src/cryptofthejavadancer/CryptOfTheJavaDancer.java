/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptofthejavadancer;

import cryptofthejavadancer.IHM.Scene.ScenePrincipale;
import cryptofthejavadancer.Model.Carte.Map;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Matthieu
 */
public class CryptOfTheJavaDancer extends Application {
    
    @Override
    public void start(Stage primaryStage) {
               
        ScenePrincipale scene = new ScenePrincipale(new Pane(), 881, 881);
        
        primaryStage.setTitle("The Crypt of the JavaDancer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        Map niveau1 = new Map();
        try {
            niveau1.chargerFichier("level1.txt");
            scene.start(niveau1);
        } catch (IOException ex) {
            System.out.println("Impossible de charger le niveau");
        }
        
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
