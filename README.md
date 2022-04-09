# CSTV

CSTV é um aplicativo para os amantes do jogo online Counter-Strike: Global Offensive, o aplicativo exibe uma lista de partidas que estão acontecendo e que irão acontecer em campeonatos por todo o mundo, com informações como os nomes e emblemas das equipes que disputarão, os horários dos confrontos e até detalhes sobre os integrantes das equipes.

### Processo de construção do app

* A intenção foi utilizar a arquitetura MVVM na organização dos arquivos e, além disso, organizar as pastas do projeto de maneira intuitiva, separando de maneira clara as camadas de serviço, repositório, UI e as entidades.
* Escolhi trabalhar com a biblioteca Coil no carregamento de imagens para a interface por ter achado mais simples e dinâmica de se utilizar.
* Para as chamadas das requisições à API PandasScore, resolvi utilizar a ferramenta Kotlin Coroutines ao invés de fazer as chamadas assíncronas diretamente do retrofit para que eu pudesse, através das suspend functions, manipular os objetos das respostas de maneira direta ao invés de receber um objeto do tipo Call<>.
* Para que a camada de repositório informasse à camada de visualização em que estado estava a chamada da requisição, utilizei os fluxos observáveis StateFlow com o uso de uma classe selada.
* Resolvi usar o componente de navegação do Android Jetpack para facilitar as transições e a passagem de informações entre um fragmento e outro do aplicativo.
* Para facilitar a troca da ProgressBar que informa ao usuário que o app está fazendo a requisição para a tela com as informações que vieram da API em si, utilizei uma ViewFlipper que é controlada pela camada de visualização e troca a visualização à depender do estado ouvido no StateFlow.
* Implementei uma ScrollView na tela de detalhes dos times para que a tela tivesse uma responsividade ao virar o celular de lado.
* Fiz uma classe específica para tratas as horas retornadas nas respostas da API que estão em fuso-horário diferente.

### Como executar o projeto:

Para executar o projeto no seu Android Studio:
1. Vá até a página do projeto no GitHub, na aba Code e clique no botão verde "Code".
2. Clique em "Download ZIP".
3. Extraia a pasta em um caminho conhecido no seu computador.
4. Ao abrir o Android Studio, clique em Import Project.
5. Selecione a pasta que você extraiu e o Android Studio abrirá o projeto.
6. Para rodar no emulador, basta selecionar o emulador desejado na IDE e clicar no botão verde de play na barra de ferramentas. (RODANDO NO EMULADOR)
7. Para rodar em um celular, é possível gerar um apk, clicando no seguinte caminho dentro da IDE Build > Build Bundle(s)/APK(s) > Build APK(s). Espere compilar e o arquivo .apk estará no seguinte caminho: app\build\outputs\apk\debug\app-debug.apk (RODANDO NO CELULAR)
8. Basta baicar o apk em um aparelho Android e após as autorizações do sistema, instalar e rodar o app.
