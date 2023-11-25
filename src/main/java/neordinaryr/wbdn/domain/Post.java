package neordinaryr.wbdn.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Post")
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Lob
    private String content;

    @Column(nullable = false)
    private String device;

    private String address;

    private Double latitue;

    private Double logitue;

    private String ISO;

    private String shutter_speed;

    private String f_number;

}
