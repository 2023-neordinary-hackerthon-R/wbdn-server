package neordinaryr.wbdn.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import neordinaryr.wbdn.domain.dto.response.PostListDto.PostListMapDto;
import neordinaryr.wbdn.repository.CustomPostRepository;
import org.qlrm.mapper.JpaResultMapper;

public class CustomPostRepositoryImpl implements CustomPostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostListMapDto> findByLocation(Double currentLat, Double currentLon, Double upperRightLat, Double upperRightLon) {
        Query nativeQuery = entityManager.createNativeQuery(
                                             "select "
                                                 + "p.post_id as postId, "
                                                 + "pp.photo_url as photoUrl, "
                                                 + "m.nickname as nickname, "
                                                 + "p.likes as likes, "
                                                 + "p.latitude, "
                                                 + "p.longitude "
                                                 + "from post as p "
                                                 + "join member m on p.member_id = m.member_id "
                                                 + "join photo as pp on p.post_id = pp.post_id "
                                                 + "where st_distance_sphere(point(:currentLon, :currentLat), point(p.longitude, p.latitude)) <= "
                                                 + "st_distance_sphere(point(:upperRightLon, :upperRightLat), point(:currentLon, :currentLat))")
                                         .setParameter("currentLat", currentLat)
                                         .setParameter("currentLon", currentLon)
                                         .setParameter("upperRightLat", upperRightLon)
                                         .setParameter("upperRightLon", upperRightLon);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(nativeQuery, PostListMapDto.class);
    }
}
