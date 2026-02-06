package framework.app.frontoffice.controller;

import framework.app.frontoffice.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    // Endpoint API: ${apiBaseUrl}/reservations
    private static final String RESERVATIONS_ENDPOINT = "/reservations";

    @GetMapping
    public String getReservations(
            @RequestParam(required = false) String date_reservation,
            Model model) {

        List<ReservationDTO> reservations;

        if (date_reservation != null && !date_reservation.isEmpty()) {
            // TODO: Appeler l'API
            // ${apiBaseUrl}/reservations?date_reservation={date_reservation}
            // Pour l'instant, utilisation de données statiques filtrées
            reservations = getStaticReservationsByDate(date_reservation);
        } else {
            // TODO: Appeler l'API ${apiBaseUrl}/reservations
            // Pour l'instant, utilisation de données statiques
            reservations = getStaticReservations();
        }

        model.addAttribute("reservations", reservations);

        return "reservations/list_reservation";
    }

    private List<ReservationDTO> getStaticReservations() {
        List<ReservationDTO> reservations = new ArrayList<>();

        reservations.add(new ReservationDTO(
                2,
                "CLIENT001",
                "Hotel Colbert",
                Timestamp.valueOf("2026-02-10 14:30:00")));

        reservations.add(new ReservationDTO(
                4,
                "CLIENT002",
                "Carlton Hotel",
                Timestamp.valueOf("2026-02-15 10:00:00")));

        reservations.add(new ReservationDTO(
                3,
                "CLIENT003",
                "Le Louvre Hotel",
                Timestamp.valueOf("2026-02-20 16:45:00")));

        reservations.add(new ReservationDTO(
                1,
                "CLIENT004",
                "Palissandre Hotel",
                Timestamp.valueOf("2026-02-25 09:15:00")));

        return reservations;
    }

    private List<ReservationDTO> getStaticReservationsByDate(String dateString) {
        List<ReservationDTO> allReservations = getStaticReservations();

        try {
            // Convertir la date string en LocalDate
            LocalDate filterDate = LocalDate.parse(dateString);

            // Filtrer les réservations par date
            return allReservations.stream()
                    .filter(reservation -> {
                        LocalDateTime reservationDateTime = reservation
                                .getDate_reservation()
                                .toLocalDateTime();
                        return reservationDateTime.toLocalDate().equals(filterDate);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // En cas d'erreur de parsing, retourner toutes les réservations
            return allReservations;
        }
    }
}
