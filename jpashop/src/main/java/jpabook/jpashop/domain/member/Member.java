package jpabook.jpashop.domain.member;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    Address address;

    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();
}
