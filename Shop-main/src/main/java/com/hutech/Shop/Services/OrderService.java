package com.hutech.Shop.Services;

import com.hutech.Shop.model.CartItem;
import com.hutech.Shop.model.Order;
import com.hutech.Shop.model.OrderDetail;
import com.hutech.Shop.repository.OrderDetailRepository;
import com.hutech.Shop.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService  @Transactional
    public Order createOrder(String customerName, List<CartItem> cartItems) {  Order order = new Order();
        order.setCustomerName(customerName); // Gọi phương thức setCustomerName // Lưu đơn hàng vào cơ sở dữ liệu
        order = orderRepository.save(order);
        // Lưu các chi tiết đơn hàng vào cơ sở dữ liệu
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        // Xóa giỏ hàng sau khi đặt hàng (tùy chọn)
        cartService.clearCart();
        return order;
    }
}