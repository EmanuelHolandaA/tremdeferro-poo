# Sistema Trem de Ferro

Trabalho da disciplina de Programação Orientada a Objetos. O sistema representa um trem composto por uma locomotiva e vagões de tipos diferentes, aplicando herança, polimorfismo e classes abstratas em Java.

## O que o sistema faz

O trem pode transportar passageiros, animais e cargas ao mesmo tempo. Cada tipo de vagão tem suas próprias regras de ocupação. A locomotiva controla a velocidade e define quantos vagões o trem pode ter.

## Estrutura do projeto

```
TremDeFerro/
├── src/
│   ├── Main.java
│   ├── Vagao.java
│   ├── Locomotiva.java
│   ├── Trem.java
│   ├── Passageiro.java
│   ├── VagaoPassageiros.java
│   ├── VagaoAnimais.java
│   └── VagaoCarga.java
├── bin/
├── .vscode/
│   ├── settings.json
│   └── launch.json
├── .gitignore
└── README.md
```

## Como rodar

Precisa ter o Java JDK instalado.

Pelo terminal:

```bash
git clone https://github.com/seu-usuario/TremDeFerro.git
cd TremDeFerro
javac -d bin src/*.java
java -cp bin Main
```

Pelo VS Code:

1. Instale a extensão Extension Pack for Java
2. Abra a pasta do projeto
3. Pressione F5

## Classes principais

**Vagao** - classe abstrata que serve de base para todos os vagões. Define os métodos embarcar, desembarcar, buscar e listar, que cada subclasse implementa do seu jeito.

**VagaoPassageiros** - limite por número de assentos, identifica passageiros pelo CPF.

**VagaoAnimais** - tem limite de quantidade, peso total e aceita só algumas espécies. Identifica por tag do animal.

**VagaoCarga** - limite de quantidade, peso e volume em m³. Identifica pelo código da carga.

**Locomotiva** - controla a velocidade com acelerar, desacelerar e parar. Define o máximo de vagões.

**Trem** - junta a locomotiva e os vagões. Usa referências do tipo Vagao, então não precisa saber o tipo concreto de cada um para chamar os métodos.

## Conceitos de POO aplicados

**Encapsulamento** - atributos privados em todas as classes, acessados por getters.

**Herança** - VagaoPassageiros, VagaoAnimais e VagaoCarga estendem Vagao.

**Classe abstrata** - Vagao não pode ser instanciada diretamente e força as subclasses a implementar os métodos.

**Polimorfismo** - o Trem guarda uma List<Vagao> e chama os métodos sem saber o tipo real de cada vagão.

**Sobrescrita** - cada subclasse implementa embarcar, listar etc de acordo com suas regras.

## Equipe

| Nome | Matícula |
|------|----|
|      |    |
|      |    |
|      |    |
