package psojavarduino;

import java.util.ArrayList;
import java.security.Principal;

/**
 *
 * @author Juliana Santiago
 */

public class Principal_1 {
    static float W =  (float) 0.9; //inercia //linspace(0.7,0.4)
    static final double c1 = 2.0; //memoria
    static final double c2 = 2.0; //cooperacao
    static final boolean VELCONTROL = true; //se controla ou nao a velocidade //DEVE CONTROLAR
    
    static int nP = 10;
    static  int Epoch = 100; 
    
    static final int DIM = 2; //x e y, para rosenbrock 
    static final float LIMax = 8;
    static final float LIMin = (float) - 7;
    
    static final float[] limiteMax = new float[DIM];
    static final float[] limiteMin = new float[DIM];
    
    static double VMAX ; 
    
    public float getW(){
        return W;
    }
    
    public double getVmax(){
        return VMAX;
    }

    public void VelMax(){
        int kv = 1;
        for(int i = 0; i < Principal_1.DIM; i++){
            Principal_1.VMAX = kv*((limiteMax[i]) - (limiteMin[i]))/2; //CASO MUDE, MUDE NA FUNÇÃO INICIALIZAR VELOCIDADE
                                                                       //MANUALMENTE
        }
    }
    
    public void atualizaLimites(){
        for(int i = 0; i < DIM; i++){
            limiteMax[i] = LIMax; 
            limiteMin[i] = LIMin; 
        }
    }
    
    public ArrayList<Integer> iniciarPSO(){
        ArrayList<Integer> posicoesMatriz = new ArrayList<>();
        Principal_1 p1 = new Principal_1();
        p1.atualizaLimites();
        p1.VelMax();
        Nuvem nuvemParticulas = new Nuvem(nP);
        nuvemParticulas.fuctionInercia(); 
        posicoesMatriz = nuvemParticulas.executarPSO();
        return posicoesMatriz;
    }
    


}
