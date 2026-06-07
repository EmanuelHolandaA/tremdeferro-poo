// Classe abstrata que representa um vagão genérico.
// Cada tipo de vagão herda daqui e implementa os métodos de acordo com suas regras.
public abstract class Vagao {

    private String id;
    protected int capacidade;

    public Vagao(String id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
    }

    public String getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public abstract int vagasDisponiveis();

    public abstract boolean embarcar(Object item);

    public abstract boolean desembarcar(String identificador);

    public abstract String buscar(String identificador);

    public abstract void listar();

    public abstract String descricao();

    @Override
    public String toString() {
        return "[Vagao " + id + " | " + descricao() + " | Capacidade: " + capacidade
                + " | Vagas: " + vagasDisponiveis() + "]";
    }
}
