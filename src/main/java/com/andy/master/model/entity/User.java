package com.andy.master.model.entity;

import com.andy.master.model.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    private RoleType role;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Event> createdEvents;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Reservation> reservations;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Notification> notifications;
}
