package com.consultancy.education.controller;
import com.consultancy.education.DTOs.responseDTOs.wishlistItem.WishlistItemResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.response.ApiFailureResponse;
import com.consultancy.education.response.ApiSuccessResponse;
import com.consultancy.education.service.WishlistItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist-item")
public class WishlistItemController {

    private final WishlistItemService wishlistItemService;

    public WishlistItemController(WishlistItemService wishlistItemService) {
        this.wishlistItemService = wishlistItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWishlistItem(@RequestParam Long studentId, @RequestParam Long collegeCourseId) {
        try{
            WishlistItemResponseDto wishlistItemResponseDto = wishlistItemService.addWishlistItem(studentId, collegeCourseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiSuccessResponse<>(wishlistItemResponseDto, "College course added to cart successfully", 201));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @GetMapping("/getWishlistItems")
    public ResponseEntity<?> getWishlistItems(@RequestParam Long studentId) {
        try{
            List<WishlistItemResponseDto> wishlistItemResponseDtos = wishlistItemService.getWishlistItems(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(wishlistItemResponseDtos, "College course fetched successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }

    @DeleteMapping("/remove/{wishlistItemId}")
    public ResponseEntity<?> removeWishlistItem(@PathVariable Long wishlistItemId) {
        try{
            WishlistItemResponseDto wishlistItemResponseDto = wishlistItemService.removeWishlistItem(wishlistItemId);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiSuccessResponse<>(wishlistItemResponseDto, "College course removed from cart successfully", 200));
        }
        catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 404));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiFailureResponse<>(new ArrayList<>(), e.getMessage(), 500));
        }
    }
}
