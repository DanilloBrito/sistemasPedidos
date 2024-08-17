package br.com.itilh.bdpedidos.sistemapedidos.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import br.com.itilh.bdpedidos.sistemapedidos.model.Produtos;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

@RestController
public class ProdutoController { 


    private final ProdutoRepository repositorio;

    public ProdutoController(ProdutoRepository repositorio){
        super(); 
        this.repositorio = repositorio;

    }

@GetMapping("/produtos")
    public List<Produtos> getTodos() { 
        return  (List<Produtos>) repositorio.findAll();                   
    }

    @GetMapping("/produto/{id}")
    public Produtos getPorId(@PathVariable BigInteger id) throws Exception {
       return (Produtos) repositorio.findById(id).orElseThrow(
           () -> new Exception("ID invalido")
        );
    }

    



    @PostMapping("/produto")
    public Produtos postProduto(@RequestBody Produtos entity) throws Exception {
         try{
        return repositorio.save(entity);
         }
         catch(Exception e){
            throw new Exception("Erro ao Salvar o Estado");
         } 
    }
  
    @PutMapping("/produto/{id}")
    public Produtos putProduto(@PathVariable BigInteger id, @RequestBody Produtos novosProdutos) throws Exception {
        Optional<Produtos> produtoArmazenamento = repositorio.findById(id);
        if(produtoArmazenamento.isPresent()){
            (produtoArmazenamento.get()).setDescricao((novosProdutos).getDescricao());
            (produtoArmazenamento.get()).setPreco_unidade_atual((novosProdutos).getPreco_unidade_atual());
            (produtoArmazenamento.get()).setQuantidade_estoque((novosProdutos).getQuantidade_estoque());
            return repositorio.save(produtoArmazenamento.get());
        }
        
        throw new Exception("Alteração não realizada");

    }



    @DeleteMapping("/produto/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        try{
            repositorio.deleteById(id);
            return "Excluido";

        }catch (Exception ex){
            throw new Exception("Não foi possivel excluir o id informado" + ex.getMessage());
        }


    }








}
