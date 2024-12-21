import logica.CercaDeFerrovia;
import logica.SifraDeCesar;
import logica.SifraDeSubstituicaoPoliAlfabetica;
import logica.SifraDeTransposicao;

public class App {
    public static void main(String[] args) throws Exception {
    //Exemplo de uso de Sifra de Transposição
        // String mensagem = "aiineidarhmoefaaa#loidr#";
        
        // String decriptado = SifraDeTransposicao.decriptar("amor", mensagem);
        // String encriptado = SifraDeTransposicao.encriptar("amor", decriptado);

        // System.out.println("Texto original: " + mensagem);
        
        // System.out.println("Texto decriptado: " + decriptado);
        // System.out.println("Texto encriptado: " + encriptado);

    //Exemplo de uso de Sifra Poli-Alfabética
        //Lógica montada apenas para caracteres de A à Z 
        // String chave = "key";
        // String mensagem = "testmessage";

        // String resultado = SifraDeSubstituicaoPoliAlfabetica.encriptar(chave, mensagem);
        // System.out.println("Mensagem encriptada: " + resultado);

        // String decriptado = SifraDeSubstituicaoPoliAlfabetica.decriptar(chave, resultado);
        // System.out.println("Mensagem decriptada: " + decriptado);

    //Exemplo de uso Cifra de Cesar
        // String mensagem = "natan";
        // int chave = 12;
        // String encriptado = SifraDeCesar.encriptar(chave, mensagem);
        // String decriptado = SifraDeCesar.decriptar(chave, encriptado);

        // System.out.println("Texto encriptado: " + encriptado);
        // System.out.println("Texto decriptado: " + decriptado);

        // for (int i = 0; i < 5; i++) {
        //     System.out.println(SifraDeCesar.decriptar(i, "qdrghvlvwdgdpdwhpdwlpd"));
        // }

    //Exemplo de uso de cerca de ferrovia
        int chave = 3;
        String mensagem = "mensagem teste";
        String encriptado = CercaDeFerrovia.encriptar(chave, mensagem);

        System.out.println("Mensagem criptografada: " + encriptado);

        String decriptado = CercaDeFerrovia.decriptar(chave, encriptado);

        System.out.println("Mensagem decriptada: " + decriptado);
    }
}
