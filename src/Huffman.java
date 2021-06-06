
public class Huffman {
    private NoHuffman raiz;
    private ListaChar inicioLista;
    private int tamanhoLista = 0;

    public String Compactar(String texto){
        String textoCompactado = "";
        ListaChar mapaFrequenciaChar = this.GetCharFrequencia(texto); // Cria a tabela de frequencia

        ListaMapaBinario mapaBinario = this.criarMapaBinario(mapaFrequenciaChar); // Criação da tabela binária, relaciona o caractere a sequencia binaria

        this.raiz = this.CriarArvore(mapaFrequenciaChar); // Cria a árvore de Huffman
        this.Compactar(raiz, "", mapaBinario);

        for(int i = 0; i < texto.length(); i++){
            char letra = texto.toCharArray()[i];
            String valor = this.GetValorBinario(letra, mapaBinario);
            textoCompactado += valor;
            if(i != texto.length() - 1){
                textoCompactado += " ";
            }
        }

        return textoCompactado;
    }

    private void Compactar(NoHuffman raiz, String sequenciaBinaria, ListaMapaBinario mapaBinario){
        if(raiz.isFolha()){
            this.BuscaInseriBinario(raiz.GetSimbolo(), sequenciaBinaria, mapaBinario);
        }else{
            // Cada vez que desço a esquerda adiciono 0 na sequencia binária
            Compactar(raiz.GetNoEsquerdo(), sequenciaBinaria.concat("0"), mapaBinario);
            // Cada vez que desço a direita adiciono 1 na sequencia binária
            Compactar(raiz.GetNoDireito(), sequenciaBinaria.concat("1"), mapaBinario);
        }
    }

    private NoHuffman CriarArvore(ListaChar mapaFrequenciaChar){
        ListaChar listaPriorizada = this.OrdenaListaChar(mapaFrequenciaChar);
        ListaChar incioListaChar = listaPriorizada;

        // Marca todos os nós como folhas
        while(listaPriorizada.GetSimbolo() != null ){
            listaPriorizada.GetSimbolo().SetFolha(true);
            if(listaPriorizada.GetProximoChar() == null){
                break;
            }
            listaPriorizada = listaPriorizada.GetProximoChar();
        }

        while(tamanhoLista > 1){
            // Pega o primeiro e o segundo nó da lista
            NoHuffman primeiroNo = incioListaChar.GetSimbolo();
            NoHuffman segundoNo = incioListaChar.GetProximoChar().GetSimbolo();

            // Crianção do novo nó raiz
            NoHuffman novoNoRaiz = new NoHuffman('6', primeiroNo.GetFrequencia() + segundoNo.GetFrequencia());
            novoNoRaiz.SetNoEsquerdo(primeiroNo);
            novoNoRaiz.SetNoDireito(segundoNo);

            listaPriorizada = this.BuscaFinalLista(inicioLista); // Encontra o final da lista
            this.InserirLista(novoNoRaiz, listaPriorizada); // Inseri na ultima depois da ultima posisao da lista
            // Remove as duas primeiras posições da lista 
            incioListaChar = this.RemoverLista(incioListaChar);
            incioListaChar = this.RemoverLista(incioListaChar);

            // Reordena a lista de nós novamente
            incioListaChar = this.OrdenaListaChar(incioListaChar);
            listaPriorizada = incioListaChar;
        }

        return listaPriorizada.GetSimbolo();
    }

