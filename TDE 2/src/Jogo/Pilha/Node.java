package Jogo.Pilha;

public class Node {
    private Integer info;
    private Node proximo;

    public Node(Integer info) {
        this.info = info;
        proximo = null;
    }

    public Integer getInfo() {
        return info;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
