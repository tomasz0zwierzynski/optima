package pl.tomzwi.optima.security.service;

import pl.tomzwi.optima.security.entity.Role;
import pl.tomzwi.optima.security.exception.RoleAlreadyDefinedException;
import pl.tomzwi.optima.security.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role addRole(String name) throws RoleAlreadyDefinedException;

}
