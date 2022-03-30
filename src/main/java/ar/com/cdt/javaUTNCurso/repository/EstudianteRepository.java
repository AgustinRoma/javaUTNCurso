package ar.com.cdt.javaUTNCurso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.cdt.javaUTNCurso.entity.EstudianteEntity;

public interface EstudianteRepository extends JpaRepository <EstudianteEntity, Long>{
	
	EstudianteEntity findByDNI(long DNI);
	
}
