# Documentação da Solução Sbornia

## Descrição da Solução

O projeto Sbornia é uma aplicação Java desenvolvida para gerenciar vendas de produtos, incluindo usuários, produtos categorizados, vendas e itens de venda. A aplicação utiliza JPA (Java Persistence API) para persistência de dados, com um design orientado a objetos que separa responsabilidades em camadas: modelo (entidades), repositório (acesso a dados), serviço (lógica de negócio) e aplicação principal.

### Funcionalidades Principais
- **Gerenciamento de Usuários**: Cadastro de usuários com informações pessoais, incluindo cálculo de idade para benefícios fiscais.
- **Gerenciamento de Produtos**: Produtos com código, descrição, estoque, preço e categoria.
- **Vendas**: Registro de vendas associadas a usuários, com itens de venda referenciando produtos.
- **Cálculo de Impostos**: Serviço para calcular impostos com base em regras específicas (ex.: desconto para usuários com mais de 60 anos ou dependentes).

A aplicação é construída com Maven e utiliza Java 8 (usar versões mais recentes estava dando bugs).

## Padrões de Design Utilizados

A arquitetura da aplicação segue vários padrões de design para promover manutenibilidade, testabilidade e separação de responsabilidades:

1. **Padrão Repository**: 
   - Interfaces como `UsuarioRepository`, `ProdutoRepository`, etc., definem contratos para acesso a dados.
   - Implementações como `UsuarioRepositoryImpl` fornecem a lógica concreta usando JPA.
   - Benefício: Abstrai o acesso a dados, facilitando testes e mudanças de tecnologia de persistência.

2. **Padrão Service Layer**:
   - Classes como `VendaService` e `ImpostoService` encapsulam a lógica de negócio.
   - Separa a lógica de aplicação da lógica de domínio e persistência.

3. **Padrão Entity (JPA)**:
   - Classes de modelo como `Usuario`, `Produto`, `Venda` são entidades JPA mapeadas para tabelas de banco de dados.
   - Usa anotações como `@Entity`, `@Id`, `@ManyToOne`, `@OneToMany` para definir relacionamentos.

4. **Padrão Data Transfer Object (DTO)** (implícito):
   - Embora não explicitamente definido, os construtores e getters/setters nas entidades servem como DTOs para transferência de dados.

5. **Padrão Factory** (potencial):
   - O `DataInitializer` pode ser visto como um factory para inicializar dados de exemplo.



### Estrutura do Projeto
- **config/**: Inicialização de dados.
- **model/**: Entidades de domínio (Usuario, Produto, Categoria, Venda, ItemVenda).
- **repository/**: Interfaces e implementações para acesso a dados.
- **service/**: Lógica de negócio (vendas e impostos).
- **App.java**: Ponto de entrada da aplicação.

### Tecnologias Utilizadas
- Java 8 .
- JPA/Hibernate para ORM.
- Maven para gerenciamento de dependências e build.
- JUnit para testes (se aplicável).

Esta documentação descreve a solução atual, foram utilizadas ferramentas de LLM para facilitar a cração de codigo. apesar de não mostrar no diagrama para não ficar completamente poluido as classes de "service" utilizam partes de "model".