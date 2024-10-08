package produto;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import usuario.Usuario;

public class GerenciarProduto {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	public GerenciarProduto(){
		emf = Persistence.createEntityManagerFactory("xuxu");
		em = emf.createEntityManager();
	}
	
	public void criar(Produto produto) {
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
	}
	
	public List<Produto> listar(){
		Query query = em.createNativeQuery("select * from tbProduto", Produto.class);
		List<Produto> listaProduto = query.getResultList();
		return listaProduto;
	}
	
	public void editar(Produto produto) {
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
	}
	
	public void remover(Produto produto) {
		em.getTransaction().begin();
		em.remove(produto);
		em.getTransaction().commit();
	}
	
	public Produto findById(int id) {
		return em.find(Produto.class, id);
	}
	
	public void fechar() {
		em.close();
		emf.close();
	}
	
}
