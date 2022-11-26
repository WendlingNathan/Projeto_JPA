package br.edu.unoesc.testejpa.app;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.unoesc.testejpa.modelo.Departamento;
import br.edu.unoesc.testejpa.modelo.Pessoa;
import br.edu.unoesc.testejpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class App2 {
	private static EntityManager em;
	
	public static void main(String[] args) {
		em = JPAUtil.getEntityManager();

		Departamento d1 = new Departamento("TI");
		Departamento d2 = new Departamento("Marketing");
		
		Pessoa pessoa1 = new Pessoa("Fulano",
								    java.sql.Date.valueOf(LocalDate.now()),
								    new BigDecimal("200.0"), d1);

		Pessoa pessoa2 = new Pessoa("Beltrano",
									java.sql.Date.valueOf("2022-12-25"),
									new BigDecimal("300.0"), d2);
		
		Pessoa pessoa3 = new Pessoa("Sicrano",
									java.sql.Date.valueOf("2022-12-25"),
									new BigDecimal("400.0"), d1);
		
//		System.out.println(pessoa2.getNome() + " - " + pessoa2.getDepartamento().getNome());
		
		em.getTransaction().begin();
		em.persist(d1);
		em.persist(d2);
		
		em.persist(pessoa1);
		em.persist(pessoa2);
		em.persist(pessoa3);
		em.getTransaction().commit();
		
		System.out.println(em.find(Pessoa.class, 2));
		
//		String jpql = "SELECT p FROM Pessoa p WHERE p.departamento.nome = :nome";
//		List<Pessoa> pessoas = em.createQuery(jpql, Pessoa.class)
//								 .setParameter("nome", "TI")
//								 .getResultList();
//		
//		if (pessoas.isEmpty()) {
//			System.out.println("Lista vazia");
//		} else {
//			for (Pessoa pessoa : pessoas) {
//				System.out.println(pessoa.getNome() + " - " + pessoa.getDepartamento().getNome());
//			}
//		}
		
//		Departamento dep = em.find(Departamento.class, 1);
//		String jpql = "SELECT p FROM Pessoa p WHERE p.departamento = :departamento";
//		List<Pessoa> pessoas = em.createQuery(jpql, Pessoa.class)
//								 .setParameter("departamento", dep)
//								 .getResultList();
//		
//		if (pessoas.isEmpty()) {
//			System.out.println("Lista vazia");
//		} else {
//			System.out.println("\nPessoa\t\tDepartamento");
//			System.out.println("----------------------------");
//			pessoas.forEach(p -> System.out.println(p.getNome() + "\t\t" + p.getDepartamento().getNome()));
//		}
		
		String jpql = "SELECT p.salario FROM Pessoa p WHERE p.id = :id";
		BigDecimal salario = em.createQuery(jpql, BigDecimal.class)
						  		.setParameter("id", 1)
						  		.getSingleResult();
		System.out.println("Salário do Fulano: " + salario);
		
		em.close();
		JPAUtil.closeEntityManagerFactory();
	}

}