public class ListaChar {
    private NoHuffman simbolo;
    private ListaChar proximoChar;

    public ListaChar(){
        this.simbolo = null;
        this.proximoChar = null;
    }

    public void SetSimbolo(NoHuffman simbolo){
        this.simbolo = simbolo;
    }

    public NoHuffman GetSimbolo(){
        return this.simbolo;
    }

    public void SetProximoChar(ListaChar proximoChar){
        this.proximoChar = proximoChar;
    }

    public ListaChar GetProximoChar(){
        return this.proximoChar;
    }
}
