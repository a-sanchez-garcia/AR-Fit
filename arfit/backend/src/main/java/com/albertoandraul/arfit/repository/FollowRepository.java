package com.albertoandraul.arfit.repository;

import com.albertoandraul.arfit.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollower_IdAndFollowed_Id(Long followerId, Long followedId);

    int countByFollowed_Id(Long followedId);

    int countByFollower_Id(Long followerId);

    boolean existsByFollower_IdAndFollowed_Id(Long followerId, Long followedId);

    List<Follow> findByFollower_Id(Long followerId);
    List<Follow> findByFollowed_Id(Long followedId);
}
