package de.bettinggame.domain.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * News repository.
 */
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByPublishDateDesc();
}
