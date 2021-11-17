package fr.eservices.drive.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.model.Perishable;

public class CatalogDaoJPAImpl implements CatalogDao {
	private EntityManager em;
	private CriteriaBuilder cb ;
	public CatalogDaoJPAImpl(EntityManager em) {
		this.em = em;
		this.cb = em.getCriteriaBuilder();
	}

	@Override
	public List<Category> getCategories() {
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> category = cq.from(Category.class);
		cq.select(category).orderBy(cb.asc(category.get("orderIdx")));
		List<Category> categories = em.createQuery(cq).getResultList();

		return categories;
	}

	@Override
	public List<Category> getArticleCategories(int id) throws DataException {
		//get les categories de l'article en fonction de son id
		/**
		 * List categories associated to an article
		 *
		 * @return categories of article, can be empty
		 * @throws DataException if article does not exist
		 */
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Article> articleRootCount = cqCount.from(Article.class);
		cqCount.select(cb.count(articleRootCount)).where(cb.equal(articleRootCount.get("id"),id));
		Long count = em.createQuery(cqCount).getSingleResult();

		if (count == 0){throw new DataException("no article found for this id");}
		else {
			CriteriaQuery<Category> cq = cb.createQuery(Category.class);
			Root<Article> articleRoot = cq.from(Article.class);
			cq.select(articleRoot.get("categories")).where(cb.equal(articleRoot.get("id"),id));
			return em.createQuery(cq).getResultList();
		}
	}

	@Override
	public List<Article> getCategoryContent(int categoryId) throws DataException {
		CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
		Root<Category> categoryRootCount = cqCount.from(Category.class);
		cqCount.select(cb.count(categoryRootCount)).where(cb.equal(categoryRootCount.get("id"),categoryId));
		Long count = em.createQuery(cqCount).getSingleResult();

		if (count == 0){throw new DataException("no category found for this id");}
		else {
			//get les categories de l'article en fonction de son id
			CriteriaQuery<Article> cq = cb.createQuery(Article.class);
			Root<Article> articleRoot = cq.from(Article.class);
			Join<Article, Category> categoryJoin = articleRoot.join("categories");
			cq.select(articleRoot).where(cb.equal(categoryJoin.get("id"), categoryId));
			return em.createQuery(cq).getResultList();
		}
	}

	@Override
	public List<Perishable> getPerished(Date day) throws DataException {
		throw new DataException("une coquille s'est glissée dans le TP !! XD");
	}
	
}
