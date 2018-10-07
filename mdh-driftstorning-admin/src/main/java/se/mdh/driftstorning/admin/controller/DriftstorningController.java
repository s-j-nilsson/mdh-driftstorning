package se.mdh.driftstorning.admin.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.mdh.driftstorning.admin.repository.AnledningRepository;
import se.mdh.driftstorning.admin.repository.DriftstorningRepository;
import se.mdh.driftstorning.admin.repository.KanalRepository;
import se.mdh.driftstorning.common.model.DriftstorningPost;

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
    model.addAttribute("driftstorningList", driftstorningRepository.findAll());
    return "list";
  }

  @GetMapping("/create")
  public String create(DriftstorningPost driftstorningPost, Model model) {
    model.addAttribute("driftstorning", driftstorningPost);
    model.addAttribute("kanaler", kanalRepository.findAll());
    model.addAttribute("anledningar", anledningRepository.findAll());
    List<DriftstorningPost> byMeddelandeSvIsNotNull = driftstorningRepository.findByMeddelandeSvIsNotNull();
    List<DriftstorningPost> byMeddelandeEnIsNotNull = driftstorningRepository.findByMeddelandeEnIsNotNull();

    List<String> tidigareMeddelandenSv = byMeddelandeSvIsNotNull.stream()
        .map(d -> d.getMeddelandeSv())
        .sorted()
        .collect(Collectors.toList());
    model.addAttribute("tidigareMeddelandenSv", tidigareMeddelandenSv);

    List<String> tidigareMeddelandenEn = byMeddelandeEnIsNotNull.stream()
        .map(d -> d.getMeddelandeEn())
        .sorted()
        .collect(Collectors.toList());
    model.addAttribute("tidigareMeddelandenEn", tidigareMeddelandenEn);

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
    model.addAttribute("driftstorning", driftstorningRepository.findById(id).get());
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
    model.addAttribute("driftstorning", driftstorningRepository.findById(id).get());
    model.addAttribute("kanaler", kanalRepository.findAll());
    model.addAttribute("anledningar", anledningRepository.findAll());
    List<DriftstorningPost> byMeddelandeSvIsNotNull = driftstorningRepository.findByMeddelandeSvIsNotNull();
    List<DriftstorningPost> byMeddelandeEnIsNotNull = driftstorningRepository.findByMeddelandeEnIsNotNull();

    List<String> tidigareMeddelandenSv = byMeddelandeSvIsNotNull.stream()
        .map(d -> d.getMeddelandeSv())
        .sorted()
        .collect(Collectors.toList());
    model.addAttribute("tidigareMeddelandenSv", tidigareMeddelandenSv);

    List<String> tidigareMeddelandenEn = byMeddelandeEnIsNotNull.stream()
        .map(d -> d.getMeddelandeEn())
        .sorted()
        .collect(Collectors.toList());
    model.addAttribute("tidigareMeddelandenEn", tidigareMeddelandenEn);
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
