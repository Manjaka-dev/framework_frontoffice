package framework.app.frontoffice.controller;

import framework.app.frontoffice.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    // Endpoint API: ${apiBaseUrl}/reservations
    private static final String RESERVATIONS_ENDPOINT = "/reservations";

    @GetMapping
    public String getReservations(
            @RequestParam(required = false) String date_reservation,
            Model model) {

        List<ReservationDTO> reservations;

        try {
            // Construction de l'URL avec ou sans paramètre de date
            String url = apiBaseUrl + RESERVATIONS_ENDPOINT;

            if (date_reservation != null && !date_reservation.isEmpty()) {
                // Appel API avec filtre de date :
                // ${apiBaseUrl}/reservations?date_reservation={date_reservation}
                url = UriComponentsBuilder.fromUriString(url)
                        .queryParam("date_reservation", date_reservation)
                        .toUriString();
            }

            // Appel de l'API
            ResponseEntity<List<ReservationDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ReservationDTO>>() {
                    });

            reservations = response.getBody();
            if (reservations == null) {
                reservations = new ArrayList<>();
            }
        } catch (Exception e) {
            // En cas d'erreur lors de l'appel API, retourner une liste vide
            reservations = new ArrayList<>();
            // TODO: Logger l'erreur
            e.printStackTrace();
        }

        model.addAttribute("reservations", reservations);

        return "reservations/list_reservation";
    }
}
