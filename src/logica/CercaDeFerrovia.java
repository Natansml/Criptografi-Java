package logica;

public class CercaDeFerrovia {

    public static String encriptar(int chave, String mensagem) {

        Character[][] matrix = new Character[chave][mensagem.length()];

        int incremento = 1;
        int i = 0;
        for (int j = 0; j < mensagem.length(); j++) {
            matrix[i][j] = mensagem.charAt(j);

            if ((i == chave-1 || i == 0) && j > 0) {
                incremento *= -1;
            }

            i += incremento;
        }

        //Exibição da Cerca
        // for (Character[] cs : matrix) {
        //     for (Character cs2 : cs) {
        //         if(cs2 == null) {
        //             System.out.print("-");
        //         } else {
        //             System.out.print(cs2);
        //         }
        //     }
        //     System.out.println("");
        // }

        //Monta a mensagem criptografada
        StringBuilder resultado = new StringBuilder();
        for (Character[] characters : matrix) {
            for (Character character : characters) {
                if (character != null) {
                    resultado.append(character);
                } 
            }
        }

        return resultado.toString();
    }

    public static String decriptar(int chave, String mensagem) {

        Character[][] matrix = new Character[chave][mensagem.length()];


        //Marca as posições em que os caracateres devem ser preenchidos
        int incremento = 1;
        int i = 0;
        for (int j = 0; j < mensagem.length(); j++) {
            matrix[i][j] =  '*';

            if ((i == chave-1 || i == 0) && j > 0) {
                incremento *= -1;
            }

            i += incremento;
        }

        //Itera sobre a matrix inserindo os caracteres na posição correta
        int cont = 0;
        for (int linha = 0; linha < chave; linha++) {
            for (int j = 0; j < mensagem.length(); j++) {
                if (matrix[linha][j] != null) {
                    matrix[linha][j] = mensagem.charAt(cont);
                    cont++;
                }
            }
        }


        // for (Character[] characters : matrix) {
        //     for (Character character : characters) {
        //         if (character != null) {
        //             System.out.print(character);
        //         }else {
        //             System.out.print("-");
        //         } 
        //     }
        //     System.out.println("");
        // }


        //Monta a mensagem original
        StringBuilder resultado = new StringBuilder();
        for (int j = 0; j < mensagem.length(); j++) {
            for (int j2 = 0; j2 < chave; j2++) {
                if (matrix[j2][j] != null) {
                    resultado.append(matrix[j2][j]);
                }
            }
        }

        return resultado.toString();
    }
}
