package com.abctreinamentos;

// Generated Jan 31, 2017 4:01:41 PM by Hibernate Tools 4.3.1
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Curso generated by hbm2java
 */
@Entity
@Table(name = "CURSO")
public class Curso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long cdCurso;
	private String nome;
	private Double valor;
	private String url;

	public Curso() {
	}

	public Curso(long cdCurso, String nome, Double valor, String url) {
		this.cdCurso = cdCurso;
		this.nome = nome;
		this.valor = valor;
		this.url = url;
	}

	@Id
	@Column(name = "cdCurso", unique = true, nullable = false, precision = 22, scale = 0)
	public long getcdCurso() {
		return this.cdCurso;
	}

	public void setcdCurso(long cdCurso) {
		this.cdCurso = cdCurso;
	}

	@Column(name = "NOME", nullable = false, length = 80)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "VALOR", nullable = false, precision = 22, scale = 0)
	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Column(name = "URL", nullable = false, length = 80)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Curso [cdCurso="+cdCurso+" nome="+nome+" valor="+valor+" url="+url+"]";
	}
}