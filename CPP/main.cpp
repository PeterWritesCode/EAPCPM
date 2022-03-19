#include <vector>
#include <fstream>
#include <iostream>
#include <bits/stdc++.h>
#include <algorithm>
#include <string>
#include <stdlib.h>
using namespace std;



int row;
int column;
int ***Tabuleiro;
int **PieceArray;
map<array<int,2>,vector<int>> compatibleKeys;
map<array<int,3>,vector<int>> compKeysCorners;


 void rotatePiece(int *piece){
    int temp;
    temp = piece[0];
    piece[0] = piece[3];
    piece[3] = piece[2];
    piece[2] = piece[1];
    piece[1] = temp;
}

void printPieces(){

    for(int i = 0; i<row; i++){
        for(int j = 0; j<column; j++){

            cout << Tabuleiro[i][j][0] << " " << Tabuleiro[i][j][1];

            if(j < column-1){
                cout <<"  " ;
            }

        }
        cout << "\n";
        for(int j = 0; j<column; j++){

            cout << Tabuleiro[i][j][3] << " " << Tabuleiro[i][j][2];
            if(j < column-1){
                cout << "  ";
            }
        }
        cout << "\n";

        if(i < row-1){
            cout << "\n";
        }
    }

}

vector<int> *findCornersComp(int one, int two, int three){
    array<int,3> key{one, two,three};

    map<array<int,3>,vector<int>>::iterator ite;
    ite = compKeysCorners.find(key);
    if (ite != compKeysCorners.end())
        return &compKeysCorners.at(key);


    vector<int> arrayFinal;
    int zeroInd = 1;
    int oneInd = 0;
    int twoInd = 3;
    for(int i = 0; i < row*column;i++){
        for(int j = 0; j<4; j++){
            zeroInd = (zeroInd+1)%4;
            oneInd = (oneInd+1)%4;
            twoInd = (twoInd+1)%4;
            if((key[0] == PieceArray[i][twoInd]) && (key[1] == PieceArray[i][oneInd]) && (key[2] == PieceArray[i][zeroInd])){
                //printf("entrei aqui\n");

                arrayFinal.push_back(i);
                break;
            }


        }
    }

    compKeysCorners.insert(pair<array<int,3>,vector<int>>(key, arrayFinal));
    return &compKeysCorners.at(key);
}

//one = baixo, two = meio, three = direita

vector<int> *findCompatibles(int one, int two){
    //cout << "findCompatibles\n";
    //printf(" 1:%d 2:%d\n",one,two);
    map<array<int,2>,vector<int>>::iterator ite;
    ite = compatibleKeys.find({one, two});
    if (ite != compatibleKeys.end()){
        //printf("entrei aqui no if fodido");
        return &compatibleKeys.at({one,two});
    }
    vector<int> arrayFinal;
    for(int i = 1; i< row*column; i++){
        int *temp = PieceArray[i];
        for(int j = 0; j < 4; j++){

            int zeroInd = j+1;
            int oneInd = j;

            if(zeroInd > 3){
                zeroInd = 0;
            }

            if((one == temp[zeroInd]) && (two == temp[oneInd])){
                //printf("Adicionei\n");
                arrayFinal.push_back(i);
                break;
            }
        }
    }
    compatibleKeys.insert(pair<array<int,2>,vector<int>>({one,two}, arrayFinal));
    return &compatibleKeys.at({one,two});

}