    // Ordena a lista de frenquencia do char que mais aparece pro menor
    private ListaChar OrdenaListaChar(ListaChar mapaFrequenciaChar){
        ListaChar inicioListaChar = mapaFrequenciaChar;
        ListaChar charAnterior = inicioListaChar;
        ListaChar atual = inicioListaChar;
        ListaChar proxiChar = inicioListaChar.GetProximoChar();
        for(ListaChar i = inicioListaChar; i.GetProximoChar() != null; i = i.GetProximoChar()){
            if(atual.GetSimbolo().GetFrequencia() < proxiChar.GetSimbolo().GetFrequencia()){
                if(atual == inicioListaChar){
                    atual.SetProximoChar(proxiChar.GetProximoChar());
                    proxiChar.SetProximoChar(atual);
                    inicioListaChar = proxiChar;
                    charAnterior = inicioListaChar;
                    atual = charAnterior;
                    proxiChar = atual.GetProximoChar();
                    i = inicioListaChar;
                }else{
                    atual.SetProximoChar(proxiChar.GetProximoChar());
                    proxiChar.SetProximoChar(atual);
                    charAnterior.SetProximoChar(proxiChar);
                    charAnterior = inicioListaChar;
                    atual = charAnterior;
                    proxiChar = atual.GetProximoChar();
                    i = inicioListaChar;
                }
            }else{
                charAnterior = atual;
                atual = proxiChar;
                proxiChar = proxiChar.GetProximoChar();
            }
        }
        return inicioListaChar;
    }

    // Cria uma lista de binarios baseada nas letras do texto
    private ListaMapaBinario criarMapaBinario(ListaChar mapaFrequenciaChar){
        ListaMapaBinario listaBinario = new ListaMapaBinario();
        ListaMapaBinario inicio = listaBinario;
        char primeiraLetra = mapaFrequenciaChar.GetSimbolo().GetSimbolo();
        while(mapaFrequenciaChar != null){
            if(inicio.GetChave() != primeiraLetra){
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
                    listaBinario = novoNO;
                    mapaFrequenciaChar = mapaFrequenciaChar.GetProximoChar();
                }

            }
        }
        return inicio;
    }

    // Cria uma lista com as letra que tem no texto e a frequencia com que ela aparecem
    private ListaChar GetCharFrequencia(String texto){
        ListaChar lista = new ListaChar();;

        for(int y = 0; y < texto.length(); y++){
            char letra = texto.toCharArray()[y];
            if(lista.GetSimbolo() == null){
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
            tamanhoLista++;
        }
        lista = inicioLista;

        return lista;
    }

    // Percorre a lista de binarios até chegar ao final dela
    private ListaMapaBinario BuscarNaListaBinaria(ListaMapaBinario lista){
        ListaMapaBinario atual, finalLista;
        finalLista = lista;
        atual = lista;
        while(atual != null && atual.getProxiBinario() != null){
            finalLista = atual;
            atual = atual.getProxiBinario();
        }
        finalLista = atual;
        return finalLista;
    }

    // Busca pela chave passada e retorna o valor binario
    private String GetValorBinario(char letra, ListaMapaBinario mapaBinario){
        while(mapaBinario != null && mapaBinario.GetChave() != letra){
           mapaBinario = mapaBinario.getProxiBinario();
        }
        
        return mapaBinario.GetValor();
    }

    // Busca a chave na lista binaria e insere seu valor
    private void BuscaInseriBinario(char chave,String valor, ListaMapaBinario lista){
        while(lista.GetChave() != chave){
            lista = lista.getProxiBinario();
        }       

        lista.SetValor(valor);
    }

    // Encontra o final da lista
    private ListaChar BuscaFinalLista(ListaChar lista){
        ListaChar atual, finalLista;
        finalLista = lista;
        atual = lista;
        while(atual != null && atual.GetProximoChar() != null){
            finalLista = atual;
            atual = atual.GetProximoChar();
        }
        return finalLista;
    }

    // Faz uma busca na lista de chars até encontra uma letra igual a que foi passada ou chegar ao final da lista
    private ListaChar BuscarNaLista(char letra, ListaChar lista){
        ListaChar atual, finalLista;
        finalLista = lista;
        atual = lista.GetProximoChar();
        atual = lista;
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

    private ListaChar RemoverLista(ListaChar lista){
        ListaChar lixo = lista;
        lista = lixo.GetProximoChar();
        tamanhoLista--;
        return lista;
    }
}
