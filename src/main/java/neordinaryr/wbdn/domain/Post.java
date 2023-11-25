package neordinaryr.wbdn.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neordinaryr.wbdn.domain.common.BaseEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Post")
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String editContents;

    @Lob
    private String additionalContents;

    @Column(nullable = false, length = 50)
    private String device;

    private Double latitude;

    private Double longitude;

    private String ISO;

    private String shutterSpeed;

    private String fNumber;

    private LocalDateTime shootingDate;

    @ColumnDefault("0")
    private Long likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private Photo photo;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
