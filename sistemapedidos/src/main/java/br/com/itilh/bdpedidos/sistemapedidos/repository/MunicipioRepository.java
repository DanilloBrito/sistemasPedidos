package br.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.itilh.bdpedidos.sistemapedidos.model.Municipios;

@Repository
public interface MunicipioRepository extends  CrudRepository<Municipios, BigInteger>  {




}
