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

class Piece {

    int TopLeft;
    int TopRight;
    int BotRight;
    int BotLeft;

    boolean TopTaken;
    boolean BottomTaken;
    boolean RightTaken;
    boolean LeftTaken;
    
    boolean Placed;
    
    int N, R, C;
    
    Piece Rleft;
    Piece Rright;
    Piece Rdown;
    
    public Piece(int TopLeft, int TopRight, int BotRight, int BotLeft) {
        this.TopLeft = TopLeft;
        this.TopRight = TopRight;
        this.BotRight = BotRight;
        this.BotLeft = BotLeft;

        this.Placed = false;
        
        this.Rleft = new Piece(BotLeft, TopLeft, TopRight, BotRight, false);
        this.Rright = new Piece(BotRight,BotLeft, TopLeft, TopRight, false);
        this.Rdown = new Piece(TopRight,BotRight,BotLeft, TopLeft, false);
    }
    
    public Piece(int TopLeft, int TopRight, int BotRight, int BotLeft, boolean coiso) {
        this.TopLeft = TopLeft;
        this.TopRight = TopRight;
        this.BotRight = BotRight;
        this.BotLeft = BotLeft;
        
        this.Placed = coiso;
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
    
    public void setTopLeft(int TopLeft) {
        this.TopLeft = TopLeft;
    }
    public void setTopRight(int TopRight) {
        this.TopRight = TopRight;
    }
    public void setBotRight(int BotRight) {
        this.BotRight = BotRight;
    }
    public void setBotLeft(int BotLeft) {
        this.BotLeft = BotLeft;
    }
    public int getTopLeft() {
        return TopLeft;
    }
    public int getTopRight() {
        return TopRight;
    }
    public int getBotRight() {
        return BotRight;
    }
    public int getBotLeft() {
        return BotLeft;
    }

    
    
    

    @Override
    public String toString() {
        return (this.getTopLeft() + " " + this.getTopRight() + "\n"
                + this.getBotLeft() + " " + this.getBotRight() + "\n");
    }
}

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
            toPrintLower = "";
        }
    }
    
    public static ArrayList<Integer> findCompatibles(int one, int two, HashMap<int[],ArrayList<Integer>> compatibleKeys, ArrayList<Piece> PieceArray, boolean firstC){
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
                    //System.out.println("Comparing with: \n" + temp);
                    if(firstC){
                        if((key[0] == temp.getTopLeft()) && (key[1] == temp.getTopRight()) && (tempArray.indexOf(i) == -1)){
                            //System.out.println("Added!1");
                            tempArray.add(i);
                        }
                    }
                    else if((key[0] == temp.getTopLeft()) && (key[1] == temp.getBotLeft()) && (tempArray.indexOf(i) == -1)){
                        //System.out.println("Added!2" + i);
                        tempArray.add(i);
                    }
                }
            }
            compatibleKeys.put(key, tempArray);
            return compatibleKeys.get(key);
        }
    }
    public static int ComparePieces(Piece first, ArrayList<Piece> PieceArray, Piece[][] Tabuleiro, int LastR, int LastC, HashMap<int[],ArrayList<Integer>> compatibleKeys) {
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
        int comoChegueiAqui = 0;
        //System.out.println("LastR: " + LastR + "\n LastC:" + LastC);
        if (LastR == 0) {
            //System.out.println("If para a primeira linha");
            comoChegueiAqui = 1;
            ArrayList<Integer> tempCompatible = findCompatibles(first.getTopRight(),first.getBotRight(),compatibleKeys,PieceArray,false);
            //System.out.println("TempCompatible dentro do LastR == 0" + tempCompatible);
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
                            //System.out.println("Coloquei a peca. \n" + temp + "\nAo lado da peca" + first);
                            //System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);
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
            //System.out.println("entrei no else, NAO ESTOU NA PRIMEIRA LINHA");
            ArrayList<Integer> tempCompatible;
            if(LastC == 0 && LastR != 0){
                //System.out.println("Primeira Coluna da nova Row");
                
                tempCompatible = findCompatibles(Tabuleiro[LastR-1][0].getBotLeft(),Tabuleiro[LastR-1][0].getBotRight(),compatibleKeys,PieceArray,true);
                //System.out.println(tempCompatible.size());
            }
            else{
                //System.out.println("DE NOVO NO ELSE");
                tempCompatible = findCompatibles(first.getTopRight(),first.getBotRight(),compatibleKeys,PieceArray,false);
            }   
            
            
            for(Integer tempCompatibleIndex : tempCompatible){
                //System.out.println("Entrei no for de compatibles");
                Piece temp = PieceArray.get(tempCompatibleIndex);
                //System.out.println("Placed or not: " + temp.getPlaced());
                if(!temp.getPlaced()){
                    //System.out.println("Entrei no if de placed");
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
                            //System.out.println("sou compativel EM CIMA");
                            //System.out.println(Tabuleiro[LastR-1][LastC] + "" + temp );
                            if(LastC == 0){
                                //System.out.println("Vou meter uma peca na Coluna 0");
                                Tabuleiro[LastR][LastC] = temp;

                                PieceArray.get(tempCompatibleIndex).setPlaced();
                                temp.setPlaced();
                                //System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);
                                //printPieces(Tabuleiro);
                                int resultado = ComparePieces(temp, PieceArray ,Tabuleiro, LastR, LastC,compatibleKeys);
                                if(resultado == 1){
                                    return 1;
                                }
                                else{
                                    PieceArray.get(tempCompatibleIndex).setNotPlaced();
                                    temp.setNotPlaced();
                                }
                            }
                            
                            else if((first.getTopRight() == temp.getTopLeft()) && (first.getBotRight() == temp.getBotLeft())){
                                //System.out.println("Vou meter uma peca numa coluna normal");
                                Tabuleiro[LastR][LastC] = temp;

                                PieceArray.get(tempCompatibleIndex).setPlaced();
                                temp.setPlaced();
                                //System.out.println("estou a imprimir o R e o C antes de enviar para a proxima:\n" + LastR + " " + LastC);
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

                try {
                    PieceArray.add(new Piece(Integer.parseInt(answerArray[0]), Integer.parseInt(answerArray[1]),Integer.parseInt(answerArray[2]), Integer.parseInt(answerArray[3])));
                    
                    
                } catch (NumberFormatException ex) {
                }
            }
            
        
            
            Piece FirstPiece = PieceArray.get(0);
          
            Tabuleiro = new Piece[R][C];
            Piece fill = new Piece(1,1,1,1);
            for(int row = 0; row<Tabuleiro.length; row++){
                for(int column = 0; column<Tabuleiro[0].length; column++){
                    Tabuleiro[row][column] = fill;
                }
            }
            Tabuleiro[0][0] = FirstPiece;
            
            FirstPiece.setPlaced();
            //System.out.println("First Piece of the game has been placed: \n" + Tabuleiro[0][0]);
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
