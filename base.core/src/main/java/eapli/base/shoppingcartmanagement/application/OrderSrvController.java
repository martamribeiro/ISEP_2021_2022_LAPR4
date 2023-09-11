package eapli.base.shoppingcartmanagement.application;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.application.ListOrderDTOService;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.productmanagement.application.ListProductDTOService;
import eapli.base.productmanagement.domain.Code;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.shoppingcartmanagement.domain.ShopCarItem;
import eapli.base.shoppingcartmanagement.domain.ShoppingCart;
import eapli.base.shoppingcartmanagement.repositories.ShoppingCartRepository;
import eapli.base.surveymanagement.application.ListQuestionnaireDTOService;
import eapli.base.surveymanagement.domain.Answer;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repositories.AnswerQuestionnaireRepository;
import eapli.base.surveymanagement.repositories.SurveyQuestionnareRepository;
import eapli.base.utils.MessageUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderSrvController {

    private final ListProductDTOService productService = new ListProductDTOService();
    private final ListQuestionnaireDTOService questionnaireService = new ListQuestionnaireDTOService();
    private final ListOrderDTOService orderService = new ListOrderDTOService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ClientRepository clientRepository = PersistenceContext.repositories().clients();
    private final ShoppingCartRepository shoppingCarRepository = PersistenceContext.repositories().shoppingCarts();
    private Product product;
    private Optional<Client> client;
    private Optional<ShoppingCart> shoppingCar;

    //for US3501
    private final SurveyQuestionnareRepository surveyRepository = PersistenceContext.repositories().questionnaries();
    private final AnswerQuestionnaireRepository answerRepository = PersistenceContext.repositories().answers();
    private Client clientSurvey;
    private Questionnaire survey;
    //end US3501

    public Iterable<ProductDTO> allProducts() {
        return productService.allProducts();
    }

    public Iterable<OrderDTO> allOpenOrders(String clientEmail, OrderStatus orderStatus){
        Client client = clientRepository.findByEmail(Email.valueOf(clientEmail)).get();
        return orderService.allOpenOrders(client, orderStatus);
    }

    //====================================US 3501 =========================================================//
    public Iterable<QuestionnaireDTO> questionnairesForClient(String email){
        Client client = clientRepository.findByEmail(Email.valueOf(email)).get();
        return questionnaireService.questionnairesForClient(client);
    }

    public void saveQuestionnaireAnswers(Map<String, List<String>> answers, QuestionnaireDTO surveyDTO, String email) {
        Questionnaire survey = surveyRepository.ofIdentity(surveyDTO.code()).get();
        Client client = clientRepository.findByEmail(Email.valueOf(email)).get();

        for (Map.Entry<String, List<String>> entry : answers.entrySet()) {
            String questionID = entry.getKey();
            List<String> listAnswers = entry.getValue();
            Answer answer = new Answer(survey, client, questionID, listAnswers);
            answerRepository.save(answer);
        }
    }

    public boolean verifyIfClientAnswered(String email, String surveyCode) {
        survey = surveyRepository.ofIdentity(surveyCode).get();
        clientSurvey = clientRepository.findByEmail(Email.valueOf(email)).get();
        Iterable<Answer> answers = answerRepository.findAnswersByClient(clientSurvey, survey);
        return !answers.iterator().hasNext();
    }
    //==================================== END US 3501 =========================================================//

    public void verifyIfProductExists(byte[] clientMessageUS, DataInputStream sIn, DataOutputStream sOut) throws IOException {
        String productUniqueInternalCode = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
        Optional<Product> product = productRepository.ofIdentity(Code.valueOf(productUniqueInternalCode));
        if(!product.isPresent()) {
            MessageUtils.writeMessageWithData((byte) 3, "[FAILURE] Product not found! Please try again.", sOut);
        } else {
            MessageUtils.writeMessageWithData((byte) 3, "[SUCCESS] Product found!", sOut);
        }
    }

    public void addProductToShoppingCart(byte[] clientMessageUS, DataInputStream sIn) throws IOException {
        String info = MessageUtils.getDataFromMessage(clientMessageUS,sIn);
        String[] array = info.split(" ");
        String quantidade = array[0];
        String email = array[1];
        String productUniqueInternalCode = array[2];
        product = productRepository.ofIdentity(Code.valueOf(productUniqueInternalCode)).get();
        client = clientRepository.findByEmail(Email.valueOf(email));
        ShopCarItem item = new ShopCarItem(Integer.parseInt(quantidade),product);
        if(client.isPresent()) {
            shoppingCar = shoppingCarRepository.findShoppingCartByClient(client.get());
            if(shoppingCar.isPresent()) {
                shoppingCar.get().addProductToShoppingCar(item);
                shoppingCarRepository.save(shoppingCar.get());
            } else {
                ShoppingCart shoppingCar1 = new ShoppingCart(client.get());
                shoppingCar1.addProductToShoppingCar(item);
                shoppingCarRepository.save(shoppingCar1);
            }
        }
    }
}
