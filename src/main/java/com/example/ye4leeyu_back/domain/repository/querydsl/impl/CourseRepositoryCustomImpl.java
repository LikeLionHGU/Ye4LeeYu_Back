package com.example.ye4leeyu_back.domain.repository.querydsl.impl;

import com.example.ye4leeyu_back.domain.entity.Course;
import com.example.ye4leeyu_back.domain.entity.QCourse;
import com.example.ye4leeyu_back.domain.repository.querydsl.CourseRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Course> findByFilters(String searchWord, String city, List<String> district, List<String> sportType, List<String> disabilityType, List<LocalDate> date, Integer price, Pageable pageable) {

        QCourse course = QCourse.course;
        BooleanBuilder builder = new BooleanBuilder();

        if(searchWord != null) {
            builder.and(isContainsSearchWord(searchWord));
        }

        if(city != null) {
            builder.and(isContainsCity(city));
        }

        if(district != null) {
            builder.and(isInAndContainsDistrict(district));
        }

        if(sportType != null) {
            builder.and(isInSportType(sportType));
        }

        if(disabilityType != null) {
            builder.and(isInDisabilityType(disabilityType));
        }

        if(date != null) {
            builder.and(isInDate(date));
        }

        if(price != null) {
            builder.and(lessThanOrEqualToPrice(price));
        }

        List<Course> courseList = jpaQueryFactory
                .selectFrom(course)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = jpaQueryFactory
                .selectFrom(course)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(courseList, pageable, totalCount);

    }

    public BooleanExpression isContainsSearchWord(String searchWord) {
        return QCourse.course.title.contains(searchWord);
    }

    public BooleanExpression isContainsCity(String city) {
        return QCourse.course.location.contains(city);
    }

    public BooleanExpression isInAndContainsDistrict(List<String> district) {
        BooleanExpression result = Expressions.FALSE;
        for (String districtName: district) {
            result = result.or(QCourse.course.location.contains(districtName));
        }

        return QCourse.course.location.in(district);
    }

    public BooleanExpression isInSportType(List<String> sportType) {
        return QCourse.course.sportType.in(sportType);
    }

    public BooleanExpression isInDisabilityType(List<String> disabilityType) {
        return QCourse.course.possibleDisabilityTypeList.any().disabilityType.in(disabilityType);
    }

    public BooleanExpression isInDate(List<LocalDate> date) {
        return QCourse.course.courseBlockList.any().date.in(date);
    }

    public BooleanExpression lessThanOrEqualToPrice(int price) {
        return QCourse.course.price.loe(price);
    }

}
