class Conductor {
    private Carro carro;

    public Conductor() {
        carro = new Carro();
    }

    public void conducir() {
        carro.encenderMotor();
    }
}