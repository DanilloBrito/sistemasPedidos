package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigInteger;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString



@Entity
@Table(name = "tb_produtos")


public class Produtos {


    @Id
    @SequenceGenerator(name = "seqProduto", sequenceName = "tb_produtos_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqProduto") 
    private BigInteger id;

    @Column(name = "tx_descricao")
    private String descricao;

    @Column(name = "dbl_quantidade_estoque")
    private Double quantidade_estoque;

    @Column(name = "nu_preco_unidade_atual") 
    private Double preco_unidade_atual;

    @Column(name = "bo_ativo")
    private Boolean ativo;

  
    
}
