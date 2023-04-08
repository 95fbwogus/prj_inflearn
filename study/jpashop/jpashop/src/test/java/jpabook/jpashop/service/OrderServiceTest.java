package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("재현 JPA", 12000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문 시 상태는 Order", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문 상품 종류 확인", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격 확인", 12000 * orderCount, getOrder.getTotalPrice());
        assertEquals("재고 줄어듦 확인", 8, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("재현 JPA", 12000, 10);
        int orderCount = 11;

        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("재고 수량 초과");
    }
    
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("재현 JPA", 12000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소 상태 : CANCEL", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("재고 원복", 10, book.getStockQuantity());
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}