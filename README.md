# Sistema de Reserva de Salas

O **Sistema de Reserva de Salas** é uma aplicação desenvolvida para facilitar a gestão de reservas de salas. O objetivo é oferecer uma interface simples e eficiente, garantindo maior organização e clareza no uso dos espaços.

### Objetivo

Desenvolver um sistema de gerenciamento de reservas de salas utilizando conceitos de **orientação a objetos** e **banco de dados**, com suporte às funcionalidades de CRUD e exibição de um calendário de ocupação.

---

## Funcionalidades

- **CRUD de Reservas**: Cadastro, visualização, edição e exclusão de reservas.
- **Tabela de Reservas**:
  - Exibe as reservas registradas no sistema.
  - Suporte à edição direta por meio de um grid com `TableView`.

---

## Estrutura do Banco de Dados

Tabela: **reserva**

| Campo            | Tipo           | Descrição                                       |
|-------------------|----------------|-------------------------------------------------|
| `id`             | INT (PK)       | Identificador único da reserva.                |
| `numeroSala`     | VARCHAR        | Número da sala reservada.                      |
| `curso`          | VARCHAR        | Nome do curso que utilizará a sala.            |
| `disciplina`     | VARCHAR        | Nome da disciplina associada à reserva.        |
| `professor`      | VARCHAR        | Nome do professor responsável pela reserva.    |
| `data`           | VARCHAR        | Data da reserva.                               |
| `horarioEntrada` | VARCHAR        | Horário de início da reserva.                  |
| `horarioSaida`   | VARCHAR        | Horário de término da reserva.                 |
| `informatica`    | BOOLEAN        | Indica se a sala precisa de equipamentos.      |
| `turno`          | VARCHAR        | Turno da reserva: `manhã`, `tarde`, ou `noite`.|

---

## Tecnologias Utilizadas

- **Linguagem de Programação**: Java
- **Interface Gráfica**: JavaFX
- **IDE**: IntelliJ IDEA
- **Banco de Dados**: MySQL
- **Design Patterns**: Aplicação de conceitos de orientação a objetos.
## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/sistema-reserva-salas.git
   cd sistema-reserva-salas
2. **Abra o projeto no IntelliJ IDEA**:
   - **Adicione o mysql conector em "dependences" nas configurações do projeto**
   - **Importe a tabela "reserva" na database "javadb"**
