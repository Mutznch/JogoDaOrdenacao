package Jogo;

import java.util.Scanner;

public class Jogo {
    private Scanner scan;
    private int jogada;
    private Partida partida;

    public Jogo() {
        scan = new Scanner(System.in);
        jogada = 0;
    }

    public void menu() {
        System.out.print("""
                        
                        JOGO DA ORDENAÇÃO

                        Digite:
                        0 - sair do jogo
                        1 - jogar

                        -->\s""");
        int escolha = scan.nextInt();
        if (escolha != 1) sair();
        tamanhoDaPilha();
        modoDeJogo();
        iniciar();
    }

    public void tamanhoDaPilha() {
        System.out.print("""
                
                
                Escolha o tamanho da pilha a ser ordenada:
                (digite 0 para sair)
                -->\s""");
        int escolha = scan.nextInt();
        if (escolha <= 0) sair();
        partida = new Partida(escolha);
        jogada = 0;
    }

    public void modoDeJogo() {
        System.out.print("""


                Escolha o modo de ordenação (sentido topo->fundo):
                0 - sair do jogo
                1 - crescente
                2 - decrescente

                -->\s""");
        int escolha = scan.nextInt();
        if (escolha == 1) partida.setModo(0);
        else if (escolha == 2) partida.setModo(1);
        else sair();
    }

    public void iniciar() {
        partida.imprimirPilhas();
        System.out.print("""

                Escolha uma opção:
                0 - sair do jogo
                1 - movimentar
                2 - solução automática

                -->\s""");
        int escolha = scan.nextInt();
        if (escolha == 1) jogar();
        else if (escolha == 2) resolver();
        else sair();
    }

    public void jogar() {
        jogada++;
        int de = 0, para = 0;
        System.out.print("\n\nJogada: " + jogada);
        partida.imprimirPilhas();
        System.out.print("""

                Escolha uma pilha para mover:
                (0 para sair)

                -->\s""");
        int escolha = -1;
        while (escolha < 0 || escolha > 3) {
            escolha = scan.nextInt();
            if (escolha == 0) sair();
            else de = escolha;
            if (escolha < 1 || escolha > 3 || partida.pilhaEstaVazia(escolha))
                System.out.println("""
                        Valor digitado incorreto

                        -->\s""");
        }
        System.out.print("""

                Escolha uma pilha para receber:
                (0 para sair)

                -->\s""");
        escolha = -1;
        while (escolha < 0 || escolha > 3) {
            escolha = scan.nextInt();
            if (escolha == 0) sair();
            else para = escolha;
            if (escolha < 0 || escolha > 3 || de == para)
                System.out.println("""
                        Valor digitado incorreto

                        -->\s""");
        }
        partida.mover(de, para);
        if (partida.estaOrdenado()) {
            partida.imprimirPilhas();
            ganhou();
        }
        else jogar();
    }

    public void resolver() {
        jogada = partida.resolver();
        ganhou();
    }

    public void ganhou() {
        System.out.println("PARABÉNS! Pilha ordenada em " + jogada + " jogadas\n\n" +
                "Digite qualquer valor para voltar ao menu ");
        scan.next();
    }

    public void sair() {
        System.exit(0);
    }
}
