import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Menu {
    public static void main(String[] args) {
        ArrayList<String> titulo = new ArrayList<>();
        ArrayList<String> autor = new ArrayList<>();
        ArrayList<String> ano = new ArrayList<>();
        ArrayList<Integer> codigo = new ArrayList<>();
        Random rand = new Random();
        HashMap<Integer, Boolean> disponibilidade = new HashMap<>();
        while(true){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    """
                    Menu Principal:
                    1 - Cadastrar novo livro
                    2 - Listar todos os livros
                    3 - Buscar livro por título
                    4 - Realizar empréstimo de livro
                    5 - Registrar devolução de livro
                    6 - Excluir livro do sistema
                    7 - Listar livros emprestados
                    8 - Contar livros disponíveis e emprestados
                    9 - Sair
                    """));

            switch (opcao){
                case 1:
                    String tituloLivro = JOptionPane.showInputDialog("Informe o título do livro:");
                    titulo.add(tituloLivro);

                    String autorLivro = JOptionPane.showInputDialog("Informe autor do livro:");
                    autor.add(autorLivro);

                    String anoLivro = JOptionPane.showInputDialog("Informe a ano do livro:");
                    ano.add(anoLivro);

                    int codigoLivro;
                    do {
                        codigoLivro = rand.nextInt(1000) + 1;
                    } while (codigo.contains(codigoLivro));

                    codigo.add(codigoLivro);
                    disponibilidade.put(codigoLivro, true);

                    break;


                case 2:
                    String statusLivro;
                    for (int i = 0; i <= titulo.size(); i++) {
                        int codStatus = codigo.get(i);
                        if (disponibilidade.get(codStatus) == true){
                            statusLivro = "Disponível";
                        }
                        else{
                            statusLivro = "Emprestado";
                        }
                        JOptionPane.showMessageDialog(null, "Livros: \n" +
                                "Título: " + titulo.get(i) + ", Autor: " + autor.get(i) + ", Ano: " + ano.get(i) +
                                ", Código: " +  codigo.get(i) + ", Status: " +  statusLivro);
                    }
                    break;
            }
        }

    }
}

