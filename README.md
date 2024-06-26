# DScommerce 
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/fabiosimplis/DEV-dscommerce-back-end/blob/main/LICENSE) 

# Sobre o projeto

DScommerce é uma aplicação back end web construída em conjunto ao curso **Java Spring Professional**, da plataforma [DevSuperior](https://devsuperior.com "Site da DevSuperior").

O sistema deve manter um cadastro de usuário, produtos e suas categorias. Cada usuário possui nome, email, telefone, data de nascimento e uma senha de acesso. Os dados dos produtos são: nome, descrição, preço e imagem. 
O sistema deve apresentar um catálogo de produtos, os quais podem ser filtrados pelo nome do produto. 
A partir desse catálogo, o usuário pode selecionar um produto para ver seus detalhes e para decidir se o adiciona a um carrinho de compras. 
O usuário pode incluir e remover itens do carrinho de compra, bem como alterar as quantidades de cada item. 
Uma vez que o usuário decida encerrar o pedido, o pedido deve então ser salvo no sistema com o status de "aguardando pagamento". 
Os dados de um pedido são: instante em que ele foi salvo, status, e uma lista de itens, onde cada item se refere a um produto e sua quantidade no pedido. 
O status de um pedido pode ser: aguardando pagamento, pago, enviado, entregue e cancelado. Quando o usuário paga por um pedido, o instante do pagamento deve ser registrado. 
Os usuários do sistema podem ser clientes ou administradores, sendo que todo usuário cadastrado por padrão é cliente. Usuários não identificados podem se cadastrar no sistema, navegar no catálogo de produtos e no carrinho de compras. 
Clientes podem atualizar seu cadastro no sistema, registrar pedidos e visualizar seus próprios pedidos. Usuários administradores tem acesso à área administrativa onde pode acessar os cadastros de usuários, produtos e categorias.

## Protótipos de tela
[FIGMA](https://www.figma.com/file/ZrGNVNG0kZL6txDv4G8P6s/DSCommerce)

## Modelo conceitual
![Modelo Conceitual](https://github.com/fabiosimplis/DEV-dscommerce-back-end/blob/main/assets/Modelo_Conceitual.png).

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- SQL
- Maven
- JWT


# Como executar o projeto

## Back end
Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/fabiosimplis/DEV-dscommerce-back-end.git

# entrar na pasta do projeto back end
cd dscommerce

# executar o projeto
./mvnw spring-boot:run
```



# Autor

Fábio S. S. Júnior

https://www.linkedin.com/in/fabio-simplis/

