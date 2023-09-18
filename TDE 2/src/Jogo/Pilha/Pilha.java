package Jogo.Pilha;

public class Pilha {
    private Node topo;

    public Pilha() {
        topo = null;
    }

    public Pilha(Node topo) {
        this.topo = topo;
    }

    public void push(int dado) {
        Node novo = new Node(dado);
        novo.setProximo(topo);
        topo = novo;
    }

    public Integer pop() {
        if (estaVazia()) return null;
        Integer excluido = getInfo();
        topo = topo.getProximo();
        return excluido;
    }

    public Integer getInfo() {
        if (estaVazia()) return null;
        return topo.getInfo();
    }

    public Node getTopo() {
        return topo;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
