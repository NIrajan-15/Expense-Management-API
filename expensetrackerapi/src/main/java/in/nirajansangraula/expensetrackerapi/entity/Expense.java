package in.nirajansangraula.expensetrackerapi.entity;

import java.sql.Date;


import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="expense_name")
    @NotBlank(message="Expense name must not be null")
    @Size(min=3, max=50, message="Expense name must be between 3 and 50 characters long")
    private String name;


    private String description;

    @Column(name="expense_amount")
    @NotBlank(message="Expense amount must not be null")
    private BigDecimal amount;

    @NotBlank(message = "Category must not be null")
    private String category;

    @NotNull(message = "Date must not be null")
    private Date date;

     
    @Column(name="created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private Date updatedAt;
    
}

