package pl.tomzwi.optima.service;

import pl.tomzwi.optima.entity.Role;
import pl.tomzwi.optima.exception.RoleAlreadyDefinedException;
import pl.tomzwi.optima.exception.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleByName(String name) throws RoleNotFoundException;

    Role addRole(String name) throws RoleAlreadyDefinedException;

}
