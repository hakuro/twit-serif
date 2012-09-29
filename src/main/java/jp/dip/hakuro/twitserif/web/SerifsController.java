package jp.dip.hakuro.twitserif.web;

import jp.dip.hakuro.twitserif.domain.Serifs;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/serifses")
@Controller
@RooWebScaffold(path = "serifses", formBackingObject = Serifs.class)
public class SerifsController {

    @RequestMapping(value = "/addentry", method = RequestMethod.POST, produces = "text/html")
    public String addEntry(@Valid Serifs serifs, BindingResult bindingResult,
            HttpServletRequest httpServletRequest) {
        return "serifses/list";
    }

    @RequestMapping(value = "/getrecent/{number}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> getRecent(@PathVariable String number) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        List<Serifs> result = Serifs.findAllSerifses();
        return new ResponseEntity<String>(Serifs.toJsonArray(result), headers, HttpStatus.OK);
    }
}
