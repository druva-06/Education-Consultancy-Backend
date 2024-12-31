package com.consultancy.education.validations;

import com.consultancy.education.model.College;

import java.util.Objects;

public class CollegeValidations {

    public static Boolean validateCollegeData(College existingCollege, College college){
        if (existingCollege != null && college != null) {
            return Objects.equals(existingCollege.getCampus(), college.getCampus()) &&
                    Objects.equals(existingCollege.getName(), college.getName()) &&
                    Objects.equals(existingCollege.getCountry(), college.getCountry()) &&
                    Objects.equals(existingCollege.getCollegeLogo(), college.getCollegeLogo()) &&
                    Objects.equals(existingCollege.getCampusGalleryVideoLink(), college.getCampusGalleryVideoLink()) &&
                    Objects.equals(existingCollege.getDescription(), college.getDescription()) &&
                    Objects.equals(existingCollege.getEligibilityCriteria(), college.getEligibilityCriteria()) &&
                    Objects.equals(existingCollege.getEstablishedYear(), college.getEstablishedYear()) &&
                    Objects.equals(existingCollege.getRanking(), college.getRanking());
        }

        return false;
    }
}
