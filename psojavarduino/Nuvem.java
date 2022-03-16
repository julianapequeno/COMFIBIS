package psojavarduino;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import javafx.scene.control.Label;
/**
 *
 * @author Juliana Santiago
 */

public class Nuvem {


    private final ArrayList<Particula> particulas;
    private int flag = 0;
    
    static double[] melhorPosicaoNuvem;

    //private double Best_result = 1.e9;
    private double Best_result = 1000000000;
    
    public Particula p1 = new Particula();
    private float[] Wvector = new float[psojavarduino.Principal_1.Epoch];

    public Nuvem(int qtdParticulas) {
        particulas = new ArrayList<>();
        for (int i = 0; i < qtdParticulas; i++) {
            particulas.add(new Particula());
        }
        melhorPosicaoNuvem = new double[psojavarduino.Principal_1.DIM];
    }
    
    public void fuctionInercia(){
        double step;
        step = ((psojavarduino.Principal_1.W/psojavarduino.Principal_1.Epoch) );
        int i = 0;
        float j;
        for(i = 0, j = psojavarduino.Principal_1.W; i< psojavarduino.Principal_1.Epoch; j-=step,i++){
            Wvector[i] = j;
        }
    }
    
    public ArrayList<Double> listBytes = new ArrayList<>();
    public double[] vetorBytes;
    public ArrayList<Double> listPassager = new ArrayList<>();
    private ArrayList<Integer> posicoesMatriz = new ArrayList<>();

    public ArrayList<Integer> executarPSO() {
        
        System.out.println("------EXECUTANDO PSO------");
        int i = 0;
        while((i != psojavarduino.Principal_1.Epoch )&&(Best_result > 1.E-9)){
            
            for (Particula p : particulas) {
                double adic;
                p.IniciarArrayIteracao();
                p.avaliarSolucao();
                vetorBytes = p.getArrayBytes();
                for(int z = 0; z < vetorBytes.length; z++){
                    adic = vetorBytes[z];
                    listBytes.add(adic);
                }
                p.zerarArray();
            }
            
            Collections.sort(particulas);
            
            double[] MelhorPosicaoParticula; 
            MelhorPosicaoParticula = particulas.get(0).getMelhorPosicao();
            
                if(particulas.get(0).getValorMelhorPosicao() < Best_result){
                    Best_result = particulas.get(0).getValorMelhorPosicao(); 
                    System.arraycopy(MelhorPosicaoParticula, 0, melhorPosicaoNuvem, 0, melhorPosicaoNuvem.length);
                    flag++;
                }

            for (Particula p : particulas) {
                p.atualizarVelocidade();
                p.atualizarPosicao();
            }
           
           resumoIteracao(i);
            
           psojavarduino.Principal_1.W = this.Wvector[i];
            System.out.println("");
            i++;
        }
        return posicoesMatriz;
    }

    private FXMLDocumentController control = new FXMLDocumentController();
    
    
    private void resumoIteracao(int iteracao) {
        System.out.println("Iteracao " + iteracao + "| Melhor " + Best_result);
        System.out.print("Vetor da Melhor Posicao: ");
        System.out.print("[");
        for(int a = 0; a < melhorPosicaoNuvem.length; a++){
             System.out.print(String.valueOf(melhorPosicaoNuvem[a]));
             System.out.print(" ");
        }
        System.out.println("]");
        
            
        System.out.println("W:"+psojavarduino.Principal_1.W);
        System.out.println("Flag:"+flag);

        String ite  = String.valueOf(iteracao);
        int digitos  = ite.length();
        char c ;
        int num = 0;
        
        if(digitos == 1){
            if(iteracao == 0){
                listPassager = listBytes; 
                control.getrecebeListBytes(listBytes);
                control.recebeVetor(listBytes);
                System.out.println(listBytes);
                posicoesMatriz  = control.printaMatriz();
                listBytes.clear();
            }else{
                listPassager = listBytes;
                listBytes.clear();
            }
        }else  if(digitos == 2){
                     c = ite.charAt(1);
                     if(c == '0'){
                         listPassager = listBytes; //está funcionando como ponteiro
                         control.getrecebeListBytes(listBytes);
                         control.recebeVetor(listBytes);
                         System.out.println(listBytes);
                         posicoesMatriz = control.printaMatriz();
                         listBytes.clear();
                     }else{
                         listPassager = listBytes;
                         listBytes.clear();
                     }
        }else if(digitos == 3){
                     c = ite.charAt(2);
                        if(c == '0'){
                             listPassager = listBytes;  
                             control.getrecebeListBytes(listBytes);
                             control.recebeVetor(listBytes);
                             System.out.println(listBytes);
                             posicoesMatriz = control.printaMatriz();
                             listBytes.clear();
                        }else{
                            System.out.println("Array; "+listBytes);
                            listPassager = listBytes;
                            listBytes.clear();
                        }
        }
    }
    
    public double getMelhorPosicaoNuvem(){
        return Best_result;
    }

}
