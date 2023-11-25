package neordinaryr.wbdn.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neordinaryr.wbdn.domain.common.BaseEntity;

@Getter
@Setter
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToOne(mappedBy = "post")
    private Photo photo;
}
