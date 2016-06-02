package de.hydro.gv.mplus.dao;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import de.hydro.gv.mplus.data.CBU;
import de.hydro.gv.mplus.data.Contract;
import de.hydro.gv.mplus.data.ContractItem;
import de.hydro.gv.mplus.data.Customer;
import de.hydro.gv.mplus.data.Plant;
import de.hydro.gv.mplus.data.SystemUser;
import de.hydro.gv.mplus.data.TimeDate;

@RequestScoped
public class TimeDateDao {
	
@PersistenceContext
private EntityManager entityManager;	
	
public TimeDateDao() {
	
}

public TimeDate findDateById (Long pId) {
	return (TimeDate) entityManager.createNamedQuery("date.find.by.id").setParameter("id", pId).getSingleResult();
}

public TimeDate findDateIdByDate (Date pDate) {
	return (TimeDate) entityManager.createNamedQuery("date.find.by.date").setParameter("date", pDate).getSingleResult();
}

public Date findDateIdByTimeDate (TimeDate pTimeDate) {
	return (Date) entityManager.createNamedQuery("date.find.by.timedate").setParameter("timedate", pTimeDate).getSingleResult();
}

}
