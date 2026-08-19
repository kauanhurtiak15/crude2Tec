import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PessoaControler {
    public void Salvar(String nome, int idade){
        String sql = "INSERT INTO pessoas (nome, idade) VALUES [?, ?]";
        try(Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar pessoa" + e.getMessage());
        }
    }

    public list<Pessoa> listarTodas(){
        List<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT id, nome, idade FROM pessoas";

        try (Connection conn = Conexao.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");

                lista.add(new Pessoa(id, nome, idade));
            }
        } catch (SQLException e){
            System.err.println("Erro ao listar as pessoas" + e.getMessage());
        }
        return lista;
    }
    public void deletar(int id){
        String sql = "DELETE FROM pessoas WHERE id = ?";
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Erro ao deletar pessoa: " + e.getMessage());
        }
    }
}
