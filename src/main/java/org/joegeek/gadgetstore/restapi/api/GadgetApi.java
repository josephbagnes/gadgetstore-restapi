package org.joegeek.gadgetstore.restapi.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.joegeek.gadgetstore.restapi.model.Gadget;
import org.joegeek.gadgetstore.restapi.repository.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/gadget")
public class GadgetApi {

  @Autowired
  private GadgetRepository gadgetRepository;

  @PostMapping("/register-product")
  public Gadget register(@RequestBody Gadget gadget) {
    gadget.setSerialNumber(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
    this.gadgetRepository.save(gadget);
    return gadget;
  }

  @GetMapping("/view/{id}")
  public Gadget view(@PathVariable("id") long id) {
    Optional<Gadget> gOpt = this.gadgetRepository.findById(id);
    if(gOpt.isPresent()){
      return gOpt.get();
    }
    return null;
  }

  @PutMapping("/update/{id}")
  public Gadget update(@PathVariable("id") long id, @RequestBody Gadget gadget) {
    Optional<Gadget> gOpt = this.gadgetRepository.findById(id);
    if(gOpt.isPresent()){
      Gadget gToEdit = gOpt.get();
      gToEdit.setBrand(gadget.getBrand());
      gToEdit.setModel(gadget.getModel());
      gToEdit.setPrice(gadget.getPrice());
      this.gadgetRepository.save(gToEdit);
      return gToEdit;
    }
    return null;
  }

  @GetMapping("/get-all")
  public List<Gadget> getAll() {
    List<Gadget> gadgets = new ArrayList<>();
    this.gadgetRepository.findAll().forEach(gadgets::add);
    return gadgets;
  }

  @GetMapping("/search/{keyword}")
  public List<Gadget> search(@PathVariable("keyword") String keyword) {
    List<Gadget> gadgets = new ArrayList<>();
    this.gadgetRepository.findAllByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(keyword, keyword)
      .forEach(gadgets::add);
    return gadgets;
  }

  @DeleteMapping("/delete/{id}")
  public Gadget delete(@PathVariable("id") long id) {
    Optional<Gadget> gOpt = this.gadgetRepository.findById(id);
    if(gOpt.isPresent()){
      Gadget gToDelete = gOpt.get();
      this.gadgetRepository.delete(gToDelete);
      return gToDelete;
    }
    return null;
  }
}