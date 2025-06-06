# Créditos - Desafio Técnico

Este projeto consiste em uma aplicação full stack para **consulta de créditos constituídos** com base em informações de NFS-e ou número de crédito.

O desafio propõe o desenvolvimento de:

✅ API RESTful com Spring Boot e PostgreSQL  
✅ Front-end em Angular  
✅ Containerização com Docker  
✅ Integração com Kafka para envio de mensagens de auditoria (desafio extra)  
✅ Testes automatizados no back-end

---

## 🗂 Estrutura do projeto

```
/creditos-desafio
├── creditos-api          -> Back-end (Spring Boot)
├── creditos-front        -> Front-end (Angular)
├── docker-compose.yml    -> Orquestração dos serviços
└── README.md             -> Este arquivo
```

---

## 🚀 Como executar o projeto

### Pré-requisitos

- Docker e Docker Compose instalados.

### Subindo os serviços

Na raiz do projeto (`/creditos-desafio`), execute:

```bash
docker compose up --build
```

Isso irá subir os seguintes containers:

- `postgres_creditos` → banco de dados PostgreSQL
- `creditos-api` → API REST em Spring Boot
- `creditos-front` → Aplicação Angular
- `zookeeper` → Gerenciamento do Kafka
- `kafka` → Broker Kafka

---

## 🌐 Acessando a aplicação

### Front-end

Abra o navegador em:

```
http://localhost:4200
```

### API REST

Os endpoints principais:

- `GET /api/creditos/{numeroNfse}` → consulta por número de NFS-e
- `GET /api/creditos/credito/{numeroCredito}` → consulta por número de crédito

A API estará disponível em:

```
http://localhost:8080
```

---

## 📝 Mensageria - Kafka

Como desafio extra, foi implementado um **Publisher Kafka**.

✅ Sempre que uma consulta for realizada nos endpoints da API, uma mensagem será enviada para o tópico `consulta_credito`.

### Verificando as mensagens enviadas

Você pode visualizar as mensagens enviadas para o tópico executando o seguinte comando (em outro terminal):

```bash
docker exec -it kafka kafka-console-consumer --bootstrap-server kafka:9092 --topic consulta_credito --from-beginning
```

Você verá mensagens como:

```
Consulta NFS-e realizada: 12345
Consulta Crédito realizada: 98765
```

---

## 🧪 Testes

### Back-end

Foi implementada cobertura básica de testes unitários e de controller no projeto `creditos-api`.

Execute localmente:

```bash
cd creditos-api
./mvnw clean test
```

---

## ✅ Funcionalidades atendidas

### Requisitos obrigatórios

- [x] API RESTful com Spring Boot e PostgreSQL
- [x] Endpoint de consulta por número de NFS-e
- [x] Endpoint de consulta por número de crédito
- [x] Front-end em Angular para permitir a busca
- [x] Docker Compose para subir os serviços
- [x] Testes automatizados no back-end

### Desafio extra

- [x] Publisher Kafka para enviar mensagem a cada consulta realizada

---

## Observações

- A aplicação foi projetada para um uso simples e responsivo.
- O front-end foi estilizado com um layout limpo, responsivo e compatível com dispositivos móveis.
- Toda a comunicação entre front-end e back-end funciona corretamente em ambiente Dockerizado.

---

## Para o Avaliador

✅ O projeto foi desenvolvido de acordo com todos os requisitos propostos:

- Implementação de uma API RESTful com Spring Boot para consulta de créditos.
- Banco de dados PostgreSQL com script de inicialização.
- Frontend em Angular 17, responsivo e integrado com a API.
- Docker Compose com todos os serviços orquestrados.

✅ Desafio extra realizado:

- Publisher Kafka implementado.
- Toda consulta de crédito e de NFS-e gera uma mensagem no tópico `consulta_credito` para fins de auditoria.
- As mensagens podem ser visualizadas no log do container `kafka`.

✅ Testes manuais executados:

- As APIs foram testadas através da interface frontend e com ferramentas como Postman.
- O frontend comunica corretamente com a API e exibe mensagens de erro adequadas quando não há resultado.

**Observação:**  
O projeto foi estruturado em dois diretórios:

- `creditos-api` → Backend Spring Boot  
- `creditos-front` → Frontend Angular  

O `docker-compose.yml` e o `README.md` estão na raiz para facilitar a execução e avaliação.

---

Obrigado pela oportunidade de realizar o desafio.  
Qualquer dúvida, fico à disposição!


---

# Fim 🚀
