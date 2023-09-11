# US4001 - As Project Manager, I want that the "AGVManager" component supports properly, at request, the needs of the "BackOfficeApp" application as well as the needs the AGV digital twin.
=======================================


# 1. Requisitos

US4001 - As Project Manager, I want that the "AGVManager" component supports properly, at request, the needs of the "BackOfficeApp" application as well as the needs the AGV digital twin.

# 2. Dados do Servidor AGVManager

| Servidor        | Port  |
|--------------|-------|
| 127.0.0.1    | 3700 |

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

| Código | Request                                                                                |
|--------|----------------------------------------------------------------------------------------|
| 6      | Enviar ao Cliente BackOffice a lista de Posições dos AGVS                              |
| 7      | Enviar ao Cliente do AGVDigitalTwin a lista de AGVS para serem mudados os seus estados |
| 8      | Enviar ao Cliente BackOffice a lista de AGVS com os seus status atualizados.           |
| 9      | Enviar ao Cliente BackOffice a WarehousePlant que está na Base de Dados.               |
| 10     | Enviar ao Cliente BackOffice a lista de AGVDocks que está na Base de Dados. |
| 11     | Enviar ao Cliente BackOffice a lista de Aisles que está na Base de Dados. |


#5 Observações

### 5.1 AGVManager Server na Cloud

Para este Sprint, foi criado um ambiente virtual destinado à AGVManager Server.
Apesar de o jar correr de forma bem sucedida nos servidores do isep, ao chamar os repositórios das bases de dados necessários à execução desta US, dá erro.
Será algo a resolver no próximo Sprint.




