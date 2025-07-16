package com.ecommerce.api.Controller;
import com.ecommerce.api.dto.CartRequest;
import com.ecommerce.api.entity.CartItem;
import com.ecommerce.api.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> viewCart(@AuthenticationPrincipal(expression = "username") String email) {
        return ResponseEntity.ok(cartService.getUserCart(email));
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@AuthenticationPrincipal(expression = "username") String email,
                                              @RequestBody CartRequest request) {
        return new ResponseEntity<>(cartService.addToCart(email, request), HttpStatus.CREATED);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateQuantity(@AuthenticationPrincipal(expression = "username") String email,
                                                   @PathVariable Long cartItemId,
                                                   @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateQuantity(email, cartItemId, quantity));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> removeItem(@AuthenticationPrincipal(expression = "username") String email,
                                           @PathVariable Long cartItemId) {
        cartService.removeItem(email, cartItemId);
        return ResponseEntity.noContent().build();
    }
}
