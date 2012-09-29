package jp.dip.hakuro.twitserif.web;

import jp.dip.hakuro.twitserif.domain.Serifs;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/serifses")
@Controller
@RooWebScaffold(path = "serifses", formBackingObject = Serifs.class)
public class SerifsController {
//    public 
}
