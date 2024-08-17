package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.itilh.bdpedidos.sistemapedidos.model.Estados;
import br.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;

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
public class EstadosController {

    private final EstadoRepository repositorio;
    


    public EstadosController(EstadoRepository repositorio){
        super();
        this.repositorio = repositorio;

    }

    @GetMapping("/estados")
    public List<Estados> getTodos() {
        return  (List<Estados>) repositorio.findAll();                   
    }

    @GetMapping("/estado/{id}")
    public Estados getPorId(@PathVariable BigInteger id) throws Exception {
       return repositorio.findById(id).orElseThrow(
           () -> new Exception("ID invalido")
        );
    }

    



    @PostMapping("/estado")
    public Estados postEstado(@RequestBody Estados entity) throws Exception {
         try{
        return repositorio.save(entity);
         }
         catch(Exception e){
            throw new Exception("Erro ao Salvar o Estado");
         } 
    }
    @PutMapping("estado/{id}")
    public Estados putAlterarEstado(@PathVariable BigInteger id, @RequestBody Estados novosDados) throws Exception {
        
        Optional<Estados> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()){
            //Atribuir novo nome ao objeto já existente no banco de dados
            estadoArmazenado.get().setNome(novosDados.getNome());
            return repositorio.save(estadoArmazenado.get());


        }
        throw new Exception("Alteração não realizada");


    
    }



    @DeleteMapping("/estado/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Estados> estadoArmazenado = repositorio.findById(id);
        if(estadoArmazenado.isPresent()){

            repositorio.delete(estadoArmazenado.get());
            return "Excluido";

        }
        throw new Exception("ID não encontrado para exclusão");
        


}
}
