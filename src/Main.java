import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        linha("SISTEMA TREM DE FERRO");

        // montando o trem
        linha("1. Criando a locomotiva e o trem");

        Locomotiva loco = new Locomotiva("Alstom Prima", 5, 160.0);
        Trem trem = new Trem(loco);
        System.out.println(loco);

        // adicionando vagoes
        linha("2. Adicionando vagoes");

        VagaoPassageiros vp1 = new VagaoPassageiros("VP-01", 4);
        VagaoPassageiros vp2 = new VagaoPassageiros("VP-02", 3);
        VagaoAnimais va1 = new VagaoAnimais("VA-01", 6, 3000.0, Arrays.asList("bovino", "equino", "ovino"));
        VagaoCarga vc1 = new VagaoCarga("VC-01", 10, 5000.0, 40.0);
        VagaoCarga vc2 = new VagaoCarga("VC-02", 8, 3000.0, 30.0);

        trem.adicionarVagao(vp1);
        trem.adicionarVagao(vp2);
        trem.adicionarVagao(va1);
        trem.adicionarVagao(vc1);
        trem.adicionarVagao(vc2);

        // esse deve ser recusado, limite e 5
        VagaoCarga vc3 = new VagaoCarga("VC-03", 8, 3000.0, 30.0);
        trem.adicionarVagao(vc3);

        System.out.println();
        trem.listarVagoes();

        // embarque de passageiros
        linha("3. Embarque de passageiros");

        Passageiro p1 = new Passageiro("111.111.111-11", "Ana Lima", 34);
        Passageiro p2 = new Passageiro("222.222.222-22", "Bruno Melo", 27);
        Passageiro p3 = new Passageiro("333.333.333-33", "Carla Dias", 45);
        Passageiro p4 = new Passageiro("444.444.444-44", "Diego Ramos", 19);
        Passageiro p5 = new Passageiro("555.555.555-55", "Eva Torres", 60);

        trem.embarcar("VP-01", p1);
        trem.embarcar("VP-01", p2);
        trem.embarcar("VP-01", p3);
        trem.embarcar("VP-01", p4);
        trem.embarcar("VP-01", p5); // vagao cheio

        // tentando embarcar animal no vagao de passageiros
        Animal bicho = new Animal("TAG-000", "Trovao", "equino", 480.0);
        trem.embarcar("VP-01", bicho);

        trem.embarcar("VP-02", p5);

        // embarque de animais
        linha("4. Embarque de animais");

        Animal a1 = new Animal("BOV-001", "Mimosa", "bovino", 520.0);
        Animal a2 = new Animal("BOV-002", "Estrela", "bovino", 490.0);
        Animal a3 = new Animal("EQU-001", "Relampago", "equino", 450.0);
        Animal a4 = new Animal("OVI-001", "Fofinha", "ovino", 75.0);
        Animal a5 = new Animal("CAP-001", "Chivato", "caprino", 60.0);   // especie nao permitida
        Animal a6 = new Animal("BOV-003", "Guerreiro", "bovino", 1600.0); // excede o peso

        trem.embarcar("VA-01", a1);
        trem.embarcar("VA-01", a2);
        trem.embarcar("VA-01", a3);
        trem.embarcar("VA-01", a4);
        trem.embarcar("VA-01", a5);
        trem.embarcar("VA-01", a6);

        // embarque de cargas
        linha("5. Embarque de cargas");

        Carga c1 = new Carga("CRG-001", "Sacas de soja", 1200.0, 8.0);
        Carga c2 = new Carga("CRG-002", "Caixas de eletronicos", 800.0, 12.0);
        Carga c3 = new Carga("CRG-003", "Pecas automotivas", 2500.0, 15.0);
        Carga c4 = new Carga("CRG-004", "Mobiliario", 600.0, 10.0);
        Carga c5 = new Carga("CRG-005", "Pedras ornamentais", 3000.0, 5.0); // excede peso

        trem.embarcar("VC-01", c1);
        trem.embarcar("VC-01", c2);
        trem.embarcar("VC-01", c3);
        trem.embarcar("VC-01", c4);
        trem.embarcar("VC-01", c5);

        // listagem completa
        linha("6. Listagem completa");
        trem.listarTudo();

        // busca de itens
        linha("7. Busca");

        System.out.println("Buscando passageiro 222.222.222-22 no VP-01:");
        String resultado = trem.buscar("VP-01", "222.222.222-22");
        System.out.println("  Resultado: " + resultado);

        System.out.println("Buscando animal BOV-002 no VA-01:");
        resultado = trem.buscar("VA-01", "BOV-002");
        System.out.println("  Resultado: " + resultado);

        System.out.println("Buscando carga inexistente no VC-01:");
        trem.buscar("VC-01", "CRG-999");

        // desembarque
        linha("8. Desembarque");

        trem.desembarcar("VP-01", "222.222.222-22");
        trem.desembarcar("VA-01", "BOV-001");
        trem.desembarcar("VC-01", "CRG-002");
        trem.desembarcar("VP-01", "999.999.999-99"); // nao existe

        // controle da locomotiva
        linha("9. Controle da locomotiva");

        trem.acelerar(40);
        trem.acelerar(60);
        trem.acelerar(80); // vai travar no maximo
        trem.desacelerar(30);
        trem.desacelerar(200); // nao passa de zero
        trem.acelerar(50);
        trem.parar();

        // estado final
        linha("10. Estado final da composicao");
        trem.listarTudo();
    }

    private static void linha(String titulo) {
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println(titulo);
        System.out.println("-------------------------------------------");
    }
}
