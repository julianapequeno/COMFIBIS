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
public class OtimizacaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
     public Button transparente;
    
    @FXML
     public Button VoltarTelaPrincipal;
    
    public void VoltarTelaPrincipal() throws IOException{
            
            Stage stage1 = (Stage) transparente.getScene().getWindow();
            stage1.getIcons().add(new Image ("/images/LOGO.png"));        
            stage1.close();
        
            //FXMLLoader root = new FXMLLoader(this.getClass().getResource("PSOJavarduino.fxml"));
          // Parent par = root.load();
            
            URL url = getClass().getResource("/psojavarduino/TelaInicial.fxml");
            Parent root = FXMLLoader.load(url);            
            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image ("/images/LOGO.png"));
            stage.setScene(new Scene(root));
           
            stage.show();
    }
    
    public void ProximaTela() throws IOException{
            
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
    
}
