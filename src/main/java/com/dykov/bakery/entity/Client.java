package com.dykov.bakery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Setter
@Getter
@NoArgsConstructor
@ToString
@FieldNameConstants
@Entity
@Table(name = "b_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "name")
    private String name;

    @Column(name = "address", nullable = false)
    private String address;



    @OneToMany(mappedBy = Order.Fields.client)
    private Set<Order> orders;
}
