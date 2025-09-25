package com.albertoandraul.arfit.service;

import com.albertoandraul.arfit.dto.FollowDTO;
import com.albertoandraul.arfit.model.Follow;
import com.albertoandraul.arfit.model.User;
import com.albertoandraul.arfit.repository.FollowRepository;
import com.albertoandraul.arfit.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public FollowService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public String follow(String username, Long userIdToFollow) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        User userToFollow = userRepository.findById(userIdToFollow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario a seguir no encontrado"));

        if (currentUser.getId().equals(userToFollow.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes seguirte a ti mismo");
        }

        if (followRepository.existsByFollower_IdAndFollowed_Id(currentUser.getId(), userToFollow.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya sigues a este usuario");
        }

        Follow follow = new Follow();
        follow.setFollower(currentUser);
        follow.setFollowed(userToFollow);
        follow.setCreatedAt(LocalDateTime.now());

        followRepository.save(follow);

        return "Ahora sigues a: " + userToFollow.getUsername();
    }

    public String unfollow(String username, Long userIdToUnfollow) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        User userToUnfollow = userRepository.findById(userIdToUnfollow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario a dejar de seguir no encontrado"));

        Follow follow = followRepository.findByFollower_IdAndFollowed_Id(currentUser.getId(), userToUnfollow.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No sigues a este usuario"));

        followRepository.delete(follow);
        return "Has dejado de seguir a: " + userToUnfollow.getUsername();
    }

    public List<FollowDTO> getFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Follow> followers = followRepository.findByFollowed_Id(userId);
        return followers.stream()
                .map(f -> new FollowDTO(f.getId(), f.getFollower().getUsername(), f.getFollowed().getUsername(), f.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<FollowDTO> getFollowing(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<Follow> following = followRepository.findByFollower_Id(userId);
        return following.stream()
                .map(f -> new FollowDTO(f.getId(), f.getFollower().getUsername(), f.getFollowed().getUsername(), f.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public boolean isFollowing(String username, Long targetUserId) {
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        return followRepository.existsByFollower_IdAndFollowed_Id(currentUser.getId(), targetUserId);
    }

    public int countFollowers(Long userId) {
        return followRepository.countByFollowed_Id(userId);
    }

    public int countFollowing(Long userId) {
        return followRepository.countByFollower_Id(userId);
    }
}
