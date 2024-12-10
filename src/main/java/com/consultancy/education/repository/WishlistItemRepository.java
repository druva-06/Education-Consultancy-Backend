package com.consultancy.education.repository;

import com.consultancy.education.model.Wishlist;
import com.consultancy.education.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
}
