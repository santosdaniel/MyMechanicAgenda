package com.santosdaniel.mymechanicagenda.model.database

data class Contact(val id: Long,
                   val lookupKey: String,
                   val displayName: String,
                   val thumbnailUri: String?,
                   val photoUri: String?)