package domain;

import java.util.Date;

public class User {
	
	public enum TIPO_USUARIO{
		ADMINISTRADOR , RRHH , UBICADORES , DESPACHANTES
	}
	
	private Long id;
	private String nombre; 
	private String apellido; 
	private String userName;
	private String password;
	private Integer dni;
	private String direccion;
	private Date fechaNac;
	private Date fechaEgreso;
	private Date fechaIngreso;
	private boolean estado = true;
	private TIPO_USUARIO tipoUsuario;
	
	
	public User clone(){
		User newUser = new User();
		newUser.setNombre(this.getNombre());
		newUser.setApellido(this.getApellido());
		newUser.setUserName(this.getUserName());
		newUser.setDni(this.getDni());
		newUser.setDireccion(this.getDireccion());
		newUser.setFechaEgreso(this.getFechaEgreso());
		newUser.setFechaIngreso(this.getFechaIngreso());
		newUser.setFechaNac(this.getFechaNac());
		newUser.setEstado(this.isEstado());
		newUser.setTipoUsuario(this.getTipoUsuario());
		newUser.setPassword(this.getPassword());
		
		return newUser;
	}
	
	public void copyFromOther(User user){
		this.setNombre(user.getNombre());
		this.setApellido(user.getApellido());
		this.setUserName(user.getUserName());
		this.setDni(user.getDni());
		this.setDireccion(user.getDireccion());
		this.setFechaEgreso(user.getFechaEgreso());
		this.setFechaIngreso(user.getFechaIngreso());
		this.setFechaNac(user.getFechaNac());
		this.setEstado(user.isEstado());
		this.setTipoUsuario(user.getTipoUsuario());
		this.setPassword(this.getPassword());
	}
	
	
	public User() {
		
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Date getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public TIPO_USUARIO getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TIPO_USUARIO tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + (estado ? 1231 : 1237);
		result = prime * result
				+ ((fechaEgreso == null) ? 0 : fechaEgreso.hashCode());
		result = prime * result
				+ ((fechaIngreso == null) ? 0 : fechaIngreso.hashCode());
		result = prime * result
				+ ((fechaNac == null) ? 0 : fechaNac.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (estado != other.estado)
			return false;
		if (fechaEgreso == null) {
			if (other.fechaEgreso != null)
				return false;
		} else if (!fechaEgreso.equals(other.fechaEgreso))
			return false;
		if (fechaIngreso == null) {
			if (other.fechaIngreso != null)
				return false;
		} else if (!fechaIngreso.equals(other.fechaIngreso))
			return false;
		if (fechaNac == null) {
			if (other.fechaNac != null)
				return false;
		} else if (!fechaNac.equals(other.fechaNac))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", apellido=" + apellido
				+ ", userName=" + userName + ", password=" + password
				+ ", dni=" + dni + ", direccion=" + direccion + ", fechaNac="
				+ fechaNac + ", fechaEgreso=" + fechaEgreso + ", fechaIngreso="
				+ fechaIngreso + ", estado=" + estado + ", tipoUsuario="
				+ tipoUsuario + "]";
	}
	
}
