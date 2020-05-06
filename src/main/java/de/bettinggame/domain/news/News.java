package de.bettinggame.domain.news;

import de.bettinggame.domain.AbstractIdentifiableEntity;
import de.bettinggame.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * News entity.
 */
@Entity
@Table(name = "news")
public class News extends AbstractIdentifiableEntity {

    @NotNull
    @Column(nullable = false)
    private String headline;

    @NotNull
    @Column(nullable = false)
    private String content;

    @NotNull
    @Column(name = "publish_date", nullable = false)
    private OffsetDateTime publishDate;

    @NotNull
    @Column(name="author_username", nullable = false)
    private String authorUsername;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    protected News() {
    }

    public String getHeadline() {
        return headline;
    }

    public String getContent() {
        return content;
    }

    public OffsetDateTime getPublishDate() {
        return publishDate;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getAuthor() {
        if (author == null) {
            return authorUsername;
        }
        return Optional.ofNullable(author.getFullname()).orElse(authorUsername);
    }
}
