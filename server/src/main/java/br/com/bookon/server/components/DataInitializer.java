package br.com.bookon.server.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.postgres.Role;
import br.com.bookon.server.repositories.postgres.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleEnum.ADMININSTRATOR);
        Role roleUser = new Role(RoleEnum.USER);
        Role roleModerator = new Role(RoleEnum.MODERATOR);

        if (roleRepository.countByName(RoleEnum.ADMININSTRATOR) == 0) {
            roleRepository.save(roleAdmin);
        }
        if (roleRepository.countByName(RoleEnum.USER) == 0) {
            roleRepository.save(roleUser);
        }
        if (roleRepository.countByName(RoleEnum.MODERATOR) == 0) {
            roleRepository.save(roleModerator);
        }
    }
}
