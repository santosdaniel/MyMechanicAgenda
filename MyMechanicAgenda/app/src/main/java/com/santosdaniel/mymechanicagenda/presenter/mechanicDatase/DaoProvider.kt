package com.santosdaniel.mymechanicagenda.presenter.mechanicDatase

import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.ModelAdapter
import com.santosdaniel.mymechanicagenda.model.database.Costumer
import com.santosdaniel.mymechanicagenda.model.database.Vehicle

/**
 * Provider of data access objects that are going allow to manage the database
 */
class DaoProvider {

    /**
     * @return The data access provider to the costumer entity
     */
    val costumerDao: ModelAdapter<Costumer>
        get() = FlowManager.getModelAdapter(Costumer::class.java)

    /**
     * @return The data access provider to the vehicle entity
     */
    val vehicleDao: ModelAdapter<Vehicle>
        get() = FlowManager.getModelAdapter(Vehicle::class.java)


}
