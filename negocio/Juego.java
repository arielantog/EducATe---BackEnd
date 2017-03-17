package negocio;

import java.util.*;

public class Juego {

	public Juego(String nombre, Tema tema) {
		Id = ID++;
		Nombre = nombre;
		Tema = tema;
		Lecciones = new ArrayList<Leccion>();
	}
	
	private static Integer ID = 1;
	private Integer Id;
	private String Nombre;
	private Tema Tema;
	private List<Leccion> Lecciones;

	public Integer agregarLeccion(Leccion leccion) {
		if(!tengoLeccion(leccion)){
			Lecciones.add(leccion);
			return leccion.getId();
		}
		return null;
	}
	
	public Leccion buscarLeccion(Integer leccion) {
		for (Leccion leccion2: Lecciones)
			if(leccion2.getId()==leccion)
				return leccion2;
		return null;
	}
	
	private boolean tengoLeccion (Leccion leccion){
		for (Leccion leccion2: Lecciones)
			if(leccion2.getId() == leccion.getId())
				return true;
		return false;
	}

	
	/*GETTERS Y SETTERS*/
	public static Integer getID() {
		return ID;
	}
	public static void setID(Integer iD) {
		ID = iD;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Tema getTema() {
		return Tema;
	}
	public void setTema(Tema tema) {
		Tema = tema;
	}
	public List<Leccion> getLecciones() {
		return Lecciones;
	}
	public void setLecciones(List<Leccion> lecciones) {
		Lecciones = lecciones;
	}
}