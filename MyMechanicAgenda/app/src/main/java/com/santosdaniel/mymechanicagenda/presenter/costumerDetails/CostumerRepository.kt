package com.santosdaniel.mymechanicagenda.presenter.costumerDetails


import com.raizlabs.android.dbflow.sql.language.SQLite
import com.santosdaniel.mymechanicagenda.helper.LogHelper
import com.santosdaniel.mymechanicagenda.model.database.Costumer
import com.santosdaniel.mymechanicagenda.presenter.genericDatabase.GenericRepository


/**
 * Has the methods to use the contact model
 */
/**
 * The constructor of the repository
 */
class CostumerRepository : GenericRepository<Costumer>() {

    /**
     * Create a new costumer
     *
     * @param costumer the costumer that is to create
     */
    override fun create(costumer: Costumer): Long? {
        try {
            return daoProvider.costumerDao.insert(costumer)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            return null
        }

    }

    /**
     * Update an existing entity
     *
     * @param costumer the entity that to update
     */
    override fun update(costumer: Costumer): Boolean {
        try {
            return daoProvider.costumerDao.update(costumer)
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            return false
        }

    }

    /**
     * @param lookupId Lookup identifier of the contact
     * @return The costumer with that lookup identifier
     */
    fun loadByLookId(lookupId: String): Costumer? {
        try {
            return SQLite.select()
                    .from(Costumer::class.java)
                    //TODO put the query in here
                    .querySingle()
        } catch (e: Exception) {
            LogHelper.e(TAG, e.message)
            return null
        }

    }

    companion object {

        private val TAG = "CostumerRepository"
    }
}
