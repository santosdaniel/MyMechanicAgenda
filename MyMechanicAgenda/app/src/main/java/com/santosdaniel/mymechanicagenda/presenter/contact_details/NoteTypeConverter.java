package com.santosdaniel.mymechanicagenda.presenter.contact_details;

import com.santosdaniel.mymechanicagenda.model.database.NoteType;

import org.greenrobot.greendao.converter.PropertyConverter;

public class NoteTypeConverter implements PropertyConverter<NoteType, String> {
    @Override
    public NoteType convertToEntityProperty(String databaseValue) {
        return NoteType.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(NoteType entityProperty) {
        return entityProperty.name();
    }
}