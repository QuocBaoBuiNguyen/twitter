package com.twitter.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chats_seq")
    @SequenceGenerator(name="chats_seq", sequenceName = "chats_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "creation_date", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime creationDateTime = LocalDateTime.now();

    @OneToMany(mappedBy = "chat")
    private List<ChatParticipant> chatParticipants = new ArrayList<>();

    @OneToMany(mappedBy = "chat")
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
