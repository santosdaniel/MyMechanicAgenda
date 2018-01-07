package com.santosdaniel.mymechanicagenda.presenter.vehicleDetails


import com.santosdaniel.mymechanicagenda.helper.DBHelper
import com.santosdaniel.mymechanicagenda.helper.LogHelper
import com.santosdaniel.mymechanicagenda.model.database.Vehicle
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.GenericRepository


/**
 * Has the methods to use the contact model
 */
/**
 * The constructor of the repository
 */
class VehicleRepository : GenericRepository<Vehicle>() {

    /**
     * Create a new vehicle
     *
     * @param vehicle the vehicle that is to create
     */
    override fun create(vehicle: Vehicle): Long? {
        return try {
            daoProvider.vehicleDao.insert(vehicle)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message, e)
            null
        }

    }

    /**
     * Update an existing entity
     *
     * @param vehicle the entity that to update
     */
    override fun update(vehicle: Vehicle): Boolean {
        return try {
            daoProvider.vehicleDao.update(vehicle)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            false
        }

    }

    /**
     * @param identifier The identifier
     * @return The vehicle that found in database (If any)
     */
    fun loadById(identifier: Long?): Vehicle? {
        return if (DBHelper.validId(identifier)) {
            null
        } else {
            //The identifier is not valid to search in database
            null
        }
    }


    /**
     * Delete a vehicle
     *
     * @param vehicle the reference to the vehicle to delete
     * @return if was possible to the element or not
     */
    fun delete(vehicle: Vehicle): Boolean {
        return daoProvider.vehicleDao.delete(vehicle)
    }


    companion object {
        private val TAG = "VehicleRepository"
    }
}
