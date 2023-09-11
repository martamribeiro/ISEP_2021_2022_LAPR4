package eapli.base.persistence.impl.inmemory;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryClientRepository extends InMemoryDomainRepository<Client, Long> implements ClientRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Client> findByEmail(Email email) {
        return matchOne(e -> e.email().equals(email));
    }
}
