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
import se.mdh.driftstorning.common.model.DriftstorningPost;
import se.mdh.driftstorning.common.repository.AnledningRepository;
import se.mdh.driftstorning.common.repository.DriftstorningRepository;
import se.mdh.driftstorning.common.repository.KanalRepository;

@Controller
public class DriftstorningController {

  private final DriftstorningRepository driftstorningRepository;

  private final AnledningRepository anledningRepository;

  private final KanalRepository kanalRepository;

  public DriftstorningController(DriftstorningRepository driftstorningRepository, AnledningRepository anledningRepository, KanalRepository kanalRepository) {
    this.driftstorningRepository = driftstorningRepository;
    this.anledningRepository = anledningRepository;
    this.kanalRepository = kanalRepository;
  }

  @GetMapping("/list")
  public String product(Model model) {
    model.addAttribute("driftavbrottList", driftstorningRepository.findAll());
    return "list";
  }

  @GetMapping("/create")
  public String create(DriftstorningPost driftstorningPost, Model model) {
    model.addAttribute("driftstorningPost", driftstorningPost);
    model.addAttribute("kanaler", kanalRepository.findAll());
    model.addAttribute("anledningar", anledningRepository.findAll());
    return "create";
  }

  @PostMapping("/create")
  public String create(@Valid DriftstorningPost driftstorningPost, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "forward:/create";
    } else {
      driftstorningRepository.save(driftstorningPost);
      return "redirect:/list";
    }
  }

  @GetMapping("/show/{id}")
  public String show(@PathVariable String id, Model model) {
    model.addAttribute("driftavbrott", driftstorningRepository.findById(id).get());
    return "show";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam String id) {
    DriftstorningPost driftstorningPost = driftstorningRepository.findById(id).get();
    driftstorningRepository.delete(driftstorningPost);

    return "redirect:/list";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable String id, Model model) {
    model.addAttribute("driftavbrott", driftstorningRepository.findById(id).get());
    model.addAttribute("kanaler", kanalRepository.findAll());
    model.addAttribute("anledningar", anledningRepository.findAll());
    return "edit";
  }

  @PostMapping("/update")
  public String update(@Valid DriftstorningPost driftstorningPost, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "forward:/edit/"+ driftstorningPost.getId();
    } else {
      driftstorningRepository.save(driftstorningPost);
      return "redirect:/list";
    }
  }
}
