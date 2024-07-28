package project.VeterinaryManagementProject.exception;

import java.time.LocalDate;

public class DoctorBusyException extends RuntimeException {
    public DoctorBusyException(LocalDate date) {
        super("Doktor " + date + " tarihinde yeni randevu için uygun değil.");
    }
}
