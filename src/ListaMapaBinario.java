
public class ListaMapaBinario {
    private char chave;
    private String valor;
    private ListaMapaBinario proximoBinario;

    public void SetChave(char letra){
        this.chave = letra;
    }

    public char GetChave(){
        return this.chave;
    }

    public void SetValor(String valor){
        this.valor = valor;
    }

    public String GetValor(){
        return this.valor;
    }

    public void SetProximoBinario(ListaMapaBinario proximo){
        this.proximoBinario = proximo;
    }

    public ListaMapaBinario getProxiBinario(){
        return this.proximoBinario;
    }
}
