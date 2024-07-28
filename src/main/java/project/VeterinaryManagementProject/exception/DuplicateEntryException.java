package project.VeterinaryManagementProject.exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(Class entityClass) {
        super("Bu " + entityClass.getSimpleName() + " zaten kayıtlı. Bu istek veri tekrarı oluşturur.");
    }
}
