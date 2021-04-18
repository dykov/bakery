package com.dykov.bakery.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@FieldNameConstants
@Embeddable
public class OrderItemId implements Serializable {
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "item_id")
    private UUID itemId;
}
