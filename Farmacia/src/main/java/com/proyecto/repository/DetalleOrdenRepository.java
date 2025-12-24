package com.proyecto.repository;

import com.proyecto.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {    
    List<DetalleOrden> findByOrdenIdOrden(Long idOrden);
}