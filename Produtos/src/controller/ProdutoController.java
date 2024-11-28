package controller;

import Produtos.model.Produto;
import Produtos.service.ProdutoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return service.criarProduto(produto);
    }

    @GetMapping
    public List<EntityModel<Produto>> listarProdutos() {
        return service.listarProdutos().stream()
                .map(produto -> EntityModel.of(produto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).buscarProduto(produto.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Produto> buscarProduto(@PathVariable Long id) {
        Produto produto = service.buscarProdutoPorId(id);
        return EntityModel.of(produto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).listarProdutos()).withRel("produtos"));
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return service.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        service.deletarProduto(id);
    }
}

