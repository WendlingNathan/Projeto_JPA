package br.edu.unoesc.testejpa.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(name = "data_nascimento", nullable = false)
	private java.sql.Date dataNascimento;
	
	@Column(precision = 12, scale = 2, nullable = false)
	private BigDecimal salario;
	
	@ManyToOne
	private Departamento depto;
	
	public Pessoa() { }

	public Pessoa(String nome, Date dataNascimento,	BigDecimal salario, Departamento departamento) {
		this(nome, dataNascimento, salario);
		this.depto = departamento;
	}
	
	public Pessoa(String nome, Date dataNascimento,	BigDecimal salario) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
	}

	public Departamento getDepartamento() { return depto; }
	public void setDepartamento(Departamento departamento) { this.depto = departamento; }

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public java.sql.Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(java.sql.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento 
				+ ", salario=" + salario
				+ ", departamento=" + depto + "]";
	}

	
}