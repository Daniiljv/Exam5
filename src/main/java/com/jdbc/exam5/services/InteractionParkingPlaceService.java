package com.jdbc.exam5.services;

import com.jdbc.exam5.dtos.CreateInteractionDto;

public interface InteractionParkingPlaceService {
    String reservePlace(CreateInteractionDto reservePlace);
    String confirmReservationByOrderId(Long orderId);

    String takePlace(CreateInteractionDto takePlace);

    String releaseParkingPlace(Long orderId);
}
