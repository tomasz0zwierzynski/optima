package pl.tomzwi.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomzwi.optima.entity.Role;
import pl.tomzwi.optima.exception.RoleAlreadyDefinedException;
import pl.tomzwi.optima.exception.RoleNotFoundException;
import pl.tomzwi.optima.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) throws RoleNotFoundException {
        return roleRepository.findByName(name).orElseThrow( () -> new RoleNotFoundException("Role not found"));
    }

    @Override
    public Role addRole(String name) throws RoleAlreadyDefinedException {
        Optional<Role> exists = roleRepository.findByName(name);

        if ( exists.isPresent() ) {
            throw new RoleAlreadyDefinedException("Role " + name + "already exists" );
        }

        Role role = new Role();
        role.setName( name );

        roleRepository.save( role );

        return role;
    }
}
