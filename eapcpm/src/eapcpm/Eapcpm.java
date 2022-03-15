/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapcpm;

import java.util.HashMap;
import static java.lang.Integer.parseInt;
import java.util.*;
import java.util.ArrayList;
/**
 *
 * @author PeterIsMyName
 */

/*class Piece {

    int[] Up;
    int[] Left = new int[4];
    int[] Down = new int[4];
    int[] Right = new int[4];
    
    boolean Placed;
    
    public Piece(int[] straight) {
        this.Up = straight;

        this.Left[0] = Up[1];
        this.Left[1] = Up[2];
        this.Left[2] = Up[3];
        this.Left[3] = Up[0];

        this.Down[0] = Up[2];
        this.Down[1] = Up[3];
        this.Down[2] = Up[0];
        this.Down[3] = Up[1];


        this.Right[0] = Up[3];
        this.Right[1] = Up[0];
        this.Right[2] = Up[1];
        this.Right[3] = Up[2];

        this.Placed = false;
        

    }

    public void setUp(int[] Up){
        this.Up = Up;
    }
    public void setLeft(int[] Left){
        this.Left = Left;
    }
    public void setDown(int[] Down){
        this.Down = Down;
    }
    public void setRight(int[] Right){
        this.Right = Right;
    }
    
    public Piece getPiece(){
        return this;
    }
    
    public boolean getPlaced(){
        return Placed;
    }
    public void setPlaced(){
        this.Placed = true;
    }
    public void setNotPlaced(){
        this.Placed = false;
    }

    @Override
    public String toString() {
        return (this.Up[0] + " " + this.Up[1] + "\n"
                + this.Up[2] + " " + this.Up[3] + "\n");
    }
}*/

public class Eapcpm {
    //numeros: array 0-999 
    static void contaNumeros(int[] arrayInts, int[] numeros){
        
        for(int numero : arrayInts){
            numeros[numero] += 1;  
        }
    }
    static void printPieces(int[][][] Tabuleiro){
        int linhas = Tabuleiro.length;
        int colunas = Tabuleiro[0].length;
        String toPrint = "";
        String toPrintLower = "";
        for(int i = 0; i<linhas; i++){
            for(int j = 0; j<colunas; j++){
   
                toPrint += Tabuleiro[i][j][0] + " " + Tabuleiro[i][j][1];
                if(j < colunas-1){
                    toPrint+= "  ";
                }
                
                toPrintLower += Tabuleiro[i][j][3] + " " + Tabuleiro[i][j][2];
                if(j < colunas-1){
                    toPrintLower+= "  ";
                }
 
            }
            
            System.out.println(toPrint);
            System.out.println(toPrintLower);
            toPrint = "";
            toPrintLower = "";
            if(i < linhas-1){
            System.out.print("\n");
            }
        }
        
        
    }
    
