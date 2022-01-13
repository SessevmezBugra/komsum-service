package com.komsum.user.dao;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakUserDao {

    List<UserRepresentation> findByUsername(String username);

    UserRepresentation findByUserId(String userId);
    
    void createAttribute(String userId, String key, String value);

}
