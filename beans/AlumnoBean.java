package beans;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import negocio.Alumno;

@Entity
@Table(name="alumnos")
public class AlumnoBean extends PersonaBean{
	@Id
	@Column(name="alumnoId")
	private int id;
	private String usuario;
	private int puntos;
	@OneToMany
	@JoinColumn (name="alumnoId")
	private List<EnsenianzaBean> ensenianzas;
	@OneToOne
	@JoinColumn (name="avatarId")
	private AvatarBean avatar;
	private boolean activo;
	
	public AlumnoBean() {
		ensenianzas = new ArrayList<EnsenianzaBean>();
	}
	/*GETTERS AND SETTERS*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public List<EnsenianzaBean> getEnsenianzas() {
		return ensenianzas;
	}
	public void setEnsenianzas(List<EnsenianzaBean> ensenianzas) {
		this.ensenianzas = ensenianzas;
	}
	public AvatarBean getAvatar() {
		return avatar;
	}
	public void setAvatar(AvatarBean avatar) {
		this.avatar = avatar;
	}
	public void agregarEnsenianza(EnsenianzaBean ensenianzaBean) {
		ensenianzas.add(ensenianzaBean);
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/*NEGOCIO*/
	public Alumno pasarNegocio() {
		Alumno alumno = new Alumno(id, getTipoDocumento(), getNroDocumento(), getNombre(), getApellido(), getUsuario(), getPassword(), getMail(), puntos, activo);
		alumno.setAvatar(avatar.pasarNegocio());
		for (EnsenianzaBean ensenianzaBean: ensenianzas){
			alumno.agregarEnsenianza(ensenianzaBean.pasarNegocio());
		}
		return alumno;
	}
}
