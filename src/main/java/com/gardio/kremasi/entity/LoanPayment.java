package com.gardio.kremasi.entity;

import com.gardio.kremasi.constant.PathDb;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = PathDb.LOAN_PAYMENT)
@Builder
public class LoanPayment extends AuditEntity{
    private Long amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "loan_detail_id")
    private LoanDetail loanDetail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Jakarta")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;



    @PrePersist
    protected void onCreate(){
        date = new Date();
    }

    public String getLoanDetailId() {
        return loanDetail != null ? loanDetail.getId() : null;
    }
}
