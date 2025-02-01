package com.wafflebank.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class AccountEntity extends BaseEntity {
    @Id
    private long accountNumber;
    private long customerId;
    private String accountType;
    private String mobileNumber;
}
