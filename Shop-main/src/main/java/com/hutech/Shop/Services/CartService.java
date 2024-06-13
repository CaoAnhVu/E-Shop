package com.hutech.Shop.Services;

import com.hutech.Shop.model.CartItem;
import com.hutech.Shop.model.Product;
import com.hutech.Shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        // Check if the requested quantity is available
        int availableQuantity = product.getNums();
        if (quantity > availableQuantity) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock for product: " + productId);
        }

        boolean productFound = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(productId)) {
                int newQuantity = cartItem.getQuantity() + quantity;
                if (newQuantity > availableQuantity) {
                    throw new IllegalArgumentException("Requested quantity exceeds available stock for product: " + productId);
                }
                cartItem.setQuantity(newQuantity);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public void incrementQuantity(Long productId) {
        changeQuantity(productId, 1);
    }

    public void decrementQuantity(Long productId) {
        changeQuantity(productId, -1);
    }

    private void changeQuantity(Long productId, int delta) {
        CartItem item = findCartItemByProductId(productId);
        if (item == null) {
            throw new IllegalArgumentException("Product not in cart: " + productId);
        }

        int newQuantity = item.getQuantity() + delta;
        if (newQuantity <= 0) {
            cartItems.remove(item);
        } else {
            Product product = item.getProduct();
            if (newQuantity > product.getNums()) {
                throw new IllegalArgumentException("Requested quantity exceeds available stock for product: " + productId);
            }
            item.setQuantity(newQuantity);
        }
    }

    private CartItem findCartItemByProductId(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == (productId));  }
    public void clearCart() {
        cartItems.clear();
    }
}