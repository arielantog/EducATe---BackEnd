package negocio;

import java.util.ArrayList;
import java.util.List;

import daos.TipoAvatarDao;
import beans.AlimentoBean;
import beans.TipoAvatarBean;

public class TipoAvatar {
	
	
	public TipoAvatar(String nombre, int alimentoMax, int nivel, int tiempoHambre) {
		Id = ID++;
		this.nombre = nombre;
		this.alimentoMax = alimentoMax;
		this.nivel = nivel;
		this.tiempoHambre = tiempoHambre;
		this.alimentos = new ArrayList<Alimento>();
		TipoAvatarDao.getInstance().grabar(pasarBean());
	}
	
	public TipoAvatar(int id, String nombre, int alimentoMax, int nivel,
			int tiempoHambre) {
		Id = id;
		this.nombre = nombre;
		this.alimentoMax = alimentoMax;
		this.nivel = nivel;
		this.tiempoHambre = tiempoHambre;
		this.alimentos = new ArrayList<Alimento>();
	}

	private static int ID = 1;
	private int Id;
	private String nombre;
	private int alimentoMax;
	private int nivel;
	private int tiempoHambre;
	private List<Alimento> alimentos;
	
	
	/*GETTERS Y SETTERS*/
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAlimentoMax() {
		return alimentoMax;
	}
	public void setAlimentoMax(int alimentoMax) {
		this.alimentoMax = alimentoMax;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getTiempoHambre() {
		return tiempoHambre;
	}
	public void setTiempoHambre(int tiempoHambre) {
		this.tiempoHambre = tiempoHambre;
	}
	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	/*BEAN*/
	public TipoAvatarBean pasarBean() {
		TipoAvatarBean tipoAvatarBean = new TipoAvatarBean();
		tipoAvatarBean.setId(getId());
		tipoAvatarBean.setNombre(nombre);
		tipoAvatarBean.setAlimentoMax(alimentoMax);
		tipoAvatarBean.setNivel(nivel);
		tipoAvatarBean.setTiempoHambre(tiempoHambre);
		for (Alimento alimento: alimentos){
			AlimentoBean alimentoBean = alimento.pasarBean();
			tipoAvatarBean.agregarAlimento(alimentoBean);
		}
		return tipoAvatarBean;
	}

	public void agregarAlimento(Alimento alimento) {
		alimentos.add(alimento);
	}

	public int agregarAlimento(Alimento alimento, boolean b) {
		if (!tengoAlimento(alimento)){
			alimentos.add(alimento);
			TipoAvatarDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El alimento ya existe en el Tipo de Avatar");
		}
		return 0;
	}

	private boolean tengoAlimento(Alimento alimento) {
		for (Alimento alimento2: alimentos){
			if (alimento.getId() == alimento2.getId())
				return true;
		}
		return TipoAvatarDao.getInstance().tengoAlimento(this.getId(), alimento.getId());
	}

}
