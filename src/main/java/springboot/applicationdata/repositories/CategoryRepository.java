package springboot.applicationdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.applicationdata.entities.Category;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
