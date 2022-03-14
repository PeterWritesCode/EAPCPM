package pcpmea;

public class Piece {

    int TopLeft;
    int TopRight;
    int BotRight;
    int BotLeft;

    boolean TopTaken;
    boolean BottomTaken;
    boolean RightTaken;
    boolean LeftTaken;

    public Piece(int TopLeft, int TopRight, int BotRight, int BotLeft) {
        this.TopLeft = TopLeft;
        this.TopRight = TopRight;
        this.BotRight = BotRight;
        this.BotLeft = BotLeft;

        this.TopTaken = false;
        this.BottomTaken = false;
        this.RightTaken = false;
        this.LeftTaken = false;
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

    public boolean getTopTaken() {
        return TopTaken;
    }

    public boolean getBottomTaken() {
        return BottomTaken;
    }

    public boolean getRightTaken() {
        return RightTaken;
    }

    public boolean getLeftTaken() {
        return LeftTaken;
    }

    public int ComparePieces(Piece first, Piece second) {
        if ((!(first.getRightTaken()) && !(second.getLeftTaken()))) {
            if ((first.getTopRight() == second.getTopLeft()) && (first.getBotRight() == second.getBotLeft())) {
                return 1;
            }
        }
        if ((!(first.getLeftTaken()) && !(second.getRightTaken()))) {
            if ((first.getTopLeft() == second.getTopRight()) && (first.getBotLeft() == second.getBotRight())) {
                return 2;
            }
        }
        if ((!(first.getTopTaken()) && !(second.getBottomTaken()))) {
            if ((first.getTopRight() == second.getBotRight()) && (first.getTopLeft() == second.getBotLeft())) {
                return 3;
            }
        }
        if ((!(first.getBottomTaken()) && !(second.getTopTaken()))) {
            if ((first.getBotRight() == second.getTopRight()) && (first.getBotLeft() == second.getTopLeft())) {
                return 4;
            }
        } else {
            return 0;
        }
        return 0;
    }

    public String toString() {
        return (this.getTopLeft() + this.getTopRight() + "\n"
                + this.getBotLeft() + this.getBotRight() + "\n");
    }
}
