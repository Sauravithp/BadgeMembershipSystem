package com.example.persistence.model;

import com.example.persistence.audit.Auditable;
import com.example.persistence.listener.BadgeEntityListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
