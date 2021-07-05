# api-serverest-rest-assured
**Automação da API ServeRest**

Este projeto tem como objetivo a automatização dos cenários de testes para os endpoints de Usuários e Produtos da API ServeRest. 

___

**Estrutura do Projeto:**

Eu estou utilizando a seguinte estrutura para este projeto:

```
api-serverest-rest-assured/
  main/java
      models/
          Constants.java
          Product.java
          User.java
      requests/
          ProductEndpoint.java
          RequestBase.java
          UserEndpoint.java
  test/java
      commons/
          TestBase.java
      product/
          DeleteProductTests.java
          GetProductTests.java
          PostProductTests.java
          PutProductTests.java
      user/
          DeleteUserTests.java
          GetUserTests.java
          PostLoginTests.java
          PostUserTests.java
          PutUserTests.java
```