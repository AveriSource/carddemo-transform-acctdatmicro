package com.mycompany.acctdatpkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.acctdatpkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AcctdatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Acctdat.class);
        Acctdat acctdat1 = new Acctdat();
        acctdat1.setId(1L);
        Acctdat acctdat2 = new Acctdat();
        acctdat2.setId(acctdat1.getId());
        assertThat(acctdat1).isEqualTo(acctdat2);
        acctdat2.setId(2L);
        assertThat(acctdat1).isNotEqualTo(acctdat2);
        acctdat1.setId(null);
        assertThat(acctdat1).isNotEqualTo(acctdat2);
    }
}
