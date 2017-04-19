package negocio;

import java.util.*;

import daos.CursoDao;
import daos.DocenteDao;
import beans.CursoBean;
import beans.DocenteBean;

public class Docente extends Persona {

	public Docente(String tipoDocumento, int nroDocumento, String nombre, String apellido) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		id = ID++;
		cursos = new ArrayList<Curso>();
		activo = true;
		DocenteDao.getInstance().grabar(pasarBean());
		
	}
	public Docente(int Id, String tipoDocumento, int nroDocumento, String nombre, String apellido, boolean activo) {
		super(tipoDocumento, nroDocumento, nombre, apellido);
		this.id = Id;
		this.activo = activo;
		cursos = new ArrayList<Curso>();
	}

	private static int ID = 1;
	private int id;
	private List<Curso> cursos;
	private boolean activo;

	public int agregarCurso(String descripcion) {
		Curso curso = buscarCurso(descripcion);
		if (curso == null){
			Curso curso2 = new Curso(descripcion);
			cursos.add(curso2);
			DocenteDao.getInstance().actualizar(pasarBean());
			return curso2.getId();
		}
		curso.activar(descripcion);
		cursos.add(curso);
		DocenteDao.getInstance().actualizar(pasarBean());
		return curso.getId();
	}
	public void agregarCurso(Curso curso) {
		cursos.add(curso);
	}

	public int cursoAgregarAlumno(int Curso, Alumno Alumno) {
		Curso curso = buscarCurso(Curso);
		if (curso != null)
			return curso.agregarAlumno(Alumno);
		return 0;
	}

	public Curso buscarCurso(int Curso) {
		for (Curso curso: cursos)
			if (curso.getId() == curso.getId())
				return curso;
		return CursoDao.getInstance().buscar(getId(), Curso);
	}
	
	public Curso buscarCurso(String descripcion) {
		for (Curso curso: cursos)
			if (curso.getDescripcion().equals(curso.getDescripcion()))
				return curso;
		return CursoDao.getInstance().buscar(descripcion);
	}
	public int cursoQuitarAlumno(int curso, int alumno) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			curso2.quitarAlumno(alumno);
			DocenteDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El curso no existe");
		}
		return 0;
	}
	public int eliminarCurso(int curso) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			cursos.remove(curso2);
			DocenteDao.getInstance().actualizar(pasarBean());
		}else{
			System.out.println("El curso no existe");
		}
		return 0;
	}
	public int modificarCurso(int curso, String descripcion) {
		Curso curso2 = buscarCurso(curso);
		if (curso2 != null && curso2.isActivo()){
			Curso curso3 = buscarCurso(descripcion);
			if (curso3 == null){
				curso2.modificar(descripcion);
				DocenteDao.getInstance().actualizar(pasarBean());
			}else{
				System.out.println("Ya existe un curso con el mismo nombre");
			}
		}else{
			System.out.println("El curso no existe");
		}
		return 0;
	}
	public void eliminar() {
		activo = false;
		DocenteDao.getInstance().actualizar(pasarBean());
		
	}
	public void activar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		activo = true;
		DocenteDao.getInstance().actualizar(pasarBean());
	}
	public void modificar(String nombre, String apellido) {
		setNombre(nombre);
		setApellido(apellido);
		DocenteDao.getInstance().actualizar(pasarBean());
	}
	
	/*GETTERS Y SETTERS*/
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	/*BEAN*/
	public DocenteBean pasarBean() {
		DocenteBean docenteBean = new DocenteBean();
		docenteBean.setId(getId());
		docenteBean.setTipoDocumento(getTipoDocumento());
		docenteBean.setNroDocumento(getNroDocumento());
		docenteBean.setNombre(getNombre());
		docenteBean.setApellido(getApellido());
		docenteBean.setActivo(activo);
		for (Curso curso: cursos){
			CursoBean cursoBean = curso.pasarBean();
			docenteBean.agregarCurso(cursoBean);
		}
			
		
		return docenteBean;
	}
	
}