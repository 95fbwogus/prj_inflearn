package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDTO;
import jpabook.jpashop.repository.order.query.OrderItemQueryDTO;
import jpabook.jpashop.repository.order.query.OrderQueryDTO;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        //lazy 초기화
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            //orderItems.stream().forEach(o -> o.getItem().getName());
        }

        return all;
    }

    @GetMapping("/api/v2/orders")
    public Result ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDTO> collect = orders.stream()
                                        .map(o -> new OrderDTO(o))
                                        .collect(toList());

        return new Result(collect);
    }

    @GetMapping("/api/v3/orders")
    public Result ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrderDTO> collect = orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(toList());

        return new Result(collect);
    }

    @GetMapping("/api/v3.1/orders")
    public Result ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDTO> collect = orders.stream()
                .map(o -> new OrderDTO(o))
                .collect(toList());

        return new Result(collect);
    }

    @GetMapping("/api/v4/orders")
    public Result ordersV4() {
        return new Result(orderQueryRepository.findOrderQueryDTOs());
    }

    @GetMapping("/api/v5/orders")
    public Result ordersV5() {
        return new Result(orderQueryRepository.findAllByDTO_optimization());
    }

    @GetMapping("/api/v6/orders")
    public Result ordersV6() {
        List<OrderFlatDTO> flats = orderQueryRepository.findAllByDTO_flat();
        return new Result(
                flats.stream()
                .collect(groupingBy(o -> new OrderQueryDTO(o.getOrderId(),
                                o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDTO(o.getOrderId(),
                                o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
                )).entrySet().stream()
                .map(e -> new OrderQueryDTO(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                        e.getKey().getAddress(), e.getValue()))
                .collect(toList())
        );

    }

    @Data
    static class OrderDTO {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private Address address;
        private List<OrderItemDTO> orderItems;

        public OrderDTO(Order o) {
            this.orderId = o.getId();
            this.name = o.getMember().getName();
            this.orderDate = o.getOrderDate();
            this.address = o.getDelivery().getAddress();
            this.orderItems = o.getOrderItems().stream()
                                .map(orderItem -> new OrderItemDTO(orderItem))
                                .collect(toList());
        }
    }

    @Data
    static class OrderItemDTO {

        private String itemName; //상품명
        private int orderPrice; //주문 가격
        private int count; //주문 수량
        public OrderItemDTO(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private List<T> data;
    }
}
