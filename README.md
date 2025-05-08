# Encurtador de URLs com Java e Spring

Este projeto implementa um serviço de encurtador de URLs usando Java com o framework Spring Boot. Ele permite que você converta URLs longas em URLs mais curtas e fáceis de compartilhar.

## Funcionalidades

* **Encurtamento de URL:** Encurta uma URL longa fornecida pelo utilizador, gerando uma URL curta única.
* **Redirecionamento:** Redireciona a URL curta para a URL original correspondente.
* **Armazenamento de URLs:** Armazena as URLs encurtadas e as suas URLs originais na base de dados MongoDB.
* **Geração de Hash:** Usa a biblioteca Apache Commons Codec para gerar hashes para as URLs curtas.
* **API RESTful:** Expõe uma API para encurtar e redirecionar URLs.

## Tecnologias Utilizadas

* Java: Linguagem de programação principal.
* Spring Boot: Framework Java para desenvolvimento rápido de aplicações web.
* MongoDB: Base de dados NoSQL para armazenar as URLs e os seus mapeamentos.
* Apache Commons Codec: Biblioteca para geração de hashes (URL curta).

## Requisitos

* Java 11 ou superior
* MongoDB instalado e em execução
* Maven

## Configuração

1.  **Clone o repositório:**

    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    ```
2.  **Configure o MongoDB:**

    * Certifique-se de que o MongoDB esteja instalado e em execução.
    * Configure a string de conexão do MongoDB no ficheiro `application.properties` ou `application.yml`. Exemplo:

    ```properties
    spring.data.mongodb.uri=mongodb://seu_usuario:sua_senha@seu_host:seu_porta/nome_do_seu_banco
    ```
3.  **Compile o projeto:**

    ```bash
    mvn clean install
    ```
4.  **Execute a aplicação:**

    ```bash
    mvn spring-boot:run
    ```

## Como Usar

A aplicação expõe uma API RESTful.

### Encurtar URL

* **Endpoint:** `POST /v1/url`
* **Request Body:**

    ```json
    {
        "url": "[https://www.exemplo.com.br/um/caminho/muito/longo](https://www.exemplo.com.br/um/caminho/muito/longo)"
    }
    ```
* **Response:**

    ```json
    {
        "shortUrl": "https://seu_dominio/hashGerado"
    }
    ```

    * `shortUrl`: A URL curta gerada.

### Redirecionar

* **Endpoint:** `GET /{shortUrl}`
* A aplicação redireciona para a `longUrl` original.

## Próximos Passos

* Adicionar tratamento de erros e validação de entrada mais robustos.
* Implementar testes unitários e de integração.
* Adicionar uma interface de utilizador para interagir com a API.
* Implementar estatísticas de acesso às URLs encurtadas.
* Personalizar a URL encurtada.
