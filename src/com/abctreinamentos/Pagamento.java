package com.abctreinamentos;

// Generated Jan 31, 2017 4:01:41 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Pagamento generated by hbm2java
 */
@Entity
@Table(name = "PAGAMENTO")
public class Pagamento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PagamentoId id;
	private String dataInscricao;

	public Pagamento() {
	}

	public Pagamento(PagamentoId id, String dataInscricao) {
		this.id = id;
		this.dataInscricao = dataInscricao;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "cpf", column = @Column(name = "CPF", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "cdCurso", column = @Column(name = "CDCURSO", nullable = false, precision = 22, scale = 0)) })
	public PagamentoId getId() {
		return this.id;
	}

	public void setId(PagamentoId id) {
		this.id = id;
	}

	@Column(name = "dataInscricao", nullable = false, length = 10)
	public String getdataInscricao() {
		return this.dataInscricao;
	}

	public void setdataInscricao(String dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@Override
	public String toString() {
		return "Pagamento [cpf="+id.getCpf()+" cdcurso="+id.getcdCurso()+" data_inscricao="+dataInscricao+"]";
	}
}
