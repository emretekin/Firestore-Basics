package com.mobilemovement.firestorebasics.models;

import android.support.annotation.NonNull;

/**
 * Created by emretekin on 08/01/18.
 */

public class UserId {

    public String userId;

    public <T extends UserId> T withId(@NonNull final String id) {
        this.userId = id;
        return (T) this;
    }


}
