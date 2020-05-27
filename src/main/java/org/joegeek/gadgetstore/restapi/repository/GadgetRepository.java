package org.joegeek.gadgetstore.restapi.repository;

import org.joegeek.gadgetstore.restapi.model.Gadget;
import org.springframework.data.repository.CrudRepository;

public interface GadgetRepository extends CrudRepository<Gadget, Long> {

  /**
   * Case-insensitive search gadgets where brand like '%brand%' or model like '%model%'
   * @param brand
   * @param model
   * @return Iterable<Gadget>
   */
  Iterable<Gadget> findAllByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}