package com.example.enity.model;

import com.example.enity.audit.Auditable;
import com.example.enity.listener.BadgeEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(BadgeEntityListener.class)
@Getter
@Setter
@ToString
public class Badge  extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private Character status;
}
