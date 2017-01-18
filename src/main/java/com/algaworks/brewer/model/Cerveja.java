package com.algaworks.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name="cerveja")
public class Cerveja implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String sku;
	private String nome;
	private String descricao;
	private String foto;
	private String contentType;
	private BigDecimal valor;
	private BigDecimal teorAlcoolico;
	private BigDecimal comissao;
	private Integer quantidadeEstoque;
	private Origem origem;
	private Sabor sabor;
	private Estilo estilo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@SKU
	@NotBlank(message="O sku é obrigatório")
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
	@NotBlank(message="O nome é obrigatório")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank(message="A descrição é obrigatória")
	@Size(max=250 , message="A Descrição deve possuir no maximo 250 caracteres")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@Column(name = "content_type")
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@NotNull(message="O valor da cerveja é obrigatório")
	@DecimalMax(value="9999999.99" , message="O valor não pode ser maior do que R$ 9.999.999.99")
	@DecimalMin(value="1.00",message="O valor não pode ser menor que R$ 1,00")
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@NotNull(message="O teor alcóolico é obrigatório")
	@DecimalMax(value="100.0" , message="O valor do teor alcóolico deve ser menor que 100")
	@Column(name="teor_alcoolico")
	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}
	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}
	
	@NotNull(message="A comissão é obrigatória")
	@DecimalMax(value="100.0" , message="A comissão deve ser igual ou menor que 100")
	public BigDecimal getComissao() {
		return comissao;
	}
	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}
	
	@NotNull(message="A quantidade em estoque é obrigatória")
	@Max(value= 999999 ,message="A quantidade em estoque deve ser menor que 999.999")
	@Column(name="quantidade_estoque")
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	@NotNull(message="O origem é obrigatória")
	@Enumerated(EnumType.STRING)
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	
	@NotNull(message="O sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	
	@NotNull(message="O estilo é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_estilo")
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public String getFotoOuMock(){
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate(){
		this.sku = sku.toUpperCase();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
		
	
}
