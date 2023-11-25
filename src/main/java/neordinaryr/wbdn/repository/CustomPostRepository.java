package neordinaryr.wbdn.repository;

import java.util.List;
import neordinaryr.wbdn.domain.dto.response.PostListDto;

public interface CustomPostRepository {

    List<PostListDto.PostListMapDto> findByLocation(Double currentLat, Double currentLon, Double upperRightLat, Double upperRightLon);

}
