package ar.com.cdt.javaUTNCurso.bo;

import java.util.List;

import ar.com.cdt.javaUTNCurso.entity.EstudianteEntity;
import ar.com.cdt.javaUTNCurso.exception.TPDatabaseException;
import ar.com.cdt.javaUTNCurso.exception.TPDuplicatedStudentException;
import ar.com.cdt.javaUTNCurso.exception.TPInvalidActionException;
import ar.com.cdt.javaUTNCurso.exception.TPNoStudentFoundException;
import ar.com.cdt.javaUTNCurso.models.EstudianteModel;
import ar.com.cdt.javaUTNCurso.models.MessageModel;


public interface EstudianteBO {

	public EstudianteEntity guardarEstudiante(EstudianteModel estudiante) throws TPDuplicatedStudentException;
	
	public EstudianteEntity getEstudiante(long dni) throws TPNoStudentFoundException;
	
	public List<EstudianteEntity> getAllEstudiantes() throws TPNoStudentFoundException;

	public MessageModel updateStudent(String accion, EstudianteModel estudiante) throws TPNoStudentFoundException, TPInvalidActionException, TPDatabaseException;
}
