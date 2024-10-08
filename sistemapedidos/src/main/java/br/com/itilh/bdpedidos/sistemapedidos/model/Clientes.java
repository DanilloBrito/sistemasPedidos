package br.com.itilh.bdpedidos.sistemapedidos.model;

import java.math.BigInteger;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_clientes")

public class Clientes {

    @Id
    @SequenceGenerator(name = "seqClientes", sequenceName = "tb_clientes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClientes") 
    private BigInteger id;
    
    @ManyToOne
    @JoinColumn(name = "id_municipio")
    private Municipios municipio;

    @Column(name = "tx_nome_razao_social")
    private String nomeSocial;

    @Column(name = "tx_cpf")
    private String cpf;

    @Column(name = "tx_endereco")
    private String endereco;

    @Column(name = "tx_bairro")
    private String bairro;

    @Column(name = "tx_cep")
    private String cep;

    @Column(name = "tx_email")
    private String email;

    @Column(name = "bo_ativo")
    private Boolean ativo;

    @Column(name = "tx_informacoes")
    private String informacoes;

    @Column(name = "tx_cnpj")
    private String cnpj;


}
