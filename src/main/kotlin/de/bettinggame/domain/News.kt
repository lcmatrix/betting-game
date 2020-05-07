package de.bettinggame.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

/**
 * News entity.
 */
@Entity
class News(identifier: String,
           @NotNull
           val headline: String,
           @NotNull
           val content: String,
           @NotNull
           @Column(name = "publish_date", nullable = false)
           val publishDate: OffsetDateTime,
           @NotNull
           val authorUsername: String,
           @ManyToOne
           @JoinColumn(name = "author_id", referencedColumnName = "id")
           var author: User?
) : AbstractIdentifiableEntity(identifier) {
    val authorName: String?
        get() {
            if (author == null) {
                return authorUsername
            }
            return author?.fullname ?: authorUsername
        }
}

/**
 * News repository.
 */
interface NewsRepository : JpaRepository<News, Int> {
    fun findAllByOrderByPublishDateDesc(): List<News>
}
