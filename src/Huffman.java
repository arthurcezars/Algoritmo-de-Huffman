public class Huffman {
    private NoHuffman raiz;
    private ListaChar inicioListaChar = new ListaChar();
    private int tamanhoLista = 0;

    public String Compactar(String texto, NoHuffman raiz, ListaMapaBinario mapaBinario){
       String textoCompactado = "";

       this.Compactar(raiz, "", mapaBinario);

        for (int i = 0; i < texto.length(); i++){
          char simbolo = texto.toCharArray()[i];
          
          if (simbolo == '\r'){
            continue;
          }
          else if (simbolo == '\n'){
            textoCompactado += System.lineSeparator();
            continue;
          }

          String valor = this.GetValorBinario(simbolo, mapaBinario);
          
          textoCompactado += valor;
        }

       return textoCompactado;
    }

    private void Compactar(NoHuffman raiz, String sequenciaBinaria, ListaMapaBinario mapaBinario){
       if (raiz.isFolha() != null){
          this.BuscarInserirBinario((char)raiz.GetSimbolo(), sequenciaBinaria, mapaBinario);
        }
       else{
          // Cada vez que desço a esquerda adiciono 0 na sequencia binária 
          Compactar(raiz.GetNoEsquerdo(), sequenciaBinaria += "0", mapaBinario);
          // Cada vez que desço a direita adiciono 1 na sequencia binária
          Compactar(raiz.GetNoDireito(), sequenciaBinaria += "1", mapaBinario);
        }
    }

    private String GetValorBinario(char simbolo, ListaMapaBinario mapaBinario){
       while (mapaBinario != null && mapaBinario.GetChave() != simbolo){
          mapaBinario = mapaBinario.GetProximoBinario();
       }

       return mapaBinario.GetValor();
    }

    private void BuscarInserirBinario(char chave, String valor, ListaMapaBinario lista){
       while (lista.GetChave() != chave){
          lista = lista.GetProximoBinario();
        }

       lista.SetValor(valor);
    }


    /// <summary>
    /// Cria uma lista com a frequencia que um simbolo aparece no texto
    /// </summary>
    /// <param name="texto">String</param>
    /// <returns>ListaFrequenciaChar</returns>
    public ListaChar GetFrequenciaChar(String texto){
        ListaChar lista = this.inicioListaChar;
        
        for (char simbolo : texto.toCharArray()) {
            if(lista.GetSimbolo() == null){
                NoHuffman novoNoHuffman = new NoHuffman(simbolo, 1);
                lista.SetSimbolo(novoNoHuffman);
                tamanhoLista += 1;
            }else if(lista != null){
                lista = this.BuscarNaListaChar(simbolo, inicioListaChar);

                if(lista.GetSimbolo().GetSimbolo() == (int)simbolo){
                    lista.GetSimbolo().SetFrequencia(lista.GetSimbolo().GetFrequencia() + 1);
                }else if(lista.GetProximoChar() == null){
                    NoHuffman novoNoHuffman = new NoHuffman(simbolo, 1);
                    this.InserirNaListaChar(novoNoHuffman, lista);
                    tamanhoLista += 1;
                }
            }
        }
        lista = inicioListaChar;

        return lista;
    }

    /// <summary>
    /// Busca na lista pelo nó que possui o simbolo, caso não encontre retorna a ultima posição da lista
    /// </summary>
    /// <param name="letra">char</param>
    /// <param name="lista">ListaChar</param>
    /// <returns>ListaChar</returns>
    private ListaChar BuscarNaListaChar(char letra, ListaChar lista){
        ListaChar finaLista, atual;
        finaLista = lista;
        atual = lista;

        while(atual != null){
            if(atual.GetSimbolo().GetSimbolo() == (int)letra){
                return atual;
            }

            finaLista = atual;
            atual = atual.GetProximoChar();
        }

        return finaLista;
    }

    /// <summary>
    /// Recebe o ultimo nó da lista e insere no apontador a referencia para um novo nó que é criado no metodo recebendo como simbolo 
    /// um NoHuffman
    /// </summary>
    /// <param name="novoNoHuffman">NoHuffman</param>
    /// <param name="lista">ListaChar</param>
    private void InserirNaListaChar(NoHuffman novoNoHuffman, ListaChar lista){
        ListaChar novoNo = new ListaChar();

        novoNo.SetSimbolo(novoNoHuffman);
        novoNo.SetProximoChar(lista.GetProximoChar());
        lista.SetProximoChar(novoNo);
    }

    /// <summary>
    /// Cria o mapa binario para ser usado na hora de gerar a arvore binaria, usando os simbolos como chave
    /// </summary>
    /// <param name="listaFrequencia">ListaChar</param>
    /// <returns>ListaMapaBinario</returns>
    public ListaMapaBinario CriarMapaBinario(ListaChar listaFrequencia){
        ListaMapaBinario listaBinario = new ListaMapaBinario();
        ListaMapaBinario inicio = listaBinario;

        char primeiroSimbolo = (char)listaFrequencia.GetSimbolo().GetSimbolo();

        while (listaFrequencia != null){
            if (inicio.GetChave() != primeiroSimbolo){
               listaBinario.SetChave((char)listaFrequencia.GetSimbolo().GetSimbolo());
               listaBinario.SetValor(null);
               listaBinario.SetProximoBinario(null);

               listaFrequencia = listaFrequencia.GetProximoChar();
               inicio = listaBinario;
            }
            else{
               listaBinario = this.BuscarFinalListaBinaria(inicio);
               if (listaBinario.GetProximoBinario() == null){
                ListaMapaBinario novoNo = new ListaMapaBinario();
                novoNo.SetChave((char)listaFrequencia.GetSimbolo().GetSimbolo());
                novoNo.SetValor(null);
                novoNo.SetProximoBinario(listaBinario.GetProximoBinario());
                listaBinario.SetProximoBinario(novoNo);
                listaBinario = novoNo;

                listaFrequencia = listaFrequencia.GetProximoChar();
               }
            }
        }

        return inicio;
    }

    /// <summary>
    /// Percorre a lista de binario até encontrar o ultimo nó e retorna para quem chamou o metodo
    /// </summary>
    /// <param name="lista">ListaMapaBinario</param>
    /// <returns>ListaMapaBinario</returns>
    private ListaMapaBinario BuscarFinalListaBinaria(ListaMapaBinario lista)
    {
        ListaMapaBinario anterior, atual;
        anterior = lista;
        atual = lista;

        while (atual != null){
            if (atual.GetProximoBinario() == null)
            {
               return atual;
            }

            anterior = atual;
            atual = atual.GetProximoBinario();
        }

        return anterior;
    }

    public NoHuffman CriarArvore(ListaChar listaFrequenciaChar){
       ListaChar listaOrdenada = this.OrdenarListaChar(listaFrequenciaChar);
       ListaChar inicioListaOrdenada = listaOrdenada;

       while (listaOrdenada.GetSimbolo() != null){
          listaOrdenada.GetSimbolo().SetFolha(true);

          if (listaOrdenada.GetProximoChar() == null){
            break;
          }

          listaOrdenada = listaOrdenada.GetProximoChar();
        }

       listaOrdenada = inicioListaOrdenada;

       while (tamanhoLista > 1){
          NoHuffman primeiroNo = listaOrdenada.GetSimbolo();
          NoHuffman segundoNo = listaOrdenada.GetProximoChar().GetSimbolo();

          NoHuffman novoNo = new NoHuffman('~', primeiroNo.GetFrequencia() + segundoNo.GetFrequencia());
          novoNo.SetNoEsquerdo(primeiroNo);
          novoNo.SetNoDireito(segundoNo);

          listaOrdenada = this.BuscarFinalListaOrdenada(inicioListaOrdenada);
          this.InserirNaListaChar(novoNo, listaOrdenada);
          tamanhoLista += 1;

          inicioListaOrdenada = this.RemoverListaOrdenada(inicioListaOrdenada);
          inicioListaOrdenada = this.RemoverListaOrdenada(inicioListaOrdenada);

          inicioListaOrdenada = this.OrdenarListaChar(inicioListaOrdenada);
          listaOrdenada = inicioListaOrdenada;
        }

       this.raiz = inicioListaOrdenada.GetSimbolo();

       return inicioListaOrdenada.GetSimbolo();
    }

    /// <summary>
    /// Ordena a lista do simbolo que mais aparece para o que menos aparece
    /// </summary>
    /// <param name="listaFrequenciaChar">Listachar</param>
    /// <returns>ListaChar</returns>
    private ListaChar OrdenarListaChar(ListaChar listaFrequenciaChar){
        ListaChar inicioDaLista = listaFrequenciaChar;
        ListaChar noAnterior = inicioDaLista;
        ListaChar noAtual = inicioDaLista;
        ListaChar proximoNo = inicioDaLista.GetProximoChar();

        for (ListaChar i = inicioDaLista; i.GetProximoChar() != null; i = i.GetProximoChar()){

            if (noAtual.GetSimbolo().GetFrequencia() > proximoNo.GetSimbolo().GetFrequencia()){
                if (noAtual == inicioDaLista){
                  noAtual.SetProximoChar(proximoNo.GetProximoChar());
                  proximoNo.SetProximoChar(noAtual);

                  inicioDaLista = proximoNo;

                  noAnterior = inicioDaLista;
                  noAtual = inicioDaLista;
                  proximoNo = inicioDaLista.GetProximoChar();

                  i = inicioDaLista;
                }
               else{
                  ListaChar auxiliar = noAnterior.GetProximoChar();
                  
                  noAnterior.SetProximoChar(noAtual.GetProximoChar());
                  noAtual.SetProximoChar(proximoNo.GetProximoChar());
                  proximoNo.SetProximoChar(auxiliar);
                  
                  noAnterior = inicioDaLista;
                  noAtual = inicioDaLista;
                  proximoNo = inicioDaLista.GetProximoChar();

                  i = inicioDaLista;
                }
            }
            else{
               noAnterior = noAtual;
               noAtual = proximoNo;
               proximoNo = proximoNo.GetProximoChar();
            }
        }   

        return inicioDaLista;
    }

    /// <summary>
    /// Busca o ultimo nó da lista ordenada
    /// </summary>
    /// <param name="lista">ListaChar</param>
    /// <returns>ListaChar</returns>
    private ListaChar BuscarFinalListaOrdenada(ListaChar lista){
        ListaChar anterior, atual;
        anterior = lista;
        atual = lista;

        while (atual != null){
            if (atual.GetProximoChar() == null){
               return atual;
            }

            anterior = atual;
            atual = atual.GetProximoChar();
        }

        return anterior;
    }

    /// <summary>
    /// Recebe a primeira posição da lista e então remove ela devolvendo a proxima prosição com a primeira
    /// </summary>
    /// <param name="lista">ListaChar</param>
    /// <returns>ListaChar</returns>
    private ListaChar RemoverListaOrdenada(ListaChar lista){
        ListaChar lixo = lista;
        lista = lixo.GetProximoChar();

        tamanhoLista -= 1;

        return lista;
    }
   

}
