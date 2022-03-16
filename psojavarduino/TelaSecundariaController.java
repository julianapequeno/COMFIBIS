/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psojavarduino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TelaSecundariaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaSecundaria.fxml"));
        
        Scene scene = new Scene(root);
        //stage.getIcons().add(new Image ("imagens/logo.jpeg"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public Button testagem;
    
    @FXML
    public Button transparente;
    
    public void TESTAGEM() throws IOException{
            
            Stage stage1 = (Stage) transparente.getScene().getWindow();
            stage1.getIcons().add(new Image ("/images/LOGO.png"));        
            stage1.close();
        
            //FXMLLoader root = new FXMLLoader(this.getClass().getResource("PSOJavarduino.fxml"));
          // Parent par = root.load();
            
            URL url = getClass().getResource("/psojavarduino/PSOGWO.fxml");
            Parent root = FXMLLoader.load(url);            
            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image ("/images/LOGO.png"));
            stage.setScene(new Scene(root));
           
            stage.show();
    }
    
    public void INTERDISCIPLINARIDADE() throws IOException{
            
            Stage stage1 = (Stage) transparente.getScene().getWindow();
            stage1.getIcons().add(new Image ("/images/LOGO.png"));        
            stage1.close();
        
            //FXMLLoader root = new FXMLLoader(this.getClass().getResource("PSsOJavarduino.fxml"));
          // Parent par = root.load();
            
            URL url = getClass().getResource("/psojavarduino/MatematicaIntroducao.fxml");
            Parent root = FXMLLoader.load(url);            
            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image ("/images/LOGO.png"));
            stage.setScene(new Scene(root));
           
            stage.show();
    }
    
    public void Voltar() throws IOException{
            
            Stage stage1 = (Stage) transparente.getScene().getWindow();
            stage1.getIcons().add(new Image ("/images/LOGO.png"));        
            stage1.close();
        
            //FXMLLoader root = new FXMLLoader(this.getClass().getResource("PSOJavarduino.fxml"));
          // Parent par = root.load();
            
            URL url = getClass().getResource("/psojavarduino/Bioinspiracao.fxml");
            Parent root = FXMLLoader.load(url);            
            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image ("/images/LOGO.png"));
            stage.setScene(new Scene(root));
           
            stage.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
