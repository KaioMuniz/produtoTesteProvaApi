# 📦 ProdutoTesteProvaApi

API RESTful para gerenciamento de produtos e tipos de produtos, desenvolvida com Spring Boot.
API de Produto que uma outra aplicação client irá utilizar, será usado para direcionar alguns questionamentos durante a entrevista técnica, com o time de desenvolvimento.Gravei todo o processo em vídeo, mostrando o passo a passo do desenvolvimento.
---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database (em memória)**
- **Swagger (Documentação da API)**
- **Docker & Docker Compose**
- **Maven**

---

## Visão Geral da Arquitetura

```
src/
└── main/
    ├── java/
    │   └── br/
    │       └── com/
    │           └── kaiomuniz/
    │               ├── configurations/          # Configurações gerais da aplicação (ex: Swagger)
    │               │   └── SwaggerConfiguration.java
    │               ├── controllers/             # Controladores REST que recebem e processam requisições HTTP
    │               │   └── ProdutoController.java
    │               ├── entities/                # Entidades JPA que representam tabelas do banco de dados
    │               │   ├── Produto.java
    │               │   └── Tipo.java
    │               ├── repositories/            # Interfaces JPA para acesso e manipulação dos dados no banco
    │               │   └── ProdutoRepository.java
    │               ├── services/                # Serviços com lógica de negócio da aplicação
    │               │   └── ProdutoService.java
    │               └── ProdutoTesteProvaApiApplication.java  # Classe principal da aplicação Spring Boot
    └── resources/
        └── application.properties               # Configurações da aplicação

```

<img width="637" height="476" alt="image" src="https://github.com/user-attachments/assets/5663102b-c9b1-44cf-a93f-fa85d6090753" />



![1](https://github.com/user-attachments/assets/bd2fde48-b50a-49ab-a411-e3206e1c15bb)
![2](https://github.com/user-attachments/assets/abc314ac-cd3e-46d1-bafd-3b215fb451b2)

![image](https://github.com/user-attachments/assets/17794a8b-ddba-4ffb-aca1-108271adfbd1)


---

## Contato

Para dúvidas ou sugestões, entre em contato:  
- kkaioribeiro@gmail.com
- https://www.linkedin.com/in/kaiomuniz/

---
