# US5100 - As Project Manager, I want that the team to develop and integrate the others components/parts of the AGV digital twin (e.g.: movement, obstacle sensors, control unit)
=======================================


# 1. Requisitos

US5100 - As Project Manager, I want that the team to develop and integrate the others components/parts of the AGV digital twin (e.g.: movement, obstacle sensors, control unit).

### 1.1 Especificações e esclarecimentos do cliente

[Question:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=16966)
Question 1:Could you specify how the speed of an AGV is determined, seeing that we do not know the maximum speed of the AGV?

Question 2:At what charge do you suppose the AGV should move to the AGV docker to charge?

Question 3:How should we measure the charging of an AGV should it have a certain percentage per minute or per hour?

Question 4:Could you specify at what percentage the discharging of the AGV happens and if it is affected by other factors other than being turned on?

Question 5:It is mentioned that when the AGV detects an obstacle 2 squares away it should reduce its velocity, could you please quantify the reduction.

[Answer:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=16966)  

Answers:

You have to notice that the overall idea is to simulate a real AGV. As so, you might start by applying basic algorithms for every AGV functions (e.g.: moving, charging/discharging battery). Probably, you might need some input information to apply such algorithms (e.g.: min, max and average speed). Such information might vary from one AGV to another, for instance, based on the AGV model.

Q1: If you need such information, you should collect such information previously (e.g.: US 2002).

Q2: Again, such value might be configurable by AGV (model).

Q3: You should adopt "seconds" as time unit.

Q4: As stated on the specifications' document: "when the AGV is moving battery consumption might be computed based on the travelled distance, but when the AGV is waiting/stopped on its dock battery consumption might be computed based on time.". Other factors might be considered, but at this stage, I recommend you to not apply a complex algorithm.

Q5: It is up to you decide that. However, notice that the idea is to avoid collision.


Yet, about this subject here are some additional hints:
- Choose a speed that allows to see the movement of the AGV on the screen;
- You should define your charging and discharging model. A simplified linear model is perfectly adequate for the problem.
- Define your charging and discharging parameters according to your model.


[Question:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=16858)


Good afternoon, dear customer.

Referring to the documentation, it is mentioned that the Route Planner module of the AGV Digital twin is responsible for "... (re)computing routes based on a source and target location on thewarehouse considering the warehouse plant only. It is worth notifying that AGV can only movehorizontally or vertically".

What do you mean by source and target location of agv ? We can set agvdock as a starting point but what would be the end point?


[Answer:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=16858)

When assigning a task to an AGV, the AGV knows which products to collect, right?

So, the source location (starting point) is the position where the AGV is at that moment (as you said, it might be the AGV dock)

The target location (end point) might be the location of a product.

However,  there are other possibilities.

For instance, consider the scenario where the AGV has to collect 2 products (say A and B).

At least three routes have to be computed:

1. From AGV Dock location to the location of product A.

2. From location of product A to the location of product B.

3. From location of product B to the AGV dock location.

[Question:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=17039)

Dear Client,

the statement says that an AGV has a total of 8 sensors, 2 in each corner of the AGV. Therefore, some doubts have arisen, such as:

What is the difference between having one or two sensors, in each corner? How do we differentiate between them? Are they in the same position?


[Answer:](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=17039)

Each sensor is a source of information to signal (or not) the presence of an obstacle (e.g.: another AGV, an aisle, etc.).

In each corner there is a sensor to evaluate obstacles in the front and another sensor  to evaluate obstacles on the side.



# 3. Dados dos Servidores envolvidos

##3.1. Dados do Servidor do AGV Manager
| Servidor        | Port  |
|--------------|-------|
| 127.0.0.1    | 3700 |

##3.2. Dados do Servidor do AGV Digital Twin
| Servidor        | Port  |
|--------------|-------|
| 127.0.0.1    | 2400 |

# 4. Fluxo de Troca de Mensagens entre o Servidor e o Cliente

![SD_Scomp.svg](./SD_US5100.svg)


# 4. Requests

No âmbito desta US, são efetuados os seguintes requests:

| Código | Request  |
|--------|-------|
| 6      | Pedido do HTTPServer para AGVManager e do AGVManager para o Digital Twin de uma lista de AGV Status |
| 7      | Envio do AGV para o Digital Twin |
| 10     | Pedido do HTTPServer para AGVManager e do AGVManager para o Digital Twin da WarehousePlant atualizada com as Posições dos AGVs |
| 11     | Confirmação do Digital Twin para AGVManager enviar WarehousePlant |
| 12     | AGVManager confirma que enviou WarehousePlant para Digital Twin |
| 13     | Confirmação do Digital Twin para AGVManager enviar o AGV |
| 14     | AGVManager confirma que enviou AGV para Digital Twin |
| 15     | Confirmação do Digital Twin para AGVManager enviar a Task do AGV |



#5 Observações

### 5.1 Servidores desta US na Cloud

Para este Sprint, foi criado um ambiente virtual destinado ao AGVManager e ao Digital Twin.  
Apesar de o jar correr de forma bem sucedida nos servidores do isep, ao chamar os repositórios das bases de dados necessários à execução desta US, dá erro.





