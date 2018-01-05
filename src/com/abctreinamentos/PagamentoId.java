package com.abctreinamentos;

// Generated Jan 31, 2017 4:01:41 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PagamentoId generated by hbm2java
 */
@Embeddable
public class PagamentoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long cpf;
	private long cdCurso;

	public PagamentoId() {
	}

	public PagamentoId(long cpf, long cdCurso) {
		this.cpf = cpf;
		this.cdCurso = cdCurso;
	}

	@Column(name = "CPF", nullable = false, precision = 22, scale = 0)
	public long getCpf() {
		return this.cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Column(name = "cdCurso", nullable = false, precision = 22, scale = 0)
	public long getcdCurso() {
		return this.cdCurso;
	}

	public void setcdCurso(long cdCurso) {
		this.cdCurso = cdCurso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PagamentoId))
			return false;
		@SuppressWarnings("unused")
		PagamentoId castOther = (PagamentoId) other;

		return true;
	}

	public int hashCode() {
		int result = 17;

		return result;
	}

}