package br.edu.unoesc.testejpa.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargos")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String nome;

	public Cargo() { }
	public Cargo(String nome) { this.nome = nome; }
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	@Override
	public String toString() {
		return "Cargo [id=" + id + ", nome=" + nome + "]";
	}
}