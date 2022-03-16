/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psojavarduino;
import java.awt.Button;
import java.awt.Color;
import static java.awt.Color.GREEN;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.lang.NullPointerException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import psojavarduino.Principal_1;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.image.Image;


/**
 *
 * @author Juliana Santiago <julianafpsc@gmail.com>
 */
public class FXMLDocumentController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // FillTransition ft = new FillTransition(Duration.millis(3000), pos00, Color.RED, Color.BLUE);
    // ft.play(); 
    }    
    
    @FXML
    public javafx.scene.control.Button botaotransparente;
    
    @FXML
    public Button botao;
    @FXML
    public GridPane Matriz = new GridPane();
    
    public Label l = new Label();
    
    public Label la = new Label();
    
    @FXML
    private MenuButton menuButton;
    @FXML
    public Label label = new Label();
    
    @FXML
    public TextField inercia;
    @FXML
    public TextField numeroParticles;
    @FXML
    public TextField iterações;
    @FXML
    public Label retornaFim = new Label();
    
   
    public void Voltar() throws IOException{
            
            Stage stage1 = (Stage) botaotransparente.getScene().getWindow();
            stage1.close();
        
            FXMLLoader root = new FXMLLoader(this.getClass().getResource("PSOGWO.fxml"));
            Parent par = root.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.getIcons().add(new Image ("/images/LOGO.png"));
            stage.setScene(new Scene(par));
           
            stage.show();
    }
   
    
    public GridPane getMatriz(){
        return Matriz;
    }
    
    private ArrayList<Integer> posicoesMatrizFinal = new ArrayList<>();
    
    public void functionClear(){
        inercia.setText("");
        numeroParticles.setText("");
        iterações.setText("");
        
        ////////ADICIONAR A LIMPA DA MATRIZ
        int aux2 = 0;
                while(aux2 < 1024){
                                l = (Label)  Matriz.getChildren().get(aux2);
                                l.setStyle("-fx-background-color:#FFFFFF"); 
                                l = (Label) Matriz.getChildren().get(15*32+16);
                                l.setStyle("-fx-background-color:#ff3e89");
                                l = (Label) Matriz.getChildren().get(17*32+14);
                                l.setStyle("-fx-background-color:#5ecae4");
                                aux2++;
                }
    }
    
    public void mudarInercia(){
        float Wnovo;
        try{
            Wnovo = Float.valueOf(this.inercia.getText());
            Principal_1.W = Wnovo;
        }catch(NumberFormatException s ){
            inercia.setText(""+Principal_1.W);
        }catch(NullPointerException e){
        }
    }
    
    public void mudarNP(){
        int novoNP;
        try{
            novoNP = Integer.valueOf(this.numeroParticles.getText());
            Principal_1.nP = novoNP;
        }catch(NullPointerException e){
        }catch(NumberFormatException s){
            numeroParticles.setText(""+Principal_1.nP);
        }
        
    }
    
    public void mudarInteracao(){
        int novainte;
        try{
            novainte = Integer.valueOf(this.iterações.getText());
            Principal_1.Epoch = novainte;
        }catch(NullPointerException e){
        }catch(NumberFormatException s){
            iterações.setText(""+Principal_1.Epoch);
        }
        
    }
    
    public static ArrayList<Integer> MelhoresPosX = new ArrayList<Integer>();
    public static ArrayList<Integer> MelhoresPosY = new ArrayList<Integer>();
    
    public static ArrayList<Integer> MelhorX = new ArrayList<Integer>();
    public static ArrayList<Integer> MelhorY = new ArrayList<Integer>();
    
    public void recuperaMelhoresPosicoes(ArrayList<Integer> posx, ArrayList<Integer> posy){
        this.MelhorX = posx;
        this.MelhorY = posy;
    }
    
    public void resultadosAlgoritmo(){
        new Thread(){
            @Override
            public void run(){  
                mudarInercia();
                mudarNP();
                mudarInteracao();
                int flag = Principal_1.nP;
                
                Principal_1 entrar = new Principal_1();
                posicoesMatrizFinal = entrar.iniciarPSO();
                int aux2 = 0;
                while(aux2 < 1024){
                                l = (Label)  Matriz.getChildren().get(aux2);
                                l.setStyle("-fx-background-color:#FFFFFF"); 
                                l = (Label) Matriz.getChildren().get(15*32+16);
                                l.setStyle("-fx-background-color:#ff3e89");
                                
                                aux2++;
                }
                                
                int w = 0; // será chamado a cada 10 partículas, ou seja, a cada iteração, 1;
                
                for(int i = 0; i < posicoesMatrizFinal.size(); i+=flag){ //de 10 em 10
                        int j = i;
                        System.out.println("MELHOR POS X: "+ MelhorX.toString());                 
                        while(j < i+flag){
                            l = (Label)  Matriz.getChildren().get(posicoesMatrizFinal.get(j));
                            l.setStyle("-fx-background-color:POWDERBLUE"); 
                            j++;
                            l = (Label) Matriz.getChildren().get(MelhorX.get(w)*32+MelhorY.get(w));
                            //l.setStyle("-fx-background-color: #63b231");    //MELHOR POSIÇÃO DA ITERAÇÃO
                        }  
                        w = w +1;
                        
                        try{
                            Thread.sleep(500);
                        }catch(Exception e){
                            System.out.println(e);
                        }      
                        if(i+ flag < posicoesMatrizFinal.size()){ 
                            int aux = 0;
                           
                            while(aux < 1024){
                                l = (Label)  Matriz.getChildren().get(aux);
                                l.setStyle("-fx-background-color:#FFFFFF"); 
                                l = (Label) Matriz.getChildren().get(15*32+16);
                                l.setStyle("-fx-background-color:#ff3e89");
                                l = (Label) Matriz.getChildren().get(17*32+14);
                                l.setStyle("-fx-background-color:#5ecae4");
                                aux++;
                                                     
                            }  
                        }

                }
                System.out.println("Iterações: "+Principal_1.Epoch+" || Partículas: "+Principal_1.nP+" || Particulas Total: "+Principal_1.Epoch*Principal_1.nP+" || Partículas printadas de 10 em 10: "+(Principal_1.Epoch/10)*Principal_1.nP);
                
            }
        }.start();
  
    }
    
    public ArrayList<Integer> printaMatriz(){
        int num;
        ArrayList<Integer> posicoes = new ArrayList<>();
            for(int i = 0; i < positionX.size() ; i++){
                    num = positionX.get(i)*32 + positionY.get(i);
                    posicoes.add(num);
            }
         return posicoes;
    }
    
    public ArrayList<Integer> positionX = new ArrayList<>();
    public ArrayList<Integer> positionY = new ArrayList<>();
    
    
    public ArrayList<Double> getrecebeListBytes(ArrayList<Double> listBytes){
        this.tam = listBytes.size()/2;
        return listBytes;
    }
   
    public int tam; 
    /* TENTATIVA DE CONFIRMAÇÃO DE DADOS
    public Double[] LISTAX = new Double[tam/2];
    public ArrayList<Integer> positionxprintar;
    
    public void recebeListaX(Double[] lista){
        LISTAX = lista.clone();
    }
    
    public void recebePositionX(ArrayList<Integer> pos){
        this.positionxprintar = (ArrayList<Integer>) pos.clone();
    }
   */
    
    private int posicaoPositionXmelhor = 0; //índices
    private int posicaoPositionYmelhor = 0;//índices
    
    
    public void recebeVetor(ArrayList<Double> listBytes){
        tam = listBytes.size()/2;
        int posx = 0 ;
        int posy = 0 ;
        
        Double[] LISTAX = new Double[tam];
        Double[] LISTAY = new Double[tam];
        
        int x = 0,y = 0;
        double xDoub, yDoub;
        
        /////////////////////////////////////////
        //APENAS PARA A PRIMEIRA MELHOR POSIÇÃO
        int flagMelhorPosX = 0;
        int flagMelhorPosY = 0;
        for(int i = 0; i < listBytes.size();i++){
            if(listBytes.get(i) == Nuvem.melhorPosicaoNuvem[0]){ 
                flagMelhorPosX = i;
            }else if(listBytes.get(i) == Nuvem.melhorPosicaoNuvem[1] ){
                flagMelhorPosY = i;
            }
        }
        //////////////////////////////////////////
        int essaposicaoX = 0; //corresponde ao índice do positionX em que está a melhor posição
        int essaposicaoY = 0;
        int auy = 0, aux = 0;
            for(int i = 0; i < listBytes.size();i++){
                if(i%2==0){
                    LISTAX[aux] = listBytes.get(i).doubleValue();
                    if( listBytes.get(i).doubleValue() == Nuvem.melhorPosicaoNuvem[0]){
                        essaposicaoX = aux;
                    }
                    aux++;
                }else {
                    LISTAY[auy] = listBytes.get(i).doubleValue();
                     if(listBytes.get(i).doubleValue() == Nuvem.melhorPosicaoNuvem[1]){
                         essaposicaoY = auy;
                     }
                     auy++;
                }
            }
            
          //  this.recebeListaX(LISTAX.clone());
      
            for(int i = 0; i < 32; i ++){ 
                for(int s = 0; s < 32; s++){ 
                    la = new Label();
                    la.setScaleX(42);
                    la.setScaleY(17);
                    Matriz.add(la, i, s);
                }
            }
            for( int a = 0; a < tam ; a++){
                
                
                if(LISTAX[a] == 0){
                    x = 15;
                    this.positionX.add(x);
                    posx++;
                    
                    if( a == essaposicaoX){
                        //MELHOR POSICAO X
                        this.posicaoPositionXmelhor = x;
                        this.MelhoresPosX.add(this.posicaoPositionXmelhor);
                    }
                    
                    if(LISTAY[a] == 0){
                        y = 16;
                       
                        this.positionY.add(y);
                        posy++;
                        
                        if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                      
                    }else if(LISTAY[a] > 0){
                        
                        yDoub =  Math.abs(LISTAY[a]);
                       
                        for(int i = 0; i < 8  ; i++){
                                int w = i+1;
                                if(yDoub > i && yDoub < w){
                                    yDoub = yDoub*2;
                                    y = (int) (16 - yDoub);
                                    break;
                                }else if(yDoub ==  w) {
                                    y = (int) (16 - (yDoub*2));
                                    break;
                                }
                            }
                        this.positionY.add(y);
                        posy++;
                        
                        if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    } else if(LISTAY[a] < 0){
                        yDoub = Math.abs(LISTAY[a]);
                        for(int i = 0; i <  7.5; i++){ 
                                int w = i   + 1;
                                if(yDoub > i && yDoub < w){
                                    yDoub = yDoub*2;
                                    y = (int) (16 + yDoub);
                                    break;
                                }else if(yDoub ==  w) {
                                    y = (int) (16 + (yDoub*2));
                                    break;
                                }

                            }
                        this.positionY.add(y);
                        posy++;
                        
                        if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    }  
                    
                }else if(LISTAX[a] > 0){  
                    xDoub = Math.abs(LISTAX[a]);
                    for(int i = 0; i <= 16 - 1 ; i++){
                                int w = i+1;
                                if(xDoub > i && xDoub < w){
                                    xDoub = xDoub*2;
                                    x = (int) (15 + xDoub);
                                    break;
                                }else if(xDoub ==  w){
                                    x = (int) (15 + (xDoub*2));
                                    break;
                                }

                            }
                    this.positionX.add(x);
                    posx++;
                    
                    if( a == essaposicaoX){
                        //MELHOR POSICAO X
                        this.posicaoPositionXmelhor = x;
                        this.MelhoresPosX.add(this.posicaoPositionXmelhor);
                    }
                    
                    if(LISTAY[a] == 0){
                        y = 16;
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    } else if(LISTAY[a] > 0){    
                        yDoub = (Math.abs(LISTAY[a]));
                        for(int i = 0; i < 8 ; i++){
                                int w = i+1;                
                                if(yDoub > i && yDoub < w){
                                    yDoub = yDoub*2;
                                    y = (int) (16 - yDoub);
                                    break;
                                }else if(yDoub ==  w){
                                    y = (int) (16 - (yDoub*2));
                                    break;
                                }
                            }
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    } else if(LISTAY[a] < 0){
                        yDoub = Math.abs(LISTAY[a]);
                        for(int i = 0; i <=  7.5; i++){ 
                                int w = i   + 1;
                                if(yDoub > i && yDoub < w){ 
                                    yDoub = yDoub*2;
                                    y = (int) (16 + yDoub);
                                    break;
                                }else if(yDoub ==  w) {
                                    y = (int) (16 + (yDoub*2));
                                    break;
                                }

                            }
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    }  
                }else if(LISTAX[a]< 0){
                        xDoub = Math.abs(LISTAX[a]);
                        for(int i = 0; i <= 16 - 1; i++){
                                    int w = i + 1;
                                    if(xDoub > i && xDoub < w){
                                        xDoub = xDoub*2; 
                                        x = (int) (15 - xDoub);
                                        break;
                                    }else if(xDoub ==  w){
                                        x = (int) (15 - (w*2));
                                        break;
                                    }

                                }
                        this.positionX.add(x);
                        posx++;
                    if( a == essaposicaoX){
                        //MELHOR POSICAO X
                        this.posicaoPositionXmelhor = x;
                        this.MelhoresPosX.add(this.posicaoPositionXmelhor);
                    }
                    if(LISTAY[a] == 0){
                        y = 16;
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    }else if(LISTAY[a] > 0){
                       yDoub = (Math.abs(LISTAY[a]));
                        for(int i = 0; i < 8 ; i++){
                                int w = i+1;
                                if(yDoub > i && yDoub < w){
                                    yDoub = yDoub*2;
                                    y = (int) (16 - yDoub);
                                    break;
                                }else if(yDoub ==  w){
                                    y = (int) (16 - (yDoub*2));
                                    break;
                                }
                            }
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    }else if (LISTAY[a] < 0){
                       yDoub = Math.abs(LISTAY[a]);
                        for(int i = 0; i <  7.5; i++){ 
                                int w = i   + 1;
                                if(yDoub > i && yDoub < w){
                                    yDoub = yDoub*2;
                                    y = (int) (16 + yDoub);
                                    break;
                                }else if(yDoub ==  w) {
                                    y = (int) (16 + (yDoub*2));
                                    break;
                                }
                            }
                        this.positionY.add(y);
                        posy++;
                         if( a == essaposicaoY){
                        //MELHOR POSICAO X
                        this.posicaoPositionYmelhor = y;
                        this.MelhoresPosY.add(this.posicaoPositionYmelhor);
                        }
                    }
                }
            }
            System.out.println("POSITION X : "+positionX.toString());
            System.out.println("POSITION Y : "+positionY.toString());
            System.out.println("Melhores índices X das iterações: "+MelhoresPosX.toString());
            System.out.println("Melhores índices Y das iterações: "+MelhoresPosY.toString());
            this.recuperaMelhoresPosicoes(MelhoresPosX, MelhoresPosY);
    }
    
    
} 
