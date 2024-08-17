package br.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;


import br.com.itilh.bdpedidos.sistemapedidos.model.Municipios;
import br.com.itilh.bdpedidos.sistemapedidos.repository.MunicipioRepository;

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
public class MunicipioController {

    private final MunicipioRepository repositorio;


    public MunicipioController(MunicipioRepository repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/municipios")
    public List<Municipios> getMunicipios() { 
        return (List<Municipios>) repositorio.findAll();
    }
    
    @GetMapping("/municipio/{id}")
    public Municipios getPorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
            () -> new Exception("ID Invalido")
        );
    }
    @PostMapping("/municipio")
    public Municipios postMunicipio(@RequestBody Municipios entity) throws Exception {
        try{
        return repositorio.save(entity);
        }catch(Exception ex){
        throw new Exception("Não foi possivel criar o municipio");
        }
    }
    @PutMapping("/Municipio/{id}")
    public Municipios putAlterarMunicipios(@PathVariable BigInteger id, @RequestBody Municipios novosMunicipios) throws Exception {
        Optional<Municipios> municipioArmazenado = repositorio.findById(id);
        if(municipioArmazenado.isPresent()){
            municipioArmazenado.get().setNome(novosMunicipios.getNome());
            return repositorio.save(municipioArmazenado.get());


        }
        throw new Exception("Alteração não realizada");

        
        
    }

    @DeleteMapping("/municipio/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        try{
            repositorio.deleteById(id);
            return "Excluido";

        }catch (Exception ex){
            throw new Exception("Não foi possivel excluir o id informado" + ex.getMessage());
        }


    }
}
