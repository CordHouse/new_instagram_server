package com.example.new_instagram_server.user.application.port.out;

import com.example.new_instagram_server.user.domain.User;

import java.util.Optional;

// 데이터베이스에 대한 인터페이스를 커스텀(queryDSL)할 경우 구현체는 어댑터 out 패키지에 생성한다.
// 의존 역전을 위해 UserRepositoryImpl 클래스가 UserRepository 를 구현시킨다.
// UserRepository 의 경우 도메인 명칭인 User 이외 다른 명칭이 쓰일 일은 없지만, 제네릭의 사용으로 다른 경우 모든 상황에 유연하게
// 대응할 수 있다는 장점이 있다.
public interface UserRepository {
    void save(User user); // 데이터 베이스에 저장

    Optional<User> findById(long id);

    Optional<User> findByNickname(String nickname);
    void delete(User user);
}
