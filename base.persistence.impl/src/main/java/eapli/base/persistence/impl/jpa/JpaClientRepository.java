package eapli.base.persistence.impl.jpa;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaClientRepository extends BaseJpaRepositoryBase<Client, Long, Long>
    implements ClientRepository {

    JpaClientRepository() {
        super("clientId");
    }

    @Override
    public Optional<Client> findByEmail(Email email) {
        final Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return matchOne("e.email=:email", params);
    }
}
