package dev.trifonov.feedback_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "review")
@Accessors(chain = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "text")
    private String text;

    @Column(name = "product_quality")
    private int productQuality;

    @DateTimeFormat
    @Column(name = "posted_at")
    private LocalDateTime postedAt;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<Image> images;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", text='" + text + '\'' +
                ", productQuality=" + productQuality +
                ", postedAt=" + postedAt +
                '}';
    }
}
