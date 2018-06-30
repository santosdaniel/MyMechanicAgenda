package com.santosdaniel.mymechanicagenda.model.database

import com.santosdaniel.mymechanicagenda.R

/**
 * Enumeration with of type of vehicles available
 */
enum class VehicleTypeEnum(translationId: Int) {
    PASSENGER(R.string.passenger),
    CONVERTIBLE(R.string.convertible),
    SALOON(R.string.saloon),
    MOTORCYCLE(R.string.motorcycle),
    COMMERCIAL_VEHICLE(R.string.commercial_vehicle),
    STATION_WAGON(R.string.station_wagon),
    TRUCK_STATION_WAGON(R.string.truck_station_wagon),
    COUPE(R.string.coupe),
    MULTIPURPOSE_PASSENGER_CAR(R.string.multipurpose_passenger_car),
    FORWARD_CONTROL_PASSENGER_CAR(R.string.forward_control_passenger_car),
    SPECIAL_PASSENGER_CAR(R.string.special_passenger),
    BUS(R.string.bus),
    TRAILER(R.string.trailer),
    SEMI_TRAILER(R.string.semi_trailer),
    MOPED(R.string.moped),
    TRUCK(R.string.truck),
    OTHER(R.string.other_type)
}