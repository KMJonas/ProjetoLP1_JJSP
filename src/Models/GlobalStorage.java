package Models;

public class GlobalStorage {
    static private int tempoMaxEsperaEntrada = 2;
    static private int tempoMaxEsperaAtendimento = 2;
    static private int tempoMaxEsperaPagamento = 2;
    static private String pathMesas;
    static private String pathPratos;
    static private String pathClientesReserva;
    static private String passwordConfiguracao;
    static private String separadorConteudo;

    public GlobalStorage(int tempoMaxEsperaEnt, int tempoMaxEsperaAtend, int tempoMaxEsperaPag) {
        tempoMaxEsperaEntrada = tempoMaxEsperaEnt;
        tempoMaxEsperaAtendimento = tempoMaxEsperaAtend;
        tempoMaxEsperaPagamento = tempoMaxEsperaPag;
    }

    public GlobalStorage(String Mesas, String Pratos, String ClientesReserva,String passwordConfig, String sepConteudo) {
        pathMesas = Mesas;
        pathPratos = Pratos;
        pathClientesReserva = ClientesReserva;
        passwordConfiguracao = passwordConfig;
        separadorConteudo = sepConteudo;
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

    public static String getPathMesas() {
        return pathMesas;
    }

    public static void setPathMesas(String pathMesas) {
        GlobalStorage.pathMesas = pathMesas;
    }

    public static String getPathPratos() {
        return pathPratos;
    }

    public static void setPathPratos(String pathPratos) {
        GlobalStorage.pathPratos = pathPratos;
    }

    public static String getPathClientesReserva() {
        return pathClientesReserva;
    }

    public static void setPathClientesReserva(String pathClientesReserva) {
        GlobalStorage.pathClientesReserva = pathClientesReserva;
    }

    public static String getSeparadorConteudo() {
        return separadorConteudo;
    }

    public static void setSeparadorConteudo(String separadorConteudo) {
        GlobalStorage.separadorConteudo = separadorConteudo;
    }
}
