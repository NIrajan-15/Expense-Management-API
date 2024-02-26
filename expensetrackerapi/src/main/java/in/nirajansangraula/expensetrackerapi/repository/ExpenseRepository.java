package in.nirajansangraula.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import in.nirajansangraula.expensetrackerapi.entity.*;
import  java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    // SELCET * FROM expense WHERE category = ?;
    Page<Expense> findByCategory(String category, Pageable page);

    // SELECT * FROM Expense WHERE name like %?%;
    Page<Expense> findByNameContaining(String name, Pageable page);

    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page); 
}
