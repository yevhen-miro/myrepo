package de.hydro.gv.orgpm.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.hydro.gv.orgpm.auth.Menu;

@Stateless
public class MenuDao {

	// @Inject
	// @LoggedInUser
	// private Person user;

	@PersistenceContext
	EntityManager em;

	public de.hydro.gv.orgpm.auth.Menu findById( Long id ) {
		return this.em.find( de.hydro.gv.orgpm.auth.Menu.class, id );
	}

	public Menu findByName( String name ) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Menu> criteria = cb.createQuery( Menu.class );
		Root<Menu> menu = criteria.from( Menu.class );
		criteria.select( menu ).where( cb.equal( menu.get( "name" ), name ) );
		return this.em.createQuery( criteria ).getSingleResult();
	}

	public List<Menu> findAllOrderedByName() {
		String sql = "SELECT m FROM Menu m, MenuRollen mr " + "		WHERE m.id = mr.menu "
				+ "			AND mr.rolle IN (:rollen) " + "		ORDER BY m.name ";
		Query q = this.em.createQuery( sql );
		// q.setParameter("rollen", user.allUserRoles());
		return q.getResultList();
	}

	public List<Menu> findAllOrderedByPosition() {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Menu> criteria = cb.createQuery( Menu.class );
		Root<Menu> menu = criteria.from( Menu.class );
		criteria.select( menu ).orderBy( cb.asc( menu.get( "position" ) ) );
		return this.em.createQuery( criteria ).getResultList();
	}

	public List<Menu> findAllRoot() {
		String sql = "SELECT m FROM Menu m, MenuRollen mr " + "		WHERE m.id = mr.menu "
				+ "			AND mr.rolle IN (:rollen) " + "			AND m.vorgaenger IS NULL " + "		ORDER BY m.name ";
		Query q = this.em.createQuery( sql );
		// q.setParameter("rollen", user.allUserRoles());
		return q.getResultList();
	}

	public Menu getEntityByName( String _name ) {
		// TODO Auto-generated method stub
		return null;
	}

}
