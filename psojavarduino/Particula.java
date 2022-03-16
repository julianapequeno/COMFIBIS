package psojavarduino;

import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import psojavarduino.Nuvem;
import psojavarduino.Principal_1;

/**
 *
 * @author Juliana Santiago
 */

public class Particula implements Comparable<Particula>  {

    public double[] posicaoAtual;
    
    private double valorPosicaoAtual;

    private final double[] CoordBEST;
    private Double ParticleBEST = new Double(1000000000);

    private double[] velocidade;
    
    private Principal_1 p1;
  
    
   

    public Particula() {
        posicaoAtual = new double[Principal_1.DIM];
        CoordBEST = new double[Principal_1.DIM];
        velocidade = new double[Principal_1.DIM];
        NumberFormat formatador = new DecimalFormat("0.0");
        inicializarParticles();
        inicializarVelocidade();

    }
    
    
    public void inicializarParticles(){
            for(int y = 0; y < Principal_1.DIM; y++){
                posicaoAtual[y] = Principal_1.limiteMin[y] + new Random().nextFloat() * (Principal_1.limiteMax[y] - Principal_1.limiteMin[y]);
                System.out.println("Posição Inicial "+"("+y+")"+":"+ posicaoAtual[y]);
            }
    }
 
    public double random(){
        double r = 0;
        for(double y = Principal_1.LIMin; y <= Principal_1.LIMax; y++){
            r = Principal_1.LIMin + new Random().nextDouble() * (Principal_1.LIMax - Principal_1.LIMin);
        }
        return r; 
    }
    
    private void inicializarVelocidade() {
        for (int i = 0; i < velocidade.length; i++) {
            velocidade[i] = (((2*new Random().nextDouble())-1)* 6.5); //MANUAL (6), ERA O VMAX
                                                                    //Como o limite é 8, então a VELMAX É 7
                                                                    //o limite minimo é o delimitador de velocidade
        }
    }
    
    public Byte posX;
    public Byte posY; 
    public double listaBytes[] = new double[2];
    
    public void rosenbrock() {
        double a = 1, b = 100;
        this.valorPosicaoAtual = Math.pow(a - posicaoAtual[0], 2) + b * Math.pow(posicaoAtual[1] - Math.pow(posicaoAtual[0], 2), 2);
        double posX = 0.0;
        double posY = 0.0;
        
        posX = posicaoAtual[0];
        posY = posicaoAtual[1];
        
        NumberFormat formatador2 = new DecimalFormat("0.0");
        
        String posAux;
        String posAuy;
        posAux = (formatador2.format(posX));
        posAux = posAux.replaceAll(",", ".");
        posX = Double.parseDouble(posAux);
        
        posAuy = (formatador2.format(posY));
        posAuy = posAuy.replaceAll(",",".");
        posY = Double.parseDouble(posAuy);

        PrecisionTransformer(posX, posY);
                
        //valorposicaoatual é a parte estatística, ex: desvio padrão
        //posicaoAtual: é o x e o y
    }
    
