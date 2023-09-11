# 2005 - "As Warehouse Employee, I want to open a web dashboard presenting the current status of the AGVs as well as their position in the warehouse layout and keeps updated automatically (e.g.: at each minute)."



# 1. Requisitos


**UC2005:** Como Warehouse Employee desejo ver uma web dashboard com o status de cada agv e a sua posição".

A interpretação feita deste requisito foi a de recuperar os estados e posições dos AGVs presentes no sistema.

# 2. Análise

## 2.1. Respostas do Cliente

>Q1: "Our question is, between what applications should the SPOMS protocol be implemented? Should the HTTP server be part of the "BackOfficeApp" and communicate with the AGV Manager using the REQUESTS_API? Or should the HTTP server be its own application and communicate only with the "BackOfficeApp", which on the other hand communicates directly with the database?"
>
>A1: "As it is being said the "HTTP Server" is part of the "Backoffice Application" in order to provide a local web page only. As so, the "HTTP Server" is a component of the "Backoffice Application"."

>Q2: "Despite in the provided sprint user stories asking for the digital twin in a web dashboard along with its status and position, in user stories of the next sprint it is said that the development of the movement of the AGV is needed which causes a minor confusion. My question is in this sprint is it required to create the movement of the AGV?"
>
>A2: "On Sprint C, the web dashboard needs to be thought and ready to show the current AGVs position, which is read from some where. Further, on sprint D, when simulating the AGV movement the AGV position will change and, therefore, we will be able to see the AGVs position changing accordingly on the web dashboard. "

>Q3: "How would you like the dashboard to look? A simple list of the AGVS along with its position and status?"
>
>A3: "No! Preferably, the dashboard should be an approximation to what is depicted on Figure 8 of the specifications document."

 
## 2.2. Regras de Negócio

* It must be used the provided application protocol (SPOMS2022).
* The dashboard is intended to be displayed on a web page provided by an existing HTTP server in the "BackOfficeApp" application and only available to localhost.
* The dashboard web page is kept updated without reloading.


# 3. Dados do Servidor do HTTP Server

| Servidor        | Port  |
|--------------|-------|
| 127.0.0.1    | 55090 |

# 4. Fluxo de Troca de Mensagens entre o Servidor e o Cliente

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

# 5. Requests

No âmbito desta US, são efetuados os seguintes requests:

| Código | Request  |
|--------|-------|
| 6      | Buscar AgvPositions disponiveis na base de dados |
| 8      | Buscar AGVs desponiveis na base de dados |

