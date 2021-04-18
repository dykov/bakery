package com.dykov.bakery.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
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
@Table(name = "b_order_items")
public class OrderItem {

    @EmbeddedId
    OrderItemId id;

    @MapsId(value = OrderItemId.Fields.orderId )
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @MapsId(value = OrderItemId.Fields.itemId)
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private int amount;

}

