package Jogo;

import Jogo.Pilha.Pilha;

import java.util.Random;

public class Partida {
    private Pilha[] pilhas;
    private Random random;
    private int modo;
    private int tamanho;

    public Partida(int tamanho) {
        pilhas = new Pilha[3];
        random = new Random();
        modo = 0;
        this.tamanho = tamanho;
        gerarPilha(tamanho);
    }

    private void gerarPilha(int tamanho) {
        for (int i = 0; i < 3; i++) pilhas[i] = new Pilha();
        for (int i = 0; i < tamanho; i++)
            pilhas[0].push(random.nextInt(1,100));
    }

    public boolean estaOrdenado() {
        Pilha p = null;
        if (pilhas[0].estaVazia() && pilhas[1].estaVazia())
            p = pilhas[2];
        else if (pilhas[0].estaVazia() && pilhas[2].estaVazia())
            p = pilhas[1];
        else if (pilhas[1].estaVazia() && pilhas[2].estaVazia())
            p = pilhas[0];
        return p != null && estaOrdenado(p);
    }

    private boolean estaOrdenado(Pilha pilha) {
        if (pilha.estaVazia()) return false;

        Pilha p = new Pilha(pilha.getTopo());
        int anterior = p.getInfo();
        boolean retorno = true;
        p.pop();

        while (!p.estaVazia()) {
            if (modo == 0 && p.getInfo() < anterior)
                retorno = false;
            else if (modo == 1 && p.getInfo() > anterior)
                retorno = false;
            anterior = p.pop();
        } p = null;
        return retorno;
    }

    public void mover(int de, int para) {
        de--; para--;
        if (pilhas[de].estaVazia()) return;
        pilhas[para].push(pilhas[de].pop());
    }

    public boolean pilhaEstaVazia(int n) {
        return pilhas[n-1].estaVazia();
    }

    public void imprimirPilhas() {
        String string = "\n\n |01| |02| |03|\n ____ ____ ____\n";
        Integer[][] valores = new Integer[3][tamanho];
        int[] tamanhos = new int[3];

        for (int i = 0; i < 3; i++) {
            Pilha p = new Pilha(pilhas[i].getTopo());
            tamanhos[i] = 0;
            for (int j = 0; j < tamanho; j++) {
                valores[i][j] = p.pop();
                if (valores[i][j] != null) tamanhos[i]++;
            }
        }
        for (int i = tamanho - 1; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (tamanhos[j] - 1 >= i)
                    string += valores[j][tamanhos[j] - 1 - i] > 9 ?
                            " |" + valores[j][tamanhos[j] - 1 - i] + "|" :
                            " |0" + valores[j][tamanhos[j] - 1 - i] + "|";
                else string += " |--|";
            } string += '\n';
        } string += " ____ ____ ____\n";
        System.out.println(string);
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int resolver() {
        Pilha p = new Pilha(pilhas[0].getTopo());
        int[] valores = new int[tamanho];
        int atual = 0;
        int ultimaPosicao = 0;
        int posicaoAtual = 0;
        int jogadas = 0;


        for (int i = 0; i < tamanho; i++)
            valores[i] = p.pop();

        while (!estaOrdenado()) {

            if (modo == 0) {
                atual = 0;
                for (int i = 0; i < tamanho; i++) {
                    if (valores[i] > atual) {
                        atual = valores[i];
                        posicaoAtual = i;
                    }
                }
            }
            else {
                atual = 101;
                for (int i = 0; i < tamanho; i++)
                    if (valores[i] < atual && valores[i] != -1) {
                        atual = valores[i];
                        posicaoAtual = i;
                    }
            }
            boolean p1 = ultimaPosicao <= posicaoAtual;
            int n = ultimaPosicao;
            while (valores[n] != atual) {
                if (p1) {
                    if (valores[n] != -1)
                        mover(1, 3);
                    n++;
                } else {
                    if (valores[n] != -1)
                        mover(3, 1);
                    n--;
                } if (valores[n] != -1) {
                    imprimirPilhas();
                    jogadas++;
                }
            }
            if (p1) mover(1,2);
            else mover(3,2);
            imprimirPilhas();
            valores[posicaoAtual] = -1;
            ultimaPosicao = posicaoAtual;
            jogadas++;
        }
        return jogadas;
    }
        // [1,-1,-1,-1,-1]   [][2,3,4,5][1]
}
