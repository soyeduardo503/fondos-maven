/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.epsilon.facade;

import java.io.Serializable;

/**
 *
 * @author Zeta
 */
public abstract class AbstractFacadeOld<T> implements Serializable{

    /**
	 * 
	 *//*
	private static final long serialVersionUID = -718948107415874220L;

	private Class<T> entityClass;

    private static EntityManager em;
    
    
    public EntityManager getEntityManager(){
    	if(em==null||!em.isOpen())
    		em=JpaUtil.getEntityManager();
    	return em;
    }
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

 
    public void create(T entity) {
    	try {
			EntityTransaction tx = getEntityManager().getTransaction();
			tx.begin();
			getEntityManager().persist(entity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Didnt save",e);
		}finally{
			getEntityManager().close();
		}
     
    }
    
    public void persis(T entity) {
    	try {
			EntityTransaction tx = getEntityManager().getTransaction();
			tx.begin();
			getEntityManager().persist(entity);
			tx.commit();
			System.out.println(entity);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Didnt save",e);
		}finally{
			//getEntityManager().close();
		}
     
    }
    public void TrasactionCommit(){
    	 getEntityManager().getTransaction().commit();
    	
    }
    
    
    public void save(T entity){
    	try {
			EntityTransaction tx = getEntityManager().getTransaction();
			tx.begin();
			getEntityManager().persist(entity);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Didnt save",e);
		}finally{
			getEntityManager().close();
		}
    }

    public void edit(T entity) throws Exception {
    	try{
    		
	    	EntityTransaction tx =getEntityManager().getTransaction();
	    	tx.begin();
	    	getEntityManager().merge(entity);
	        tx.commit();
    	}catch(Exception e){
    		Log.error("Didnt merge",e);
    		e.printStackTrace();
    		throw e;
    		
    	}finally{
    		getEntityManager().close();
    	}
    }
    public void close(){
    	if(getEntityManager()!=null&&getEntityManager().isOpen()){
    		
    		
    		getEntityManager().close();
    	}
    }

    public void remove(T entity) throws Exception{
    	try{
	    	EntityTransaction tx = getEntityManager().getTransaction();
	    	tx.begin();
	        getEntityManager().remove(getEntityManager().merge(entity));
	        tx.commit();
    	}catch(Exception e){
    		Log.error("Didnt remnove",e);
    		e.printStackTrace();
    		throw e;
    		
    	}finally{
    		getEntityManager().close();
    	}
    }

    public T find(Object id) {
    	
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
    	getEntityManager().close();
    	
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).setFlushMode(FlushModeType.AUTO).getResultList();
    }
    
    public List<T> findAllActive() {
    	getEntityManager().close();
    	String qName=entityClass.getSimpleName()+".findByAct";
         Query t = getEntityManager().createNamedQuery(qName).setParameter("act", "A");
        
        
        List<T>list= (List<T>)t.getResultList();
        List<T>listFill=new ArrayList<T>();
        Field[] fields = entityClass.getDeclaredFields();
        for(Field f:fields)
        if(f.getName().equalsIgnoreCase("act")){
        	
        	listFill=list;
        }
        return listFill;
    }
    

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
*/
    
    
}
