package Models;

public class GlobalStorage {
    static private int tempoMaxEsperaEntrada;
    static private int tempoMaxEsperaAtendimento;
    static private int tempoMaxEsperaPagamento;
    static private String passwordConfiguracao = "configPass";

    public GlobalStorage(int tempoMaxEsperaEnt, int tempoMaxEsperaAtend, int tempoMaxEsperaPag) {
        tempoMaxEsperaEntrada = tempoMaxEsperaEnt;
        tempoMaxEsperaAtendimento = tempoMaxEsperaAtend;
        tempoMaxEsperaPagamento = tempoMaxEsperaPag;
    }

    public static int getTempoMaxEsperaEntrada() {
        return tempoMaxEsperaEntrada;
    }

    public static void setTempoMaxEsperaEntrada(int tempoMaxEsperaEnt) {
        tempoMaxEsperaEntrada = tempoMaxEsperaEnt;
    }

    public static int getTempoMaxEsperaAtendimento() {
        return tempoMaxEsperaAtendimento;
    }

    public static void setTempoMaxEsperaAtendimento(int tempoMaxEsperaAtend) {
        tempoMaxEsperaAtendimento = tempoMaxEsperaAtend;
    }

    public static int getTempoMaxEsperaPagamento() {
        return tempoMaxEsperaPagamento;
    }

    public static void setTempoMaxEsperaPagamento(int tempoMaxEsperaPag) {
        tempoMaxEsperaPagamento = tempoMaxEsperaPag;
    }

    public static String getPasswordConfiguracao() {
        return passwordConfiguracao;
    }

    public static void setPasswordConfiguracao(String passwordConfiguracao) {
        GlobalStorage.passwordConfiguracao = passwordConfiguracao;
    }
}
