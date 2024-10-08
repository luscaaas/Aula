package usuario;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class GerenciarUsuario {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public GerenciarUsuario() {
		emf = Persistence.createEntityManagerFactory("xuxu");
		em = emf.createEntityManager();	
	}
	
	public void criar(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	public List<Usuario> listar(){
		Query query = em.createNativeQuery("select * from tbUsuario", Usuario.class);
		List<Usuario> listaUsuario = query.getResultList();
		return listaUsuario;
		
	}
	
	public void editar(Usuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();	
	}
	
	public void remover(Usuario usuario) {
		em.getTransaction().begin();
		em.remove(usuario);
		em.getTransaction().commit();
	}
	
	public Usuario findById(int id) {
		return em.find(Usuario.class, id);
	}
	
	public void fechar() {
		em.close();
		emf.close();
	}
	
}
