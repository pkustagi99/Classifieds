package com.amazon.altas22.classifieds;

import com.amazon.altas22.classifieds.model.Category;
import com.amazon.altas22.classifieds.model.Classifieds;
import com.amazon.altas22.classifieds.model.Order;
import com.amazon.altas22.classifieds.model.User;

public class ClassifiedsSession {
    // Can hold the reference of a User Object :)
    public static User user = null;
    public static Classifieds classified = null;
    public static Order order = null;

    public static Category category = null;
}
