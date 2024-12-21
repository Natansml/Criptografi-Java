package logica;

public class SifraDeCesar {

    public static String encriptar(int chave, String mensagem) {

        StringBuilder resultado = new StringBuilder();
        for (char c : mensagem.toCharArray()) {

            char novo_char = (char)  (c + chave);
            resultado.append(novo_char);
        }
        return resultado.toString();
    }

    public static String decriptar(int chave, String mensagem) {

        StringBuilder resultado = new StringBuilder();
        for (char c : mensagem.toCharArray()) {

            char novo_char = (char)  (c - chave);
            resultado.append(novo_char);
        }
        return resultado.toString();
    }

}
