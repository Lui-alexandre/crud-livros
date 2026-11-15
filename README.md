# CRUD de Livros — Spring Boot, JPA e MySQL

Atividade prática de Projeto de Software e Segurança da Informação (UMC).
Integrante: Luis Alexandre

## Como rodar

1. Abra o **XAMPP** e inicie **MySQL** e **Apache**.
2. No **phpMyAdmin** (http://localhost/phpmyadmin), crie o banco:

   ```sql
   CREATE DATABASE db_livros CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. A senha do banco NÃO fica no código. O `application.properties` lê as variáveis de ambiente
   `DB_USER` (padrão `root`) e `DB_PASSWORD` (padrão vazio, que é o padrão do XAMPP).
   Se o seu MySQL tiver senha, defina antes de rodar:

   - Windows (PowerShell): `$env:DB_PASSWORD="sua_senha"`
   - Linux/Mac: `export DB_PASSWORD="sua_senha"`
   - Na IDE (IntelliJ/Eclipse/STS): Run Configuration > Environment variables > `DB_PASSWORD=sua_senha`

4. Rode o projeto:

   ```bash
   mvn spring-boot:run
   ```

   Ou execute `CrudLivrosApplication` pela IDE.

5. Acesse http://localhost:8080/livros

O Hibernate cria a tabela `livros` automaticamente (`ddl-auto=update`).

## Estrutura

```
model/       Livro.java            entidade JPA + Bean Validation
repository/  LivroRepository.java  JpaRepository + Query Method
service/     LivroService.java     regras de negócio
controller/  LivroController.java  rotas listar, novo, salvar, editar, excluir
templates/livros/  lista.html, form.html   (Thymeleaf)
static/css/        estilo.css
```

## Rotas

| Método | Rota                  | Ação                                  |
|--------|-----------------------|---------------------------------------|
| GET    | /livros               | Lista (aceita ?busca=texto)           |
| GET    | /livros/novo          | Formulário vazio                      |
| POST   | /livros/salvar        | Salva (cadastro/edição) e redireciona |
| GET    | /livros/editar/{id}   | Formulário preenchido                 |
| POST   | /livros/excluir/{id}  | Exclui e redireciona                  |
