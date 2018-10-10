package se.mdh.driftstorning.service.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.mdh.driftstorning.service.model.Driftstorning;
import se.mdh.driftstorning.service.model.Driftstorningar;
import se.mdh.driftstorning.service.service.DriftstorningService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/driftstorningar")
public class DriftstorningController {
  private static final Log log = LogFactory.getLog(DriftstorningController.class);

  private DriftstorningService service;

  public DriftstorningController(DriftstorningService service) {
    this.service = service;
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Det finns en driftstörning."),
      @ApiResponse(code = 204, message = "Det finns ingen driftstörning."),
  })
  @ApiOperation(value = "Hämta pågående driftstörningar för ett antal kanaler")
  @GetMapping("/pagaende")
  public Resource<Driftstorningar> getPagaendeDriftstorning(@ApiParam(value = "Kanaler att kontrollera") @RequestParam(value = "kanal", required = false, defaultValue = "") final List<String> kanaler,
                                                            @ApiParam(value = "Marginal i minuter") @RequestParam(value = "marginal", defaultValue = "0") final int marginalMinuter,
                                                            HttpServletResponse response) {
    Driftstorningar allaDriftstorningar = service.getAllaDriftstorningar();

    Driftstorningar pagaendeDriftstorningar = service.filterDriftstorningar(allaDriftstorningar, kanaler, marginalMinuter, LocalDateTime.now());

    addLinksToDriftstorningar(response, pagaendeDriftstorningar);

    Resource<Driftstorningar> driftstorningarResource = new Resource<>(pagaendeDriftstorningar);
    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllaDriftstorningar(kanaler, null));

    driftstorningarResource.add(linkTo.withRel("alla-driftstorningar"));

    return driftstorningarResource;
  }

  private void addLinksToDriftstorningar(HttpServletResponse response, Driftstorningar pagaendeDriftstorningar) {
    if(pagaendeDriftstorningar == null || pagaendeDriftstorningar.getDriftstorningar().isEmpty()) {
      response.setStatus(HttpStatus.NO_CONTENT.value());
    } else {
      List<Driftstorning> driftstorningarList = pagaendeDriftstorningar.getDriftstorningar();
      for(Driftstorning driftstorning : driftstorningarList) {
        Link selfRel = linkTo(methodOn(this.getClass()).getDriftstorning(driftstorning.getDriftstorningId(), null)).withSelfRel();
        driftstorning.add(selfRel);
      }
    }
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Det finns en driftstörning."),
      @ApiResponse(code = 204, message = "Det finns ingen driftstörning."),
  })
  @ApiOperation(value = "Hämta alla driftstörningar för ett antal kanaler")
  @GetMapping("/")
  public Resource<Driftstorningar> getAllaDriftstorningar(@ApiParam(value = "Kanaler att kontrollera") @RequestParam(value = "kanal", required = false, defaultValue = "") final List<String> kanaler,
                                                         HttpServletResponse response) {
    Driftstorningar allaDriftstorningar = service.getAllaDriftstorningar();

    Driftstorningar driftstorningar = service.filterDriftstorningar(allaDriftstorningar, kanaler);

    addLinksToDriftstorningar(response, driftstorningar);

    Resource<Driftstorningar> driftstorningarResource = new Resource<>(driftstorningar);

    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getPagaendeDriftstorning(kanaler, 0, null));

    driftstorningarResource.add(linkTo.withRel("pagaende-driftstorningar"));

    return driftstorningarResource;
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Det finns en driftstörning."),
  })
  @ApiOperation(value = "Hämta en driftstörning")
  @GetMapping("/{id}")
  public Resource<Driftstorning> getDriftstorning(@PathVariable String id, HttpServletResponse response) {
    Optional<Driftstorning> driftstorningOptional = service.getDriftstorning(id);
    if(!driftstorningOptional.isPresent()) {
      response.setStatus(HttpStatus.NO_CONTENT.value());
    }

    Resource<Driftstorning> driftstorningResource = new Resource<>(driftstorningOptional.get());

    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getPagaendeDriftstorning(Collections.<String>emptyList(), 0, null));
    driftstorningResource.add(linkTo.withRel("pagaende-driftstorningar"));

    linkTo = linkTo(methodOn(this.getClass()).getAllaDriftstorningar(Collections.<String>emptyList(), null));
    driftstorningResource.add(linkTo.withRel("alla-driftstorningar"));


    return driftstorningResource;
  }
}
