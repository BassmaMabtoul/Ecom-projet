package org.sid.customerservice.repository;

import org.sid.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //Cette annotation est suvisante pour mettre en place un web service rest fool qui permet de gerer les produits
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
