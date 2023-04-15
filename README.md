# Desafio-CapGov

Projeto desenvolvido por [Igor Andrade](https://github.com/andradeigor). Feito como uma resolução do desafio proposto pelo CapGov.

- [Como usar](#-como-usar)
- [Rotas](#-rotas)
- [Testando](#-testando)
- [Tecnologias](#-tecnologias)
- [Contribuidores](#-contribuidores)


## 🤖 Como Usar:

Criando o Banco de Dados Mysql:
```sql
CREATE DATABASE CAPGOV;

USE CAPGOV;

CREATE TABLE Users(
userID varchar(255) PRIMARY KEY,
email VARCHAR(255) NOT NULL unique ,
password VARCHAR(255) NOT NULL, 
name VARCHAR(255) NOT NULL,
createdAt TIMESTAMP,
updatedAt TIMESTAMP);

#Caso encontre um erro na geração dessa tabela, altere o tipo de updatedAt para DATETIME.


CREATE TABLE Posts(
postID VARCHAR(255) PRIMARY KEY,
title varchar(255) not null,
content varchar(255) not null,
createdAt timestamp,
updatedAt timestamp,
userID varchar(255), foreign key (userID) references Users(userID) );

#Caso encontre um erro na geração dessa tabela, altere o tipo de updatedAt para DATETIME.

```

Rodando o Servidor localmente:

```bash
 # Clone esse repositório
 $ git clone https://github.com/andradeigor/desafioCapGov

 #Acesse a pasta do projeto
 $ cd desafioCapGov

 # Importe o repositório para dentro do eclipse.

 # Instale dependências

 # Copie o example.hibernate.cfg.xml e renomeie como hibernate.cfg.xml
 $ cp ./src/main/resources/example.hibernate.cfg.xml ./src/main/resources/example.hibernate.cfg.xml

 # Substituia as variáveis de ambiente junto com as credênciais para o login no banco de dados e do database

 # Ligue servidor rodando a classe CapGov.java dentro do pacote aplication.

```

## 📜 Rotas:
  Rotas sem necessidadde de credencias:
  
| Rota  | Método  | Corpo da Requisição  | Resposta  |
| ------------- | ------------- | ------------- | ------------- |
| /user  | Post  | email, name, password  |Json com os dados do User criado.  |
| /auth  | Post  | email, senha  |Json com o token do usuário.  |

As rotas abaixo precisam vir acompanhadas obrigatoriamente de um header extra chamado "Authorization", 
nele você irá informar o token recebido na rota auth da seguinte maneira: "Bearer token". Um exemplo de como configurar isso no Insomnia abaixo:

![Exemplo Token](https://user-images.githubusercontent.com/21049910/232223139-175454d9-e5a9-4ab6-b6f4-6c89457acf02.jpeg)

Tendo configurado o token, agora podemos fazer uso das rotas que necessitam de credenciais:


| Rota  | Método  | Corpo da Requisição  | Resposta  |
| ------------- | ------------- | ------------- | ------------- |
| /user  | Get  |  -  |Json com os dados do seu User.  |
| /posts  | Post  | title,content  |Json com os dados do Post criado. |
| /posts  | Get  | -  |Json com os dados de todos os Post criados pelo seu User. |
| /posts/:id  | Get  | -  |Json com os dados do Post que tem o id informado. |
|  /posts/:id  | Put  | title,content  | - |
|  /posts/:id  | Delete  | -  | - |

## 🚧 Testando

Use o arquivo model.txt para se guiar e faça os requests usando programas como: Insominia/Postman.

## 💻 Tecnologias

-Jersey
-Hibernate
-Mysql connector
-jBCrypt
-jjwt-api

## 👥 Contribuidores

Esses são os contribuidores do projeto (<a href="https://allcontributors.org/docs/en/emoji-key">emoji key</a>).

<table>
  <tr>
    <td align="center"><a href="https://github.com/andradeigor"><img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/21049910?v=4" width="100px;" alt=""/><br /><sub><b>Igor Andrade</b></sub></a><br /><a href="https://github.com/andradeigor/DiscordBotUFRJ/commits?author=andradeigor" title="Igor Andrade">🤔 💻 🚧</a></td>
  </tr>
</table>
