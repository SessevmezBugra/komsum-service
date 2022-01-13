package com.komsum.user.dao.impl;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Repository;

import com.komsum.user.dao.KeycloakGroupDao;
import com.komsum.user.dao.common.KeycloakResource;
import com.komsum.user.model.KeycloakAdminClientConfig;

//@ConditionalOnProperty(
//        name = "custom.security.config.enabled",
//        havingValue = "true",
//        matchIfMissing = true)
@Repository
public class KeycloakGroupDaoImpl extends KeycloakResource implements KeycloakGroupDao {

    public KeycloakGroupDaoImpl(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig) {
        super(keycloak, keycloakAdminClientConfig);
    }

    @Override
    public void addUser(String groupId, String userId) {
        UsersResource usersResource = getUsersResource();
        UserResource userResource = usersResource.get(userId);
        userResource.joinGroup(groupId);
    }

    @Override
    public void removeUser(String groupId, String userId) {

    }

    @Override
    public List<GroupRepresentation> findAll() {
        return getGroupResource().groups();
    }
}
