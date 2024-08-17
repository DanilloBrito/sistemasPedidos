package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Clientes;
import br.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
public class ClienteController {

    private final ClienteRepository repositorio;

    public ClienteController(ClienteRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Clientes> getClientes() {
        return (List<Clientes>) repositorio.findAll();
    }
    @GetMapping("/cliente/{id}")
    public Clientes getPorId(@PathVariable BigInteger id) throws Exception{
        return repositorio.findById(id).orElseThrow(
        () -> new Exception("ID Invalido")

        );
    }
    @PostMapping("path")
    public Clientes postCliente(@RequestBody Clientes entity) throws Exception {
       try{
        return repositorio.save(entity);
       }catch(Exception ex){
        throw new Exception("Não foi possivel criar o municipio o cliente");
       }
        
 
    }
    
    @PutMapping("/cliente/{id}")
    public Clientes putAlterarClientes(@PathVariable BigInteger id, @RequestBody Clientes novoCliente) throws Exception {
       Optional<Clientes> clienteArmazenado = repositorio.findById(id);
       if (clienteArmazenado.isPresent()){
        clienteArmazenado.get().setBairro(novoCliente.getBairro());
        clienteArmazenado.get().setInformacoes(novoCliente.getInformacoes());
        clienteArmazenado.get().setCpf(novoCliente.getCpf());   
        clienteArmazenado.get().setCep(novoCliente.getCep());
        clienteArmazenado.get().setEmail(novoCliente.getEmail());
        clienteArmazenado.get().setEndereco(novoCliente.getEndereco());
        return repositorio.save(clienteArmazenado.get());
        


       }
        throw new Exception("Alteração não realizada");
    }

     @DeleteMapping("/cliente/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {
        try{
            repositorio.deleteById(id);
            return "Excluido";
        }catch (Exception ex){
            throw new Exception("Não foi possivel excluir o id informado"+ ex.getMessage());
        }
    } 



}
