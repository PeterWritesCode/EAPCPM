/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapcpm;

/**
 *
 * @author PeterIsMyName
 */

public class Piece {

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

        this.TopTaken = false;
        this.BottomTaken = false;
        this.RightTaken = false;
        this.LeftTaken = false;
        
        this.Placed = false;
        
        this.Rleft = new Piece(BotLeft, TopLeft, TopRight, BotRight, true);
        this.Rright = new Piece(BotRight,BotLeft, TopLeft, TopRight, true);
        this.Rdown = new Piece(TopRight,BotRight,BotLeft, TopLeft, true);
    }
    
    public Piece(int TopLeft, int TopRight, int BotRight, int BotLeft, boolean coiso) {
        this.TopLeft = TopLeft;
        this.TopRight = TopRight;
        this.BotRight = BotRight;
        this.BotLeft = BotLeft;

        this.TopTaken = false;
        this.BottomTaken = false;
        this.RightTaken = false;
        this.LeftTaken = false;
        
        this.Placed = false;
    }
    
    public int getN(){
        return N;
    }
    public int getR(){
        return R;
    }
    public int getC(){
        return C;
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

