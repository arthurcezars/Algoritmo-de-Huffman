import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       // App app = new App();
        //String texto = app.GetText();

        Huffman huffman = new Huffman();
        //String textoCompactado = huffman.Compactar(texto);
        String textoCompactado = huffman.Compactar("texto");
        System.out.println("Texto Compactado: " + textoCompactado);
    }

    private String GetText(){
        Scanner io = new Scanner(System.in);
        System.out.println("Escreva uma mensagem: ");
        return io.nextLine();
    }
}
