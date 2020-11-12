<h1>Segundo projeto: Exemplo de Intent</h1> 

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Java&message=java&color=yellow&style=for-the-badge&logo=Java"/>
</p>

> Status do Projeto: :heavy_check_mark: (concluido)

### Tópicos 

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Imagem do Projeto](#imagem-do-projeto)

## Descrição do projeto 

<p align="justify">
  Dando continuidade ao estudo de desenvolvimento de aplicativos, será criado um aplicativo para a chamada de diversas outras ações, que estão relacionadas a outras aplicações dentro de um sistema Android. <br> Uma intent é um objeto. Como um objeto, tem atributos e métodos que podem definir parâmetros de configuração para a execução direcionada do aplicativo chamado. O nome do componente, pode ser definido de forma implícita ou explícita. A ação deve ser definida por meio de uma string contendo os parâmetros necessários para a execução da ação.
</p>

## Funcionalidades

:heavy_check_mark: Para cada componente, foi definido o método de exceção na propriedade OnClick de cada botão na view.

:heavy_check_mark: O método chamaWeb utiliza como identificador uma chamada para “https:”, o que fará com que um navegador seja chamado para execução pelo sistema operacional (Android), com a página já determinada como parâmetro da chamada da intent.

:heavy_check_mark: O método chamaTelefone utiliza como identificador uma chamada para “tel:”, o
que fará com que o aplicativo do telefone seja chamado, já com o número do telefone definido pelo parâmetro da chamada da intent.

:heavy_check_mark: O método chamaMapa utiliza como identificador uma chamada para “geo:”, o que
fará com que seja chamado pelo Android um aplicativo de mapas, já com o endereço predeterminado pelo parâmetro da chamada.

:heavy_check_mark: O método chamaSMS utiliza como identificador uma chamada para “sms:”, o que
fará com que o aplicativo de SMS seja chamado pelo Android. No parâmetro da chamada, foi determinado o número do telefone para o envio do SMS. Ainda foi definido um parâmetro extra para a chamada pela intent, definindo o corpo da mensagem.

:heavy_check_mark: Por final, o método chamaToast faz uma chamada a um componente do Android,
que é responsável por apresentar uma mensagem sobre a tela, sendo o tempo padrão de três segundos.

## Imagem do Projeto :dash:

![Screenshot_20201112-074932](https://user-images.githubusercontent.com/54650669/98931700-1e421c00-24bd-11eb-8279-ee6552c62c8a.png)


## Desenvolvedores/Contribuintes :octocat:

Responsável pelo desenvolvimento do projeto

| [<img src="https://avatars0.githubusercontent.com/u/54650669?s=460&u=256c0c28b9d5560d21d734ceedb09439a7521cc2&v=4" width=115><br><sub>Itamar Santos da Silva</sub>](https://github.com/itamar1986) |
| :---: |

## Linkedin <a href="https://www.linkedin.com/in/itamar-santos-da-silva-463b0a176" target="_blank"> Itamar Santos da Silva</a>


