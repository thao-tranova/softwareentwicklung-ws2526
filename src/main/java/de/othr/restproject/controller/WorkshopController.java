package de.othr.restproject.controller;

import de.othr.restproject.model.Workshop;
import de.othr.restproject.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/workshops")
public class WorkshopController {
  @Autowired
  private WorkshopService workshopService;

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<Workshop>>> getAllWorkshops() {
    List<Workshop> workshops = workshopService.getAllWorkshops();
    List<EntityModel<Workshop>> workshopModels = workshops.stream()
       .map(workshop -> EntityModel.of(workshop,
          linkTo(methodOn(WorkshopController.class).getWorkshopById(workshop.getId())).withSelfRel(),
          linkTo(methodOn(WorkshopController.class).getAllWorkshops()).withRel("workshops"))
       ).collect(Collectors.toList());
    return ResponseEntity.ok(CollectionModel.of(workshopModels));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Workshop>> getWorkshopById(@PathVariable Long id) {
    Workshop workshop = workshopService.getWorkshopById(id);
    if (workshop == null) {
      return ResponseEntity.notFound().build();
    }
    EntityModel<Workshop> entityModel = EntityModel.of(workshop,
       linkTo(methodOn(WorkshopController.class).getWorkshopById(id)).withSelfRel(),
       linkTo(methodOn(WorkshopController.class).getAllWorkshops()).withRel("workshops"));
    return ResponseEntity.ok(entityModel);
  }
  // Implement other endpoints for POST, PUT, and DELETE operations,
  // using similar HATEOAS principles to provide links to related resources.
}