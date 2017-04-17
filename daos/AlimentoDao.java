package daos;

import hibernate.HibernateUtil;
import negocio.Alimento;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.AlimentoBean;

public class AlimentoDao {
	private static AlimentoDao instancia;
	private static SessionFactory sf;
	
	public static AlimentoDao getInstance(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new AlimentoDao();
		}
		return instancia;
	}

	public void cargarVariableGlobal() {
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			Query query = session.createQuery("select MAX(a.Id) from AlimentoBean a ");
			int variableGlobal = (int) query.uniqueResult();
			Alimento.setID(variableGlobal+1);
		}
		catch(Exception e){
			System.out.println("No existen Alimentos");
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	public void grabar(AlimentoBean alimento){
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(alimento);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public void actualizar(AlimentoBean alimento) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(alimento);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public Alimento buscar(int id) {
		Session session = sf.openSession();
		session.beginTransaction();
		AlimentoBean alimentoBean = (AlimentoBean) session.get(AlimentoBean.class, id);
		Alimento alimento = alimentoBean.pasarNegocio();
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alimento;
	}
	
	public Alimento buscar(String nombre) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from AlimentoBean a where a.nombre = ?");
		query.setString(0, nombre);
		Alimento alimento = null;
		try{
			AlimentoBean alimentoBean = (AlimentoBean) query.uniqueResult();
			alimento = alimentoBean.pasarNegocio();
		}catch(Exception e){
			
		}
		session.flush();
		session.getTransaction().commit();
		session.close();
		return alimento;
	}
}