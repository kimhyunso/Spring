package com.example.chapter11.service;

import com.example.chapter11.domain.Address;
import com.example.chapter11.domain.Member;
import com.example.chapter11.domain.Order;
import com.example.chapter11.domain.enums.OrderStatus;
import com.example.chapter11.domain.item.Book;
import com.example.chapter11.domain.item.Item;
import com.example.chapter11.repository.ItemRepository;
import com.example.chapter11.repository.MemberRepository;
import com.example.chapter11.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrderServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("상품주문 : order")
    @Test
    public void 상품주문() throws Exception{

        // given
        Member member = createMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * 2, getOrder.getTotalPrice());
        // assertEquals(8, item.getStockQuantity());
    }

    @DisplayName("상품주문 재고수량 초과 : order stock over")
    @Test
    public void 상품주문_재고수량초과() throws Exception{
        // given
        Member member = createMember();
        Item item = createBook("AAA", 10000, 10);
        int orderCount = 11;
        // when
        orderService.order(member.getId(), item.getId(), orderCount);

        // then
        fail("재고 수량 부족 예외 발생");
    }

    @DisplayName("주문취소 : cancel")
    @Test
    public void 주문취소() throws Exception{
        // given
        Member member = createMember();
        Item item = createBook("Ccc", 10000, 10);
        int orderCount = 2;
        // when

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);

        // then
        Order getOrder = orderRepository.findById(orderId).get();

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());
    }


    private Member createMember(){
        Member member = new Member();
        member.setName("회원D");
        member.setAddress(new Address("서울", "구로구", "1234123"));
        memberService.join(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        itemService.saveItem(book);
        return book;
    }

}