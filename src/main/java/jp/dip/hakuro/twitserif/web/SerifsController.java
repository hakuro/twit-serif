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
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RequestMapping("/serifses")
@Controller
@RooWebScaffold(path = "serifses", formBackingObject = Serifs.class)
public class SerifsController {
    private static final Logger logger = LoggerFactory.getLogger(SerifsController.class);
    private String twRequestUrl = "http://search.twitter.com/search.json";
    private String requestLang = "lang=ja";
    private String glRequestUrl = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0";

    @RequestMapping(value = "/addentry", method = RequestMethod.POST, produces = "text/html")
    public String addEntry(@Valid Serifs serifs, BindingResult bindingResult,
            HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            logger.info("error");
            return "serifses/list";
        }
        logger.info("ok");
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

    @RequestMapping(value = "/timeline/{word}", headers = "Accept=application/json")
    public ResponseEntity<String> getTimeline(@PathVariable String word) {
        String url = twRequestUrl + "?q=" + word + "&" + requestLang;
        String result = httpRequest(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/glimage/{word}", headers = "Accept=application/json")
    public ResponseEntity<String> getGoogleImage(@PathVariable String word) {
        String url = glRequestUrl + "&q=" + word;
        String result = httpRequest(url);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    private String httpRequest(String url) {
        String result = "";

        try {
            URL accessURL = new URL(url);
            URLConnection con = accessURL.openConnection();
            BufferedInputStream in = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = br.readLine()) != null) {
                result += line + "\n";
            }
        } catch (MalformedURLException e) {
            logger.error("URL is invalid. :" + twRequestUrl);
            e.printStackTrace();
        } catch (UnknownHostException e) {
            logger.error("Unknown Host.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
