package com.consultancy.education.DTOs.requestDTOs.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRequestDto {
    Pagination pagination;
    List<Sort> sort;
    Filters filters;
    Search search;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Pagination {
        int page;
        int size;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Sort {
        String field;
        String order;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Filters {
        String category;
        RatingFilter rating;
        DateRange dateAdded;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class RatingFilter {
            int gte;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class DateRange {
            String from;
            String to;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Search {
        String term;
        List<String> fields;
    }
}

