package com.mycompany.acctdatpkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.acctdatpkg.IntegrationTest;
import com.mycompany.acctdatpkg.domain.Acctdat;
import com.mycompany.acctdatpkg.repository.AcctdatRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AcctdatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AcctdatResourceIT {

    private static final Long DEFAULT_ACCTDAT_ID = 1L;
    private static final Long UPDATED_ACCTDAT_ID = 2L;

    private static final Integer DEFAULT_ACCT_ID = 11;
    private static final Integer UPDATED_ACCT_ID = 10;

    private static final String DEFAULT_ACCT_ACTIVE_STATUS = "A";
    private static final String UPDATED_ACCT_ACTIVE_STATUS = "B";

    private static final Double DEFAULT_ACCT_CURR_BAL = 12D;
    private static final Double UPDATED_ACCT_CURR_BAL = 11D;

    private static final Double DEFAULT_ACCT_CREDIT_LIMIT = 12D;
    private static final Double UPDATED_ACCT_CREDIT_LIMIT = 11D;

    private static final Double DEFAULT_ACCT_CASH_CREDIT_LIMIT = 12D;
    private static final Double UPDATED_ACCT_CASH_CREDIT_LIMIT = 11D;

    private static final String DEFAULT_ACCT_OPEN_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_OPEN_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_EXPIRAION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_EXPIRAION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_REISSUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_REISSUE_DATE = "BBBBBBBBBB";

    private static final Double DEFAULT_ACCT_CURR_CYC_CREDIT = 12D;
    private static final Double UPDATED_ACCT_CURR_CYC_CREDIT = 11D;

    private static final Double DEFAULT_ACCT_CURR_CYC_DEBIT = 12D;
    private static final Double UPDATED_ACCT_CURR_CYC_DEBIT = 11D;

    private static final String DEFAULT_ACCT_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_ACCT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCT_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILLER = "AAAAAAAAAA";
    private static final String UPDATED_FILLER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/acctdats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AcctdatRepository acctdatRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAcctdatMockMvc;

    private Acctdat acctdat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acctdat createEntity(EntityManager em) {
        Acctdat acctdat = new Acctdat()
            .acctdatId(DEFAULT_ACCTDAT_ID)
            .acctId(DEFAULT_ACCT_ID)
            .acctActiveStatus(DEFAULT_ACCT_ACTIVE_STATUS)
            .acctCurrBal(DEFAULT_ACCT_CURR_BAL)
            .acctCreditLimit(DEFAULT_ACCT_CREDIT_LIMIT)
            .acctCashCreditLimit(DEFAULT_ACCT_CASH_CREDIT_LIMIT)
            .acctOpenDate(DEFAULT_ACCT_OPEN_DATE)
            .acctExpiraionDate(DEFAULT_ACCT_EXPIRAION_DATE)
            .acctReissueDate(DEFAULT_ACCT_REISSUE_DATE)
            .acctCurrCycCredit(DEFAULT_ACCT_CURR_CYC_CREDIT)
            .acctCurrCycDebit(DEFAULT_ACCT_CURR_CYC_DEBIT)
            .acctAddrZip(DEFAULT_ACCT_ADDR_ZIP)
            .acctGroupId(DEFAULT_ACCT_GROUP_ID)
            .filler(DEFAULT_FILLER);
        return acctdat;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Acctdat createUpdatedEntity(EntityManager em) {
        Acctdat acctdat = new Acctdat()
            .acctdatId(UPDATED_ACCTDAT_ID)
            .acctId(UPDATED_ACCT_ID)
            .acctActiveStatus(UPDATED_ACCT_ACTIVE_STATUS)
            .acctCurrBal(UPDATED_ACCT_CURR_BAL)
            .acctCreditLimit(UPDATED_ACCT_CREDIT_LIMIT)
            .acctCashCreditLimit(UPDATED_ACCT_CASH_CREDIT_LIMIT)
            .acctOpenDate(UPDATED_ACCT_OPEN_DATE)
            .acctExpiraionDate(UPDATED_ACCT_EXPIRAION_DATE)
            .acctReissueDate(UPDATED_ACCT_REISSUE_DATE)
            .acctCurrCycCredit(UPDATED_ACCT_CURR_CYC_CREDIT)
            .acctCurrCycDebit(UPDATED_ACCT_CURR_CYC_DEBIT)
            .acctAddrZip(UPDATED_ACCT_ADDR_ZIP)
            .acctGroupId(UPDATED_ACCT_GROUP_ID)
            .filler(UPDATED_FILLER);
        return acctdat;
    }

    @BeforeEach
    public void initTest() {
        acctdat = createEntity(em);
    }

    @Test
    @Transactional
    void createAcctdat() throws Exception {
        int databaseSizeBeforeCreate = acctdatRepository.findAll().size();
        // Create the Acctdat
        restAcctdatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acctdat)))
            .andExpect(status().isCreated());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeCreate + 1);
        Acctdat testAcctdat = acctdatList.get(acctdatList.size() - 1);
        assertThat(testAcctdat.getAcctdatId()).isEqualTo(DEFAULT_ACCTDAT_ID);
        assertThat(testAcctdat.getAcctId()).isEqualTo(DEFAULT_ACCT_ID);
        assertThat(testAcctdat.getAcctActiveStatus()).isEqualTo(DEFAULT_ACCT_ACTIVE_STATUS);
        assertThat(testAcctdat.getAcctCurrBal()).isEqualTo(DEFAULT_ACCT_CURR_BAL);
        assertThat(testAcctdat.getAcctCreditLimit()).isEqualTo(DEFAULT_ACCT_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctCashCreditLimit()).isEqualTo(DEFAULT_ACCT_CASH_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctOpenDate()).isEqualTo(DEFAULT_ACCT_OPEN_DATE);
        assertThat(testAcctdat.getAcctExpiraionDate()).isEqualTo(DEFAULT_ACCT_EXPIRAION_DATE);
        assertThat(testAcctdat.getAcctReissueDate()).isEqualTo(DEFAULT_ACCT_REISSUE_DATE);
        assertThat(testAcctdat.getAcctCurrCycCredit()).isEqualTo(DEFAULT_ACCT_CURR_CYC_CREDIT);
        assertThat(testAcctdat.getAcctCurrCycDebit()).isEqualTo(DEFAULT_ACCT_CURR_CYC_DEBIT);
        assertThat(testAcctdat.getAcctAddrZip()).isEqualTo(DEFAULT_ACCT_ADDR_ZIP);
        assertThat(testAcctdat.getAcctGroupId()).isEqualTo(DEFAULT_ACCT_GROUP_ID);
        assertThat(testAcctdat.getFiller()).isEqualTo(DEFAULT_FILLER);
    }

    @Test
    @Transactional
    void createAcctdatWithExistingId() throws Exception {
        // Create the Acctdat with an existing ID
        acctdat.setId(1L);

        int databaseSizeBeforeCreate = acctdatRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAcctdatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acctdat)))
            .andExpect(status().isBadRequest());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAcctIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = acctdatRepository.findAll().size();
        // set the field null
        acctdat.setAcctId(null);

        // Create the Acctdat, which fails.

        restAcctdatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acctdat)))
            .andExpect(status().isBadRequest());

        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAcctdats() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        // Get all the acctdatList
        restAcctdatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(acctdat.getId().intValue())))
            .andExpect(jsonPath("$.[*].acctdatId").value(hasItem(DEFAULT_ACCTDAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].acctId").value(hasItem(DEFAULT_ACCT_ID)))
            .andExpect(jsonPath("$.[*].acctActiveStatus").value(hasItem(DEFAULT_ACCT_ACTIVE_STATUS)))
            .andExpect(jsonPath("$.[*].acctCurrBal").value(hasItem(DEFAULT_ACCT_CURR_BAL.doubleValue())))
            .andExpect(jsonPath("$.[*].acctCreditLimit").value(hasItem(DEFAULT_ACCT_CREDIT_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].acctCashCreditLimit").value(hasItem(DEFAULT_ACCT_CASH_CREDIT_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].acctOpenDate").value(hasItem(DEFAULT_ACCT_OPEN_DATE)))
            .andExpect(jsonPath("$.[*].acctExpiraionDate").value(hasItem(DEFAULT_ACCT_EXPIRAION_DATE)))
            .andExpect(jsonPath("$.[*].acctReissueDate").value(hasItem(DEFAULT_ACCT_REISSUE_DATE)))
            .andExpect(jsonPath("$.[*].acctCurrCycCredit").value(hasItem(DEFAULT_ACCT_CURR_CYC_CREDIT.doubleValue())))
            .andExpect(jsonPath("$.[*].acctCurrCycDebit").value(hasItem(DEFAULT_ACCT_CURR_CYC_DEBIT.doubleValue())))
            .andExpect(jsonPath("$.[*].acctAddrZip").value(hasItem(DEFAULT_ACCT_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].acctGroupId").value(hasItem(DEFAULT_ACCT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].filler").value(hasItem(DEFAULT_FILLER)));
    }

    @Test
    @Transactional
    void getAcctdat() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        // Get the acctdat
        restAcctdatMockMvc
            .perform(get(ENTITY_API_URL_ID, acctdat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(acctdat.getId().intValue()))
            .andExpect(jsonPath("$.acctdatId").value(DEFAULT_ACCTDAT_ID.intValue()))
            .andExpect(jsonPath("$.acctId").value(DEFAULT_ACCT_ID))
            .andExpect(jsonPath("$.acctActiveStatus").value(DEFAULT_ACCT_ACTIVE_STATUS))
            .andExpect(jsonPath("$.acctCurrBal").value(DEFAULT_ACCT_CURR_BAL.doubleValue()))
            .andExpect(jsonPath("$.acctCreditLimit").value(DEFAULT_ACCT_CREDIT_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.acctCashCreditLimit").value(DEFAULT_ACCT_CASH_CREDIT_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.acctOpenDate").value(DEFAULT_ACCT_OPEN_DATE))
            .andExpect(jsonPath("$.acctExpiraionDate").value(DEFAULT_ACCT_EXPIRAION_DATE))
            .andExpect(jsonPath("$.acctReissueDate").value(DEFAULT_ACCT_REISSUE_DATE))
            .andExpect(jsonPath("$.acctCurrCycCredit").value(DEFAULT_ACCT_CURR_CYC_CREDIT.doubleValue()))
            .andExpect(jsonPath("$.acctCurrCycDebit").value(DEFAULT_ACCT_CURR_CYC_DEBIT.doubleValue()))
            .andExpect(jsonPath("$.acctAddrZip").value(DEFAULT_ACCT_ADDR_ZIP))
            .andExpect(jsonPath("$.acctGroupId").value(DEFAULT_ACCT_GROUP_ID))
            .andExpect(jsonPath("$.filler").value(DEFAULT_FILLER));
    }

    @Test
    @Transactional
    void getNonExistingAcctdat() throws Exception {
        // Get the acctdat
        restAcctdatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAcctdat() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();

        // Update the acctdat
        Acctdat updatedAcctdat = acctdatRepository.findById(acctdat.getId()).get();
        // Disconnect from session so that the updates on updatedAcctdat are not directly saved in db
        em.detach(updatedAcctdat);
        updatedAcctdat
            .acctdatId(UPDATED_ACCTDAT_ID)
            .acctId(UPDATED_ACCT_ID)
            .acctActiveStatus(UPDATED_ACCT_ACTIVE_STATUS)
            .acctCurrBal(UPDATED_ACCT_CURR_BAL)
            .acctCreditLimit(UPDATED_ACCT_CREDIT_LIMIT)
            .acctCashCreditLimit(UPDATED_ACCT_CASH_CREDIT_LIMIT)
            .acctOpenDate(UPDATED_ACCT_OPEN_DATE)
            .acctExpiraionDate(UPDATED_ACCT_EXPIRAION_DATE)
            .acctReissueDate(UPDATED_ACCT_REISSUE_DATE)
            .acctCurrCycCredit(UPDATED_ACCT_CURR_CYC_CREDIT)
            .acctCurrCycDebit(UPDATED_ACCT_CURR_CYC_DEBIT)
            .acctAddrZip(UPDATED_ACCT_ADDR_ZIP)
            .acctGroupId(UPDATED_ACCT_GROUP_ID)
            .filler(UPDATED_FILLER);

        restAcctdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAcctdat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAcctdat))
            )
            .andExpect(status().isOk());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
        Acctdat testAcctdat = acctdatList.get(acctdatList.size() - 1);
        assertThat(testAcctdat.getAcctdatId()).isEqualTo(UPDATED_ACCTDAT_ID);
        assertThat(testAcctdat.getAcctId()).isEqualTo(UPDATED_ACCT_ID);
        assertThat(testAcctdat.getAcctActiveStatus()).isEqualTo(UPDATED_ACCT_ACTIVE_STATUS);
        assertThat(testAcctdat.getAcctCurrBal()).isEqualTo(UPDATED_ACCT_CURR_BAL);
        assertThat(testAcctdat.getAcctCreditLimit()).isEqualTo(UPDATED_ACCT_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctCashCreditLimit()).isEqualTo(UPDATED_ACCT_CASH_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctOpenDate()).isEqualTo(UPDATED_ACCT_OPEN_DATE);
        assertThat(testAcctdat.getAcctExpiraionDate()).isEqualTo(UPDATED_ACCT_EXPIRAION_DATE);
        assertThat(testAcctdat.getAcctReissueDate()).isEqualTo(UPDATED_ACCT_REISSUE_DATE);
        assertThat(testAcctdat.getAcctCurrCycCredit()).isEqualTo(UPDATED_ACCT_CURR_CYC_CREDIT);
        assertThat(testAcctdat.getAcctCurrCycDebit()).isEqualTo(UPDATED_ACCT_CURR_CYC_DEBIT);
        assertThat(testAcctdat.getAcctAddrZip()).isEqualTo(UPDATED_ACCT_ADDR_ZIP);
        assertThat(testAcctdat.getAcctGroupId()).isEqualTo(UPDATED_ACCT_GROUP_ID);
        assertThat(testAcctdat.getFiller()).isEqualTo(UPDATED_FILLER);
    }

    @Test
    @Transactional
    void putNonExistingAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, acctdat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acctdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(acctdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(acctdat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAcctdatWithPatch() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();

        // Update the acctdat using partial update
        Acctdat partialUpdatedAcctdat = new Acctdat();
        partialUpdatedAcctdat.setId(acctdat.getId());

        partialUpdatedAcctdat
            .acctdatId(UPDATED_ACCTDAT_ID)
            .acctCreditLimit(UPDATED_ACCT_CREDIT_LIMIT)
            .acctCashCreditLimit(UPDATED_ACCT_CASH_CREDIT_LIMIT)
            .acctOpenDate(UPDATED_ACCT_OPEN_DATE)
            .acctReissueDate(UPDATED_ACCT_REISSUE_DATE)
            .acctCurrCycCredit(UPDATED_ACCT_CURR_CYC_CREDIT)
            .acctAddrZip(UPDATED_ACCT_ADDR_ZIP);

        restAcctdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAcctdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAcctdat))
            )
            .andExpect(status().isOk());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
        Acctdat testAcctdat = acctdatList.get(acctdatList.size() - 1);
        assertThat(testAcctdat.getAcctdatId()).isEqualTo(UPDATED_ACCTDAT_ID);
        assertThat(testAcctdat.getAcctId()).isEqualTo(DEFAULT_ACCT_ID);
        assertThat(testAcctdat.getAcctActiveStatus()).isEqualTo(DEFAULT_ACCT_ACTIVE_STATUS);
        assertThat(testAcctdat.getAcctCurrBal()).isEqualTo(DEFAULT_ACCT_CURR_BAL);
        assertThat(testAcctdat.getAcctCreditLimit()).isEqualTo(UPDATED_ACCT_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctCashCreditLimit()).isEqualTo(UPDATED_ACCT_CASH_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctOpenDate()).isEqualTo(UPDATED_ACCT_OPEN_DATE);
        assertThat(testAcctdat.getAcctExpiraionDate()).isEqualTo(DEFAULT_ACCT_EXPIRAION_DATE);
        assertThat(testAcctdat.getAcctReissueDate()).isEqualTo(UPDATED_ACCT_REISSUE_DATE);
        assertThat(testAcctdat.getAcctCurrCycCredit()).isEqualTo(UPDATED_ACCT_CURR_CYC_CREDIT);
        assertThat(testAcctdat.getAcctCurrCycDebit()).isEqualTo(DEFAULT_ACCT_CURR_CYC_DEBIT);
        assertThat(testAcctdat.getAcctAddrZip()).isEqualTo(UPDATED_ACCT_ADDR_ZIP);
        assertThat(testAcctdat.getAcctGroupId()).isEqualTo(DEFAULT_ACCT_GROUP_ID);
        assertThat(testAcctdat.getFiller()).isEqualTo(DEFAULT_FILLER);
    }

    @Test
    @Transactional
    void fullUpdateAcctdatWithPatch() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();

        // Update the acctdat using partial update
        Acctdat partialUpdatedAcctdat = new Acctdat();
        partialUpdatedAcctdat.setId(acctdat.getId());

        partialUpdatedAcctdat
            .acctdatId(UPDATED_ACCTDAT_ID)
            .acctId(UPDATED_ACCT_ID)
            .acctActiveStatus(UPDATED_ACCT_ACTIVE_STATUS)
            .acctCurrBal(UPDATED_ACCT_CURR_BAL)
            .acctCreditLimit(UPDATED_ACCT_CREDIT_LIMIT)
            .acctCashCreditLimit(UPDATED_ACCT_CASH_CREDIT_LIMIT)
            .acctOpenDate(UPDATED_ACCT_OPEN_DATE)
            .acctExpiraionDate(UPDATED_ACCT_EXPIRAION_DATE)
            .acctReissueDate(UPDATED_ACCT_REISSUE_DATE)
            .acctCurrCycCredit(UPDATED_ACCT_CURR_CYC_CREDIT)
            .acctCurrCycDebit(UPDATED_ACCT_CURR_CYC_DEBIT)
            .acctAddrZip(UPDATED_ACCT_ADDR_ZIP)
            .acctGroupId(UPDATED_ACCT_GROUP_ID)
            .filler(UPDATED_FILLER);

        restAcctdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAcctdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAcctdat))
            )
            .andExpect(status().isOk());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
        Acctdat testAcctdat = acctdatList.get(acctdatList.size() - 1);
        assertThat(testAcctdat.getAcctdatId()).isEqualTo(UPDATED_ACCTDAT_ID);
        assertThat(testAcctdat.getAcctId()).isEqualTo(UPDATED_ACCT_ID);
        assertThat(testAcctdat.getAcctActiveStatus()).isEqualTo(UPDATED_ACCT_ACTIVE_STATUS);
        assertThat(testAcctdat.getAcctCurrBal()).isEqualTo(UPDATED_ACCT_CURR_BAL);
        assertThat(testAcctdat.getAcctCreditLimit()).isEqualTo(UPDATED_ACCT_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctCashCreditLimit()).isEqualTo(UPDATED_ACCT_CASH_CREDIT_LIMIT);
        assertThat(testAcctdat.getAcctOpenDate()).isEqualTo(UPDATED_ACCT_OPEN_DATE);
        assertThat(testAcctdat.getAcctExpiraionDate()).isEqualTo(UPDATED_ACCT_EXPIRAION_DATE);
        assertThat(testAcctdat.getAcctReissueDate()).isEqualTo(UPDATED_ACCT_REISSUE_DATE);
        assertThat(testAcctdat.getAcctCurrCycCredit()).isEqualTo(UPDATED_ACCT_CURR_CYC_CREDIT);
        assertThat(testAcctdat.getAcctCurrCycDebit()).isEqualTo(UPDATED_ACCT_CURR_CYC_DEBIT);
        assertThat(testAcctdat.getAcctAddrZip()).isEqualTo(UPDATED_ACCT_ADDR_ZIP);
        assertThat(testAcctdat.getAcctGroupId()).isEqualTo(UPDATED_ACCT_GROUP_ID);
        assertThat(testAcctdat.getFiller()).isEqualTo(UPDATED_FILLER);
    }

    @Test
    @Transactional
    void patchNonExistingAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, acctdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acctdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(acctdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAcctdat() throws Exception {
        int databaseSizeBeforeUpdate = acctdatRepository.findAll().size();
        acctdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAcctdatMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(acctdat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Acctdat in the database
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAcctdat() throws Exception {
        // Initialize the database
        acctdatRepository.saveAndFlush(acctdat);

        int databaseSizeBeforeDelete = acctdatRepository.findAll().size();

        // Delete the acctdat
        restAcctdatMockMvc
            .perform(delete(ENTITY_API_URL_ID, acctdat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Acctdat> acctdatList = acctdatRepository.findAll();
        assertThat(acctdatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
