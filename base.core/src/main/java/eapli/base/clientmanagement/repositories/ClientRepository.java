package eapli.base.clientmanagement.repositories;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;


public interface ClientRepository extends DomainRepository<Long, Client> {

    Optional<Client> findByEmail(Email email);

}
