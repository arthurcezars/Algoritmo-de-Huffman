public class NoHuffman {
    private int simbolo;
    private Integer frequencia;
    private NoHuffman esquerdo;
    private NoHuffman direito;
    private Boolean folha;

    public NoHuffman(int simbolo, Integer frequencia){
        this.simbolo = simbolo;
        this.frequencia = frequencia;
        this.esquerdo = null;
        this.direito = null;
    }

    public void SetSimbolo(int simbolo){
        this.simbolo = simbolo;
    }

    public int GetSimbolo(){
        return this.simbolo;
    }

    public void SetFrequencia(Integer frequencia){
        this.frequencia = frequencia;
    }

    public Integer GetFrequencia(){
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

    public void SetFolha(Boolean folha){
        this.folha = folha;
    }

    public Boolean isFolha(){
        return folha;
    }

    public String toString(){
        return "[Simbolo=" + this.simbolo + "]";
    }
}
