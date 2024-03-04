package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Recipedetail;

@Repository
public interface RecipeDetailDAO extends JpaRepository<Recipedetail, Integer>{
	public Recipedetail findByNo(int no); // 상세보기
}
