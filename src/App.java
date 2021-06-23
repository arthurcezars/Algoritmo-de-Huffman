import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.BitSet;

public class App {
    public static void main(String[] args) throws Exception {
       String linha, textoNormal = "", textoCompactado;

       Huffman huffman = new Huffman();

       ListaChar listaFrequencia = new ListaChar();
       ListaMapaBinario mapaBinario;

       try{
            FileReader arquivo = new FileReader("D:\\Repositorio Local\\Faculdade\\UCL - Sistemas de Informação\\Algoritmos e Estruturas " + 
                "de Dados II\\Algoritmo de Huffman\\src\\arquivos\\teste.txt");
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

       try {
           
           //FileWriter fw = new FileWriter("D:\\Repositorio Local\\Faculdade\\UCL - Sistemas de Informação\\Algoritmos e Estruturas " + 
                //"de Dados II\\Algoritmo de Huffman\\src\\arquivos\\testeCompactado.txt");
            
            //fw.write(textoCompactado);
            //fw.close();

            BitSet bs = new BitSet();
            int i = 0;
            for (char simbolo : textoCompactado.toCharArray()) {
                if(simbolo == '0'){
                    bs.set(i, false);
                    i += 1;
                }else if(simbolo == '1'){
                    bs.set(i, true);
                    i += 1;
                }
            }
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\Repositorio Local\\Faculdade\\UCL - Sistemas de Informação\\Algoritmos e Estruturas " + 
                "de Dados II\\Algoritmo de Huffman\\src\\arquivos\\testeCompactado2.txt"));
            oos.writeObject(bs);
            oos.close();

        }catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());           
        }finally{
            System.out.println("Arquivo gerado com sucesso!");  
        }
    }
}
