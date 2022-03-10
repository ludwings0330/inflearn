package jpabook.jpashop;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    static class InitService {

        @PersistenceContext
        private EntityManager em;

        public void dbInit1() {
            final Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            final Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            final Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            final Order order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2);

            em.persist(order);
        }

        public void dbInit2() {
            final Member member = createMember("userB", "서울", "1", "1111");
            em.persist(member);

            final Book book1 = createBook("Spring1 BOOK", 10000, 100);
            em.persist(book1);

            final Book book2 = createBook("Spring2 BOOK", 20000, 100);
            em.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            final Order order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2);

            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            final Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            final Book book = new Book();

            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);

            return book;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }

}
