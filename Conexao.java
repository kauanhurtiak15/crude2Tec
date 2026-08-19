import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "idbc:sqlite:banco.db";
    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL);
    }
    public static void inicializarBanco(){
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT not null," +
                "idade INTEGER NOT NULL);";
        try (Connection conn = conectar();
        Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro no Banco" + e.getMessage());
        }
    }
}