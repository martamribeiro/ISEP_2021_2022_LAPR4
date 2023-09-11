# US1901 - As Project Manager, I want that the "OrdersServer" component supports properly, at request, the needs of the "CustomerApp" application.
=======================================


# 1. Requisitos

US1901 - As Project Manager, I want that the "OrdersServer" component supports properly, at request, the needs of the "CustomerApp" application.

# 2. Dados do Servidor da Order

| Servidor  | Port  |
|-----------|-------|
| 127.0.0.1 | 10000 |

# 3. Fluxo de Troca de Mensagens entre o Servidor e o Cliente

* Sempre que o cliente solicita um request, através de uma conexão TCP, segue-se o seguinte fluxo de comunicação:

**1.** Espera pela mensagem do Cliente com o Código de Teste (0).  
**2.** Manda ao Cliente o Código de Entendido (2).
**3.** Cliente solicita o request desejado através do seu código associado  
**4.** Socket aguarda mensagem do client e verifica qual o request a executar através do código

<-----------------------Execução do Request----------------------->

* Para terminar o request, o servidor:

**5.** Espera pela mensagem do Cliente com o Código de Fim (1).  
**6.** Manda ao Cliente o Código de Entendido (2).  
**7.** Fecha o Socket.  

# 4. Requests

No âmbito desta US, são efetuados os seguintes requests:

| Código | Request                                          |
|--------|--------------------------------------------------|
| 4      | Mostrar Catálogo de Produtos                     |
| 3      | Verificar se o Produto desejado existe           |
| 5      | Adicionar o Produto ao Carrinho de Compras       |
| 13     | Mostrar Open Orders do Cliente                   |
| 12     | Mostrar Questionários ao Cliente                 |
| 14     | Guardar as respostas ao Questionário do Cliente  |
| 16     | Verificar se o Cliente respondeu ao Questionário |



# 5. Observações 

### 5.1 Order Server na Cloud

Para este Sprint, foi criado um ambiente virtual destinado à Order Server.  
Apesar de o jar correr de forma bem sucedida nos servidores do isep, ao chamar os repositórios das bases de dados necessários à execução desta US, dá erro.  
Será algo a resolver no próximo Sprint.




