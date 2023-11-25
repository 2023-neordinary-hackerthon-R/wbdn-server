package neordinaryr.wbdn.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Post")
@Table(name="post")
public class Post {

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

}
