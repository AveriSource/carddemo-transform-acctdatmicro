package com.mycompany.acctdatpkg.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Acctdat.
 */
@Entity
@Table(name = "acctdat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Acctdat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "acctdat_id")
    private Long acctdatId;

    @NotNull
    @Max(value = 11)
    @Column(name = "acct_id", nullable = false)
    private Integer acctId;

    @Size(max = 1)
    @Column(name = "acct_active_status", length = 1)
    private String acctActiveStatus;

    @DecimalMax(value = "12")
    @Column(name = "acct_curr_bal")
    private Double acctCurrBal;

    @DecimalMax(value = "12")
    @Column(name = "acct_credit_limit")
    private Double acctCreditLimit;

    @DecimalMax(value = "12")
    @Column(name = "acct_cash_credit_limit")
    private Double acctCashCreditLimit;

    @Size(max = 10)
    @Column(name = "acct_open_date", length = 10)
    private String acctOpenDate;

    @Size(max = 10)
    @Column(name = "acct_expiraion_date", length = 10)
    private String acctExpiraionDate;

    @Size(max = 10)
    @Column(name = "acct_reissue_date", length = 10)
    private String acctReissueDate;

    @DecimalMax(value = "12")
    @Column(name = "acct_curr_cyc_credit")
    private Double acctCurrCycCredit;

    @DecimalMax(value = "12")
    @Column(name = "acct_curr_cyc_debit")
    private Double acctCurrCycDebit;

    @Size(max = 10)
    @Column(name = "acct_addr_zip", length = 10)
    private String acctAddrZip;

    @Size(max = 10)
    @Column(name = "acct_group_id", length = 10)
    private String acctGroupId;

    @Size(max = 178)
    @Column(name = "filler", length = 178)
    private String filler;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Acctdat id(Long id) {
        this.id = id;
        return this;
    }

    public Long getAcctdatId() {
        return this.acctdatId;
    }

    public Acctdat acctdatId(Long acctdatId) {
        this.acctdatId = acctdatId;
        return this;
    }

    public void setAcctdatId(Long acctdatId) {
        this.acctdatId = acctdatId;
    }

    public Integer getAcctId() {
        return this.acctId;
    }

    public Acctdat acctId(Integer acctId) {
        this.acctId = acctId;
        return this;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public String getAcctActiveStatus() {
        return this.acctActiveStatus;
    }

    public Acctdat acctActiveStatus(String acctActiveStatus) {
        this.acctActiveStatus = acctActiveStatus;
        return this;
    }

    public void setAcctActiveStatus(String acctActiveStatus) {
        this.acctActiveStatus = acctActiveStatus;
    }

    public Double getAcctCurrBal() {
        return this.acctCurrBal;
    }

    public Acctdat acctCurrBal(Double acctCurrBal) {
        this.acctCurrBal = acctCurrBal;
        return this;
    }

    public void setAcctCurrBal(Double acctCurrBal) {
        this.acctCurrBal = acctCurrBal;
    }

    public Double getAcctCreditLimit() {
        return this.acctCreditLimit;
    }

    public Acctdat acctCreditLimit(Double acctCreditLimit) {
        this.acctCreditLimit = acctCreditLimit;
        return this;
    }

    public void setAcctCreditLimit(Double acctCreditLimit) {
        this.acctCreditLimit = acctCreditLimit;
    }

    public Double getAcctCashCreditLimit() {
        return this.acctCashCreditLimit;
    }

    public Acctdat acctCashCreditLimit(Double acctCashCreditLimit) {
        this.acctCashCreditLimit = acctCashCreditLimit;
        return this;
    }

    public void setAcctCashCreditLimit(Double acctCashCreditLimit) {
        this.acctCashCreditLimit = acctCashCreditLimit;
    }

    public String getAcctOpenDate() {
        return this.acctOpenDate;
    }

    public Acctdat acctOpenDate(String acctOpenDate) {
        this.acctOpenDate = acctOpenDate;
        return this;
    }

    public void setAcctOpenDate(String acctOpenDate) {
        this.acctOpenDate = acctOpenDate;
    }

    public String getAcctExpiraionDate() {
        return this.acctExpiraionDate;
    }

    public Acctdat acctExpiraionDate(String acctExpiraionDate) {
        this.acctExpiraionDate = acctExpiraionDate;
        return this;
    }

    public void setAcctExpiraionDate(String acctExpiraionDate) {
        this.acctExpiraionDate = acctExpiraionDate;
    }

    public String getAcctReissueDate() {
        return this.acctReissueDate;
    }

    public Acctdat acctReissueDate(String acctReissueDate) {
        this.acctReissueDate = acctReissueDate;
        return this;
    }

    public void setAcctReissueDate(String acctReissueDate) {
        this.acctReissueDate = acctReissueDate;
    }

    public Double getAcctCurrCycCredit() {
        return this.acctCurrCycCredit;
    }

    public Acctdat acctCurrCycCredit(Double acctCurrCycCredit) {
        this.acctCurrCycCredit = acctCurrCycCredit;
        return this;
    }

    public void setAcctCurrCycCredit(Double acctCurrCycCredit) {
        this.acctCurrCycCredit = acctCurrCycCredit;
    }

    public Double getAcctCurrCycDebit() {
        return this.acctCurrCycDebit;
    }

    public Acctdat acctCurrCycDebit(Double acctCurrCycDebit) {
        this.acctCurrCycDebit = acctCurrCycDebit;
        return this;
    }

    public void setAcctCurrCycDebit(Double acctCurrCycDebit) {
        this.acctCurrCycDebit = acctCurrCycDebit;
    }

    public String getAcctAddrZip() {
        return this.acctAddrZip;
    }

    public Acctdat acctAddrZip(String acctAddrZip) {
        this.acctAddrZip = acctAddrZip;
        return this;
    }

    public void setAcctAddrZip(String acctAddrZip) {
        this.acctAddrZip = acctAddrZip;
    }

    public String getAcctGroupId() {
        return this.acctGroupId;
    }

    public Acctdat acctGroupId(String acctGroupId) {
        this.acctGroupId = acctGroupId;
        return this;
    }

    public void setAcctGroupId(String acctGroupId) {
        this.acctGroupId = acctGroupId;
    }

    public String getFiller() {
        return this.filler;
    }

    public Acctdat filler(String filler) {
        this.filler = filler;
        return this;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acctdat)) {
            return false;
        }
        return id != null && id.equals(((Acctdat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Acctdat{" +
            "id=" + getId() +
            ", acctdatId=" + getAcctdatId() +
            ", acctId=" + getAcctId() +
            ", acctActiveStatus='" + getAcctActiveStatus() + "'" +
            ", acctCurrBal=" + getAcctCurrBal() +
            ", acctCreditLimit=" + getAcctCreditLimit() +
            ", acctCashCreditLimit=" + getAcctCashCreditLimit() +
            ", acctOpenDate='" + getAcctOpenDate() + "'" +
            ", acctExpiraionDate='" + getAcctExpiraionDate() + "'" +
            ", acctReissueDate='" + getAcctReissueDate() + "'" +
            ", acctCurrCycCredit=" + getAcctCurrCycCredit() +
            ", acctCurrCycDebit=" + getAcctCurrCycDebit() +
            ", acctAddrZip='" + getAcctAddrZip() + "'" +
            ", acctGroupId='" + getAcctGroupId() + "'" +
            ", filler='" + getFiller() + "'" +
            "}";
    }
}
