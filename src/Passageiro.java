public class Passageiro {

    private String cpf;
    private String nome;
    private int idade;

    public Passageiro(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ", " + idade + " anos)";
    }
}
