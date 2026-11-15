package br.edu.umc.livros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.umc.livros.model.Livro;
import br.edu.umc.livros.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository repository;

    // Injeção de dependência por construtor
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listar(String busca) {
        if (busca == null || busca.isBlank()) {
            return repository.findAll();
        }
        return repository.findByTituloContainingIgnoreCase(busca.trim());
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Livro salvar(Livro livro) {
        // Regra de negócio simples: remove espaços extras antes de gravar
        livro.setTitulo(livro.getTitulo().trim());
        livro.setAutor(livro.getAutor().trim());
        return repository.save(livro);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
