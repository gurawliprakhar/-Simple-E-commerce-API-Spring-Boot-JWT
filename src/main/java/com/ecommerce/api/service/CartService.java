package com.ecommerce.api.service;
import com.ecommerce.api.dto.CartRequest;
import com.ecommerce.api.entity.CartItem;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.entity.User;
import com.ecommerce.api.repository.CartItemRepository;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<CartItem> getUserCart(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return cartItemRepository.findByUser(user);
    }

    public CartItem addToCart(String email, CartRequest request) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existing = cartItemRepository.findByUser(user).stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            return cartItemRepository.save(item);
        }

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    public CartItem updateQuantity(String email, Long cartItemId, int quantity) {
        User user = userRepository.findByEmail(email).orElseThrow();
        CartItem item = cartItemRepository.findById(cartItemId)
                .filter(i -> i.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    public void removeItem(String email, Long cartItemId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        CartItem item = cartItemRepository.findById(cartItemId)
                .filter(i -> i.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new RuntimeException("Item not found"));
        cartItemRepository.delete(item);
    }
}

