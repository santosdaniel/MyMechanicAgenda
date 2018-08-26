package com.santosdaniel.mymechanicagenda.presenter.genericDatabase


import com.santosdaniel.mymechanicagenda.presenter.mechanicDatase.DaoProvider

/**
 * Has the elements common to the repositories
 */
abstract class GenericRepository<Entity>
/**
 * Default constructor
 */
protected constructor() {

    /**
     * Reference to the data access objects provider
     */
    protected val daoProvider: DaoProvider = DaoProvider()

    /**
     * Create a new entity
     *
     * @param entity the entity that is to create
     */
    abstract fun create(entity: Entity): Long?

    /**
     * Update an existing entity
     *
     * @param entity the entity that to update
     * @return If was possible update the entity or not
     */
    abstract fun update(entity: Entity): Boolean
}
