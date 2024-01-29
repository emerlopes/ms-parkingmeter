# Projeto MSParkingMeter

Este projeto é uma aplicação Java Spring Boot que gerencia operações relacionadas a um sistema de controle de
estacionamento. Utiliza Gradle como ferramenta de build e gerenciamento de dependências e segue uma arquitetura limpa e
modular.

## Estrutura do Projeto

O projeto está organizado em diferentes camadas, refletindo a separação de responsabilidades:

1. **Application**: Contém classes e configurações para iniciar a aplicação, incluindo controladores REST.
2. **Domain**: O coração da aplicação com as regras de negócio, livre de dependências externas.
3. **Infrastructure**: Elementos para comunicação com o mundo exterior, como bancos de dados e clientes de serviços web.
4. **Repositories**: Interfaces para a camada de persistência.

## Dependências Externas

Para o MSParkingMeter funcionar corretamente, ele depende de outras duas aplicações: `ms-drivers` e `ms-payments`. É
necessário clonar e executar estas aplicações:

1. **ms-drivers**
    - Clone: `git clone https://github.com/emerlopes/ms-drivers.git`
    - Inicialização: Siga as instruções no README do repositório para subir a aplicação.

2. **ms-payments**
    - Clone: `git clone https://github.com/emerlopes/ms-payments.git`
    - Inicialização: Siga as instruções no README do repositório para subir a aplicação.

## Executando o Projeto

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `./gradlew bootRun` para iniciar a aplicação.

## Testando o Projeto

1. Navegue até o diretório do projeto.
2. Uma collection para testar o fluxo completo está disponível na pasta `misc`. Importe-a no Insomnia para uso.

### Notificações

Para testar a funcionalidade de notificação, ative-a alterando a propriedade `twilio.notify=false` para `true` no
arquivo `application.properties`.

# Sistema de Notificação do MSParkingMeter

O MSParkingMeter oferece um sistema de notificação sofisticado para melhorar a experiência do usuário com relação ao
controle do tempo de estacionamento. Existem duas formas de notificação:

1. **Notificação Agendada para Estacionamento Fixo**:
    - Esta notificação é programada para ser enviada quando faltarem 15 minutos para o término do prazo fixo de
      estacionamento.
    - É especialmente útil para usuários com um período de estacionamento pré-estabelecido, permitindo que recebam um
      lembrete oportuno para voltar ao veículo ou estender o tempo se necessário.

2. **Notificação Imediata para Estacionamento Variável**:
    - Em casos de estacionamento variável, a notificação é enviada de forma imediata.
    - Para fins de teste, ao encerrar o estacionamento, o sistema considera o horário de encerramento como a data e hora
      atual acrescidas de 85 minutos. Assim, 85 minutos são adicionados à variável `parkingEndTime`, facilitando a
      realização de testes da funcionalidade.

### Importância do Cadastro Correto do Número de Telefone

- Para receber as notificações, é essencial que o número de telefone do condutor esteja corretamente cadastrado no
  sistema.
- O formato do número deve seguir o padrão DDD + número de telefone. Por exemplo: `119123456789`.
- Certificar-se de que o número está correto é crucial para o funcionamento efetivo das notificações.

Com essas funcionalidades de notificação, o MSParkingMeter busca oferecer um serviço mais conveniente e adaptado às
necessidades individuais dos usuários, garantindo que eles sejam devidamente informados sobre o status do
estacionamento.

## Documentação das APIs

As URLs das documentações Swagger das APIs envolvidas são:

| API             | URL Documentação Swagger                                    |
|-----------------|-------------------------------------------------------------|
| ms-drivers      | [index.html](http://localhost:8080/swagger-ui/index.html#/) |
| ms-payments     | [index.html](http://localhost:8081/swagger-ui/index.html#/) |
| ms-parkingmeter | [index.html](http://localhost:8082/swagger-ui/index.html#/) |

## Contribuindo

Pull requests são bem-vindos. Abra uma issue primeiro para discutir mudanças importantes. Atualize os testes conforme
necessário.

## Licença

[MIT](https://choosealicense.com/licenses/mit/)
