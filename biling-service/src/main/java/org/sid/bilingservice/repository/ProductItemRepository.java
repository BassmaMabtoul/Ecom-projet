package org.sid.bilingservice.repository;

import org.sid.bilingservice.entities.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItems,Long> {
}
