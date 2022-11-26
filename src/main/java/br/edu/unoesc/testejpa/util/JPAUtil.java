package br.edu.unoesc.testejpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("unoesc-devti");
	
	private JPAUtil() { }
	
	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	
	public static void closeEntityManagerFactory() {
		EMF.close();
	}
}
