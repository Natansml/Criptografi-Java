package modelos;

public class posicaoChave implements Comparable<posicaoChave>{
    public char caracter;
    public int posicao;

    public posicaoChave(char caracter, int posicao) {
        this.caracter = caracter;
        this.posicao = posicao;
    }

    public char getCaracter() {
        return caracter;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString() {
        return "[caracter=" + caracter + ", posicao=" + posicao + "]";
    }

    @Override 
    public int compareTo(posicaoChave outraPosicaoChave) { 
        return Integer.compare(this.posicao, outraPosicaoChave.posicao); 
    }
}
