package neordinaryr.wbdn.domain;


import jakarta.persistence.*;
import lombok.*;
import neordinaryr.wbdn.domain.common.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Member")
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(nullable = false,length = 20, unique = true)
    private String login_id;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String nickname;

}
