package in.nirajansangraula.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nirajansangraula.expensetrackerapi.entity.*;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
