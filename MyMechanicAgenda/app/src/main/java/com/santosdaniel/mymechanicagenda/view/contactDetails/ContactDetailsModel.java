package com.santosdaniel.mymechanicagenda.view.contactDetails;

import java.io.Serializable;

/**
 * Model used by the contact details activity
 */

public class ContactDetailsModel implements Serializable {
    private static final long serialVersionUID = -5117894820028821060L;

    private String lookupId;
    private String title;
    private String imageUri;

    public String getLookupId() {
        return lookupId;
    }

    public void setLookupId(String lookupId) {
        this.lookupId = lookupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
