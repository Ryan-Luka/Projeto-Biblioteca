import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;
public class Menu {
    public static void main(String[] args) {
        ArrayList<String> titulo = new ArrayList<>();
        ArrayList<String> autor = new ArrayList<>();
        ArrayList<String> ano = new ArrayList<>();
        ArrayList<Integer> codigo = new ArrayList<>();
        ArrayList<String> leitor = new ArrayList<>();
        HashMap<Integer, Boolean> disponibilidade = new HashMap<>();

        int contadorCodigo = 0;
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
            int codigoLivro;
            int indice;
            int codStatus;
            switch (opcao){
                case 1:
                    String tituloLivro = JOptionPane.showInputDialog("Informe o título do livro:");
                    titulo.add(tituloLivro);

                    String autorLivro = JOptionPane.showInputDialog("Informe autor do livro:");
                    autor.add(autorLivro);

                    String anoLivro = JOptionPane.showInputDialog("Informe a ano do livro:");
                    ano.add(anoLivro);

                    leitor.add("");

                    contadorCodigo++;
                    codigo.add(contadorCodigo);

                    disponibilidade.put(contadorCodigo, true);

                    JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");

                    break;


                case 2:
                    StringBuilder livros = new StringBuilder("Livros cadastrados:\n");
                    if (titulo.isEmpty()){
                        livros.append("Nenhum livro cadastrado!");
                    }else {
                        for (int i = 0; i < titulo.size(); i++) {
                            codStatus = codigo.get(i);
                            statusLivro = disponibilidade.get(codStatus) ? "Disponível" : "Emprestado";
                            livros.append("Título: ").append(titulo.get(i))
                                    .append(", Autor: ").append(autor.get(i))
                                    .append(", Ano: ").append(ano.get(i))
                                    .append(", Código: ").append(codigo.get(i))
                                    .append(", Status: ").append(statusLivro).append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, livros.toString());
                    break;

                case 3:
                    String buscarLivro = JOptionPane.showInputDialog("Informe parte do título do livro:").toLowerCase();
                    boolean encontrado = false;
                    StringBuilder resultado = new StringBuilder("Livros encontrados:\n");
                    for (int i = 0; i < titulo.size(); i++) {
                        if (titulo.get(i).toLowerCase().contains(buscarLivro)) {
                            codStatus = codigo.get(i);
                            statusLivro = disponibilidade.get(codStatus) ? "Disponível" : "Emprestado";
                            resultado.append("Título: ").append(titulo.get(i))
                                    .append(", Autor: ").append(autor.get(i))
                                    .append(", Ano: ").append(ano.get(i))
                                    .append(", Código: ").append(codigo.get(i))
                                    .append(", Status: ").append(statusLivro).append("\n");
                            encontrado = true;
                        }

                    }
                    if (!encontrado){
                        JOptionPane.showMessageDialog(null, "Nenhum livro encontrado!");
                    }else{
                        JOptionPane.showMessageDialog(null, resultado.toString());
                    }
                    break;

                case 4:
                    codigoLivro = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do livro:"));
                    if(disponibilidade.containsKey(codigoLivro)){

                        if (disponibilidade.get(codigoLivro)){
                            String nomeLeitor = JOptionPane.showInputDialog("Informe seu nome:");
                            indice = codigo.indexOf(codigoLivro);
                            leitor.set(indice, nomeLeitor);
                            disponibilidade.put(codigoLivro, false);
                        }else{
                            JOptionPane.showMessageDialog(null, "Livro está emprestado!");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                    }
                    break;

                case 5:
                    codigoLivro = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do livro:"));
                    if(disponibilidade.containsKey(codigoLivro)){

                        if (disponibilidade.get(codigoLivro)){
                            JOptionPane.showMessageDialog(null, "Livro está disponível!");
                        }else{
                            indice = codigo.indexOf(codigoLivro);
                            leitor.set(indice, "");
                            disponibilidade.put(codigoLivro, true);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                    }
                    break;

                case 6:
                    codigoLivro = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do livro:"));
                    if(codigo.contains(codigoLivro)){
                        if(disponibilidade.get(codigoLivro)){
                            Object[] opcoes = {"Sim", "Não", "Cancelar"};
                            int resposta = JOptionPane.showInternalOptionDialog(null, "Escolha uma opção:", "Excluir livro?",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[2]);
                            if (resposta == 0){
                                indice = codigo.indexOf(codigoLivro);
                                titulo.remove(indice);
                                autor.remove(indice);
                                ano.remove(indice);
                                codigo.remove(indice);
                                leitor.remove(indice);
                                disponibilidade.remove(codigoLivro);
                                JOptionPane.showMessageDialog(null, "Livro excluido com sucesso!");
                            }else if (resposta == 1){
                                JOptionPane.showMessageDialog(null, "Livro não excluido!");
                            }else if (resposta == 2){
                                JOptionPane.showMessageDialog(null, "Operação cancelada!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Livro está emprestado e não pode ser excluído!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Livro não encontrado!");
                    }

                    break;

                case 7:
                    StringBuilder livrosEmprestados = new StringBuilder("Livros Emprestados:\n");
                    if (titulo.isEmpty()){
                        livrosEmprestados.append("Nenhum livro cadastrado!");
                    }else {
                        for (int i = 0; i < titulo.size(); i++) {
                            codStatus = codigo.get(i);
                            statusLivro = disponibilidade.get(codStatus) ? "Disponível" : "Emprestado";
                            if (!disponibilidade.get(codStatus)) {
                                livrosEmprestados.append("Título: ").append(titulo.get(i))
                                        .append(", Autor: ").append(autor.get(i))
                                        .append(", Ano: ").append(ano.get(i))
                                        .append(", Código: ").append(codigo.get(i))
                                        .append(", Status: ").append(statusLivro)
                                        .append(", Leitor: ").append(leitor.get(i)).append("\n");
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, livrosEmprestados.toString());
                    break;

                case 8:
                    int disponiveis = 0;
                    int emprestados = 0;
                    for (int i = 0; i < titulo.size(); i++) {
                        codStatus = codigo.get(i);
                        if (disponibilidade.get(codStatus)){
                            disponiveis++;
                        }else{
                            emprestados++;
                        }
                    }
                    int total = disponiveis + emprestados;
                    JOptionPane.showMessageDialog(null, "Quantidade de livros disponíveis para emprestimo: " + disponiveis +
                            "\nQuantidade de livros emprestados: " + emprestados +
                            "\nQuantidade total: " + total);

                    break;

                case 9:
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Digite um valor numérico válido no campo!",
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

