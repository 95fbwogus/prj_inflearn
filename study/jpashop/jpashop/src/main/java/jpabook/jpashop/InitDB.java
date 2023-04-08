package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Random;

/**
 *  총 주문 2개
 *
 *  UserA
 *      * JPA1 BOOK
 *      * JPA2 BOOK
 *  UserB
 *      * SPRING1 BOOK
 *      * SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        //initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit1() {


            Book book1 = createBook("JPA1", 10000, 10000000);
            em.persist(book1);

            Book book2 = createBook("JPA2", 20000, 20000000);
            em.persist(book2);

            Book book3 = createBook("SPRING1", 30000, 30000000);
            em.persist(book3);

            Book book4 = createBook("SPRING2", 40000, 20000000);
            em.persist(book4);

            StringBuffer sb = new StringBuffer();


            for(int i = 0; i < 300000 ; i++) {
                sb.delete(0, sb.length());
                sb.append("user").append(i);
                Random random = new Random();

                Member member = createMember(sb.toString(), "서울", getStreet(), String.valueOf((int)(Math.random() * (99999 - 10000 + 1)) + 10000));
                em.persist(member);


                OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, random.nextInt(5)+1);
                OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, random.nextInt(5)+1);
                OrderItem orderItem3 = null;
                OrderItem orderItem4 = null;
                if((random.nextInt(1000)+1) % 2 == 0) {
                    orderItem3 = OrderItem.createOrderItem(book3, book3.getPrice(), random.nextInt(5)+1);
                    orderItem4 = OrderItem.createOrderItem(book4, book4.getPrice(), random.nextInt(5)+1);
                }

                Delivery delivery = createDelivery(member);
                Order order = Order.createOrder(member, delivery, orderItem1, orderItem2, orderItem3, orderItem4);
                em.persist(order);
            }
        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

//        public void dbInit2() {
//            Member member = createMember("userB", "평양","2", "2222");
//            em.persist(member);
//
//            Delivery delivery = createDelivery(member);
//            Order order = Order.createOrder(member, delivery,orderItem1, orderItem2);
//            em.persist(order);
//        }

        private static Book createBook(String name, int price, int stock) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stock);
            return book1;
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private static String getStreet() {
            StringBuffer zipcode = new StringBuffer();
            Random random = new Random();
            return zipcode.append("street").append(random.nextInt(10000)+1).toString();
        }
    }
}


