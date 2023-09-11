/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.repositories.OrderItemRepository;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.repositories.TaskRepository;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.repositories.ShopCarItemRepository;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.warehousemanagement.repositories.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.repositories.PlantRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public OrderItemRepository orderItems(final TransactionalContext tx) {
        return new JpaOrderItemRepository(tx);
    }

    @Override
    public OrderItemRepository orderItems() {
        return new JpaOrderItemRepository(Application.settings().getPersistenceUnitName());
    }

    /* Acrescentei - Dúvida */
    @Override
    public ClientRepository clients() {
        return new JpaClientRepository();
    }

    @Override
    public ShoppingCartRepository shoppingCarts() {
        return new JpaShoppingCartRepository();
    }

    @Override
    public ShopCarItemRepository shopCarItems() {
        return new JpaShopCarItemRepository();
    }

    @Override
    public AGVRepository agvs() {
        return new JpaAGVRepository();
    }

    @Override
    public ProductRepository products() {
        return new JpaProductRepository();
    }

    @Override
    public ProductCategoryRepository productCategories() {
        return new JpaProductCategoryRepository();
    }

    @Override
    public BinRepository bins() {
        return new JpaBinRepository();
    }

    /* Acrescentei - Dúvida */
    @Override
    public OrderRepository orders() {
        return new JpaOrderRepository();
    }
    @Override
    public TaskRepository tasks() {return new JpaTaskRepository();}

    @Override
    public PlantRepository plants(){return new JpaPlantRepository();}
    @Override
    public AisleRepository aisles(){return new JpaAisleRepository();}
    @Override
    public RowRepository rows(){return new JpaRowRepository();}
    @Override
    public ShelfRepository shelfs(){return new JpaShelfRepository();}
    @Override
    public AgvDockRepository agvDocks(){return new JpaAgvDockRepository();}

    @Override
    public SquareRepository squares() {
        return new JpaSquareRepository();
    }

    @Override
    public SurveyQuestionnareRepository questionnaries() {
        return new JpaSurveyQuestionnaireRepository();
    }

    @Override
    public AgvPositionRepository positions(){return new JpaAgvPositionRepository();}

    @Override
    public AnswerQuestionnaireRepository answers(){
        return new JpaAnswerQuestionnaireRepository();
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
