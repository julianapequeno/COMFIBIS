/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psojavarduino;

/**
 *
 * @author julia_000
 */
/*
public  class Transformador {
    //classe para manter funções de tranformação ou detalhamento
    
    public Transformador(){
        
    }
    
    public double[] PrecisionTransformer(double positionX, double positionY, byte posX, byte posY){
        double positions[] = new double[2];
        posX =(byte) positionX;
        posY =(byte) positionY;
        
        System.out.println("BYTE POSX: "+posX);
        System.out.println("BYTES POSY: "+posY);
        
        //ARREDONDA OS VALORES RECEBIDOS PELA ROSEMBROCK DE 0.5 EM 0.5
        //System.out.println("RosembrockRecebe pós formatação: "+posicaoAtual[0]+" e "+posicaoAtual[1]);
        
        //Para os números positivos
        double soma = 0.5;
        double listaBytesX = 0;
        double listaBytesY = 0;
        
        if(positionX >= 0){
            
            if(positionX == posX){ // caso seja igual a parte inteira dele, ou seja, caso seja num inteiro
                listaBytesX = positionX;
                System.out.println("Inteiro");
            }else if(positionX + soma > posX + 1){
                //é maior que int.5
                System.out.println("X 2");
                listaBytesX = posX + 1 ;
            }else if(positionX + soma == posX + 1){
                //é igual, ent fica em int.5
                listaBytesX = positionX ;
                System.out.println("X 3");
            }else if(positionX + soma < posX + 1){
                double porcen;
                //System.out.println("Parte fracionaria: "+porcen);
                porcen = 0.5;
                listaBytesX = posX + porcen ;
                System.out.println("X 0.5");
               // System.out.println("Tentativa de ida para o 0.5: "+listaBytes[0]);
            }
        }
        
        //y maior que zero
        if(positionY >= 0){
            if(positionY == posY){
               listaBytesY = positionY;
               System.out.println("inteiro");
           }else if(positionY + soma > posY + 1){
               //é maior que int.5
               listaBytesY = posY + 1;
               System.out.println("Y 2");
           }else if(positionY + soma == posY + 1){
               //é igual, ent fica em int.5
               listaBytesY = positionY;
               System.out.println("Y 3");
           }else if(positionY + soma < posY + 1){
                double porcen1;
                //System.out.println("Parte fracionaria: "+porcen1);
                porcen1 = 0.5;
                listaBytesY = posY + porcen1;
                System.out.println("Y 0.5");
               // System.out.println("Tentativa de ida para o 0.5: "+listaBytes[1]);
           }
        }
        
        //para os números negativos
        
        if(positionX < 0){ //x negativo
            double somanegativa = - 0.5;
            
            if(positionX == posX){
                listaBytesX = positionX;
                System.out.println("X -1");
            }else if(positionX + somanegativa > posX - 1){
                //System.out.println("Somaneg: "+posicaoAtual[0] + somanegativa);
                //é maior que int.5
                listaBytesX = posX - 1;
                System.out.println("X -2");
               // System.out.println("OK 1 - "+posicaoAtual[0]);
            }else if(positionX + somanegativa == posX - 1){
                //é igual, ent fica em int.5
               // System.out.println("Somaneg: "+posicaoAtual[0] + somanegativa);
                listaBytesX = positionX;
                //System.out.println("OK 2 - "+posicaoAtual[0]);
                System.out.println("X -3");
            }else if(positionX + somanegativa < posX - 1){
                //System.out.println("Somaneg: "+posicaoAtual[0] + somanegativa);
                //é menor que o int.5
                double porcen2 ;
                //System.out.println("Parte fracionaria: "+porcen2);
                porcen2 = - 0.5;
                listaBytesX = posX + porcen2;
                System.out.println("X -4");
                //System.out.println("Tentativa de ida para o 0.5: "+listaBytes[0]);
            }
        }
        
       if(positionY < 0){ //y negativo
           double somanegativa = - 0.5;
           
           if(positionY == posY){
               listaBytesY = positionY;
               System.out.println("Y -1");
           }else if(positionY + somanegativa > posY - 1){
               //é maior que int.5
              // System.out.println("Somaneg: "+posicaoAtual[1] + somanegativa);
               //System.out.println("OK 1.1 - "+posicaoAtual[1]);
               listaBytesY = posY - 1;
               System.out.println("X -2");
           }else if(positionY + somanegativa == posY - 1){
              // System.out.println("Somaneg: "+posicaoAtual[1] + somanegativa);
               //é igual, ent fica em int.5
               listaBytesY = positionY;
               //System.out.println("OK 2.2 - "+posicaoAtual[1]);
               System.out.println("X -3");
           }else if(positionY + somanegativa < posY - 1){
               //System.out.println("Somaneg: "+posicaoAtual[1] + somanegativa);
                double porcen3;
                //System.out.println("Parte fracionaria: "+porcen3);
                porcen3 =  - 0.5;
                listaBytesY = posY + porcen3;
                System.out.println("X -4");
                //System.out.println("Tentativa de ida para o 0.5: "+listaBytes[1]);
           }
       }
       
       //System.out.println("RosembrockEnvia: "+listaBytes[0]+" e "+listaBytes[1]);
       positions[0] = listaBytesX;
       positions[1] = listaBytesY;
       return positions;
    }
}

*/