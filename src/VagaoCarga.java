import java.util.ArrayList;
import java.util.List;

class Carga {

    private String codigo;
    private String descricao;
    private double peso;
    private double volume;

    public Carga(String codigo, String descricao, double peso, double volume) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.peso = peso;
        this.volume = volume;
    }

    public String getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public double getPeso() { return peso; }
    public double getVolume() { return volume; }

    @Override
    public String toString() {
        return "[" + codigo + "] " + descricao + " | " + peso + " kg | " + volume + " m3";
    }
}

// Vagao de carga. Tem limite de numero de itens, peso total e volume total.
public class VagaoCarga extends Vagao {

    private List<Carga> cargas;
    private double pesoMaximo;
    private double volumeMaximo;

    public VagaoCarga(String id, int capacidade, double pesoMaximo, double volumeMaximo) {
        super(id, capacidade);
        this.cargas = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
        this.volumeMaximo = volumeMaximo;
    }

    public double pesoAtual() {
        double t = 0;
        for (Carga c : cargas) t += c.getPeso();
        return t;
    }

    public double volumeAtual() {
        double t = 0;
        for (Carga c : cargas) t += c.getVolume();
        return t;
    }

    @Override
    public int vagasDisponiveis() {
        return capacidade - cargas.size();
    }

    @Override
    public boolean embarcar(Object item) {
        if (!(item instanceof Carga)) {
            System.out.println("  Erro: so cargas podem ser embarcadas neste vagao.");
            return false;
        }

        Carga c = (Carga) item;

        if (vagasDisponiveis() == 0) {
            System.out.println("  Numero maximo de itens atingido.");
            return false;
        }

        if (pesoAtual() + c.getPeso() > pesoMaximo) {
            System.out.println("  Peso excedido! Carga '" + c.getDescricao() + "' nao embarcou.");
            return false;
        }

        if (volumeAtual() + c.getVolume() > volumeMaximo) {
            System.out.println("  Volume excedido! Carga '" + c.getDescricao() + "' nao embarcou.");
            return false;
        }

        if (buscar(c.getCodigo()) != null) {
            System.out.println("  Carga com codigo " + c.getCodigo() + " ja esta no vagao.");
            return false;
        }

        cargas.add(c);
        System.out.println("  Carga '" + c.getDescricao() + "' embarcada. Peso: "
                + pesoAtual() + "/" + pesoMaximo + " kg | Volume: "
                + volumeAtual() + "/" + volumeMaximo + " m3.");
        return true;
    }

    @Override
    public boolean desembarcar(String codigo) {
        Carga encontrada = encontrarPorCodigo(codigo);
        if (encontrada == null) {
            System.out.println("  Carga com codigo " + codigo + " nao encontrada.");
            return false;
        }
        cargas.remove(encontrada);
        System.out.println("  Carga '" + encontrada.getDescricao() + "' desembarcada.");
        return true;
    }

    @Override
    public String buscar(String codigo) {
        Carga c = encontrarPorCodigo(codigo);
        return c != null ? c.toString() : null;
    }

    @Override
    public void listar() {
        System.out.println("  Vagao de Carga [" + getId() + "]");
        System.out.printf("  Peso: %.1f/%.1f kg | Volume: %.1f/%.1f m3%n",
                pesoAtual(), pesoMaximo, volumeAtual(), volumeMaximo);
        if (cargas.isEmpty()) {
            System.out.println("  (vazio)");
            return;
        }
        for (int i = 0; i < cargas.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + cargas.get(i));
        }
    }

    @Override
    public String descricao() {
        return "Carga";
    }

    private Carga encontrarPorCodigo(String codigo) {
        for (Carga c : cargas) {
            if (c.getCodigo().equals(codigo)) return c;
        }
        return null;
    }
}
