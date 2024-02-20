package in.nirajansangraula.expensetrackerapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import in.nirajansangraula.expensetrackerapi.entity.Expense;
import in.nirajansangraula.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepo;

    
    @Override
    public List<Expense> getAllExpenses() {
        
        return expenseRepo.findAll();
    }
}
