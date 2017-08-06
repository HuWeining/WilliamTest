package com.example.demo.repository;

import com.example.demo.entity.travelResource.TravelPlan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

/**
 * Created by huweining on 2017/6/13.
 */
@Repository
public interface TravelPlanRepository extends CrudRepository<TravelPlan,Integer>, JpaSpecificationExecutor<TravelPlan>{
    List<TravelPlan> findByAreaAndSceneAndSeasonAndSuitAgeAndCategory(int area, int scene, int season, int suitAge, int category);

    default List<TravelPlan> findTravelPlanByTags(int area, int scene, int season, int suitAge, int category) {
        return findAll(buildTagsSpecification(area,scene,season,suitAge,category));
    }

    default Specification<TravelPlan> buildTagsSpecification(int area, int scene, int season, int suitAge, int category) {
        return new Specification<TravelPlan>() {
            public Predicate toPredicate(Root<TravelPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(area != 0){
                    predicates.add(cb.equal(root.<String>get("AREA"),area));
                }
                if(scene != 0){
                    predicates.add(cb.equal(root.<String>get("SCENE"),scene));
                }
                if(season != 0){
                    predicates.add(cb.equal(root.<String>get("SEASON"),season));
                }
                if(suitAge != 0){
                    predicates.add(cb.equal(root.<String>get("SUIT_AGE"),suitAge));
                }
                if(category != 0){
                    predicates.add(cb.equal(root.<String>get("CATEGORY"),category));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }
}
