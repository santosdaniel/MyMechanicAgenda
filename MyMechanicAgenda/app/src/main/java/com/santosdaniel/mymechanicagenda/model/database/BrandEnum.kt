package com.santosdaniel.mymechanicagenda.model.database

import com.santosdaniel.mymechanicagenda.R

/**
 * Enumeration with list of brands available in the system
 */
enum class BrandEnum(val brandName: Int, val icon: Int) {
    ALFA_ROMEO(R.string.alfa_romeo, R.mipmap.alfa_romeo),
    AUDI(R.string.audi, R.mipmap.audi),
    BMW(R.string.bmw, R.mipmap.bmw),
    CHRYSLER(R.string.chrysler, R.mipmap.chrysler),
    CITROEN(R.string.citroen, R.mipmap.citroen),
    DACIA(R.string.dacia, R.mipmap.dacia),
    DS(R.string.ds, R.mipmap.ds),
    FIAT(R.string.fiat, R.mipmap.fiat),
    FORD(R.string.ford, R.mipmap.ford),
    HONDA(R.string.honda, R.mipmap.honda),
    HYUNDAI(R.string.hyundai, R.mipmap.hyundai),
    JAGUAR(R.string.jaguar, R.mipmap.jaguar),
    JEEP(R.string.jeep, R.mipmap.jeep),
    KIA(R.string.kia, R.mipmap.kia),
    LANCIA(R.string.lancia, R.mipmap.lancia),
    LAND_ROVER(R.string.land_rover, R.mipmap.land_rover),
    LEXUS(R.string.lexus, R.mipmap.lexus),
    MAZDA(R.string.mazda, R.mipmap.mazda),
    MERCEDES(R.string.mercedes, R.mipmap.mercedes),
    MINI(R.string.mini, R.mipmap.mini),
    MITSUBISHI(R.string.mitsubishi, R.mipmap.mitsubishi),
    NISSAN(R.string.nissan, R.mipmap.nissan),
    OPEL(R.string.opel, R.mipmap.opel),
    PEUGEOT(R.string.peugeot, R.mipmap.peugeot),
    PORSCHE(R.string.porche, R.mipmap.porche),
    RENAULT(R.string.renault, R.mipmap.renault),
    SEAT(R.string.seat, R.mipmap.seat),
    SKODA(R.string.skoda, R.mipmap.skoda),
    SUZUKI(R.string.suzuki, R.mipmap.suzuki),
    TOYOTA(R.string.toyota, R.mipmap.toyota),
    VAUXHALL(R.string.vauxhall, R.mipmap.vauxhall),
    VOLKSWAGEN(R.string.volswagen, R.mipmap.volswagen),
    VOLVO(R.string.volvo, R.mipmap.volvo),
    UNKNOWN(R.string.unknown_brand, R.mipmap.unknown_brand)

}