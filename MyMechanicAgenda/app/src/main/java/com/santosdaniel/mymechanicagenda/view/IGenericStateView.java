package com.santosdaniel.mymechanicagenda.view;

/**
 * Interface that should be implemented by the views that allow a direct set of their state
 */
public interface IGenericStateView<T> {

    /**
     * Set the state of the view
     *
     * @param state The state to set
     */
    void setState(T state);
}
