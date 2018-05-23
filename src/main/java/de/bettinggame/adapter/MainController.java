package de.bettinggame.adapter;

import de.bettinggame.domain.News;
import de.bettinggame.domain.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for index/startpage.
 */
@Controller
public class MainController extends AbstractController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String root(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "index";
        }

        List<News> newsList = newsRepository.findAllByOrderByPublishDateDesc();
        model.addAttribute(newsList);
        return "news/news";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
