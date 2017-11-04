package com.santosdaniel.mymechanicagenda.model.database

import com.santosdaniel.mymechanicagenda.R

/**
 * Types of existing documents
 */
enum class DocumentTypeEnum(translationId: Int, iconId: Int) {
    REGISTRATION_CARD(R.string.registration_card, R.mipmap.registration_car),
    INVOICE(R.string.invoice, R.mipmap.invoice_document),
    PHOTO(R.string.vehicle_photo, R.mipmap.photo_icon)
}