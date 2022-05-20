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

    List<String> listaInOrdem = new ArrayList<>();
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
        inOrdem(raiz);
        StringBuilder result = new StringBuilder();
        for (String valor: listaInOrdem
             ) {
            result.append(valor);
        }
        return result.toString();
    }
}
