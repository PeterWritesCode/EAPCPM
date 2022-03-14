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
public class Eapcpm {

    public static void printPieces(Piece[][] Tabuleiro){
        int linhas = Tabuleiro.length;
        int colunas = Tabuleiro[0].length;
        String toPrint = "";
        String toPrintLower = "";
        for(int i = 0; i<linhas; i++){
            for(int j = 0; j<colunas; j++){
   
                toPrint += Tabuleiro[i][j].getTopLeft() + " " + Tabuleiro[i][j].getTopRight();
                toPrint += "\t";

                toPrintLower += Tabuleiro[i][j].getBotLeft() + " " + Tabuleiro[i][j].getBotRight();
                toPrintLower += "\t";
 
            }
            System.out.println(toPrint);
            System.out.println(toPrintLower);
            toPrint = "";
        }
    }
    
    public static ArrayList<Integer> findCompatibles(int one, int two, HashMap<int[],ArrayList<Integer>> compatibleKeys, ArrayList<Piece> PieceArray){
        int[] key = new int[2];
        key[0] = one;
        key[1] = two;
        if(compatibleKeys.get(key) != null){
            return compatibleKeys.get(key);
        }
        else{
            ArrayList<Integer> tempArray = new ArrayList<>();
            for(int i = 0; i<PieceArray.size(); i++){
                Piece temp = PieceArray.get(i);
                for(int j = 0; j < 4; j++){

                    if(j == 1){
                        temp = PieceArray.get(i).Rright;
                    }
                    if(j == 2){
                        temp = PieceArray.get(i).Rdown;
                    }
                    if(j == 3){
                        temp = PieceArray.get(i).Rleft;
                    }
                    if((key[0] == temp.getTopLeft()) && (key[1] == temp.getBotLeft()) && (tempArray.indexOf(i) == -1)){
                        tempArray.add(i);
                    }
                }
            }
            compatibleKeys.put(key, tempArray);
            return compatibleKeys.get(key);
        }
    }
    public static int ComparePieces(Piece first, ArrayList<Piece> PieceArray, Piece[][] Tabuleiro, int LastR, int LastC, HashMap<int[],ArrayList<Integer>> compatibleKeys) {
        
        
        int row = Tabuleiro.length;
        int column = Tabuleiro[0].length;

        if((LastR == row-1) && (LastC == column-1)){
            return 1;
        }
        if(LastC == column-1){
            LastC = 0;
            LastR+=1;
        }
        else{
            LastC++;
        }
        System.out.println("LastR: " + LastR + "\n LastC:" + LastC);
        if (LastR == 0) {
            
            ArrayList<Integer> tempCompatible = findCompatibles(first.getTopRight(),first.getBotRight(),compatibleKeys,PieceArray);
            for (Integer tempCompatibleIndex : tempCompatible) {
                Piece temp = PieceArray.get(tempCompatibleIndex);
                if(!temp.getPlaced()){
                    for(int i = 0; i<4; i++){
                        if(i == 1){
                            temp = PieceArray.get(tempCompatibleIndex).Rleft;
                        }
                        if(i == 2){
                            temp = PieceArray.get(tempCompatibleIndex).Rright;
                        }
                        if(i == 3){
                            temp = PieceArray.get(tempCompatibleIndex).Rdown;
                        }
                        if((first.getTopRight() == temp.getTopLeft()) && (first.getBotRight() == temp.getBotLeft())){
                            Tabuleiro[LastR][LastC] = temp;
                            
                            
                            PieceArray.get(tempCompatibleIndex).setPlaced();
                            temp.setPlaced();
                            int resultado = ComparePieces(temp, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                            if(resultado == 1){
                                return 1;
                            }
                            else{
                                PieceArray.get(tempCompatibleIndex).setNotPlaced();
                                temp.setNotPlaced();
                            }
                        }   
                    }
                }
            }
        }
        else{
            ArrayList<Integer> tempCompatible;
            if(LastC == 0){
                System.out.println("Primeira Coluna da nova Row");
                tempCompatible = findCompatibles(Tabuleiro[LastR-1][LastC].getBotLeft(),Tabuleiro[LastR-1][LastC].getBotRight(),compatibleKeys,PieceArray);
            }
            else{
                tempCompatible = findCompatibles(first.getTopRight(),first.getBotRight(),compatibleKeys,PieceArray);
            }   
            
            
            for(Integer tempCompatibleIndex : tempCompatible){
                Piece temp = PieceArray.get(tempCompatibleIndex);
                if(!temp.getPlaced()){
                    for(int i = 0; i<4; i++){
                        if(i == 1){
                            temp = PieceArray.get(tempCompatibleIndex).Rleft;
                        }
                        if(i == 2){
                            temp = PieceArray.get(tempCompatibleIndex).Rright;
                        }
                        if(i == 3){
                            temp = PieceArray.get(tempCompatibleIndex).Rdown;
                        }
                        if((Tabuleiro[LastR-1][LastC].getBotRight() == temp.getTopRight()) && (Tabuleiro[LastR-1][LastC].getBotLeft() == temp.getTopLeft())){
                            if(LastC == 0){
                                System.out.println("Vou meter uma peca na Coluna 0");
                                Tabuleiro[LastR][LastC] = temp;

                                PieceArray.get(tempCompatibleIndex).setPlaced();
                                temp.setPlaced();
                                int resultado = ComparePieces(temp, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                                if(resultado == 1){
                                    return 1;
                                }
                                else{
                                    PieceArray.get(tempCompatibleIndex).setNotPlaced();
                                    temp.setNotPlaced();
                                }
                            }
                            else if((first.getTopRight() == temp.getBotRight()) && (first.getTopLeft() == temp.getBotLeft())){
                                System.out.println("Vou meter uma peca numa coluna normal");
                                Tabuleiro[LastR][LastC] = temp;

                                PieceArray.get(tempCompatibleIndex).setPlaced();
                                temp.setPlaced();
                                int resultado = ComparePieces(temp, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                                if(resultado == 1){
                                    return 1;
                                }
                                else{
                                    PieceArray.get(tempCompatibleIndex).setNotPlaced();
                                    temp.setNotPlaced();
                                }
                            }
                        }
                    }   
                }
            }
        }


        
        
        
        return 0;
        
       
    }
    
    public static void main(String[] args) {
        ArrayList<Piece> PieceArray = new ArrayList<>();
        HashMap<int[],ArrayList<Integer>> compatibleKeys = new HashMap<int[],ArrayList<Integer>>();
        int N = 0,R = 0,C = 0;
        Piece[][] Tabuleiro;

        Scanner sc = new Scanner(System.in);
        String answer;
        answer = sc.nextLine();
        int tries = 0;
        try {
            tries = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
        }

        for (int i = 0; i < tries; i++) {
            System.out.println("Try Number: " + i);
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

                try {
                    PieceArray.add(new Piece(Integer.parseInt(answerArray[0]), Integer.parseInt(answerArray[1]),Integer.parseInt(answerArray[2]), Integer.parseInt(answerArray[3])));
                    
                    
                } catch (NumberFormatException ex) {
                }
            }
            
        
            
            Piece FirstPiece = PieceArray.get(0);
          
            Tabuleiro = new Piece[R][C];

            Tabuleiro[0][0] = FirstPiece;
            //Para sabermos onde vamos ter que colocar a proxima peca
            int LastPieceR = 0; 
            int LastPieceC = 0;
            
            
            if(ComparePieces(FirstPiece, PieceArray, Tabuleiro,LastPieceR,LastPieceC,compatibleKeys) == 1){
                printPieces(Tabuleiro);
            }
            else{
                System.out.println("impossible puzzle!");
            }
            
            
            
            PieceArray.clear();
        }

    }
    
}
