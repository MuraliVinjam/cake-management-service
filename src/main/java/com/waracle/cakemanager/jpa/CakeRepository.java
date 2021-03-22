package com.waracle.cakemanager.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CakeRepository extends JpaRepository<Cake, Long> {

}
