package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.Aisle;
import eapli.base.warehousemanagement.domain.Bin;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.TheRow;
import eapli.base.warehousemanagement.repositories.AisleRepository;
import eapli.base.warehousemanagement.repositories.BinRepository;
import eapli.base.warehousemanagement.repositories.RowRepository;
import eapli.base.warehousemanagement.repositories.ShelfRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;

import java.time.Period;
import java.util.*;

/**
 * @author Marta Ribeiro 1201592
 */
public class BinsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinsBootstrapper.class);
    private final BinRepository binRepository = PersistenceContext.repositories().bins();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final AisleRepository aisleRepository = PersistenceContext.repositories().aisles();
    private final RowRepository rowRepository = PersistenceContext.repositories().rows();
    private final ShelfRepository shelfRepository = PersistenceContext.repositories().shelfs();

    private Aisle getAisle(final Long id){
        return aisleRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }

    private TheRow getRow(final Long id){
        return rowRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }

    private Shelf getShelf(final Long id){
        return shelfRepository.findByID(id).orElseThrow(IllegalAccessError::new);
    }

    private Iterator<Aisle> getAisles() {
        return aisleRepository.findAll().iterator();
    }

    private Iterator<Shelf> getShelves() {
        return shelfRepository.findAll().iterator();
    }

    private List<TheRow> getRows() {
        List<TheRow> list = new ArrayList<>();
        Iterator<TheRow> rows = rowRepository.findAll().iterator();
        rows.forEachRemaining(list::add);
        return list;
    }

    private Product getProduct(final Code id){
        return productRepository.ofIdentity(id).orElseThrow(IllegalAccessError::new);
    }


    @Override
    public boolean execute(){

        Double size1 = 0.000665;
        Double size2 = 0.00031;
        /*Aisle aisle1 = getAisle(1L);
        Aisle aisle2 = getAisle(2L);
        Aisle aisle3 = getAisle(3L);
        Aisle aisle4 = getAisle(4L);
        TheRow row1 = getRow(1L);
        TheRow row2 = getRow(2L);
        TheRow row3 = getRow(3L);
        TheRow row4 = getRow(4L);
        TheRow row5 = getRow(5L);
        TheRow row6 = getRow(6L);
        TheRow row7 = getRow(7L);
        Shelf shelf32 = getShelf(32L);
        Shelf shelf33 = getShelf(33L);
        Shelf shelf34 = getShelf(34L);
        Shelf shelf37 = getShelf(37L);
        Shelf shelf38 = getShelf(38L);
        Shelf shelf39 = getShelf(39L);
        Shelf shelf40 = getShelf(40L);
        Shelf shelf43 = getShelf(43L);
        Shelf shelf44 = getShelf(44L);
        Shelf shelf45 = getShelf(45L);
        Shelf shelf46 = getShelf(46L);
        Shelf shelf47 = getShelf(47L);
        Shelf shelf48 = getShelf(48L);
        Shelf shelf49 = getShelf(49L);
        Shelf shelf50 = getShelf(50L);
        Shelf shelf59 = getShelf(59L);
        Shelf shelf60 = getShelf(60L);
        Shelf shelf61 = getShelf(61L);
        Shelf shelf62 = getShelf(62L);
        Shelf shelf63 = getShelf(63L);
        Shelf shelf66 = getShelf(66L);
        Shelf shelf69 = getShelf(69L);
        Shelf shelf70 = getShelf(70L);
        Shelf shelf71 = getShelf(71L);
        Shelf shelf72 = getShelf(72L);
        Shelf shelf75 = getShelf(75L);
        Shelf shelf76 = getShelf(76L);
        Shelf shelf77 = getShelf(77L);
        Shelf shelf78 = getShelf(78L);
        Shelf shelf79 = getShelf(79L);*/


        Iterator<Aisle> aisles = getAisles();
        Aisle aisle1 = aisles.next();
        Aisle aisle2 = aisles.next();
        Aisle aisle3 = aisles.next();
        Aisle aisle4 = aisles.next();

        Iterator<Shelf> shelves = getShelves();
        Shelf shelf1 = shelves.next();
        Shelf shelf2 = shelves.next();
        Shelf shelf3 = shelves.next();
        Shelf shelf4 = shelves.next();
        Shelf shelf5 = shelves.next();
        Shelf shelf6 = shelves.next();
        Shelf shelf7 = shelves.next();
        Shelf shelf8 = shelves.next();
        Shelf shelf9 = shelves.next();
        Shelf shelf10 = shelves.next();
        Shelf shelf11 = shelves.next();
        Shelf shelf12 = shelves.next();
        Shelf shelf13 = shelves.next();
        Shelf shelf14 = shelves.next();
        Shelf shelf15 = shelves.next();
        Shelf shelf16 = shelves.next();
        Shelf shelf17 = shelves.next();
        Shelf shelf18 = shelves.next();
        Shelf shelf19 = shelves.next();
        Shelf shelf20 = shelves.next();
        Shelf shelf21 = shelves.next();
        Shelf shelf22 = shelves.next();
        Shelf shelf23 = shelves.next();
        Shelf shelf24 = shelves.next();
        Shelf shelf25 = shelves.next();
        Shelf shelf26 = shelves.next();
        Shelf shelf27 = shelves.next();
        Shelf shelf28 = shelves.next();
        Shelf shelf29 = shelves.next();
        Shelf shelf30 = shelves.next();





        List<TheRow> listRows = getRows();

        Product product1 = getProduct(Code.valueOf("lmsp.00001"));
        Product product2 = getProduct(Code.valueOf("apsp.00001"));
        Product product3 = getProduct(Code.valueOf("tnfs.00001"));

        register(size1,aisle1,listRows.get(0),shelf1,product1);
        register(size1,aisle1,listRows.get(0),shelf2,product1);
        register(size1,aisle1,listRows.get(0),shelf3,product1);
        register(size1,aisle1,listRows.get(1),shelf4,product1);
        register(size1,aisle1,listRows.get(1),shelf5,product1);
        register(size1,aisle1,listRows.get(1),shelf6,product1);
        register(size1,aisle1,listRows.get(1),shelf7,product1);
        register(size1,aisle1,listRows.get(2),shelf8,product1);
        register(size1,aisle1,listRows.get(2),shelf9,product1);
        register(size1,aisle1,listRows.get(2),shelf10,product1);

        register(size1,aisle1,listRows.get(2),shelf11,product2);
        register(size1,aisle1,listRows.get(2),shelf12,product2);
        register(size1,aisle1,listRows.get(2),shelf13,product2);
        register(size1,aisle1,listRows.get(2),shelf14, product2);
        register(size1,aisle1,listRows.get(2),shelf15,product2);
        register(size1,aisle2,listRows.get(3),shelf16,product2);
        register(size1,aisle2,listRows.get(3),shelf17,product2);
        register(size1,aisle2,listRows.get(3),shelf18,product2);
        register(size1,aisle2,listRows.get(3),shelf19,product2);
        register(size1,aisle2,listRows.get(3),shelf20,product2);

        register(size2,aisle2,listRows.get(4),shelf21,product3);
        register(size2,aisle2,listRows.get(5),shelf22,product3);
        register(size2,aisle2,listRows.get(5),shelf23,product3);
        register(size2,aisle2,listRows.get(5),shelf24,product3);
        register(size2,aisle2,listRows.get(5),shelf25,product3);
        register(size2,aisle2,listRows.get(6),shelf26,product3);
        register(size2,aisle2,listRows.get(6),shelf27,product3);
        register(size2,aisle2,listRows.get(6),shelf28,product3);
        register(size2,aisle2,listRows.get(6),shelf29,product3);
        register(size2,aisle2,listRows.get(6),shelf30,product3);

        return true;
    }

    public void register(Double size, Aisle aisle, TheRow row, Shelf shelf, Product product){
        try{
            final var bin = new Bin(size,aisle,row,shelf,product);

            binRepository.save(bin);
            LOGGER.debug(String.valueOf(aisle), row, shelf);
        } catch (final IntegrityViolationException | ConcurrencyException | TransactionSystemException e){
            LOGGER.warn("Assuming already exists bin in aisle {} , row {} and shelf {} (see trace log for details on {} {})", aisle, row, shelf,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(String.valueOf(e));
        }
    }

}
