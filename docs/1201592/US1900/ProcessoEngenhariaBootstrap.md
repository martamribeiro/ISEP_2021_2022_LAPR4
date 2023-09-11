# 1900 - "As Project Manager, I intend that, for demonstration purposes, the system has the possibility of being initialized (bootstrap) with some information related to the product catalog and auxiliary information (e.g., categories of products)."



# 1. Requisitos


**UC1900:** Como um Project Manager, eu pretendo que, para a demonstração, o sistema tenha a possibilidade de ser inicializado (bootstrap) com alguma informação relacionada com o catálogo do produto e informação auxiliar (e.g., categorias de produtos).

A interpretação feita deste requisito foi no sentido de popular as tabelas da base de dados, de forma a facilitar a demonstração da aplicação.

# 2. Análise

## 2.1. Dados de bootstrap necessários


* Product Categories
* Products
* Clients
* Warehouses
* AGVs


# 3. Design


## 3.1. Organização dos Bootstrappers

* BaseBootstrap
  * BaseBootstrapper
    * MasterUsersBootstrapper
  * BaseDemoBootstrapper
    * BackofficeUsersBootstrapper
    * ClientUserBootstrapper
    * ClientsBootstrapper
    * WarehousesBootstrapper
    * ProductCategoriesBootstrapper
    * ProductsBootstrapper
    * AGVsBootstrapper
  * BaseDemoSmokeTester


# 4. Implementação

## 4.1. Classe BaseBootstrap


    [...]

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        new BaseBootstrapper().execute();

        System.out.println("\n\n------- DEMO DATA -------");
        new BaseDemoBootstrapper().execute();

        System.out.println("\n\n------- BASIC SCENARIO -------");
        new BaseDemoSmokeTester().execute();

    }

    [...]



## 4.2. Classe BaseBootstrapper


    [...]

    @Override
    public boolean execute() {
        final Action[] actions = { new MasterUsersBootstrapper(), };

        registerPowerUser();
        authenticateForBootstrapping();

        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    [...]



## 4.3. Classe BaseDemoBootstrapper


    [...]

     @Override
    public boolean execute() {
        final Action[] actions = { new BackofficeUsersBootstrapper(),
                new ClientUserBootstrapper(),
                new ClientsBootstrapper(),
                new WarehousesBootstrapper(),
                new ProductCategoriesBootstrapper(),
                new ProductsBootstrapper(),
                new AGVsBootstraper(),
        };

        authenticateForBootstrapping();

        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    [...]



# 5. Integração/Demonstração

Esta User Story foi implementada na totalidade, sendo dependente de grande parte das restantes USs, uma vez que para popular as tabelas é necessário que as funcionalidades para criação dos objectos estejam funcionais.

# 6. Observações

n/a