package project.VeterinaryManagementProject.exception;

import java.time.LocalDate;

public class DoctorAppointmentClashException extends RuntimeException {
    public DoctorAppointmentClashException(LocalDate date) {
        super("Doktor bu tarihte çalışmamaktadır veya girilen saatte başka bir randevu mevcuttur.");
    }
}
