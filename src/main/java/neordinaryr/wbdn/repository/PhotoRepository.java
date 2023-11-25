package neordinaryr.wbdn.repository;

import neordinaryr.wbdn.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    
}
