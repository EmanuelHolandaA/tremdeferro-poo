import java.util.ArrayList;
import java.util.List;

// Vagao de passageiros. A unica restricao aqui e o numero de assentos.
// Cada passageiro e identificado pelo CPF.
public class VagaoPassageiros extends Vagao {

    private List<Passageiro> passageiros;

    public VagaoPassageiros(String id, int capacidade) {
        super(id, capacidade);
        this.passageiros = new ArrayList<>();
    }

    @Override
    public int vagasDisponiveis() {
        return capacidade - passageiros.size();
    }

    @Override
    public boolean embarcar(Object item) {
        if (!(item instanceof Passageiro)) {
            System.out.println("  Erro: so passageiros podem embarcar neste vagao.");
            return false;
        }

        Passageiro p = (Passageiro) item;

        if (vagasDisponiveis() == 0) {
            System.out.println("  Vagao lotado! " + p.getNome() + " nao pôde embarcar.");
            return false;
        }

        if (buscar(p.getCpf()) != null) {
            System.out.println("  Passageiro com CPF " + p.getCpf() + " ja esta no vagao.");
            return false;
        }

        passageiros.add(p);
        System.out.println("  " + p.getNome() + " embarcou.");
        return true;
    }

    @Override
    public boolean desembarcar(String cpf) {
        Passageiro encontrado = encontrarPorCpf(cpf);
        if (encontrado == null) {
            System.out.println("  Passageiro com CPF " + cpf + " nao encontrado.");
            return false;
        }
        passageiros.remove(encontrado);
        System.out.println("  " + encontrado.getNome() + " desembarcou.");
        return true;
    }

    @Override
    public String buscar(String cpf) {
        Passageiro p = encontrarPorCpf(cpf);
        return p != null ? p.toString() : null;
    }

    @Override
    public void listar() {
        System.out.println("  Vagao de Passageiros [" + getId() + "]");
        if (passageiros.isEmpty()) {
            System.out.println("  (vazio)");
            return;
        }
        for (int i = 0; i < passageiros.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + passageiros.get(i));
        }
    }

    @Override
    public String descricao() {
        return "Passageiros";
    }

    private Passageiro encontrarPorCpf(String cpf) {
        for (Passageiro p : passageiros) {
            if (p.getCpf().equals(cpf)) return p;
        }
        return null;
    }
}
