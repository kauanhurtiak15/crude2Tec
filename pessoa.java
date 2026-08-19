public class pessoa {
    private int id;
    private String nome;
    private int idade;

    public pessoa (int id, String nome, int idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }
    public pessoa (String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public String setNome(String nome){
        this.nome = nome;
    }
    public int getIdade(){
        return idade;
    }
    public int setIdade(int idade){
        this.idade = idade;
    }
}
