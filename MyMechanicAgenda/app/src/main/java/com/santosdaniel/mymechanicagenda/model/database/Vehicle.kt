package com.santosdaniel.mymechanicagenda.model.database


import com.raizlabs.android.dbflow.annotation.*
import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.MyMechanicDatabase
import java.io.Serializable

/**
 * Used to represent one vehicle in the application
 */
@Table(database = MyMechanicDatabase::class)
class Vehicle : GenericEntity(), Serializable {

    /**
     * List of customers that the vehicle have associate
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = CUSTOMERS_VARIABLE_NAME)
    var customers: List<Customer>? = null

    /**
     * List of document that the vehicle have associate
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = DOCUMENTS_VARIABLE_NAME)
    var documents: List<Document>? = null

    /**
     * Vehicle identification number
     */
    @Column(name = VI_NUMBER_COLUMN_NAME)
    var VINumber: String? = null

    /**
     * The brand of the vehicle
     *
     */
    @Column(name = BRAND_COLUMN_NAME, typeConverter = BrandEnumConverter::class)
    var brand: BrandEnum? = null

    /**
     * The model of the vehicle to use
     */
    @ForeignKeyReference(columnName =  MODEL_COLUMN_NAME, foreignKeyColumnName = GenericEntity.ID_COLUMN_NAME)
    @ForeignKey(tableClass = VehicleModel::class)
    var model: VehicleModel? = null


    /**
     * Indicates the yer of the vehicle
     */
    @Column(name = YEAR_COLUMN_NAME)
    var year: Int? = null

    /**
     * List of reparations made in the vehicle
     */
    @get:OneToMany(methods = arrayOf(OneToMany.Method.ALL), variableName = REPARATIONS_VARIABLE_NAME)
    var reparations: List<Reparation>? = null

    companion object {
        private const val serialVersionUID = -4913897499672647187L
        private const val CUSTOMERS_VARIABLE_NAME = "customers"
        private const val DOCUMENTS_VARIABLE_NAME = "documents"
        private const val VI_NUMBER_COLUMN_NAME = "vi_number"
        private const val BRAND_COLUMN_NAME = "brand"
        private const val MODEL_COLUMN_NAME = "model"
        private const val YEAR_COLUMN_NAME = "year"
        private const val REPARATIONS_VARIABLE_NAME = "reparations"
    }
}
