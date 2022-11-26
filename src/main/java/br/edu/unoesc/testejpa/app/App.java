package br.edu.unoesc.testejpa.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.edu.unoesc.testejpa.modelo.Pessoa;
import br.edu.unoesc.testejpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class App {
	private static EntityManager em;
	
	private List<Pessoa> buscarTodos() {
		String jpql = "SELECT p FROM Pessoa p";
		
		return em.createQuery(jpql, Pessoa.class).getResultList();
	}
	
	private Pessoa buscarPorId(Integer id) {
		return em.find(Pessoa.class, id);
	}
	
	private void adicionar() {
		Pessoa pessoa1 = new Pessoa("Fulano",
				 				    java.sql.Date.valueOf(LocalDate.now()),
				 				    new BigDecimal("200.0"));
		
		Pessoa pessoa2 = new Pessoa("Beltrano",
				    				java.sql.Date.valueOf("2022-12-25"),
				    				new BigDecimal("300.0"));
		
		Pessoa pessoa3 = new Pessoa("Sicrano",
									java.sql.Date.valueOf("2022-12-25"),
									new BigDecimal("400.0"));

		em.getTransaction().begin();
		em.persist(pessoa1);
		em.persist(pessoa2);
		em.persist(pessoa3);
		em.getTransaction().commit();
	}
	
	private List<Pessoa> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Pessoa p WHERE p.nome LIKE :nome";
		
		return em.createQuery(jpql, Pessoa.class)
				  .setParameter("nome", "%" + nome + "%")
				  .getResultList();
	}
	
	private void excluir(Integer id) {
		Pessoa pessoa = this.buscarPorId(id);
		
		em.getTransaction().begin();
		em.remove(pessoa);
		em.getTransaction().commit();
	}
	
	private void alterar(Integer id) {
		Pessoa pessoa = this.buscarPorId(id);
		
		pessoa.setNome("Fulana");
		System.out.println(pessoa);
		
		em.getTransaction().begin();
		em.getTransaction().commit();
	}
	
	private void testarMerge(Integer id) {
		Pessoa pessoa = this.buscarPorId(id);
		em.clear();
		
		pessoa = em.merge(pessoa);
		pessoa.setNome("Beltrana");
		
		em.getTransaction().begin();
		em.getTransaction().commit();
	}

	public static void main(String[] args) {
		App app = new App();
		
		// Recupera o gerenciador de entidades
		em = JPAUtil.getEntityManager();
		
		app.adicionar();
		app.alterar(1);
		app.testarMerge(2);
		//app.excluir(2);
		
		List<Pessoa> lista = app.buscarPorNome("ano");
		
		for (Pessoa p : lista) {
			System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getSalario());
		}
		
//		Pessoa pessoa = app.buscarPorId(1);
//		if (pessoa == null) {
//			System.out.println("Pessoa não existe!");
//		} else {
//			System.out.println(pessoa.getNome());
//		}
		
//		List<Pessoa> lista = app.buscarTodos();
//		
//		for (Pessoa p : lista) {
//			System.out.println(p.getNome() + " - " + p.getSalario());
//		}
	
//		for (int i = 0; i < lista.size(); i++) {
//			System.out.println(lista.get(i).getNome() + " - " + lista.get(i).getSalario());
//		}
		
//		lista.forEach(System.out::println);
//		lista.forEach(p -> System.out.println(p.getNome() + " - " + p.getSalario()));
		
		// Fechar o gerenciador de entidades
		em.close();
		JPAUtil.closeEntityManagerFactory();
	}

}
