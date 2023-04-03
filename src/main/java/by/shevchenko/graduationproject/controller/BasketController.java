package by.shevchenko.graduationproject.controller;

import by.shevchenko.graduationproject.entity.BasketEntity;
import by.shevchenko.graduationproject.entity.ProductEntity;
import by.shevchenko.graduationproject.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("basket")
@Tag(name = "Basket controller", description = "Basket controller is designed to work with the user's basket")
@SecurityRequirement(name = "JWT")
public class BasketController {
    private final BasketService basketService;

    @Operation(summary = "add product to basket", description = "this method return list <ProductEntity>",
            responses = @ApiResponse(responseCode = "200"
                    , description = "List of product"
                    , content = {@Content(mediaType = "application/json"
                    , array = @ArraySchema(schema = @Schema(implementation = BasketEntity.class)))}))
    @PostMapping("{productId}/{userId}")
    public List<ProductEntity> addToBasket(@PathVariable Long productId, @PathVariable Long userId) {
        return basketService.addToBasket(productId, userId);
    }

    @Operation(summary = "Removes a product from basket"
            , description = "method returns nothing"
            , responses = {@ApiResponse(responseCode = "200"
            , content = {@Content(mediaType = "application/json"
            , schema = @Schema(implementation = BasketEntity.class))})})
    @DeleteMapping("{id}/{basketId}")
    public List<ProductEntity> deleteFromBasket(@PathVariable Long id, @PathVariable Long basketId) {
        return basketService.deleteFromBasket(id, basketId);
    }
}
