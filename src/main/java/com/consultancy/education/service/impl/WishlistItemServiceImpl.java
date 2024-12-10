package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.responseDTOs.wishlistItem.WishlistItemResponseDto;
import com.consultancy.education.model.CollegeCourse;
import com.consultancy.education.model.Student;
import com.consultancy.education.model.Wishlist;
import com.consultancy.education.model.WishlistItem;
import com.consultancy.education.repository.CollegeCourseRepository;
import com.consultancy.education.repository.StudentCollegeCourseRegistrationRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.repository.WishlistItemRepository;
import com.consultancy.education.service.WishlistItemService;
import com.consultancy.education.transformer.WishlistItemTransformer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistItemServiceImpl implements WishlistItemService {

    private final WishlistItemRepository wishlistItemRepository;
    private final StudentRepository studentRepository;
    private final CollegeCourseRepository collegeCourseRepository;

    public WishlistItemServiceImpl(WishlistItemRepository wishlistItemRepository, StudentRepository studentRepository, CollegeCourseRepository collegeCourseRepository) {
        this.wishlistItemRepository = wishlistItemRepository;
        this.studentRepository = studentRepository;
        this.collegeCourseRepository = collegeCourseRepository;
    }

    @Override
    public WishlistItemResponseDto addWishlistItem(Long studentId, Long collegeCourseId) {
        if(studentRepository.findById(studentId).isEmpty()){
            throw new EntityNotFoundException("Student is not found");
        }
        if(collegeCourseRepository.findById(collegeCourseId).isEmpty()){
            throw new EntityNotFoundException("College course is not found");
        }

        Student student = studentRepository.findById(studentId).get();
        Wishlist wishlist = student.getWishlist();

        WishlistItem wishlistItem = new WishlistItem();

        wishlistItem.setWishlist(wishlist);
        wishlistItem.setCollegeCourse(collegeCourseRepository.findById(collegeCourseId).get());
        wishlist.getWishlistItems().add(wishlistItem);

        wishlistItem = wishlistItemRepository.save(wishlistItem);
        return WishlistItemTransformer.toResDTO(wishlistItem, studentId);
    }

    @Override
    public WishlistItemResponseDto removeWishlistItem(Long wishlistItemId) {
        if(wishlistItemRepository.findById(wishlistItemId).isEmpty()){
            throw new EntityNotFoundException("Wishlist item is not found");
        }
        WishlistItem wishlistItem = wishlistItemRepository.findById(wishlistItemId).get();
        wishlistItemRepository.deleteById(wishlistItemId);
        return WishlistItemTransformer.toResDTO(wishlistItem, wishlistItem.getWishlist().getStudent().getId());
    }

    @Override
    public List<WishlistItemResponseDto> getWishlistItems(Long studentId) {
        if(studentRepository.findById(studentId).isEmpty()){
            throw new EntityNotFoundException("Student is not found");
        }
        Student student = studentRepository.findById(studentId).get();
        Wishlist wishlist = student.getWishlist();
        return WishlistItemTransformer.toResDTO(wishlist.getWishlistItems(), studentId);
    }
}
