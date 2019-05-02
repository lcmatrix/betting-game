package de.bettinggame.adapter;

import de.bettinggame.domain.news.News;
import de.bettinggame.domain.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller for index/startpage.
 */
@Controller
public class MainController implements AbstractController {
    private NewsRepository newsRepository;

    @Autowired
    public MainController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @RequestMapping("/")
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
