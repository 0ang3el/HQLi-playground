package hqli.persistent;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@LocalBean
public class PostRepository {

	@PersistenceContext(unitName = "HQLiDS")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Post post) {
		em.persist(post);
	}

	public List<Post> getByName_Insecure(String name) {
		Query query = em.createQuery("SELECT p FROM Post p where p.name='" + name + "'", Post.class);
		return (List<Post>) query.getResultList();
	}

	public List<Post> getByName_Secure(String name) {
		Query query = em.createQuery("SELECT p FROM Post p where p.name=:name", Post.class);
		query.setParameter("name", name);
		return (List<Post>) query.getResultList();
	}
	
	public List<Post> getByName_Criteria(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
		Root<Post> pRoot = criteria.from(Post.class);
		criteria.where(builder.equal(pRoot.get("name"), name));
		
		Query query = em.createQuery(criteria);
		return (List<Post>) query.getResultList();
	}
	
}
