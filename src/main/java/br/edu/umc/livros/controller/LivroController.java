package br.edu.umc.livros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.umc.livros.model.Livro;
import br.edu.umc.livros.service.LivroService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    // Injeção de dependência por construtor
    public LivroController(LivroService service) {
        this.service = service;
    }

    // LISTAR (com busca opcional por título)
    @GetMapping
    public String listar(@RequestParam(required = false) String busca, Model model) {
        model.addAttribute("livros", service.listar(busca));
        model.addAttribute("busca", busca);
        return "livros/lista";
    }

    // NOVO: abre o formulário vazio
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    // SALVAR: serve para cadastro e edição (padrão POST/Redirect/GET)
    @PostMapping("/salvar")
    public String salvar(@Valid Livro livro, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "livros/form";
        }
        boolean novo = livro.getId() == null;
        service.salvar(livro);
        redirect.addFlashAttribute("mensagem",
                novo ? "Livro cadastrado com sucesso." : "Livro atualizado com sucesso.");
        return "redirect:/livros";
    }

    // EDITAR: carrega o livro e reaproveita o formulário
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        return service.buscarPorId(id)
                .map(livro -> {
                    model.addAttribute("livro", livro);
                    return "livros/form";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("mensagem", "Livro não encontrado.");
                    return "redirect:/livros";
                });
    }

    // EXCLUIR
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("mensagem", "Livro excluído.");
        return "redirect:/livros";
    }
}
