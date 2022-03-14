package pcpmea;

import static java.lang.Integer.parseInt;
import java.util.*;
import java.util.ArrayList;

/**
 * 
 * 0 - Impossivel
 * 1 - Direita
 * 2 - Esquerda
 * 3 - Cima
 * 4 - Baixo
 * 
 */
public class Pcpmea {

    public static void main(String[] args) {
        System.out.println("Hello World");

        Scanner sc = new Scanner(System.in);
        sc = answer.newLine();
        try {
            int tries = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < tries; i++) {
            sc = answer.newLine();
            String[] answerArray = answer.split();

            try {

                int N = Integer.parseInt(answerArray[0]);
                Piece[] PieceArray = new Piece[N];

                int R = Integer.parseInt(answerArray[1]);
                int C = Integer.parseInt(answerArray[2]);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

            for (int j = 0; j < N; j++) {
                sc = answer.newLine();
                answerArray = answer.split();

                try {
                    Piece[j] = new Piece(Integer.parseInt(answerArray[0]), Integer.parseInt(answerArray[1]),
                            Integer.parseInt(answerArray[2]), Integer.parseInt(answerArray[3]));
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }

        }

        System.out.println(Piece[0].toString());
    }
}