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

    List<TravelPlan> findByAlreadyExisted(boolean alreadyExisted);

    default List<TravelPlan> findTravelPlanByTags(int area, int scene, int season, int suitAge, int category, boolean alreadyExisted) {
        return findAll(buildTagsSpecification(area,scene,season,suitAge,category, alreadyExisted));
    }

    default Specification<TravelPlan> buildTagsSpecification(int area, int scene, int season, int suitAge, int category, boolean alreadyExisted) {
        return new Specification<TravelPlan>() {
            public Predicate toPredicate(Root<TravelPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(area != 0){
                    predicates.add(cb.equal(root.<Integer>get("area"),area));
                }
                if(scene != 0){
                    predicates.add(cb.equal(root.<Integer>get("scene"),scene));
                }
                if(season != 0){
                    predicates.add(cb.equal(root.<Integer>get("season"),season));
                }
                if(suitAge != 0){
                    predicates.add(cb.equal(root.<Integer>get("suitAge"),suitAge));
                }
                if(category != 0){
                    predicates.add(cb.equal(root.<Integer>get("category"),category));
                }
                predicates.add(cb.equal(root.<Boolean>get("alreadyExisted"),alreadyExisted));
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
    }
}
