package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
    private final EntityManager em;
    public List<OrderQueryDTO> findOrderQueryDTOs() {
        List<OrderQueryDTO> result = findOrders();

        result.forEach(o->{
            List<OrderItemQueryDTO> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });

        return result;
    }

    private List<OrderItemQueryDTO> findOrderItems(Long orderId) {
        return em.createQuery(
"select new jpabook.jpashop.repository.order.query.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)" +
        " from OrderItem oi" +
        " join oi.item i" +
        " where oi.order.id =: orderId", OrderItemQueryDTO.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDTO> findOrders() {
        return em.createQuery(
"select new jpabook.jpashop.repository.order.query.OrderQueryDTO(o.id, m.name, o.orderDate, o.status, d.address)" +
        " from Order o" +
        " join o.member m" +
        " join o.delivery d", OrderQueryDTO.class).getResultList();
    }

    public List<OrderQueryDTO> findAllByDTO_optimization() {
        List<OrderQueryDTO> result = findOrders();
        List<OrderItemQueryDTO> orderItems = findOrderItemMap(toOrderIds(result));
        Map<Long, List<OrderItemQueryDTO>> orderItemMap
                = orderItems.stream().collect(Collectors.groupingBy(oiQueryDto -> oiQueryDto.getOrderId()));
        //orderItems.stream().collect(Collectors.groupingBy(OrderItemQueryDTO::getOrderId));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    private static List<Long> toOrderIds(List<OrderQueryDTO> result) {
        return result.stream().map(o -> o.getOrderId()).collect(Collectors.toList());
    }

    private List<OrderItemQueryDTO> findOrderItemMap(List<Long> orderIds) {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.query.OrderItemQueryDTO(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                                " from OrderItem oi" +
                                " join oi.item i" +
                                " where oi.order.id in :orderIds", OrderItemQueryDTO.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
    }

    public List<OrderFlatDTO> findAllByDTO_flat() {
        return em.createQuery(
"select new" +
        " jpabook.jpashop.repository.order.query.OrderFlatDTO(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)" +
        " from Order o" +
        " join o.member m" +
        " join o.delivery d" +
        " join o.orderItems oi" +
        " join oi.item i", OrderFlatDTO.class)
                .getResultList();
    }
}
