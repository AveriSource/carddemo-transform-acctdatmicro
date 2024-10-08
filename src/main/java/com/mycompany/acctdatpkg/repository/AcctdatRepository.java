package com.mycompany.acctdatpkg.repository;

import com.mycompany.acctdatpkg.domain.Acctdat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Spring Data SQL repository for the Acctdat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcctdatRepository extends JpaRepository<Acctdat, Long> {
  List<Acctdat> findByAcctId(Integer acctId);
}
