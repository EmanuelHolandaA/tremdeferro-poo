import java.util.ArrayList;
import java.util.List;

// O trem é composto por uma locomotiva e uma lista de vagões.
// Usa referências do tipo Vagao para poder trabalhar com qualquer subtipo
// sem precisar saber qual é o tipo concreto de cada um (polimorfismo).
public class Trem {

    private Locomotiva locomotiva;
    private List<Vagao> vagoes;

    public Trem(Locomotiva locomotiva) {
        this.locomotiva = locomotiva;
        this.vagoes = new ArrayList<>();
    }

    public boolean adicionarVagao(Vagao vagao) {
        if (vagoes.size() >= locomotiva.getMaxVagoes()) {
            System.out.println("Limite de vagoes atingido ("
                    + locomotiva.getMaxVagoes() + "). Vagao nao adicionado.");
            return false;
        }
        vagoes.add(vagao);
        System.out.println("Vagao " + vagao.getId() + " (" + vagao.descricao() + ") adicionado.");
        return true;
    }

    public boolean removerVagao(String idVagao) {
        Vagao v = encontrarVagao(idVagao);
        if (v == null) {
            System.out.println("Vagao " + idVagao + " nao encontrado.");
            return false;
        }
        vagoes.remove(v);
        System.out.println("Vagao " + idVagao + " removido.");
        return true;
    }

    public boolean embarcar(String idVagao, Object item) {
        Vagao v = encontrarVagao(idVagao);
        if (v == null) {
            System.out.println("Vagao " + idVagao + " nao encontrado.");
            return false;
        }
        return v.embarcar(item);
    }

    public boolean desembarcar(String idVagao, String identificadorItem) {
        Vagao v = encontrarVagao(idVagao);
        if (v == null) {
            System.out.println("Vagao " + idVagao + " nao encontrado.");
            return false;
        }
        return v.desembarcar(identificadorItem);
    }

    public String buscar(String idVagao, String identificadorItem) {
        Vagao v = encontrarVagao(idVagao);
        if (v == null) {
            System.out.println("Vagao " + idVagao + " nao encontrado.");
            return null;
        }
        String resultado = v.buscar(identificadorItem);
        if (resultado == null) {
            System.out.println("Item '" + identificadorItem + "' nao encontrado no vagao " + idVagao + ".");
        }
        return resultado;
    }

    // Percorre todos os vagoes e chama listar() em cada um.
    // O Java resolve qual implementacao usar em tempo de execucao.
    public void listarTudo() {
        System.out.println("=== Composicao completa ===");
        System.out.println(locomotiva);
        System.out.println("Total de vagoes: " + vagoes.size());
        System.out.println();
        for (Vagao v : vagoes) {
            v.listar();
            System.out.println();
        }
    }

    public void listarVagoes() {
        System.out.println("--- Vagoes da composicao ---");
        for (Vagao v : vagoes) {
            System.out.println("  " + v);
        }
    }

    public void acelerar(double delta) { locomotiva.acelerar(delta); }
    public void desacelerar(double delta) { locomotiva.desacelerar(delta); }
    public void parar() { locomotiva.parar(); }

    public Locomotiva getLocomotiva() { return locomotiva; }

    private Vagao encontrarVagao(String id) {
        for (Vagao v : vagoes) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }
}
