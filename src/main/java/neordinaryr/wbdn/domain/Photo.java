package neordinaryr.wbdn.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Photo")
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photo_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", referencedColumnName = "post_id")
    private Post post;
}
