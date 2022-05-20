package RN;

import javax.swing.JOptionPane;

public class Arvore{

    private No raiz;
    private final No nil = new No();

    public No getRaiz() {
        return raiz;
    }

    public Arvore() {
        this.raiz = this.nil;
        this.raiz.setPai(this.nil);
        this.nil.setCor(true);
        this.nil.setConteudo(0);
    }

    private void left_rotate(No x) {
        No y = x.getDir();

        x.setDir(y.getEsq());
        if (!y.getEsq().equals(this.nil)) {
            y.getEsq().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai().equals(this.nil)) {
            this.raiz = y;
            y.setEsq(x);
            x.setPai(y);
        } else if (x == x.getPai().getEsq()) {
            y.setPai(x.getPai());
            x.getPai().setEsq(y);
            y.setEsq(x);
            x.setPai(y);
        } else {
            x.getPai().setDir(y);
            y.setPai(x.getPai());
            y.setEsq(x);
            x.setPai(y);
        }
    }

    private void righr_rotate(No x) {
        No y = x.getEsq();
        x.setEsq(y.getDir());
        if (!y.getDir().equals(this.nil)) {
            y.getDir().setPai(x);
        }
        y.setPai(x.getPai());
        if (x.getPai().equals(this.nil)) {
            this.raiz = y;
            y.setDir(x);
            x.setPai(y);
        } else if (x == x.getPai().getDir()) {
            y.setPai(x.getPai());
            x.getPai().setDir(y);
            y.setDir(x);
            x.setPai(y);
        } else {
            x.getPai().setEsq(y);
            y.setPai(x.getPai());
            y.setDir(x);
            x.setPai(y);
        }

    }

    public void inserir(int z) {
        No n = new No();
        n.setConteudo(z);
        n.setCor(false);
        this.RB_Insert(n);
    }

    private void RB_Insert(No z) {
        No y = this.nil;
        No x = this.raiz;
        while (!x.equals(this.nil)) {
            y = x;
            if (z.getConteudo() < x.getConteudo()) {
                x = x.getEsq();
            } else {
                x = x.getDir();
            }
            if (x == null) {
                x = this.nil;
            }
        }
        x = y;
        z.setPai(y);
        if (y.equals(this.nil)) {
            this.raiz = z;
            this.raiz.setEsq(this.nil);
            this.raiz.setDir(this.nil);
        } else if (z.getConteudo() < x.getConteudo()) {
            y.setEsq(z);
        } else {
            y.setDir(z);

        }
        z.setCor(false);
        z.setDir(nil);
        z.setEsq(nil);
        z.setCor(false);
        this.RB_insert_Fixup(z);
        this.raiz.setCor(true);
    }

    private void RB_insert_Fixup(No z) {
        while (!z.getPai().isCor()) {
            if (z.getPai() == z.getPai().getPai().getEsq()) {
                No y = z.getPai().getPai().getDir();
                if (!y.isCor()) {
                    z.getPai().setCor(true);
                    y.setCor(true);
                    z.getPai().getPai().setCor(false);
                    z = z.getPai().getPai();
                } else {
                    if (z == z.getPai().getDir()) {
                        z = z.getPai();
                        this.left_rotate(z);
                    }
                    z.getPai().setCor(true);
                    z.getPai().getPai().setCor(false);
                    this.righr_rotate(z.getPai().getPai());
                }
            } else {
                No y = z.getPai().getPai().getEsq();
                if (!y.isCor()) {
                    z.getPai().setCor(true);
                    y.setCor(true);
                    z.getPai().getPai().setCor(false);
                    z = z.getPai().getPai();
                } else {
                    if (z == z.getPai().getEsq()) {
                        z = z.getPai();
                        this.righr_rotate(z);
                    }
                    z.getPai().setCor(true);
                    z.getPai().getPai().setCor(false);
                    this.left_rotate(z.getPai().getPai());
                }
            }
        }
        this.raiz.setCor(true);
    }

    private void RB_tansplant(No u, No v) {
        if (u.getPai().equals(this.nil)) {
            this.raiz = v;
        } else if (u.equals(u.getPai().getEsq())) {
            u.getPai().setEsq(v);
        } else {
            u.getPai().setDir(v);
        }
        v.setPai(u.getPai());
    }

