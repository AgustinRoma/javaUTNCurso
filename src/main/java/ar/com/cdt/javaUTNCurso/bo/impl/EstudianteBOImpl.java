package ar.com.cdt.javaUTNCurso.bo.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.cdt.javaUTNCurso.bo.EstudianteBO;
import ar.com.cdt.javaUTNCurso.entity.EstudianteEntity;
import ar.com.cdt.javaUTNCurso.exception.TPDatabaseException;
import ar.com.cdt.javaUTNCurso.exception.TPDuplicatedStudentException;
import ar.com.cdt.javaUTNCurso.exception.TPInvalidActionException;
import ar.com.cdt.javaUTNCurso.exception.TPNoStudentFoundException;
import ar.com.cdt.javaUTNCurso.models.EstudianteModel;
import ar.com.cdt.javaUTNCurso.models.MessageModel;
import ar.com.cdt.javaUTNCurso.repository.EstudianteRepository;



@Component
public class EstudianteBOImpl implements EstudianteBO {
	
	public static final Logger logger = LoggerFactory.getLogger(EstudianteBOImpl.class);
	public static final String update = "modificar";
	public static final String setActive = "alta";
	public static final String setInactive = "baja";
	
	
	@Autowired
	EstudianteRepository repository;

	@Override
	public EstudianteEntity guardarEstudiante(EstudianteModel estudiante) throws TPDuplicatedStudentException{
		logger.info("inicia en guardarEstudiante");
		
		EstudianteEntity estudianteRegistrado = repository.findByDNI(estudiante.DNI);
		if (estudianteRegistrado != null) {
			throw new TPDuplicatedStudentException("Se encontro un usuario registrado con el DNI: " + String.valueOf(estudiante.DNI), "guardarEstudiante");
		}
		EstudianteEntity entity = mapEntityToModel(estudiante);
		return repository.save(entity);
	}
	
	@Override
	public EstudianteEntity getEstudiante(long dni) throws TPNoStudentFoundException {
		logger.info("entra en getEstudiante");
		EstudianteEntity entity = repository.findByDNI(dni);
		if (entity == null) {
			throw new TPNoStudentFoundException("No se encontro ningun estudiante registrado con el DNI: " + dni , "getEstudiante");
		}
		return entity;
	}
	
	@Override
	public List<EstudianteEntity> getAllEstudiantes() throws TPNoStudentFoundException {
		List<EstudianteEntity> estudiantes = repository.findAll();
		if (estudiantes == null) {
			throw new TPNoStudentFoundException("No se encontraron estudiantes registrados", "getAllEstudiantes");
		}
		return estudiantes;
	}
	
	@Override
	public MessageModel updateStudent(String accion, EstudianteModel estudiante) throws TPNoStudentFoundException, TPInvalidActionException, TPDatabaseException {
		logger.info("entra en updateStudent ");
		Long DNI = Long.valueOf(estudiante.DNI);
		EstudianteEntity entity = repository.findByDNI(DNI);
		if (entity == null) {
			throw new TPNoStudentFoundException("No se encontraron estudiantes registrados", "updateStudent");
		}
		if (accion.equals(update)) {
			logger.info("Actualizo al estudiante, DNI: {}", estudiante.DNI);
			try {
				if (!entity.activo) {
					throw new TPNoStudentFoundException("Usted se encuentra dado de baja", "updateStudent");
				}
				entity.setNombre(estudiante.nombre);
				entity.setApellido(estudiante.apellido);
				entity.setEdad(estudiante.edad);
				repository.save(entity);
				return new MessageModel("El estudiante se actualizo correctamente");
			} catch (Exception e) {
				logger.error("Ocurrio un error al actualizar el estudiante: {}", e.getMessage());
				throw new TPDatabaseException(e.getMessage(), "updateStudent");
			}
		} else if (accion.equals(setInactive)) {
			logger.info("Baja al estudiante, DNI: {}", estudiante.DNI);
			try {
				if (!entity.activo) {
					throw new TPNoStudentFoundException("Usted ya esta de baja", "updateStudent");
				}
				entity.setActivo(false);
				repository.save(entity);
				return new MessageModel("El estudiante se dio de baja correctamente");
			} catch (Exception e) {
				logger.error("Error al darse de baja al estudiante: {}", e.getMessage());
				throw new TPDatabaseException(e.getMessage(), "updateStudent");
			}
		} else if (accion.equals(setActive)) {
			logger.info("Alta a estudiante, DNI: {}", estudiante.DNI);
			try {
				if (!entity.activo) {
					entity.setActivo(true);
					repository.save(entity); 
					return new MessageModel("El estudiante se dio de alta correctamente");
				} else {
					throw new TPDuplicatedStudentException("Ya se encuentra registrado", "updateStudent");
				}
			} catch (Exception e) {
				logger.error("Ocurrio un error al dar de alta al estudiante: {}", e.getMessage());
				throw new TPDatabaseException(e.getMessage(), "updateStudent");
			}
		} else {
			throw new TPInvalidActionException("La accion es invalida", "updateStudent");
		}
	}
	
	public EstudianteEntity mapEntityToModel(EstudianteModel estudiante) {
		EstudianteEntity entity = new EstudianteEntity();
		entity.setNombre(estudiante.nombre);
		entity.setApellido(estudiante.apellido);
		entity.setDNI(estudiante.DNI);
		entity.setEdad(estudiante.edad);
		entity.setActivo(true);
		return entity;
	}
}
