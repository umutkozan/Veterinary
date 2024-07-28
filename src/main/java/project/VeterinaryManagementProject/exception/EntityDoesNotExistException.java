package project.VeterinaryManagementProject.exception;

public class EntityDoesNotExistException extends RuntimeException {
    public EntityDoesNotExistException(Long id, Class entityClass) {
        super(entityClass.getSimpleName() + " id:" + id + " bulunamadı.");
    }
}
