package project.VeterinaryManagementProject.exception;

public class EntityDuplicateException extends RuntimeException {
    public EntityDuplicateException(Class entityClass) {
        super("Bu " + entityClass.getSimpleName() + " daha önce sisteme kayıt edilmiş.");
    }
}
