package br.edu.umc.livros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 2, max = 120, message = "O título deve ter entre 2 e 120 caracteres.")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório.")
    @Size(min = 2, max = 80, message = "O autor deve ter entre 2 e 80 caracteres.")
    private String autor;

    @NotNull(message = "O ano é obrigatório.")
    @Min(value = 1450, message = "O ano deve ser maior ou igual a 1450.")
    @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100.")
    private Integer ano;

    // Construtor vazio: obrigatório para o JPA
    public Livro() {
    }

    public Livro(String titulo, String autor, Integer ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
}
