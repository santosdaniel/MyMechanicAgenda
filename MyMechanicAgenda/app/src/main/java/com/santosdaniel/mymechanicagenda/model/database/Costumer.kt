package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.OneToMany
import com.raizlabs.android.dbflow.annotation.Table
import com.santosdaniel.mymechanicagenda.MyMechanicAgendaApp

/**
 * Entity mapped to table "COSTUMER".
 */
/**
 * Default constructor
 */
@Table(database = MyMechanicAgendaApp::class)
class Costumer : GenericEntity() {


    /**
     * Full name of the costumer
     */
    /**
     * @return The full name of the costumer
     */
    /**
     * Set the full name of the costumer
     *
     * @param fullName the full name of the costumer
     */
    @Column
    var fullName: String? = null

    /**
     * Lookup of the costumer
     */
    /**
     * @return The lookup of the contact associated with costumer
     */
    /**
     * @param lookup The lookup of the contact associated with costumer
     */
    @Column
    var lookup: String? = null

    /**
     * List of vehicles that the costumer have associate
     */
    /**
     * @return The list of vehicles of the costumer
     */
    /**
     * @param vehicles Set the list of vehicles of the costumer
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = "vehicles")
    var vehicles: List<Vehicle>? = null
}