    static ArrayList<Integer> findCompatibles(int one, int two, HashMap<int[],ArrayList<Integer>> compatibleKeys, ArrayList<int[][]> PieceArray, boolean firstC){
        int[] key = new int[2];
        key[0] = one;
        key[1] = two;
        //System.out.println("Looking for a piece compatible with: " + key[0] + " " + key[1]);
        if(compatibleKeys.get(key) != null){
            return compatibleKeys.get(key);
        }
        else{
            ArrayList<Integer> tempArray = new ArrayList<>();
            for(int i = 0; i<PieceArray.size(); i++){
               // System.out.println("Peca: " + i + "tem: " + PieceArray.get(i).getPlaced());
                
                for(int j = 0; j < 4; j++){
                    int[] temp = PieceArray.get(i)[j];
                    
                    //System.out.println("Comparing with: \n" + temp);
                    if(firstC){
                        if((key[0] == temp[0]) && (key[1] == temp[1]) && (tempArray.indexOf(i) == -1)){
                            //System.out.println("Added!1");
                            tempArray.add(i);
                        }
                    }
                    else if((key[0] == temp[0]) && (key[1] == temp[3]) && (tempArray.indexOf(i) == -1)){
                        //System.out.println("Added!2" + i);
                        tempArray.add(i);
                    }
                }
            }
            compatibleKeys.put(key, tempArray);
            return compatibleKeys.get(key);
        }
    }
    static int ComparePieces(int[][] first, int indexFirst, ArrayList<int[][]> PieceArray, int[][][] Tabuleiro, int LastR, int LastC, HashMap<int[],ArrayList<Integer>> compatibleKeys) {
        //System.out.println("entrie na funcao de novo, estamos nesta linha e coluna:" +LastR + " " + LastC );
        
        int row = Tabuleiro.length;
        int column = Tabuleiro[0].length;

        if((LastR == row-1) && (LastC == column-1)){
            //System.out.println("Entrei e vou acabar");
            return 1;
        }
        else if(LastC == column-1){
            //System.out.println("Entrei no segundo e vou continuar");
            LastC = 0;
            LastR+=1;
        }
        else{
            LastC++;
        }
        //System.out.println("LastR: " + LastR + "\n LastC:" + LastC);
        if (LastR == 0) {
            //System.out.println("If para a primeira linha");
            ArrayList<Integer> tempCompatible = findCompatibles(first[indexFirst][1],first[indexFirst][2],compatibleKeys,PieceArray,false);
            //System.out.println("TempCompatible dentro do LastR == 0" + tempCompatible);
            for (int i = 0; i<tempCompatible.size();i++) {
                int[][] AllPiece = PieceArray.get(i);
                int[] temp = AllPiece[indexFirst];
                if(AllPiece[4][0] == 0){
                    
                    if((first[indexFirst][1] == temp[0]) && (first[indexFirst][2] == temp[3])){
                        Tabuleiro[LastR][LastC] = temp;


                        AllPiece[4][0] = 1;
                        
                        //System.out.println("Coloquei a peca. \n" + temp + "\nAo lado da peca" + first);
                        //System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);
                        int resultado = ComparePieces(PieceArray.get(i), i, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                        if(resultado == 1){
                            return 1;
                        }
                        else{
                            AllPiece[4][0] = 1;
                            
                        }
                    }   
                    
                }
            }
        }
        else{
            //System.out.println("entrei no else, NAO ESTOU NA PRIMEIRA LINHA");
            ArrayList<Integer> tempCompatible;
            if(LastC == 0 && LastR != 0){
                //System.out.println("Primeira Coluna da nova Row");
                
                tempCompatible = findCompatibles(Tabuleiro[LastR-1][0][3],Tabuleiro[LastR-1][0][2],compatibleKeys,PieceArray,true);
                //System.out.println(tempCompatible.size());
            }
            else{
                //System.out.println("DE NOVO NO ELSE");
                tempCompatible = findCompatibles(first[indexFirst][1],first[indexFirst][2],compatibleKeys,PieceArray,false);
            }   
            
            
            for(int i = 0; i<tempCompatible.size();i++){
                //System.out.println("Entrei no for de compatibles");
                int[][] AllPiece = PieceArray.get(i);
                int[] temp = AllPiece[indexFirst];
                if(AllPiece[4][0] == 0){
                    //System.out.println("Entrei no if de placed");
                   
                    if((Tabuleiro[LastR-1][LastC][2] == temp[1]) && (Tabuleiro[LastR-1][LastC][3] == temp[0])){
                        //System.out.println("sou compativel EM CIMA");
                        //System.out.println(Tabuleiro[LastR-1][LastC] + "" + temp );
                        if(LastC == 0){
                            //System.out.println("Vou meter uma peca na Coluna 0");
                            Tabuleiro[LastR][LastC] = temp;

                            AllPiece[4][0] = 1;
                            
                            //System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);
                            //printPieces(Tabuleiro);
                            int resultado = ComparePieces(PieceArray.get(i), i, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                            if(resultado == 1){
                                return 1;
                            }
                            else{
                                AllPiece[4][0] = 0;
                                
                            }
                        }

                        else if((first[indexFirst][1] == temp[0]) && (first[indexFirst][2] == temp[3])){
                            //System.out.println("Vou meter uma peca numa coluna normal");
                            Tabuleiro[LastR][LastC] = temp;

                            AllPiece[4][0] = 1;
                            
                            //System.out.println("estou a imprimir o R e o C antes de enviar para a proxima:\n" + LastR + " " + LastC);
                            int resultado = ComparePieces(PieceArray.get(i), i, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                            if(resultado == 1){
                                return 1;
                            }
                            else{
                                AllPiece[4][0] = 1;
                                
                            }
                        }

                    }
                       
                }
                
            }
        }


        
        
        
        return 0;
        
       
    }
    
    public static void main(String[] args) {
        ArrayList<int[][]> PieceArray = new ArrayList<>();
        HashMap<int[],ArrayList<Integer>> compatibleKeys = new HashMap<int[],ArrayList<Integer>>();
        int N = 0,R = 0,C = 0;
        
        int[][][] Tabuleiro;
        
        Scanner sc = new Scanner(System.in);
        String answer;
        answer = sc.nextLine();
        int tries = 0;
        try {
            tries = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
        }

        for (int i = 0; i < tries; i++) {
            //System.out.println("Try Number: " + i);
            answer = sc.nextLine();
            String[] answerArray = answer.split(" ");
            
            try {

                N = Integer.parseInt(answerArray[0]);
                

                R = Integer.parseInt(answerArray[1]);
                C = Integer.parseInt(answerArray[2]);
                
                if(N == (R*C)){
                }else{
                    return;
                }
                
            } catch (NumberFormatException ex) {
            }
            
            for (int j = 0; j < N; j++) {
                answer = sc.nextLine();
                answerArray = answer.split(" ");
                int[] answerInt = new int[4];
                try {
                    for(int num = 0; num<4; num++){
                        answerInt[num] = Integer.parseInt(answerArray[num]);
                    }
                    int[][] temp = new int[5][4];
                    temp[0] = answerInt;
                    temp[1] = new int[]{answerInt[3],answerInt[0],answerInt[1],answerInt[2]};
                    temp[2] = new int[]{answerInt[2],answerInt[3],answerInt[0],answerInt[1]};
                    temp[3] = new int[]{answerInt[1],answerInt[2],answerInt[3],answerInt[0]};
                    temp[4] = new int[]{0,0,0,0};
                    PieceArray.add(temp);
                    
                } catch (NumberFormatException ex) {
                }
            }
            
        
            
            int[][] FirstPiece = PieceArray.get(0);
          
            Tabuleiro = new int[R][C][4];
            int[] fill = new int[]{1,1,1,1};
            for(int row = 0; row<R; row++){
                for(int column = 0; column<C; column++){
                    Tabuleiro[row][column] = fill;
                }
            }
            Tabuleiro[0][0] = FirstPiece[0];
            
            FirstPiece[4][0] = 1;
            //System.out.println("First Piece of the game has been placed: \n" + Tabuleiro[0][0]);
            //Para sabermos onde vamos ter que colocar a proxima peca
            int LastPieceR = 0; 
            int LastPieceC = 0;
            
            
            if(ComparePieces(FirstPiece, 0, PieceArray, Tabuleiro,LastPieceR,LastPieceC,compatibleKeys) == 1){
                printPieces(Tabuleiro);
            }
            else{
                printPieces(Tabuleiro);
                System.out.println("impossible puzzle!");
            }
            
            
            
            PieceArray.clear();
        }

    }
    
}