int comparePieces(int LastR, int LastC) {
    //printPieces();
    //printf("LastR: %d, LastC: %d\n",LastR,LastC);
    if((LastR == row-1) && (LastC == column-1)){
        return 1;
    }
    else if(LastC == column-1){
        LastC = 0;
        LastR+=1;
    }
    else{
        LastC++;
       // cout << LastC << " Coluna\n";
    }
    //printf("LastR: %d, LastC: %d\n",LastR,LastC);
    if (LastR == 0) {


        vector<int> *tempCompatible = findCompatibles(Tabuleiro[LastR][LastC-1][1],Tabuleiro[LastR][LastC-1][2]);

        for (int i : *tempCompatible) {

            int *AllPiece = PieceArray[i];
            if(AllPiece[4] == 0){

                for(int j = 0; j<4;j++){
                    rotatePiece(AllPiece);
                    if((Tabuleiro[LastR][LastC-1][1] == AllPiece[0]) && (Tabuleiro[LastR][LastC-1][2] == AllPiece[3])){

                        Tabuleiro[LastR][LastC] = AllPiece;


                        AllPiece[4] = 1;
                        int resultado = comparePieces(LastR, LastC);

                        if(resultado == 1){
                            return 1;
                        }

                            AllPiece[4] = 0;
                    }
                }
            }
        }

    }
    else{
        vector<int> *tempCompatible;
        if(LastC == 0){
            //printf("LastC == 0\n");
            tempCompatible = findCompatibles(Tabuleiro[LastR-1][0][2],Tabuleiro[LastR-1][0][3]);
            for (int i : *tempCompatible) {
                //printf("Encontrei uma pecinha hihi\n");
                int *AllPiece = PieceArray[i];

                if(AllPiece[4] == 0){
                    for(int j = 0; j<4;j++){
                        rotatePiece(AllPiece);
                        if((Tabuleiro[LastR-1][LastC][2] == AllPiece[1]) && (Tabuleiro[LastR-1][LastC][3] == AllPiece[0])){

                            Tabuleiro[LastR][LastC] = AllPiece;

                            AllPiece[4] = 1;


                            int resultado = comparePieces(LastR, LastC);

                            if(resultado == 1){
                                return 1;
                            }
                                AllPiece[4] = 0;
                        }
                    }
                }
            }
        }
        else{
            tempCompatible = findCornersComp(Tabuleiro[LastR][LastC-1][2],Tabuleiro[LastR][LastC-1][1],Tabuleiro[LastR-1][LastC][2]);
            for (int i : *tempCompatible) {



                int *AllPiece = PieceArray[i];
                //printf("Ola: %d\n", AllPiece[0]);
                if(AllPiece[4] == 0){
                    for(int j = 0; j<4;j++){
                        rotatePiece(AllPiece);
                        //printf("Adeus: %d\n", AllPiece[0]);
                        if((Tabuleiro[LastR-1][LastC][2] == AllPiece[1]) && (Tabuleiro[LastR][LastC-1][1] == AllPiece[0]) && (Tabuleiro[LastR][LastC-1][2] == AllPiece[3])){

                            Tabuleiro[LastR][LastC] = AllPiece;

                            AllPiece[4] = 1;

                            int resultado = comparePieces(LastR, LastC);

                            if(resultado == 1){
                                return 1;
                            }
                                AllPiece[4] = 0;
                        }
                    }
                }
            }
        }
    }
    return 0;
}


int main() {


    int tries;
    cin >> tries;


    for (int i = 0; i < tries; i++) {
        int N = 0, R = 0, C = 0;


        array<int, 1000> numeros{};

        cin >> N;
        cin >> R;
        cin >> C;

        PieceArray = new int *[N];
        for (int j = 0; j < N; j++) {
            PieceArray[j] = new int[5];


            cin >> PieceArray[j][0] >> PieceArray[j][1] >> PieceArray[j][2] >> PieceArray[j][3];
            numeros[PieceArray[j][0]]++;numeros[PieceArray[j][1]]++;numeros[PieceArray[j][2]]++;numeros[PieceArray[j][3]]++;


            //cout << "\n" << PieceArray[j][0] << " Cenas\n";


            PieceArray[j][4] = 0;
        }

        int contaImpares = 0;
        for (int c = 0; c < 999; c++) if ((numeros[c] % 2) != 0) contaImpares++;

        if (contaImpares > 4) {
            cout << "impossible puzzle!\n";
        } else {

            int *FirstPiece = PieceArray[0];

            Tabuleiro = new int **[R];
            for (int o = 0; o < R; o++) {
                Tabuleiro[o] = new int *[C];
                for (int j = 0; j < C; ++j) {
                    Tabuleiro[o][j] = new int[5];
                }
            }

            Tabuleiro[0][0] = FirstPiece;
            row = R;
            column = C;
            FirstPiece[4] = 1;



            if (comparePieces(0, 0) == 1) {
                printPieces();
            } else {
                cout << "impossible puzzle!\n";
            }
            compatibleKeys.clear();
            compKeysCorners.clear();
        }
    }
}
