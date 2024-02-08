package com.example.new_instagram_server.user.port.out;

import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 데이터베이스에 대한 인터페이스를 커스텀(queryDSL)할 경우 구현체는 어댑터 out 패키지에 생성한다.
public interface UserRepository extends JpaRepository<User, Long> {
}
