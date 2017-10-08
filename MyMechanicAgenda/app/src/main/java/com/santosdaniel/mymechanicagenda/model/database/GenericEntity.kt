package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.NotNull
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.MyMechanicAgendaApp

import java.util.Date


/**
 * Contains a set of fields that are going to be keep by all the entities in database
 */
/**
 * Default constructor of the generic entity
 */
@Table(database = MyMechanicAgendaApp::class)
abstract class GenericEntity {

    /**
     * Identifier of the entity in database
     */
    @PrimaryKey
    var id: Long? = null

    /**
     * Date when the entity was created
     */
    @NotNull
    var createdDate: Date? = null

    /**
     * Date when the entity was last modified
     * Ambiguous note: Created = Consider as modified too
     */
    var modifiedDate: Date? = null
}
