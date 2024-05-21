package challenge.preonboarding.wanted.member.domain;

import challenge.preonboarding.wanted.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("회원")
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx")
    private Long memberIdx;

    @Column(length = 20)
    private String id;

    @Column(length = 60)
    private String password;

    @Column
    private String name;
}
