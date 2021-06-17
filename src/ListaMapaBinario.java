public class ListaMapaBinario {
    private char chave;
    private String valor;
    private ListaMapaBinario proximoBinario;

    public void SetChave(char chave){
        this.chave = chave;
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

    public void SetProximoBinario(ListaMapaBinario proximoBinario){
        this.proximoBinario = proximoBinario;
    }

    public ListaMapaBinario GetProximoBinario(){
        return this.proximoBinario;
    }
}
