package ilknurdogan.servicefinder.repository;

import ilknurdogan.servicefinder.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByServiceProvider_Id(Long id);
}
