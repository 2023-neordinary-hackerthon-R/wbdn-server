package neordinaryr.wbdn.repository;

import neordinaryr.wbdn.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Long countByPostId(Long postId);
}
