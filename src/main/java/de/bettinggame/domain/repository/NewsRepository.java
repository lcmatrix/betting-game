package de.bettinggame.domain.repository;

import de.bettinggame.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * News repository.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByPublishDateDesc();
}
