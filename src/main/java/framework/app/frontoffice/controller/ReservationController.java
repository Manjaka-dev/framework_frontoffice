package framework.app.frontoffice.controller;

import framework.app.frontoffice.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    // Endpoint API: ${apiBaseUrl}/reservations
    private static final String RESERVATIONS_ENDPOINT = "/reservations";

    @GetMapping
    public String getReservations(Model model) {
        // TODO: Appeler l'API ${apiBaseUrl}/reservations
        // Pour l'instant, utilisation de données statiques
        
        List<ReservationDTO> reservations = getStaticReservations();
        model.addAttribute("reservations", reservations);
        
        return "reservations";
    }

    private List<ReservationDTO> getStaticReservations() {
        List<ReservationDTO> reservations = new ArrayList<>();
        
        reservations.add(new ReservationDTO(
            2,
            "CLIENT001",
            "Hotel Colbert",
            Timestamp.valueOf("2026-02-10 14:30:00")
        ));
        
        reservations.add(new ReservationDTO(
            4,
            "CLIENT002",
            "Carlton Hotel",
            Timestamp.valueOf("2026-02-15 10:00:00")
        ));
        
        reservations.add(new ReservationDTO(
            3,
            "CLIENT003",
            "Le Louvre Hotel",
            Timestamp.valueOf("2026-02-20 16:45:00")
        ));
        
        reservations.add(new ReservationDTO(
            1,
            "CLIENT004",
            "Palissandre Hotel",
            Timestamp.valueOf("2026-02-25 09:15:00")
        ));
        
        return reservations;
    }
}
