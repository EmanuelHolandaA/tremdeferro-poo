// Representa a locomotiva do trem.
// Ela controla a velocidade e define o limite de vagões da composição.
public class Locomotiva {

    private String modelo;
    private int maxVagoes;
    private double velocidadeAtual;
    private double velocidadeMaxima;

    public Locomotiva(String modelo, int maxVagoes, double velocidadeMaxima) {
        this.modelo = modelo;
        this.maxVagoes = maxVagoes;
        this.velocidadeAtual = 0;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public String getModelo() { return modelo; }
    public int getMaxVagoes() { return maxVagoes; }
    public double getVelocidadeAtual() { return velocidadeAtual; }

    public void acelerar(double delta) {
        if (delta <= 0) {
            System.out.println("  Valor invalido para aceleracao.");
            return;
        }
        velocidadeAtual = Math.min(velocidadeAtual + delta, velocidadeMaxima);
        System.out.printf("  Acelerando... velocidade atual: %.1f km/h%n", velocidadeAtual);
    }

    public void desacelerar(double delta) {
        if (delta <= 0) {
            System.out.println("  Valor invalido para desaceleracao.");
            return;
        }
        velocidadeAtual = Math.max(velocidadeAtual - delta, 0);
        System.out.printf("  Desacelerando... velocidade atual: %.1f km/h%n", velocidadeAtual);
    }

    public void parar() {
        velocidadeAtual = 0;
        System.out.println("  O trem parou.");
    }

    public boolean estaEmMovimento() {
        return velocidadeAtual > 0;
    }

    @Override
    public String toString() {
        return "Locomotiva " + modelo + " | Vel. atual: " + velocidadeAtual
                + " km/h | Vel. max: " + velocidadeMaxima + " km/h | Max vagoes: " + maxVagoes;
    }
}
