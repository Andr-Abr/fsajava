package com.fsa.crudvehiculos.springbootfsa.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fsa.crudvehiculos.springbootfsa.modelo.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VehiculoRepositorio extends JpaRepository<Vehiculo, Integer> {
	List<String> findModelosByMarca(String marca);
	List<Integer> findAniosByMarca(String marca);
	
    @Query("SELECT v FROM Vehiculo v WHERE " +
            "(:marca IS NULL OR v.marca LIKE %:marca%) AND " +
            "(:modelo IS NULL OR v.modelo LIKE %:modelo%) AND " +
            "(:anio IS NULL OR v.anio = :anio)")
     Page<Vehiculo> buscarVehiculos(
         @Param("marca") String marca, 
         @Param("modelo") String modelo, 
         @Param("anio") Integer anio, 
         Pageable pageable);

	@Query("SELECT DISTINCT v.marca FROM Vehiculo v ORDER BY v.marca")
	List<String> findDistinctMarcas();

	@Query("SELECT DISTINCT v.modelo FROM Vehiculo v ORDER BY v.modelo")
	List<String> findDistinctModelos();

	@Query("SELECT DISTINCT v.anio FROM Vehiculo v ORDER BY v.anio DESC")
	List<Integer> findDistinctAnios();
}