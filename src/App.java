import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       String linha, textoNormal = "", textoCompactado;

       Huffman huffman = new Huffman();

       ListaChar listaFrequencia = new ListaChar();
       ListaMapaBinario mapaBinario;

       try{
            FileReader arquivo = new FileReader("D:\\Repositorio Local\\Faculdade\\UCL - Sistemas de Informação\\Algoritmos e Estruturas " + 
                "de Dados II\\Algoritmo de Huffman\\arquivos\\teste.txt");
            BufferedReader bReader = new BufferedReader(arquivo);

            while((linha = bReader.readLine()) != null){
                listaFrequencia = huffman.GetFrequenciaChar(linha);
                textoNormal += linha + System.lineSeparator();
            }

            arquivo.close();
       }catch(Exception e){
           System.out.println("Exception: " + e.getMessage());
       }finally{
            System.out.println("Arquivo lido com sucesso!");
       }

       mapaBinario = huffman.CriarMapaBinario(listaFrequencia);

       NoHuffman raiz = huffman.CriarArvore(listaFrequencia);

       textoCompactado = huffman.Compactar(textoNormal, raiz, mapaBinario);

       System.out.println(textoCompactado);
    }
}
