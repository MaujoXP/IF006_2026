public class Notificador {
    private INotificacion canal;    
    
    public Notificador(INotificacion canal) {
        this.canal = canal;
    }
    
    public void notificar(String mensaje) {
        canal.enviarNotificacion(mensaje);
    }
}