package RN;

import java.util.ArrayList;
import java.util.List;

public class No {

    private int conteudo;
    private boolean cor;
    private No dir;
    private No esq;
    private No pai;

    public int getConteudo() {
        return conteudo;
    }

    public void setConteudo(int conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isCor() {
        return cor;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    private void inOrdem(No raiz){
        if(raiz.getEsq().getConteudo() != 0) {
            inOrdem(raiz.getEsq());
        }
        visitar(raiz);
        if(raiz.getDir().getConteudo() != 0) {
            inOrdem(raiz.getDir());
        }
    }
    List<String> listaInOrdem;
    private void visitar(No raiz) {
        String cor = raiz.isCor() ? "N" : "R";
        String conteudo = raiz.getConteudo() + "";
        int altura = 0;
        while (raiz.getPai().getConteudo() != 0){
            raiz = raiz.getPai();
            altura ++;
        }
        listaInOrdem.add(conteudo + "," + altura + "," + cor + " ");
    }

    public String imprimir(No raiz){
        listaInOrdem = new ArrayList<>();
        inOrdem(raiz);
        StringBuilder result = new StringBuilder();
        for (String valor: listaInOrdem
             ) {
            result.append(valor);
        }
        return result.toString();
    }

    public void imp() {
        if (dir != null) {
            dir.imprimirArvore(true, "");
        }
        printValue();
        if (esq != null) {
            esq.imprimirArvore(false, "");
        }
    }

    private void printValue() {
        System.out.println(conteudo + "" + cor);
    }

    // use string and not stringbuffer on purpose as we need to change the indent at each recursion
    private void imprimirArvore(boolean isRight, String indent) {
        if (dir != null) {
            dir.imprimirArvore(true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printValue();
        if (esq != null) {
            esq.imprimirArvore(false, indent + (isRight ? " |      " : "        "));
        }
    }
}
