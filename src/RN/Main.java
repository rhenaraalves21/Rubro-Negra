package RN;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Arvore[] versoes = new Arvore[100];
        List<Valores> INCouREM = new ArrayList<>();
        List<Valores> SUCouIMP = new ArrayList<>();

        FileInputStream stream = new FileInputStream("src/arquivo.txt");
        InputStreamReader reader = new InputStreamReader(stream);

        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while(linha != null) {
            String[] valorLinha = linha.split(" ");
            if (valorLinha.length == 2 && !valorLinha[0].equals("IMP"))
                INCouREM.add(new Valores(valorLinha[0], Integer.parseInt(valorLinha[1])));
            else if (valorLinha.length == 3){
                    SUCouIMP.add(new Valores(valorLinha[0], Integer.parseInt(valorLinha[1]), Integer.parseInt(valorLinha[2])));
            } else if (valorLinha.length == 2 && valorLinha[0].equals("IMP")) {
                SUCouIMP.add(new Valores(valorLinha[0], Integer.parseInt(valorLinha[1]), -1));
            }


            linha = br.readLine();
        }

        int cont = 0;
        for (int i = 0; i < INCouREM.size(); i++) {
            Arvore arvore = new Arvore();
            int j = 0;
            if (cont < 100){
                while (j < cont + 1){
                    if (INCouREM.get(j).getAcao().equals("INC"))
                        arvore.inserir(INCouREM.get(j).getValue());
                    if (INCouREM.get(j).getAcao().equals("REM"))
                        arvore.RB_delete(INCouREM.get(j).getValue());
                    j ++;
                }
                versoes[cont] = arvore;
                cont++;
            }else
                break;

        }

        FileOutputStream streamSaida = new FileOutputStream("src/saida.txt");
        OutputStreamWriter writer = new OutputStreamWriter(streamSaida);
        for (Valores valor: SUCouIMP) {
            if (valor.getAcao().equals("SUC")){
                if(versoes[valor.getVersao()] != null &&
                        versoes[valor.getVersao()].buscar(valor.getValue(), versoes[valor.getVersao()]).getConteudo() != 0){
                    System.out.println("SUC "+ valor.getValue() + " " + valor.getVersao());
                    System.out.println(versoes[valor.getVersao()].Sucessor(valor.getValue(), versoes[valor.getVersao()]));
                }else{
                    System.out.println("SUC "+ valor.getValue() + " " + (cont -1));
                    System.out.println(versoes[cont- 1].Sucessor(valor.getValue(), versoes[cont- 1]));
                }
            }
            if (valor.getAcao().equals("IMP")){
                if(versoes[valor.getValue()] != null){
                    System.out.println("IMP " + valor.getValue());
                    System.out.println(versoes[valor.getValue()].imprimir());
                    writer.write(versoes[valor.getValue()].imprimir());
                    writer.close();
                }

            }
        };

    }
}