    public void RB_delete(int v) {
        No z = this.encontra(v, this.raiz);
        No y = z;
        boolean cor = y.isCor();
        No x;
        if (z.getConteudo() == v) {
            if (z.getEsq() == this.nil) {
                x = z.getDir();
                this.RB_tansplant(z, z.getDir());
            } else if (z.getDir() == this.nil) {
                x = z.getEsq();
                this.RB_tansplant(z, z.getEsq());
            } else {
                y = this.sucessor(z.getDir());
                cor = y.isCor();
                x = y.getDir();

                if (y.getPai() == z) {
                    x.setPai(y);
                } else {
                    this.RB_tansplant(y, y.getDir());
                    y.setDir(z.getDir());
                    y.getDir().setPai(y);
                }
                this.RB_tansplant(z, y);
                y.setEsq(z.getEsq());
                y.getEsq().setPai(y);
                y.setCor(z.isCor());
            }

            if (cor) {
                this.RB_Delete_fixup(x);
            }
        } else {
            JOptionPane.showMessageDialog(null, "valor encontrado");
        }
    }

    private void RB_Delete_fixup(No x) {
        while (!x.equals(this.raiz) && x.isCor()) {
            No w;
            if (x.getPai().getEsq() == x) {
                w = x.getPai().getDir();
                if (!w.isCor()) {
                    w.setCor(true);
                    x.getPai().setCor(false);
                    this.left_rotate(x.getPai());
                    w = x.getPai().getDir();
                }
                if (w.getEsq().isCor() && w.getDir().isCor()) {
                    w.setCor(false);
                    x = x.getPai();
                } else {
                    if (w.getDir().isCor()) {
                        w.getEsq().setCor(true);
                        w.setCor(false);
                        this.righr_rotate(w);
                        w = x.getPai().getDir();
                    }
                    w.setCor(x.getPai().isCor());
                    x.getPai().setCor(true);
                    w.getDir().setCor(true);
                    this.left_rotate(x.getPai());
                    x = this.raiz;
                }
            } else {
                w = x.getPai().getEsq();
                if (!w.isCor()) {
                    System.out.println("1");
                    w.setCor(true);
                    x.getPai().setCor(false);
                    this.righr_rotate(x.getPai());
                    w = x.getPai().getEsq();
                }
                if (w.getDir().isCor() && w.getEsq().isCor()) {
                    w.setCor(false);
                    x = x.getPai();
                } else {
                    if (w.getEsq().isCor()) {
                        w.getDir().setCor(true);
                        w.setCor(false);
                        this.left_rotate(w);
                        w = x.getPai().getEsq();
                    }
                    w.setCor(x.getPai().isCor());
                    x.getPai().setCor(true);
                    w.getEsq().setCor(true);
                    this.righr_rotate(x.getPai());
                    x = this.raiz;
                }
            }
        }
        x.setCor(true);
    }

    private No sucessor(No aux) {
        while (aux.getEsq().getEsq() != null) {
            aux = aux.getEsq();
        }
        return aux;
    }

    private No encontra(int x, No pt) {
        if (pt == null) {
            return null;
        } else {
            if (x == pt.getConteudo()) {
                return pt;
            } else {
                if (x < pt.getConteudo()) {
                    if (pt.getEsq() == null) {
                        return pt;
                    } else {
                        return encontra(x, pt.getEsq());
                    }
                } else {
                    if (pt.getDir() == null) {
                        return pt;
                    } else {
                        return encontra(x, pt.getDir());
                    }
                }
            }
        }
    }

    public String Sucessor(int valor, Arvore novaArvore){
        No buscado = buscar(valor, novaArvore);
        if(valor == buscado.getConteudo()){
            if (buscado.getDir().getConteudo() == 0){
                return "INF";
            }else {
                buscado = buscado.getDir();
                while (buscado.getEsq().getConteudo() != 0){
                    buscado = buscado.getEsq();
                }
                return "" + buscado.getConteudo();
            }
        }else{
            return "valor nao encontrado";
        }
    }

    public No buscar(int valor, Arvore novaArvore){
        No buscado = novaArvore.getRaiz();
        while (buscado.getConteudo() != valor && buscado.getConteudo() != 0){
            if (buscado.getEsq() != null && valor > buscado.getConteudo())
                buscado = buscado.getDir();
            else if (buscado.getEsq() != null && valor < buscado.getConteudo())
                buscado = buscado.getEsq();
        }
        return buscado;
    }
    public String imprimir() {
        return this.raiz.imprimir(this.raiz);
    }
}
