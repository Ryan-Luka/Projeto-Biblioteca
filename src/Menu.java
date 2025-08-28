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
            String statusLivro;
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
                    StringBuilder livros = new StringBuilder("Livros cadastrados:\n");
                    for (int i = 0; i < titulo.size(); i++) {
                        int codStatus = codigo.get(i);
                        if (disponibilidade.get(codStatus)){
                            statusLivro = "Disponível";
                        }
                        else{
                            statusLivro = "Emprestado";
                        }
                        livros.append("Título: ").append(titulo.get(i))
                                .append(", Autor: ").append(autor.get(i))
                                .append(", Ano: ").append(ano.get(i))
                                .append(", Código: ").append(codigo.get(i))
                                .append(", Status: ").append(statusLivro).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, livros.toString());
                    break;

                case 3:
                    String buscarLivro = JOptionPane.showInputDialog("Informe o título do livro:");
                    if (titulo.contains(buscarLivro)){
                        int indice = titulo.indexOf(buscarLivro);
                        int codStatus = codigo.get(indice);
                        if (disponibilidade.get(codStatus)){
                            statusLivro = "Disponível";
                        }
                        else{
                            statusLivro = "Emprestado";
                        }
                        JOptionPane.showMessageDialog(null, "Livros encontrados: \n" +
                                "Título: " + titulo.get(indice) + ", Autor: " + autor.get(indice) +
                                ", Ano: " + ano.get(indice) +
                                ", Código: " +  codigo.get(indice) + ", Status: " + statusLivro);
                    }else{
                        JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                    }
                    break;
            }
        }

    }
}

