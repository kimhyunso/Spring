package com.example.chapter12.service;

import com.example.chapter12.domain.Delivery;
import com.example.chapter12.domain.Member;
import com.example.chapter12.domain.Order;
import com.example.chapter12.domain.OrderItem;
import com.example.chapter12.domain.item.Item;
import com.example.chapter12.repository.MemberRepository;
import com.example.chapter12.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) throws Exception{
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new RuntimeException("조회하는 회원이 없습니다." + memberId));

        Item item = itemService.findOne(itemId);

        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem =
                OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문하신 정보가 없습니다." + orderId));

        order.cancel();
    }


    // JPQL
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }

}
