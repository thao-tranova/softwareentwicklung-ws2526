package de.othr.restproject.service;

import de.othr.restproject.model.Workshop;
import de.othr.restproject.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {
  @Autowired
  private WorkshopRepository workshopRepository;
  public List<Workshop> getAllWorkshops() {
    return workshopRepository.findAll();
  }
  public Workshop getWorkshopById(Long id) {
    return workshopRepository.findById(id).orElse(null);
  }
  public Workshop createWorkshop(Workshop workshop) {
    return workshopRepository.save(workshop);
  }
  public Workshop updateWorkshop(Workshop workshop) {
    return workshopRepository.save(workshop);
  }
  public void deleteWorkshop(Long id) {
    workshopRepository.deleteById(id);
  }
}
