// Include header file
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>


class Settlement {
public:
    // returns a value of standard input in c++
    template < typename T > static T input() {
        T value;
        std::cin >> value;
        return value;
    }

};
class Eapcpm
{
public:
    static void printPieces(std::vector<std::vector<std::vector<int>>> &Tabuleiro)
    {
        int linhas = Tabuleiro.size();
        int colunas = Tabuleiro[0].size();
        std::string toPrint = "";
        std::string toPrintLower = "";
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < colunas; j++)
            {
                toPrint += std::to_string(Tabuleiro[i][j][0]) + " " + std::to_string(Tabuleiro[i][j][1]);
                if (j < colunas - 1)
                {
                    toPrint += "  ";
                }
                toPrintLower += std::to_string(Tabuleiro[i][j][3]) + " " + std::to_string(Tabuleiro[i][j][2]);
                if (j < colunas - 1)
                {
                    toPrintLower += "  ";
                }
            }
            std::cout << toPrint << std::endl;
            std::cout << toPrintLower << std::endl;
            toPrint = "";
            toPrintLower = "";
            if (i < linhas - 1)
            {
                std::cout << "\n";
            }
        }
    }
    static std::vector<Array *>* findCompatibles(int one, int two, std::unordered_map<Array *> compatibleKeys, std::vector<std::vector<std::vector<int>>> &PieceArray, bool firstC)
    {
        std::vector<int> key(2);
        key[0] = one;
        key[1] = two;
        // System.out.println("Looking for a piece compatible with: " + key[0] + " " + key[1] + " in " + firstC);
        if (compatibleKeys[key] != nullptr)
        {
            return compatibleKeys[key];
        }
        else
        {
            std::vector<Array *> arrayFinal = std::vector<>();
            for (int i = 1; i < PieceArray.size(); i++)
            {
                // System.out.println("Peca: " + i + "tem: " + PieceArray[i][1][0]);
                std::vector<int> temp = PieceArray[i][0];
                for (int j = 0; j < 4; j++)
                {
                    // System.out.println("Comparing with: \n" + Arrays.toString(temp));
                    if ((key[0] == temp[0]) && (key[1] == temp[3]))
                    {
                        std::vector<int> tempArray(2);
                        // System.out.println("Added!1");
                        tempArray[0] = i;
                        if (firstC)
                        {
                            // System.out.println("firstC");
                            tempArray[1] = j;
                            tempArray[1]++;
                            if (tempArray[1] > 3)
                            {
                                tempArray[1] = 0;
                            }
                        }
                        else
                        {
                            //                            System.out.println("normal " + j);
                            tempArray[1] = j;
                        }
                        arrayFinal.push_back(tempArray);
                    }
                    Eapcpm::rotatePiece(temp);
                }
            }
            compatibleKeys[key] = arrayFinal;
            return compatibleKeys[key];
        }
    }
    //    numRots esta entre 1 e 4
    static void rotatePiece(std::vector<int> &piece)
    {
        int temp;
        temp = piece[0];
        piece[0] = piece[3];
        piece[3] = piece[2];
        piece[2] = piece[1];
        piece[1] = temp;
    }
    static int ComparePieces(std::vector<std::vector<int>> &first, std::vector<std::vector<std::vector<int>>> &PieceArray, std::vector<std::vector<std::vector<int>>> &Tabuleiro, int LastR, int LastC, std::unordered_map<> compatibleKeys)
    {
        //        System.out.println("entrie na funcao de novo, estamos nesta linha e coluna:" +LastR + " " + LastC );
        int row = Tabuleiro.size();
        int column = Tabuleiro[0].size();
        if ((LastR == row - 1) && (LastC == column - 1))
        {
            // System.out.println("Entrei e vou acabar");
            return 1;
        }
        else
        {
            if (LastC == column - 1)
            {
                // System.out.println("Entrei no segundo e vou continuar");
                LastC = 0;
                LastR += 1;
            }
            else
            {
                LastC++;
            }
        }
        //        System.out.println("LastR: " + LastR + "\n LastC:" + LastC);
        if (LastR == 0)
        {
            //            System.out.println("If para a primeira linha");
            std::vector<> tempCompatible = Eapcpm::findCompatibles(first[0][1], first[0][2], compatibleKeys, PieceArray, false);
            //            System.out.println("TempCompatible dentro do LastR == 0");
            for (int i = 0; i < tempCompatible.size(); i++)
            {
                std::vector<std::vector<int>> AllPiece = PieceArray[tempCompatible[i][0]];
                std::vector<std::vector<int>> temp;
                System.arraycopy(AllPiece[0],0,temp[0],0,AllPiece[0].size());
                System.arraycopy(AllPiece[1],0,temp[1],0,AllPiece[1].size());
                //                System.out.println("Peca a compara antes de rodar:\n" + Arrays.toString(AllPiece[0]) + "\n");
                if (AllPiece[1][0] == 0)
                {
                    for (int rot = 0; rot < tempCompatible[i][1]; rot++)
                    {
                        Eapcpm::rotatePiece(temp[0]);
                    }
                    //                    System.out.println("Rotacao efetuada: " + tempCompatible.get(i)[1]);
                    //                    System.out.println("ainda nao foi placed");
                    //                    System.out.println("Comparing:\n" + Arrays.toString(first[0]) + "\n with:\n" +Arrays.toString(AllPiece[0]));
                    if ((first[0][1] == temp[0][0]) && (first[0][2] == temp[0][3]))
                    {
                        Tabuleiro[LastR][LastC] = temp[0];
                        AllPiece[1][0] = 1;
                        //                        System.out.println("Coloquei a peca. \n" + Arrays.toString(AllPiece[0]) + "\nAo lado da peca" + Arrays.toString(first[0]));
                        //                        System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);printPieces(Tabuleiro);
                        int resultado = Eapcpm::ComparePieces(temp, PieceArray, Tabuleiro, LastR, LastC, compatibleKeys);
                        if (resultado == 1)
                        {
                            return 1;
                        }
                        else
                        {
                            AllPiece[1][0] = 0;
                        }
                    }
                }
            }
        }
        else
        {
            //            System.out.println("entrei no else, NAO ESTOU NA PRIMEIRA LINHA");
            std::vector<> tempCompatible;
            if (LastC == 0 && LastR != 0)
            {
                //                System.out.println("Primeira Coluna da nova Row");
                tempCompatible = Eapcpm::findCompatibles(Tabuleiro[LastR - 1][0][2], Tabuleiro[LastR - 1][0][3], compatibleKeys, PieceArray, true);
            }
            else
            {
                //                System.out.println("DE NOVO NO ELSE");
                tempCompatible = Eapcpm::findCompatibles(first[0][1], first[0][2], compatibleKeys, PieceArray, false);
            }
            for (int i = 0; i < tempCompatible.size(); i++)
            {
                //                System.out.println("Entrei no for de compatibles");
                std::vector<std::vector<int>> AllPiece = PieceArray[tempCompatible[i][0]];
                //                System.out.println("Antes de rodar: \n" + Arrays.toString(AllPiece[0]));
                std::vector<std::vector<int>> temp;
                System.arraycopy(AllPiece[0],0,temp[0],0,AllPiece[0].size());
                System.arraycopy(AllPiece[1],0,temp[1],0,AllPiece[1].size());
                //                System.out.println(Arrays.toString(temp[0]));
                if (AllPiece[1][0] == 0)
                {
                    for (int rot = 0; rot < tempCompatible[i][1]; rot++)
                    {
                        Eapcpm::rotatePiece(temp[0]);
                    }
                    //                    System.out.println("Entrei no if de placed");
                    //                    System.out.println(Arrays.toString(Tabuleiro[LastR-1][LastC]) + "" + Arrays.toString(AllPiece[0]) );
                    if ((Tabuleiro[LastR - 1][LastC][2] == temp[0][1]) && (Tabuleiro[LastR - 1][LastC][3] == temp[0][0]))
                    {
                        //                        System.out.println("sou compativel EM CIMA");
                        //                        System.out.println(Arrays.toString(Tabuleiro[LastR-1][LastC]) + "" + Arrays.toString(AllPiece[0]) );
                        if (LastC == 0)
                        {
                            //                            System.out.println("Vou meter uma peca na Coluna 0");
                            Tabuleiro[LastR][LastC] = temp[0];
                            AllPiece[1][0] = 1;
                            //                            System.out.println("Vou enviar para a funcao de novo, Linha: " + LastR);
                            //                            printPieces(Tabuleiro);
                            int resultado = Eapcpm::ComparePieces(temp, PieceArray, Tabuleiro, LastR, LastC, compatibleKeys);
                            if (resultado == 1)
                            {
                                return 1;
                            }
                            else
                            {
                                AllPiece[1][0] = 0;
                            }
                        }
                        else
                        {
                            if ((first[0][1] == temp[0][0]) && (first[0][2] == temp[0][3]))
                            {
                                //                            System.out.println("Vou meter uma peca numa coluna normal");
                                //                            System.out.println(Arrays.toString(Tabuleiro[LastR-1][LastC]) + "" + Arrays.toString(temp[0]) );
                                Tabuleiro[LastR][LastC] = temp[0];
                                AllPiece[1][0] = 1;
                                //                            System.out.println("estou a imprimir o R e o C antes de enviar para a proxima:\n" + LastR + " " + LastC);
                                //                            printPieces(Tabuleiro);
                                int resultado = Eapcpm::ComparePieces(temp, PieceArray, Tabuleiro, LastR, LastC, compatibleKeys);
                                if (resultado == 1)
                                {
                                    return 1;
                                }
                                else
                                {
                                    AllPiece[1][0] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        //        System.out.println("nao deu vou sair");
        return 0;
    }
    static void main(std::vector<std::string> &args)
    {
        java.util.Scanner sc =  java.util.Scanner(java.lang.System.in);
        std::string answer;
        answer = Settlement::input<string>();
        int tries = 0;
        try
        {
            tries = stoi(answer);
        } catch (java.lang.NumberFormatException ex)
        {
        }
        for (int i = 0; i < tries; i++)
        {
            std::unordered_map<> compatibleKeys = std::unordered_map<>();
            int N = 0;
            int R = 0;
            int C = 0;
            std::vector<std::vector<std::vector<int>>> Tabuleiro;
            std::vector<int> numeros(99);
            // System.out.println("Try Number: " + i);
            answer = Settlement::input<string>();
            std::vector<std::string> answerArray = Settlement::string_split(answer," ");
            try
            {
                N = stoi(answerArray[0]);
                R = stoi(answerArray[1]);
                C = stoi(answerArray[2]);
                if (N == (R * C))
                {
                }
                else
                {
                    std::cout << "impossible puzzle!" << std::endl;
                    break;
                }
            } catch (java.lang.NumberFormatException ex)
            {
            }
            std::vector<std::vector<std::vector<int>>> PieceArray;
            for (int j = 0; j < N; j++)
            {
                answer = Settlement::input<string>();
                answerArray = Settlement::string_split(answer," ");
                std::vector<int> answerInt(4);
                try
                {
                    for (int num = 0; num < 4; num++)
                    {
                        answerInt[num] = stoi(answerArray[num]);
                        numeros[answerInt[num]] += 1;
                    }
                    answerArray = nullptr;
                    std::vector<std::vector<int>> temp;
                    temp[0] = answerInt;
                    temp[1] = std::vector<int>
                            {0, 0, 0, 0};
                    PieceArray[j] = temp;
                } catch (java.lang.NumberFormatException ex)
                {
                }
            }
            int contaImpares = 0;
            for (int c = 0; c < numeros.size(); c++)
            {
                // System.out.println("tou aqui");
                if ((numeros[c] % 2) == 0)
                {
                }
                else
                {
                    contaImpares++;
                }
            }
            if (contaImpares > 4)
            {
                std::cout << "impossible puzzle!" << std::endl;
            }
            else
            {
                std::vector<std::vector<int>> FirstPiece = PieceArray[0];
                Tabuleiro = ;
                Tabuleiro[0][0] = FirstPiece[0];
                FirstPiece[1][0] = 1;
                // System.out.println("First Piece of the game has been placed: \n" + Tabuleiro[0][0]);
                // Para sabermos onde vamos ter que colocar a proxima peca
                int LastPieceR = 0;
                int LastPieceC = 0;
                if (Eapcpm::ComparePieces(FirstPiece, PieceArray, Tabuleiro, LastPieceR, LastPieceC, compatibleKeys) == 1)
                {
                    Eapcpm::printPieces(Tabuleiro);
                }
                else
                {
                    std::cout << "impossible puzzle!" << std::endl;
                }
                Tabuleiro = nullptr;
                compatibleKeys.clear();
                PieceArray = nullptr;
                numeros = nullptr;
                FirstPiece = nullptr;
                System.gc();
            }
        }
    }
};
int main(int argc, char **argv){
    std::vector<std::string> parameter(argv + 1, argv + argc);
    Eapcpm::main(parameter);
    return 0;
};