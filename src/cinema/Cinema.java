/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.CinemaConsts;

/**
 *
 * @author Roger
 */
public class Cinema extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMain.fxml"));
        
        Scene scene = new Scene(root);
 
        stage.setTitle(CinemaConsts.TITLE_MAIN);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



//package cinema;
//
//import util.ValidaDataException;
//import view.MainUI;
//
//public class Cinema {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws ValidaDataException {
//        // TODO code application logic here
//        new MainUI().executar();
//    }
//    
//}
