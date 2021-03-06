package ar.com.cdt.javaUTNCurso.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estudiante")
public class EstudianteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		
	public Long id;
	
	@NotNull
	public String nombre;
	
	@NotNull
	public String apellido;
	
	@NotNull
	public long DNI;
	
	@NotNull
	public int edad;
	
	@NotNull
	public boolean activo;
	
	
	public EstudianteEntity() {
		super();
	}

	public EstudianteEntity(Long id, String nombre, String apellido, long dNI, int edad, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.edad = edad;
		this.activo = activo;
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
	public long getDNI() {
		return DNI;
	}
	public void setDNI(long dNI) {
		DNI = dNI;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "EstudianteEntity [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI
				+ ", edad=" + edad + ", activo=" + activo + "]";
	}
	
	
}
