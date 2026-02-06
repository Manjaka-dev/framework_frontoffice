package framework.app.frontoffice.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Integer nb_passager;
    private String id_client;
    private String nom_hotel;
    private LocalDateTime date_reservation;

    public ReservationDTO() {
    }

    public ReservationDTO(Integer nb_passager, String id_client, String nom_hotel, LocalDateTime date_reservation) {
        this.nb_passager = nb_passager;
        this.id_client = id_client;
        this.nom_hotel = nom_hotel;
        this.date_reservation = date_reservation;
    }

    public Integer getNb_passager() {
        return nb_passager;
    }

    public void setNb_passager(Integer nb_passager) {
        this.nb_passager = nb_passager;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public LocalDateTime getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDateTime date_reservation) {
        this.date_reservation = date_reservation;
    }
}
