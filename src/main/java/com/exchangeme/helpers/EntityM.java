package com.exchangeme.helpers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityM {

    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("exchangeMe");
}
