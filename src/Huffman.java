public class Huffman {
    private NoHuffman raiz;
    private ListaChar inicioLista;

    public String Compactar(String texto){
        ListaChar mapaFrequenciaChar = this.GetCharFrequencia(texto); // Cria a tabela de frequencia

        ListaMapaBinario mapaBinario = this.criarMapaBinario(mapaFrequenciaChar); // Criação da tabela binária, relaciona o caractere a sequencia binaria

        return null;
    }

    private NoHuffman CriarArvore(ListaChar mapaFrequenciaChar){

        return null;
    }

    private ListaChar InicalizarArvore(ListaChar mapaFrequenciaChar){
        ListaChar inicioListaChar = mapaFrequenciaChar;
        return null;
    }

    // Cria uma lista de binarios baseada nas letras do texto
    private ListaMapaBinario criarMapaBinario(ListaChar mapaFrequenciaChar){
        ListaMapaBinario listaBinario = new ListaMapaBinario();
        ListaMapaBinario inicio = listaBinario;
        while(mapaFrequenciaChar.GetProximoChar() != null){
            if(listaBinario == null){
                listaBinario.SetChave(mapaFrequenciaChar.GetSimbolo().GetSimbolo());
                listaBinario.SetValor(null);
                listaBinario.SetProximoBinario(null);
                mapaFrequenciaChar = mapaFrequenciaChar.GetProximoChar();
                inicio = listaBinario;
            }else{
                listaBinario = BuscarNaListaBinaria(inicio);
                if(listaBinario.getProxiBinario() == null){
                    ListaMapaBinario novoNO = new ListaMapaBinario();
                    novoNO.SetChave(mapaFrequenciaChar.GetSimbolo().GetSimbolo());
                    novoNO.SetValor(null);
                    novoNO.SetProximoBinario(listaBinario.getProxiBinario());
                    listaBinario.SetProximoBinario(novoNO);
                }

            }
        }
        return inicio;
    }

    // Cria uma lista com as letra que tem no texto e a frequencia com que ela aparecem
    private ListaChar GetCharFrequencia(String texto){
        ListaChar lista = new ListaChar();

        for(int y = 0; y < texto.length(); y++){
            int i = 0;
            char letra = texto.toCharArray()[i];
            if(lista == null){
                NoHuffman novoNoHuffman = new NoHuffman(letra, 1);
                lista.SetSimbolo(novoNoHuffman);
                inicioLista = lista;
            }else if(lista != null){
                lista = this.BuscarNaLista(letra, inicioLista);
                if(lista.GetSimbolo().GetSimbolo() == letra){
                    lista.GetSimbolo().SetFrequencia(lista.GetSimbolo().GetFrequencia() + 1);
                }else if(lista.GetProximoChar() == null){
                    NoHuffman novoNoHuffman = new NoHuffman(letra, 1);
                    this.InserirLista(novoNoHuffman, lista);
                }
            }
        }
        lista = inicioLista;

        return lista;
    }

    // Percorre a lista de binarios até chegar ao final dela
    private ListaMapaBinario BuscarNaListaBinaria(ListaMapaBinario lista){
        ListaMapaBinario atual, finalLista;
        finalLista = lista;
        atual = lista.getProxiBinario();
        while(atual != null && atual.getProxiBinario() != null){
            finalLista = atual;
            atual = atual.getProxiBinario();
        }
        return finalLista;
    }

    // Faz uma busca na lista de chars até encontra uma letra igual a que foi passada ou chegar ao final da lista
    private ListaChar BuscarNaLista(char letra, ListaChar lista){
        ListaChar atual, finalLista;
        finalLista = lista;
        atual = lista.GetProximoChar();
        while (atual != null && atual.GetSimbolo().GetSimbolo() != letra){
            finalLista = atual;
            atual = atual.GetProximoChar();
        }
        return finalLista;
    }

    // Inseri um novo nó na lista de chars
    private void InserirLista(NoHuffman novoNoHuffman, ListaChar lista){
        ListaChar novoNO = new ListaChar();
        novoNO.SetSimbolo(novoNoHuffman);
        novoNO.SetProximoChar(lista.GetProximoChar());
        lista.SetProximoChar(novoNO);
    }
}
