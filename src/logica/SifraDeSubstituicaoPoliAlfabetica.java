package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import modelos.posicaoChave;

public class SifraDeSubstituicaoPoliAlfabetica {
    //OU sifra de Vigenère
    private static char[][] gerarMatriz() {

        List<posicaoChave> alfabeto = new ArrayList<>();
        List<Character> alfabeto_mod = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) { 
            posicaoChave pos = new posicaoChave(c, ((int) c) % 97);
            alfabeto.add(pos);
            alfabeto_mod.add(c);
        }

        char[][] matrix = new char[alfabeto_mod.size()][alfabeto_mod.size()];

        for (int i = 0; i < alfabeto_mod.size(); i++) {
            for (int j = 0; j < alfabeto_mod.size(); j++) {
                matrix[i][j] = alfabeto_mod.get(j);
            }
            alfabeto_mod.add(alfabeto_mod.remove(0));
        }

        // Exibição da matriz resultante
        // for (char[] cs : matrix) {
        //     for (char cs2 : cs) {
        //         System.out.print(cs2 + " ");
        //     }
        //     System.out.println(" ");
        // }
        // System.out.println("----------------------------------------");
        return matrix;
    }

    public static String encriptar(String chave, String mensagem) {
        chave = chave.replaceAll("\\s+", "").toLowerCase();
        mensagem = mensagem.replaceAll("\\s+", "").toLowerCase();
        
        char [][] matrix = SifraDeSubstituicaoPoliAlfabetica.gerarMatriz();

        //Gera lista de caracteres para uso como referencia
        List<Character> alfabeto_ref = new ArrayList<>();
        for (char cs : matrix[0]) {
            alfabeto_ref.add(cs);
        }

        //Recupera a letra da chave e a letra da mensagem e recupera a letra correta na matriz
        StringBuilder mensagem_criptografada = new StringBuilder();
        for (int i = 0; i < mensagem.length(); i++) {
            char letra_chave = chave.charAt(i%chave.length());
            char letra_mensagem = mensagem.charAt(i);
            int indice_coluna = alfabeto_ref.indexOf(letra_chave);
            int indice_linha = alfabeto_ref.indexOf(letra_mensagem);
            mensagem_criptografada.append(matrix[indice_linha][indice_coluna]);
        }
        
        return mensagem_criptografada.toString();
    }

    public static String decriptar(String chave, String mensagem) {
        chave = chave.replaceAll("\\s+", "").toLowerCase();
        mensagem = mensagem.replaceAll("\\s+", "").toLowerCase();

        char [][] matrix = SifraDeSubstituicaoPoliAlfabetica.gerarMatriz();

        //Gera lista de caracteres para uso como referencia
        List<Character> alfabeto_ref = new ArrayList<>();
        for (char cs : matrix[0]) {
            alfabeto_ref.add(cs);
        }

        //Recupera a letra da chave e a letra da mensagem e recupera a letra correta na matriz
        StringBuilder mensagem_resultante = new StringBuilder();
        for (int i = 0; i < mensagem.length(); i++) {
            char letra_chave = chave.charAt(i%chave.length());
            char letra_mensagem = mensagem.charAt(i);
            int indice_coluna = alfabeto_ref.indexOf(letra_chave);

            String coluna = IntStream.range(0, matrix.length)
                .mapToObj(linha -> String.valueOf(matrix[linha][indice_coluna]))
                .collect(Collectors.joining());

            int indice_linha = coluna.indexOf(letra_mensagem);

            // int indice_linha = alfabeto_ref.indexOf(letra_mensagem);
            mensagem_resultante.append(matrix[indice_linha][0]);
        }
        return mensagem_resultante.toString();
    }
}
