package com.example.new_instagram_server.chatroom.domain;

import com.example.new_instagram_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String lastMessage;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastSendAt;

    @PrePersist
    public void setDate() {
        this.lastSendAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiver;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupReceiver", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> groupReceiver;

    // 1:1 대화방
    public ChatRoom(User host, User receiver, String message) {
        this.host = host;
        this.receiver = receiver;
        this.lastMessage = message;
    }

    // 그룹 대화방
    public ChatRoom(User host, List<User> groupReceiver, String message) {
        this.host = host;
        this.groupReceiver = groupReceiver;
        this.lastMessage = message;
    }
}
