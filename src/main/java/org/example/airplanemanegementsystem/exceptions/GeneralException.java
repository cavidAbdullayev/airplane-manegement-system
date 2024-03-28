package org.example.airplanemanegementsystem.exceptions;

import org.example.airplanemanegementsystem.enums.*;

public class GeneralException extends RuntimeException {
    public GeneralException(AircraftEnum aircraftEnum) {
        super(aircraftEnum.getMessage());
    }

    public GeneralException(CustomerEnum customerEnum) {
        super(customerEnum.getMessage());
    }

    public GeneralException(FlightEnum flightEnum) {
        super(flightEnum.getMessage());
    }

    public GeneralException(PersonnelEnum personnelEnum) {
        super(personnelEnum.getMessage());
    }

    public GeneralException(TicketEnum ticketEnum) {
        super(ticketEnum.getMessage());
    }
}