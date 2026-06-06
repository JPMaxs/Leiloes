import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        // 1. Conecta ao banco de dados usando o método correto da sua classe conectaDAO
        conn = new conectaDAO().connectDB();
        
        // 2. Prepara o comando SQL de inserção
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            // 3. Executa o comando
            prep.execute();
            prep.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto no DAO: " + erro.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        // 1. Conecta ao banco de dados
        conn = new conectaDAO().connectDB();
        
        // 2. Comando SQL para buscar todos os produtos
        String sql = "SELECT * FROM produtos";
        
        try {
            prep = conn.prepareStatement(sql);
            // 3. Executa a busca e guarda o resultado no resultset
            resultset = prep.executeQuery();
            
            // Limpa a lista antes de preencher para não duplicar dados caso o método seja chamado de novo
            listagem.clear();
            
            // 4. Varre o banco de dados linha por linha e joga na lista do Java
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
            }
            
            prep.close();
            resultset.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos no DAO: " + erro.getMessage());
        }
        
        return listagem;
    }

    public void venderProduto(int id) {
        // 1. Conecta ao banco de dados
        conn = new conectaDAO().connectDB();
        
        // 2. Comando SQL para atualizar o status do produto baseado no ID fornecido
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        try {
            prep = conn.prepareStatement(sql);
            
            // 3. Substitui o ponto de interrogação pelo ID que o usuário digitou
            prep.setInt(1, id);
            
            // 4. Executa a atualização no banco
            prep.executeUpdate();
            prep.close();
            
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto no DAO: " + erro.getMessage());
        }
    } // <-- ESSA CHAVE ESTAVA FALTANDO AQUI!

    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        // 1. Conecta ao banco de dados
        conn = new conectaDAO().connectDB();
        
        // 2. Cria uma nova lista específica para os itens vendidos
        ArrayList<ProdutosDTO> listagemVendidos = new ArrayList<>();
        
        // 3. Comando SQL filtrando apenas pelo status 'Vendido'
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            // 4. Preenche a lista com os dados do banco
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagemVendidos.add(produto);
            }
            
            prep.close();
            resultset.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
        }
        
        return listagemVendidos;
    }
}