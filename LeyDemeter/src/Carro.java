class Carro {
    private Motor motor;

    public Carro() {
        motor = new Motor();
    }
    
    public void encenderMotor() {
        motor.encender();
    }
    
    public Motor getMotor() {
        return motor;
    }
}
