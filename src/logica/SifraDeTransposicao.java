package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import modelos.posicaoChave;

public class SifraDeTransposicao {
    //Do Tipo Colunar Simples ou Retangular
    public static ArrayList<posicaoChave> ordenarChave(String chave) {

        chave = chave.strip();
        chave = chave.toLowerCase();
        char[] arraychave = chave.toCharArray();
        Arrays.sort(arraychave);
        //Coloca os caracteres da chave em um set já ordenado
        Set<Character> setDeCaracteres = new LinkedHashSet<>();
        for (char c : arraychave) {
            setDeCaracteres.add(c);
        }
        //Gera um hash map com o indice e a posição na ordem alfabetica de cada caractere
        HashMap<Integer, Integer> posicoes_finais = new HashMap<>();
        int posicao = 1;
        for (Character character : setDeCaracteres) {
            int indice = 0;
            for (Character character2 : chave.toCharArray()) {
                if (character2 == character) {
                    // System.out.println("caracter " + character + " no indice: " + indice + " recebe a posição: " + posicao);
                    posicoes_finais.put(indice, posicao);
                    posicao++;
                }
                indice++;
            }
        }

        //gera um arraylist de objetos contendo o caractere e sua respectiva posição mantendo a ordenação
        ArrayList<posicaoChave> chaveComPosicoes = new ArrayList<>();
        for(int i = 0; i<chave.length(); i++) {
            // posicoes_finais.get(i);
            // chave.charAt(i);
            chaveComPosicoes.add(new posicaoChave(chave.charAt(i), posicoes_finais.get(i)));
        }

        return chaveComPosicoes;
    }

    public static String encriptar(String chave, String mensagem) {
        ArrayList<posicaoChave> chave_ordenada = SifraDeTransposicao.ordenarChave(chave);

        char[] array_mensagem = mensagem.toCharArray();
        char[][] matriz_mensagem = new char[array_mensagem.length/chave.length()+1][chave.length()];
        
        //Estrutura de repetição de popula a matriz que vai conter a mensagem
        for (int i = 0; i < array_mensagem.length; i++) {
            int coluna = i % chave.length();
            int linha = i / chave.length();
            //System.out.println("linha: " + linha + " Coluna: " + coluna);
            matriz_mensagem[linha][coluna] = array_mensagem[i];
        }


        //Gerar uma String com o texto criptografado
        StringBuilder texString = new StringBuilder();
        int posicao = 1;
        //Vasculha todos os elementos de chave ordenada n vezes igual ao tamanho da chave
        for (int i = 0; i < chave_ordenada.size(); i++) {
            //verifica cada elemento de chave_ordenada e caso a posicão seja igual a posição atual...
            for (int j = 0;j < chave_ordenada.size(); j++) {
                int posicao_chave = chave_ordenada.get(j).getPosicao();
                if(posicao_chave == posicao) {
                    //...itera sobre as linhas para concatenar o caractere correto
                    for (int k = 0; k < array_mensagem.length/chave.length()+1; k++) {
                        //Adiciona o caractere correto
                        if (matriz_mensagem[k][j] != '\u0000') {
                        texString.append(matriz_mensagem[k][j]);
                        }
                    }
                    //incrementa a posição e passa para o próximo ciclo
                    posicao++;
                    continue;
                }
            }   
        }
        return texString.toString();
    }

    public static String decriptar(String chave, String mensagem) {
        ArrayList<posicaoChave> chave_ordenada = SifraDeTransposicao.ordenarChave(chave);
        
        char[] array_mensagem = mensagem.toCharArray();
        char[][] matriz_encriptada = new char[array_mensagem.length/chave.length()+1][chave.length()];
        char[][] matriz_resultante = new char[array_mensagem.length/chave.length()+1][chave.length()];
        //Estrutura de repetição de popula a matriz que vai conter a mensagem
        for (int i = 0; i < array_mensagem.length; i++) {
            int coluna = i % chave.length();
            int linha = i / chave.length();
            //System.out.println("linha: " + linha + " Coluna: " + coluna);
            matriz_encriptada[linha][coluna] = array_mensagem[i];
        }

        int posicao = 1;
        int caractere = 0;
        //Verifica cada objeto em chave ordenada
        for (int i = 0; i < chave_ordenada.size(); i++) {
            int posicao_chave = chave_ordenada.get(i).getPosicao();
            if(posicao_chave == posicao) {
                //System.out.println("Posicao: " + posicao_chave + " foi encontrado na coluna: " + i);
                //Verifica se o elemento da coluna i na ultima linha é vazio
                if (matriz_encriptada[array_mensagem.length/chave.length()][i] == '\u0000') {
                    //Sendo vazio sabemos que os caracteres preenchidos na coluna são equivalentes
                    //ao numero de linhas da matriz menos 1 então iteramos sobre cada linha
                    //e colocamos os caracteres na posição correta da matriz na ordem em que a mensagem
                    //encriptada foi enviada
                    for (int j = 0; j < array_mensagem.length/chave.length(); j++) {
                        matriz_resultante[j][i] = mensagem.charAt(caractere);
                        caractere++;
                    }
                } else {
                    //caso não seja vazio o numero de elementos equivale ao numero de linhas
                    //então executamos a mesma lógica do for acima
                    for (int j = 0; j < array_mensagem.length/chave.length()+1; j++) {
                        matriz_resultante[j][i] = mensagem.charAt(caractere);
                        caractere++;
                    }
                }
                posicao++;
                //Reseta o loop para vasculhar todas as chaves novamente
                //procurando a posição seguinte
                i = -1;
            }
        }
        //Por fim gerados o texto decriptado iterando sobre a matriz resultante cujos caracteres
        //já estão ordenados
        StringBuilder texString = new StringBuilder();
        for (char linha[] : matriz_resultante) {
            for (char cs2 : linha) {
                if (cs2 != '\u0000') {
                    texString.append(cs2);
                }
            }
        }
        return texString.toString();
    }
}
