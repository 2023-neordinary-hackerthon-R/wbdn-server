package neordinaryr.wbdn.repository;

import neordinaryr.wbdn.domain.Post;
import neordinaryr.wbdn.domain.dto.response.PostListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByLikesDesc();
}
