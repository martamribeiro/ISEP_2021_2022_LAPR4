package eapli.base.productmanagement.application;


import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;


import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 *
 * @author Ana Albergaria
 *
 */
public class ListProductDTOService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<ProductDTO> allProducts() {

        final Iterable<Product> products = this.productRepository.findAll();

        // transform for the presentation layer
        final List<ProductDTO> ret = new ArrayList<>();
        products.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
