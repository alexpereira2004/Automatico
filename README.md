# MS Automatico

A finalidade deste microserviço é ser responsável por um agendamento da execução de scrapings de leitura de dados de ativos.

O agendamento deve ocorrer respeitando algumas premissas:

- Ser executado em um dia útil;
- Possuir um controle de quais ativos já participaram do processo no dia, em caso de leitura de dados já realizada, não deverão ser incluídos em uma nova requisição;
- Verificar que o scraping seja executado somente para os ativos estão sendo seguidos.

### Dependências

Para executar este projeto é necessário um **banco de dados** e um serviço de mensageria **RabbitMQ**.

O banco de dados é responsável por persistir dados referentes à ativos, dias úteis e agenda.

O serviço de mensageria recebe os eventos de solicitação de scraping. Também servirá como receptor de eventos de confirmação da execução com sucesso do processo de scraping.

### Banco de dados
O arquivo documents/dia_util.sql possui a definição completa de dias úteis e feriados nacionais de 2022 até 2052.

Abaixo, o diagrama ER da primeira versão:

![Home](documents/images/ER_v1.png)

Descrição das tabelas:

| Nome     | Descrição                                                                                                                                                                                                                                                                                                                 |
|----------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ativo    | **Seguindo:** Define quando o MS irá enviar o ativo para solicitar scraping <br> **Ultima_atualizacao**: Mantém a data da última cotação do ativo                                                                                                                                                                         |
| Agenda   | **Dia**: <br> 0 - Segunda <br> 1 - Terça <br> 2 - Quarta <br> 3 - Quinta <br> 4 - Sexta <br> 5 - Sábado <br> 6 - Domingo <br> 7 - Todos os dias <br> **Inicio**: Hora e inicio  <br> **Fim**: Hora que a agenda encerra <br> **Periodicidade**: Tempo em minutos entre tentativas de scraping dentro do período da agenda |
| Dia_util | **Dia_ano**: Dia do ano<br> **Feriado**: Em caso do dia ser um feriado, descrição de qual feriado é. <br> **Semana**: Semana do ano <br> **Tipo**: <br>Tipo do dia<br>0 - Dia útil<br>1 - Fim de semana<br>2 - Feriado                                                                                                        |
