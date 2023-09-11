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
package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.repositories.OrderItemRepository;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
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
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public OrderItemRepository orderItems(final TransactionalContext tx) {
        return new InMemoryOrderItemRepository();
    }

    @Override
    public OrderItemRepository orderItems() {
        return orderItems(null);
    }

    @Override
    public TaskRepository tasks(){return new InMemoryTaskRepository();}

    /* Acrescentei - Dúvida */
    @Override
    public ClientRepository clients() {
        return new InMemoryClientRepository();
    }

    @Override
    public ShoppingCartRepository shoppingCarts() {
        return new InMemoryShoppingCartRepository();
    }

    @Override
    public ShopCarItemRepository shopCarItems() {
        return new InMemoryShopCarItemRepository();
    }

    @Override
    public AGVRepository agvs() {
        return new InMemoryAGVRepository();
    }

    @Override
    public ProductRepository products() {
        return new InMemoryProductRepository();
    }

    //FALTA ACRESCENTAR RETURN
    @Override
    public ProductCategoryRepository productCategories() {
        return new InMemoryProductCategoryRepository();
    }

    @Override
    public BinRepository bins(){
        return new InMemoryBinRepository();
    }

    @Override
    public OrderRepository orders() {
        return new InMemoryOrderRepository();
    }

    @Override
    public PlantRepository plants(){return new InMemoryPlantRepository();}

    @Override
    public AisleRepository aisles(){return new InMemoryAisleRepository();}
    @Override
    public RowRepository rows(){return new InMemoryRowRepository();}
    @Override
    public ShelfRepository shelfs(){return new InMemoryShelfRepository();}
    @Override
    public AgvDockRepository agvDocks(){return new InMemoryAgvDockRepository();}

    @Override
    public SquareRepository squares() {
        return new InMemorySquareRepository();
    }

    @Override
    public SurveyQuestionnareRepository questionnaries() {
        return new InMemorySurveyQuestionnaireRepository();
    }

    @Override
    public AgvPositionRepository positions() {return new InMemoryAgvPositionRepository();}

    @Override
    public AnswerQuestionnaireRepository answers(){
        return new InMemoryAnswerQuestionnaireRepository();
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
