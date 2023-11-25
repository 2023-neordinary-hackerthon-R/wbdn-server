package neordinaryr.wbdn.domain;

import jakarta.persistence.*;
import lombok.*;
import neordinaryr.wbdn.domain.common.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Photo")
@Table(name="photo")
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", referencedColumnName = "post_id")
    private Post post;
}
