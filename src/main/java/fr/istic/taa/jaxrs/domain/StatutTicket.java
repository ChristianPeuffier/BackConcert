package fr.istic.taa.jaxrs.domain;

public enum StatutTicket {
    /**
     * Ticket is sold.
     */
    ACHETE,
    /**
     * Ticket is canceled.
     */
    ANNULE,
    /**
     * Ticket is refunded.
     */
    REMBOURSE,
    /**
     * Ticket is reserved.
     */
    RESERVE
}
