package com.santosdaniel.mymechanicagenda.view;

/**
 * Represents all the information to show an item in the recycle view
 */

public class GenericListItem {

    /**
     * Identifier of the item
     */
    @SuppressWarnings("unused")
    public long id;

    /**
     * Uri to the image of the item
     */
    public String imageUri;

    /**
     * Title of the item
     */
    public String title;

    /**
     * Description of the item
     */
    public String description;

    /**
     * Url with details of the item
     */
    public String detailsUrl;
}
