import java.util.ArrayList;
import java.util.List;

class Animal {

    private String tag;
    private String nome;
    private String especie;
    private double peso;

    public Animal(String tag, String nome, String especie, double peso) {
        this.tag = tag;
        this.nome = nome;
        this.especie = especie;
        this.peso = peso;
    }

    public String getTag() { return tag; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public double getPeso() { return peso; }

    @Override
    public String toString() {
        return nome + " [" + tag + "] - " + especie + ", " + peso + " kg";
    }
}

// Vagao de animais. Alem do limite de quantidade, tem restricao de peso total
// e so aceita as especies que foram definidas na criacao do vagao.
public class VagaoAnimais extends Vagao {

    private List<Animal> animais;
    private double pesoMaximo;
    private List<String> especiesPermitidas;

    public VagaoAnimais(String id, int capacidade, double pesoMaximo, List<String> especiesPermitidas) {
        super(id, capacidade);
        this.animais = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
        this.especiesPermitidas = new ArrayList<>(especiesPermitidas);
    }

    public double pesoAtual() {
        double total = 0;
        for (Animal a : animais) total += a.getPeso();
        return total;
    }

    @Override
    public int vagasDisponiveis() {
        return capacidade - animais.size();
    }

    @Override
    public boolean embarcar(Object item) {
        if (!(item instanceof Animal)) {
            System.out.println("  Erro: so animais podem embarcar neste vagao.");
            return false;
        }

        Animal a = (Animal) item;

        if (vagasDisponiveis() == 0) {
            System.out.println("  Vagao lotado! " + a.getNome() + " nao pôde embarcar.");
            return false;
        }

        if (!especiesPermitidas.contains(a.getEspecie().toLowerCase())) {
            System.out.println("  Especie '" + a.getEspecie() + "' nao e permitida neste vagao.");
            return false;
        }

        if (pesoAtual() + a.getPeso() > pesoMaximo) {
            System.out.println("  Peso maximo excedido! " + a.getNome() + " nao pôde embarcar.");
            return false;
        }

        if (buscar(a.getTag()) != null) {
            System.out.println("  Animal com tag " + a.getTag() + " ja esta no vagao.");
            return false;
        }

        animais.add(a);
        System.out.println("  " + a.getNome() + " embarcou. Peso atual: "
                + pesoAtual() + "/" + pesoMaximo + " kg.");
        return true;
    }

    @Override
    public boolean desembarcar(String tag) {
        Animal encontrado = encontrarPorTag(tag);
        if (encontrado == null) {
            System.out.println("  Animal com tag " + tag + " nao encontrado.");
            return false;
        }
        animais.remove(encontrado);
        System.out.println("  " + encontrado.getNome() + " desembarcou.");
        return true;
    }

    @Override
    public String buscar(String tag) {
        Animal a = encontrarPorTag(tag);
        return a != null ? a.toString() : null;
    }

    @Override
    public void listar() {
        System.out.println("  Vagao de Animais [" + getId() + "]");
        System.out.println("  Peso: " + pesoAtual() + "/" + pesoMaximo + " kg");
        if (animais.isEmpty()) {
            System.out.println("  (vazio)");
            return;
        }
        for (int i = 0; i < animais.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + animais.get(i));
        }
    }

    @Override
    public String descricao() {
        return "Animais";
    }

    private Animal encontrarPorTag(String tag) {
        for (Animal a : animais) {
            if (a.getTag().equals(tag)) return a;
        }
        return null;
    }
}
