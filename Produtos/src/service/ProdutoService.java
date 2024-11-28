package service;


import Produtos.model.Produto;
import Produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criarProduto(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = buscarProdutoPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setTipo(produtoAtualizado.getTipo());
        produto.setClassificacao(produtoAtualizado.getClassificacao());
        produto.setTamanho(produtoAtualizado.getTamanho());
        produto.setPreco(produtoAtualizado.getPreco());
        return repository.save(produto);
    }

    public void deletarProduto(Long id) {
        repository.deleteById(id);
    }
}