    public void PrecisionTransformer(double positionX, double positionY){
        
        posX =(byte) positionX;
        posY =(byte) positionY;
        
        //ARREDONDA OS VALORES RECEBIDOS PELA ROSEMBROCK DE 0.5 EM 0.5

        double soma = 0.5;
        
        if(positionX >= 0){
            
            if(positionX == posX){ // caso seja igual a parte inteira dele, ou seja, caso seja num inteiro
                listaBytes[0] = positionX;
            }else if(positionX + soma > posX + 1){
                //é maior que int.5
                listaBytes[0] = posX + 1 ;
            }else if(positionX + soma == posX + 1){
                //é igual, ent fica em int.5
                listaBytes[0] = positionX ;
            }else if(positionX + soma < posX + 1){
                double porcen;
                porcen = 0.5;
                listaBytes[0] = posX + porcen ;
            }
        }

        if(positionY >= 0){
            if(positionY == posY){
               listaBytes[1] = positionY;
           }else if(positionY + soma > posY + 1){
               //é maior que int.5
               listaBytes[1] = posY + 1;
           }else if(positionY + soma == posY + 1){
               //é igual, ent fica em int.5
               listaBytes[1] = positionY;
           }else if(positionY + soma < posY + 1){
                double porcen1;
                porcen1 = 0.5;
                listaBytes[1] = posY + porcen1;
           }
        }
        
        if(positionX < 0){ //x negativo
            double somanegativa = - 0.5;
            if(positionX == posX){
                listaBytes[0] = positionX;
            }else if(positionX + somanegativa > posX - 1){
                //é maior que int.5
                listaBytes[0] = posX - 1;
            }else if(positionX + somanegativa == posX - 1){
                //é igual, ent fica em int.5
                listaBytes[0] = positionX;
            }else if(positionX + somanegativa < posX - 1){
                //é menor que o int.5
                double porcen2 ;
                porcen2 = - 0.5;
                listaBytes[0] = posX + porcen2;
            }
        }
        
       if(positionY < 0){ //y negativo
           double somanegativa = - 0.5;
           if(positionY == posY){
               listaBytes[1] = positionY;
           }else if(positionY + somanegativa > posY - 1){
               //é maior que int.5
               listaBytes[1] = posY - 1;
           }else if(positionY + somanegativa == posY - 1){
               //é igual, ent fica em int.5
               listaBytes[1] = positionY;
           }else if(positionY + somanegativa < posY - 1){
                double porcen3;
                porcen3 =  - 0.5;
                listaBytes[1] = posY + porcen3;
           }
       }
    }
    
    public double[] getArrayBytes(){
        return listaBytes;
    }
    
    public void IniciarArrayIteracao(){
        listaBytes = new double[2];
    }
    
    public void zerarArray(){
        listaBytes = null;
    }

    //Verifica se o valor da posicao atual eh melhor que o valor da melhor solucao ja encontrada
    //Se for, substitui
    
    public void avaliarSolucao() {
        rosenbrock();
        if (valorPosicaoAtual < ParticleBEST) { //FUNCAO DE MÍNIMO, POR ISSO O <
            ParticleBEST = valorPosicaoAtual;
            System.arraycopy(posicaoAtual, 0, CoordBEST, 0, posicaoAtual.length);
        }
    }

    //Valor da posicao atual recebe a acuracia do classificador
    public void setValorPosicaoAtual(double v) {
        valorPosicaoAtual = v;
    }

    public void atualizarVelocidade() {
        for (int i = 0; i < velocidade.length; i++) {
            velocidade[i] = ((Principal_1.W)* velocidade[i])
                    + (Principal_1.c1 * random() * (CoordBEST[i] - posicaoAtual[i]))
                    + (Principal_1.c2 * random() * (Nuvem.melhorPosicaoNuvem[i] - posicaoAtual[i]));
            
            if (Principal_1.VELCONTROL) {
                //limita a velocidade entre [-VMAX;VMAX]
                //Não permite que ele passe do máximo e do mínimo de velocidadem que seria o máximo negativo
                if (velocidade[i] > Principal_1.VMAX) {
                    velocidade[i] = Principal_1.VMAX;
                } else if (velocidade[i] < -Principal_1.VMAX) { 
                    velocidade[i] = -Principal_1.VMAX;
                }
            }
        }
    }

    public void atualizarPosicao() {
        //double s;
        for (int i = 0; i < posicaoAtual.length; i++) {
            posicaoAtual[i] += velocidade[i];
            //System.out.println("Posição Atualizada, Partícula: "+i+":"+posicaoAtual[i]);
            if(posicaoAtual[i] > Principal_1.LIMax){
                posicaoAtual[i] = Principal_1.LIMax;
            } else if(posicaoAtual[i] < Principal_1.LIMin){
                posicaoAtual[i] = Principal_1.LIMin;
            }
        }
    }

    public double getValorPosicaoAtual() {
        return valorPosicaoAtual;
    }

    public double getValorMelhorPosicao() {
        return ParticleBEST;
    }
    
    public void setValorMelhorPosicao(double vM){
        this.ParticleBEST = vM;
    }

    public double[] getMelhorPosicao() {
        return CoordBEST;
    }

    public double[] getPosicaoAtual() {
        return posicaoAtual;
    }
    
    @Override
    public int compareTo(Particula o) {
        return ParticleBEST.compareTo(o.getValorMelhorPosicao());
    }
    
    public double[] getVel(){
        return velocidade;
    }
    
   
}
