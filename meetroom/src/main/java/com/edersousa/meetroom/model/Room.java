package com.edersousa.meetroom.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "meetroom")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "startHour", nullable = false)
    private String startHour;

    @Column(name = "endHour", nullable = false)
    private String endHour;

}
