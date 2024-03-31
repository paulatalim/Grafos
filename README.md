![header](https://capsule-render.vercel.app/api?type=waving&color=d736ff&fontColor=ffffff&height=200&section=header&text=Grafos&fontSize=45&animation=fadeIn&fontAlignY=38)

## Orientações

Para rodar o projeto siga as seguintes instruções

### 1. Configuração do ambiente

Para rodar o projeto, primeiramente é necessário que o computador tenha o ambiente de desenvolvimento instalado e configurado, para isso siga as seguintes instruções:

**a. Intale o VS code em seu computador**

- [Link para instalção do VS code](https://code.visualstudio.com/download)

> Selecione o seu Sistema Operacional, baixe e instale o VS Code.

**b. Configure o VS code para rodar Java**

- [Orientações para instalação do Java no VS Code](https://code.visualstudio.com/docs/java/java-tutorial)

**c. Configuração do terminal externo (opcional)**

Para uma melhor experiência com o programa, abra as configurações do VS Code com o atalho `ctrl` + `,` e vá em `Extensões>Java Debugger` na opção `Settings: Console` selecione o item `externalTerminal`.


### 2. Clonar projeto

Para clonar o projeto abra seu prompt comando e coloque os seguintes comando

```
git clone https://github.com/paulatalim/Grafos.git
```

```
cd Grafos
```

> Obs: instale o [git](https://git-scm.com/) para rodar os comandos acima

### 3. Rodar o programa

a. Abra o projeto em seu VS Code;

b. Abra o arquivo `App.java`, localizado na pasta `src`;

c. Selecione a opção `Run Java`, localizado no canto superior direito do VS code.

## Possíveis Erros

Ao tentar compilar o código, pode ocorrer um erro de compatibilidade com o `jdk`

Para resolver esse problema siga as seguintes instruções:

1. Abra a pasta do projeto no `command prompt`

2. Insira os seguintes comandos no terminal:

    ```
    cd src
    ```
    ```
    javac App.java -d ../bin
    ```

3. Volte para o VS Code e refaça o [Passo 3](#3-rodar-o-programa)

## Créditos

### Integrantes do grupo

- [Gabriel Cecconello](https://www.linkedin.com/in/gabriel-cecconello/)
- [Paula Talim](https://www.linkedin.com/in/paulatalim/)
- [Rafael Vicente](https://www.linkedin.com/in/rafael-vicente-8726a6204/)

### Professor Orientador

- [Walisson Ferreira](https://www.linkedin.com/in/walisson-ferreira-4b580a36/)

![footer](https://capsule-render.vercel.app/api?type=waving&color=d736ff&height=200&section=footer&animation=fadeIn)
