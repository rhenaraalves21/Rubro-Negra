package RN;

public class Valores {
    private String acao;
    private int value;
    private  int versao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getVersao() {return versao; }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public Valores(String acao, int value, int versao) {
        this.acao = acao;
        this.value = value;
        this.versao = versao;
    }

    public Valores(String acao, int value) {
        this.acao = acao;
        this.value = value;
    }
}
