package com.consultancy.education.repository.custom.impl;

import com.consultancy.education.DTOs.requestDTOs.search.SearchCourseRequestDto;
import com.consultancy.education.DTOs.responseDTOs.collegeCourse.CollegeCourseResponseDto;
import com.consultancy.education.DTOs.responseDTOs.search.SearchCourseResponseDto;
import com.consultancy.education.repository.custom.CollegeCourseRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.stream.Collectors;

public class CollegeCourseRepositoryCustomImpl implements CollegeCourseRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchCourseResponseDto<CollegeCourseResponseDto> searchCollegeCourses(SearchCourseRequestDto searchCourseRequestDto) {
        // Get the total rows count
        long totalRows = getCountQuery(searchCourseRequestDto);

        // Calculate the total pages
        int limitPerPage = searchCourseRequestDto.getPagination().getSize();
        int totalPages = (int) Math.ceil((double) totalRows / limitPerPage);

        // Get the paginated query results
        List<CollegeCourseResponseDto> result = getPaginatedQuery(searchCourseRequestDto);

        // Create a Pagination object
        SearchCourseResponseDto.Pagination pagination = new SearchCourseResponseDto.Pagination(
                searchCourseRequestDto.getPagination().getPage(),
                limitPerPage,
                totalPages,
                totalRows
        );

        // Create the final response object
        SearchCourseResponseDto<CollegeCourseResponseDto> response = new SearchCourseResponseDto<>();
        response.setData(result);
        response.setPagination(pagination);

        return response;
    }

    private long getCountQuery(SearchCourseRequestDto searchCourseRequestDto) {
        // Build the count query dynamically based on the filters
        StringBuilder countQueryStr = new StringBuilder("SELECT COUNT(DISTINCT cc.id) " +
                "FROM college_courses cc " +
                "INNER JOIN colleges clg ON clg.id = cc.college_id " +
                "INNER JOIN courses crs ON crs.id = cc.course_id WHERE 1=1 ");

        // Add conditions for the count query
        addFilterConditions(countQueryStr, searchCourseRequestDto);

        // Execute the count query
        Query countQuery = entityManager.createNativeQuery(countQueryStr.toString());

        // Set parameters dynamically for the count query
        setQueryParameters(countQuery, searchCourseRequestDto);

        // Get the total row count
        return ((Number) countQuery.getSingleResult()).longValue();
    }

    private List<CollegeCourseResponseDto> getPaginatedQuery(SearchCourseRequestDto searchCourseRequestDto) {
        // Build the query dynamically for the paginated results
        StringBuilder queryStr = new StringBuilder("SELECT DISTINCT " +
                "cc.id AS collegeCourseId, " +
                "cc.college_id AS collegeId, " +
                "cc.course_id AS courseId, " +
                "clg.name AS collegeName, " +
                "crs.name AS courseName " +
                "FROM college_courses cc " +
                "INNER JOIN colleges clg ON clg.id = cc.college_id " +
                "INNER JOIN courses crs ON crs.id = cc.course_id WHERE 1=1 ");

        // Add conditions for the main query
        addFilterConditions(queryStr, searchCourseRequestDto);

        // Add pagination if available
        if (searchCourseRequestDto.getPagination() != null) {
            int size = searchCourseRequestDto.getPagination().getSize();
            int offset = (searchCourseRequestDto.getPagination().getPage() - 1) * size;
            queryStr.append(" LIMIT ").append(size).append(" OFFSET ").append(offset);
        }

        // Execute the query and set parameters dynamically
        Query query = entityManager.createNativeQuery(queryStr.toString(), CollegeCourseResponseDto.class);

        // Set parameters dynamically for the main query
        setQueryParameters(query, searchCourseRequestDto);

        // Get the paginated result list
        return query.getResultList();
    }

    private void addFilterConditions(StringBuilder queryStr, SearchCourseRequestDto searchCourseRequestDto) {
        // Add filter conditions dynamically based on the filters
        for (SearchCourseRequestDto.Filters.Filter filter : searchCourseRequestDto.getFilters().getFilters()) {
            String table = filter.getTable();
            String column = filter.getColumn();
            String value = filter.getValue();

            if (value != null && !value.isEmpty()) {
                if ("courses".equalsIgnoreCase(table)) {
                    queryStr.append("AND crs.").append(column).append(" LIKE :").append(column).append(" ");
                } else if ("colleges".equalsIgnoreCase(table)) {
                    queryStr.append("AND clg.").append(column).append(" LIKE :").append(column).append(" ");
                }
            }
        }

        if (searchCourseRequestDto.getFilters().getCountries() != null && !searchCourseRequestDto.getFilters().getCountries().isEmpty()) {
            queryStr.append("AND clg.country IN (:countries) ");
        }
    }

    private void setQueryParameters(Query query, SearchCourseRequestDto searchCourseRequestDto) {
        // Set parameters dynamically based on filters
        if (searchCourseRequestDto.getFilters() != null) {
            if (searchCourseRequestDto.getFilters().getFilters() != null) {
                for (SearchCourseRequestDto.Filters.Filter filter : searchCourseRequestDto.getFilters().getFilters()) {
                    String value = filter.getValue();
                    if (value != null && !value.isEmpty()) {
                        query.setParameter(filter.getColumn(), "%" + value + "%");
                    }
                }
            }

            if (searchCourseRequestDto.getFilters().getCountries() != null && !searchCourseRequestDto.getFilters().getCountries().isEmpty()) {
                query.setParameter("countries", searchCourseRequestDto.getFilters().getCountries().stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()));
            }
        }
    }
}
