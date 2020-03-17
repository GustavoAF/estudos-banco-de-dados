package com.estudos.banco.spring.estudosbancodedados.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                       //É utilizada para informar o nome da tabela relacionada a essa entidade no banco de dados;
@Table(name = "tb_Cozinha")   //Indica que a classe será mapeada e passará a ser gerenciada pela JPA
public class Cozinha {
	
	@Id // Indica que o atributo da classe será gerenciado pela JPA e terá o seu valor automaticamente incrementado;
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Permite que o banco utilize a estratégia de gerar automaticamente os campos chave da tabela.
	private Long id;
	
	@Column(name = "descricao", length = 30)  //Nos possibilita fazer algumas configurações, como o nome da coluna no banco de dados, 
	private String nome;                      //tamanho da coluna, obrigatoriedade de campos, etc. 
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Cozinha other = (Cozinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
