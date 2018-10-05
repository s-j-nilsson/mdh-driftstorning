package se.mdh.driftstorning.admin.controller;

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
import se.mdh.driftstorning.common.model.AnledningPost;
import se.mdh.driftstorning.common.repository.AnledningRepository;

@Controller
@RequestMapping(value = "/anledningar")
public class AnledningController {

  private final AnledningRepository anledningRepository;

  @Autowired
  public AnledningController(AnledningRepository anledningRepository) {
    this.anledningRepository = anledningRepository;
  }

  @GetMapping("/list")
  public String product(Model model) {
    model.addAttribute("anledningar", anledningRepository.findAll());
    return "anledningar/list";
  }

  @GetMapping("/create")
  public String create(AnledningPost anledning, Model model) {
    model.addAttribute("anledning", anledning);
    return "anledningar/create";
  }

  @PostMapping("/create")
  public String create(@Valid AnledningPost anledning, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "anledningar/create";
    } else {
      anledningRepository.save(anledning);
      return "redirect:/anledningar/list";
    }
  }

  @GetMapping("/show/{id}")
  public String show(@PathVariable String id, Model model) {
    model.addAttribute("anledning", anledningRepository.findById(id).get());
    return "anledningar/show";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam String id) {
    AnledningPost anledning = anledningRepository.findById(id).get();
    anledningRepository.delete(anledning);

    return "redirect:/anledningar/list";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable String id, Model model) {
    model.addAttribute("anledning", anledningRepository.findById(id).get());
    return "anledningar/edit";
  }

  @PostMapping("/update")
  public String update(@Valid AnledningPost anledning, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "anledningar/edit/"+ anledning.getId();
    } else {
      anledningRepository.save(anledning);
      return "redirect:/anledningar/list";
    }
  }
}
