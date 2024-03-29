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
    - Verifique se a aplicação está rodando na porta 8080 sem erros.
    - Pode acontecer de a aplicação não subir corretamente na primeira vez. Caso isso ocorra, tente novamente.

2. **ms-payments**
    - Clone: `git clone https://github.com/emerlopes/ms-payments.git`
    - Inicialização: Siga as instruções no README do repositório para subir a aplicação.
    - Verifique se a aplicação está rodando na porta 8081 sem erros.
    - Pode acontecer de a aplicação não subir corretamente na primeira vez. Caso isso ocorra, tente novamente.

## Executando o Projeto

1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `./gradlew bootRun` para iniciar a aplicação.

## Testando o Projeto

### Passos Necessários para Testar o Fluxo de Estacionamento

Siga estes passos para testar o fluxo completo no sistema de estacionamento:

#### 1. Navegação até o Diretório do Projeto

- Navegue até o diretório do projeto onde a collection do Insomnia está localizada.

#### 2. Importação da Collection no Insomnia

- Importe a collection disponível na pasta `misc` no Insomnia para uso.

#### 3. Criação de um Condutor

- **Antes de iniciar os testes**, é essencial cadastrar um condutor no sistema.
- Execute a request `1.1 Cadastro condutor` para realizar o cadastro.
- Este passo é **fundamental** para associar as operações de estacionamento a um usuário específico.

#### 4. Registro de um Veículo

- Após o cadastro do condutor, registre um veículo para ele.
- Execute a request `1.2 Cadastro veículos`, informando o `driver_external_id` gerado anteriormente.
- Isso associa o veículo ao condutor no sistema.
- É possivel consultar os veiculos cadastrados com a request `1.3 Consulta condutor`.

#### 5. Registro de uma Forma de Pagamento

- Em seguida, registre uma forma de pagamento para o condutor.
- Utilize a seção `2. Registro de Forma de Pagamento` na collection do Insomnia.
- Escolha e execute uma das requests disponíveis para este fim.
- 1 para CREDIT_CARD, 2 para DEBIT_CARD ou 3 para PIX).
- A forma de pagamento é crucial para os serviços de estacionamento.

#### 6. Início de um Período de Estacionamento

- Com veículo e forma de pagamento configurados, inicie um período de estacionamento.
- Para estacionamento **fixo**, execute a request `3.1` com o `driver_external_id` na URL.
- Para estacionamento **variável**, use a request `3.2` com o `driver_external_id` na URL.
- Informe o `period-type-id` (1 para FIXO ou 2 para VARIÁVEL).
- Para testar a obrigatorioedade da duracao do estacionamento em periodo fixo, remova o parametro `requested-minutes` da
  request `3.1`.
- O requested-minutes tem um periodo minimo de 60 minutos

#### 7. Encerramento do Período de Estacionamento

> Lembre-se que 80 minutos são adicionados ao horário de encerramento, ou seja, hora atual + 80 minutos.
> 
> Entao, se o horario atual for 10:00, o horario de encerramento sera 11:20.
> 
> Caso encerre um periodo fixo previsto para 60 minutos, o horario de encerramento sera 11:20, e serao cobrados o minutos
  excedentes. Entao as horas previstas serao calculadas dentro da tarifa fixa, somando com as horas excedentes que serao calculadas com base na tarifa variavel.
> 
> Caso encerre um periodo fixo previsto para 120 minutos, o horario de encerramento sera 11:20, e nao serao cobrados
  minutos excedentes, apenas os minutos previstos na tarifa fixa.
> 
> Caso encerre um periodo variavel, o horario de encerramento sera 11:20 e sera cobrado a tarifa variavel.

- Para encerrar, informe apenas o `parking_control_id` na URL da request apropriada.
- Este passo encerra a sessão e permite a cobrança.
- Os dados do recibo de pagamento são retornados na resposta, dentro do objeto `payment_receipt`.

## Notificações

Para testar a funcionalidade de notificação, ative-a alterando a propriedade `twilio.notify=false` para `true` no
arquivo `application.properties`.

### Sistema de Notificação do MSParkingMeter

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
      atual acrescidas de 80 minutos. Assim, 80 minutos são adicionados à variável `parkingEndTime`, facilitando a
      realização de testes da funcionalidade.
    - Exemplo: se 60 minitos forem solicitados, o horário de encerramento sempre será 80 minutos após a data e hora atual.


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
