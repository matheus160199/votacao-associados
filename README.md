# votacao-associados
Serviço de votação em pautas para associados.

#### Algumas das técnicas utilizadas:
- Utilização do _Retrofit_ para a chamada na API de CPF utilizada na validação do associado **(Tarefa Bônus 1)**;
- Utilização do _Kafka_ como fila de mensageria para envio de resultado de pauta **(Tarefa Bônus 2)**;
- Para o versionamento da API foi utilizado o modelo de versionamento _"path/URI"_, no caso está na v1 **(Tarefa Bônus 4)**;
- Utilização do JMeter para testes de performance **(Tarefa Bônus 3)**;
- Containerização do _BD, Kafka e Zookeeper_;
- Utilização do _TimerTask e Timer_ para o fechamento da sessão de pauta na data e horário estipulado;
- Testes unitários;
- Filtros para validação de dados de requisição;

#### Observações importantes:
- Na classe Main (VotacaoApplication.java) foi criado um Consumer para fim de exemplificação e comprovação de como chegaria a mensagem de resultado de pauta para seus Consumers, printando a mesma;
- O endpoint de criação de associado as vezes retorna um BadRequest dizendo que o associado não está apto para o voto mesmo com um CPF válido, no caso esse é um problema na API externa de CPF que as vezes retorna UNABLE_TO_VOTE mesmo com um CPF válido, após 2 ou 3 tentativas a validação é feita corretamente;
- Devido ao erro na API de CPF, os testes de performance também podem ser afetados!

## Instruções para teste

###### Subir o container docker com o banco de dados, kafka e zookeeper:
```
docker compose up -d
```
###### Rodar a classe main da aplicação:
```
VotacaoApplication.java
```
#### A aplicação rodará na porta localhost:8080

###### Teste de performance (opcional):
```
mvn jmeter:jmeter
```
**OU**
```
 mvn clean install
 ```

#### Endpoints:

###### Collection do Postman:
###### No Postman selecionar: import -> link -> colar a URL
https://www.getpostman.com/collections/0b7a584f3736dd31343d

#### OU

###### Criação de nova pauta:
```
POST - /v1/pauta/nova?nome={nome}
```
###### Abertura de sessão de uma pauta:
```
PUT - /v1/pauta/abre-sessao?id={pauta_id}&data_expiracao={dd/MM/aaaa hh:MM:ss}
```
###### Criação de novo associado:
```
POST - /v1/associado/novo?cpf={cpf}
```
###### Criação de novo voto:
```
POST - /v1/voto/novo?associado_id={associado_id}&voto={SIM/NAO}&pauta_id={pauta_id}
```
