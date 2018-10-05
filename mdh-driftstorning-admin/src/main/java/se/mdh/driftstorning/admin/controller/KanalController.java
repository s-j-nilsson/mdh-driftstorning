package se.mdh.driftstorning.admin.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.mdh.driftstorning.common.model.KanalPost;
import se.mdh.driftstorning.common.repository.KanalRepository;

@Controller
@RequestMapping(value = "/kanaler")
public class KanalController {

  private final KanalRepository kanalRepository;

  @Autowired
  public KanalController(KanalRepository kanalRepository) {
    this.kanalRepository = kanalRepository;
  }

  @GetMapping("/list")
  public String product(Model model) {
    model.addAttribute("kanaler", kanalRepository.findAll());
    return "kanaler/kanaler";
  }

  @GetMapping("/create")
  public String create(KanalPost kanal, Model model) {
    model.addAttribute("kanal", kanal);
    return "kanaler/create";
  }

  @PostMapping("/create")
  public String create(@Valid KanalPost kanal, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "kanaler/create";
    } else {
      kanalRepository.save(kanal);
      return "redirect:/kanaler/list";
    }
  }

  @GetMapping("/show/{id}")
  public String show(@PathVariable String id, Model model) {
    model.addAttribute("kanal", kanalRepository.findById(id).get());
    return "kanaler/show";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam String id) {
    Optional<KanalPost> kanal = kanalRepository.findById(id);
    if(kanal.isPresent()) {
      kanalRepository.delete(kanal.get());
    }
    return "redirect:/kanaler/list";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable String id, Model model) {
    model.addAttribute("kanal", kanalRepository.findById(id).get());
    return "kanaler/edit";
  }

  @PostMapping("/update")
  public String update(@Valid KanalPost kanal, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "kanaler/edit/"+ kanal.getId();
    } else {
      kanalRepository.save(kanal);
      return "redirect:/kanaler/list";
    }
  }
}
