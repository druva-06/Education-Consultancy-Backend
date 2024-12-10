package com.consultancy.education.service;

import com.consultancy.education.DTOs.responseDTOs.wishlistItem.WishlistItemResponseDto;

import java.util.List;

public interface WishlistItemService {
    WishlistItemResponseDto addWishlistItem(Long studentId, Long studentCollegeCourseId);

    WishlistItemResponseDto removeWishlistItem(Long wishlistItemId);

    List<WishlistItemResponseDto> getWishlistItems(Long studentId);
}
