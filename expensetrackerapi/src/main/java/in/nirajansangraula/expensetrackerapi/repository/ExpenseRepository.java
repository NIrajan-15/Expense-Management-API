package in.nirajansangraula.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import in.nirajansangraula.expensetrackerapi.entity.*;
import  java.sql.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    // SELECT * FROM Expense WHERE user_id = ? AND category = ?;
    Page<Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);

    // SELECT * FROM Expense WHERE user_id = ? AND name LIKE %?%;
    Page<Expense> findByUserIdAndNameContaining(Long userId, String name, Pageable page);

    // SELECT * FROM Expense WHERE user_id = ? AND date BETWEEN ? AND ?;
    Page<Expense> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate, Pageable page); 

    // SELECT * FROM Expense WHERE user_id = ?;
    Page<Expense> findByUserId(Long userId, Pageable pageable);

    // SELECT * FROM Expense WHERE user_id = ? AND id = ?;
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);


}
