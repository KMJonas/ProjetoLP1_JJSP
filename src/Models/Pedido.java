    package Models;

    public class Pedido {
        private int idPedido;
        private Mesa mesaAssociada;
        private Prato[] pratos;
        private int momentoCriacao;
        private int tempoPreparacao;
        private int tempoConsumo;
        private boolean preparado;
        private boolean consumido;
        private boolean prontoParaEntrega;
        private int status; // 0: Finalizado, 1: Ativo, 2: Entregue
        private int statusPedido;
        private boolean prejuizo;


        // Construtor
        public Pedido(int idPedido, Mesa mesaAssociada, Prato[] pratos, int momentoCriacao, int tempoPreparacao, int status, int i) {
            this.idPedido = idPedido;
            this.mesaAssociada = mesaAssociada;
            this.pratos = pratos;
            this.momentoCriacao = momentoCriacao;
            this.tempoPreparacao = tempoPreparacao;
            this.tempoConsumo = tempoConsumo;
            this.statusPedido = status;
            this.status = status;
            this.preparado = false;
            this.consumido = false;
        }


        public boolean isPrejuizo() {
            return prejuizo;
        }

        public void setPrejuizo(boolean prejuizo) {
            this.prejuizo = prejuizo;
        }


        public void setTempoConsumo(int tempoConsumo) {
            this.tempoConsumo = tempoConsumo;
        }


        public int getIdPedido() {
            return idPedido;
        }


        public void setStatusPedido(int statusPedido){
            this.statusPedido = statusPedido;
        }

        public int getStatusPedido(){
            return statusPedido;
        }
        public void setProntoParaEntrega(boolean prontoParaEntrega) {
            this.prontoParaEntrega = prontoParaEntrega;
        }

        public boolean isProntoParaEntrega() {
            return prontoParaEntrega;
        }

        public boolean isConsumido() {
            return consumido;
        }


        public void setFinalizado(boolean finalizado) {
            this.status = finalizado ? 0 : this.status;
        }


        public void setMomentoEntrega(int momentoEntrega) {
            this.momentoCriacao = momentoEntrega - tempoPreparacao;
        }


        public int getTempoConsumo() {
            return tempoConsumo;  // Deve retornar o tempo de consumo, não unidade de tempo
        }


        public int getTempoPreparacao() {
            return tempoPreparacao;
        }

        public int getMomentoCriacao() {
            return momentoCriacao;
        }

        public void setPreparado(boolean preparado) {
            this.preparado = preparado;
        }

        public boolean isPreparado() {
            return preparado;
        }

        public boolean isFinalizado() {
            return status == 0;
        }


        public int getMomentoEntrega() {
            return momentoCriacao + tempoPreparacao;
        }

        public void setConsumido(boolean consumido) {
            this.consumido = consumido;
        }

        public Mesa getMesaAssociada() {
            return mesaAssociada;
        }

        public Prato[] getPratos() {
            return pratos;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public void adicionarPrato(Prato pratoSelecionado) {
        }
    }