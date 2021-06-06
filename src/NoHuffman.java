public class NoHuffman {
    private char simbolo;
    private int frequencia;
    private NoHuffman esquerdo;
    private NoHuffman direito;
    private boolean folha;
    
    public NoHuffman(char simbolo, int frequencia){
        this.simbolo = simbolo;
        this.frequencia = frequencia;
        this.esquerdo = null;
        this.direito = null;
    }

    public void SetSimbolo(char simbolo){
        this.simbolo = simbolo;
    }

    public char GetSimbolo(){
        return this.simbolo;
    }

    public void SetFrequencia(int frequencia){
        this.frequencia = frequencia;
    }

    public int GetFrequencia(){
        return this.frequencia;
    }

    public void SetNoEsquerdo(NoHuffman esquerdo){
        this.esquerdo = esquerdo;
    }

    public NoHuffman GetNoEsquerdo(){
        return this.esquerdo;
    }

    public void SetNoDireito(NoHuffman direito){
        this.direito = direito;
    }

    public NoHuffman GetNoDireito(){
        return this.direito;
    }

    public boolean Folha(){
        return this.esquerdo == null &&  this.direito == null;
    }

    public int Compara(NoHuffman no){
        return this.frequencia - no.frequencia;
    }

    public void SetFolha(boolean folha){
        this.folha = folha;
    }

    public boolean isFolha(){
        return folha;
    }

    public String toString(){
        return "[Simbolo=" + this.simbolo + "]";
    }
}
