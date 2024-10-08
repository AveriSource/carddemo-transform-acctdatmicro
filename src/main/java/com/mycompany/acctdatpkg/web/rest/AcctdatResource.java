package com.mycompany.acctdatpkg.web.rest;

import com.mycompany.acctdatpkg.domain.Acctdat;
import com.mycompany.acctdatpkg.repository.AcctdatRepository;
import com.mycompany.acctdatpkg.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.acctdatpkg.domain.Acctdat}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AcctdatResource {

    private final Logger log = LoggerFactory.getLogger(AcctdatResource.class);

    private static final String ENTITY_NAME = "acctdatmicroAcctdat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AcctdatRepository acctdatRepository;

    public AcctdatResource(AcctdatRepository acctdatRepository) {
        this.acctdatRepository = acctdatRepository;
    }

    /**
     * {@code POST  /acctdats} : Create a new acctdat.
     *
     * @param acctdat the acctdat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acctdat, or with status {@code 400 (Bad Request)} if the acctdat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/acctdats")
    public ResponseEntity<Acctdat> createAcctdat(@Valid @RequestBody Acctdat acctdat) throws URISyntaxException {
        log.debug("REST request to save Acctdat : {}", acctdat);
        if (acctdat.getId() != null) {
            throw new BadRequestAlertException("A new acctdat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Acctdat result = acctdatRepository.save(acctdat);
        return ResponseEntity
            .created(new URI("/api/acctdats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /acctdats/:id} : Updates an existing acctdat.
     *
     * @param id the id of the acctdat to save.
     * @param acctdat the acctdat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acctdat,
     * or with status {@code 400 (Bad Request)} if the acctdat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acctdat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/acctdats/{id}")
    public ResponseEntity<Acctdat> updateAcctdat(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Acctdat acctdat
    ) throws URISyntaxException {
        log.debug("REST request to update Acctdat : {}, {}", id, acctdat);
        if (acctdat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acctdat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acctdatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Acctdat result = acctdatRepository.save(acctdat);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, acctdat.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /acctdats/:id} : Partial updates given fields of an existing acctdat, field will ignore if it is null
     *
     * @param id the id of the acctdat to save.
     * @param acctdat the acctdat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acctdat,
     * or with status {@code 400 (Bad Request)} if the acctdat is not valid,
     * or with status {@code 404 (Not Found)} if the acctdat is not found,
     * or with status {@code 500 (Internal Server Error)} if the acctdat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/acctdats/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Acctdat> partialUpdateAcctdat(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Acctdat acctdat
    ) throws URISyntaxException {
        log.debug("REST request to partial update Acctdat partially : {}, {}", id, acctdat);
        if (acctdat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acctdat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!acctdatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Acctdat> result = acctdatRepository
            .findById(acctdat.getId())
            .map(
                existingAcctdat -> {
                    if (acctdat.getAcctdatId() != null) {
                        existingAcctdat.setAcctdatId(acctdat.getAcctdatId());
                    }
                    if (acctdat.getAcctId() != null) {
                        existingAcctdat.setAcctId(acctdat.getAcctId());
                    }
                    if (acctdat.getAcctActiveStatus() != null) {
                        existingAcctdat.setAcctActiveStatus(acctdat.getAcctActiveStatus());
                    }
                    if (acctdat.getAcctCurrBal() != null) {
                        existingAcctdat.setAcctCurrBal(acctdat.getAcctCurrBal());
                    }
                    if (acctdat.getAcctCreditLimit() != null) {
                        existingAcctdat.setAcctCreditLimit(acctdat.getAcctCreditLimit());
                    }
                    if (acctdat.getAcctCashCreditLimit() != null) {
                        existingAcctdat.setAcctCashCreditLimit(acctdat.getAcctCashCreditLimit());
                    }
                    if (acctdat.getAcctOpenDate() != null) {
                        existingAcctdat.setAcctOpenDate(acctdat.getAcctOpenDate());
                    }
                    if (acctdat.getAcctExpiraionDate() != null) {
                        existingAcctdat.setAcctExpiraionDate(acctdat.getAcctExpiraionDate());
                    }
                    if (acctdat.getAcctReissueDate() != null) {
                        existingAcctdat.setAcctReissueDate(acctdat.getAcctReissueDate());
                    }
                    if (acctdat.getAcctCurrCycCredit() != null) {
                        existingAcctdat.setAcctCurrCycCredit(acctdat.getAcctCurrCycCredit());
                    }
                    if (acctdat.getAcctCurrCycDebit() != null) {
                        existingAcctdat.setAcctCurrCycDebit(acctdat.getAcctCurrCycDebit());
                    }
                    if (acctdat.getAcctAddrZip() != null) {
                        existingAcctdat.setAcctAddrZip(acctdat.getAcctAddrZip());
                    }
                    if (acctdat.getAcctGroupId() != null) {
                        existingAcctdat.setAcctGroupId(acctdat.getAcctGroupId());
                    }
                    if (acctdat.getFiller() != null) {
                        existingAcctdat.setFiller(acctdat.getFiller());
                    }

                    return existingAcctdat;
                }
            )
            .map(acctdatRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, acctdat.getId().toString())
        );
    }

    /**
     * {@code GET  /acctdats} : get all the acctdats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acctdats in body.
     */
    @GetMapping("/acctdats")
    public ResponseEntity<List<Acctdat>> getAllAcctdats(@RequestParam(name = "acctId", required = false) Integer acctId, Pageable pageable) {
        if(acctId != null) {
            log.debug("REST request to get a list of Acctdats by acctId");
            List<Acctdat> acctdats = acctdatRepository.findByAcctId(acctId);
            return ResponseEntity.ok().body(acctdats);
        }
        log.debug("REST request to get a page of Acctdats");
        Page<Acctdat> page = acctdatRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acctdats/:id} : get the "id" acctdat.
     *
     * @param id the id of the acctdat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acctdat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/acctdats/{id}")
    public ResponseEntity<Acctdat> getAcctdat(@PathVariable Long id) {
        log.debug("REST request to get Acctdat : {}", id);
        Optional<Acctdat> acctdat = acctdatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(acctdat);
    }

    /**
     * {@code DELETE  /acctdats/:id} : delete the "id" acctdat.
     *
     * @param id the id of the acctdat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/acctdats/{id}")
    public ResponseEntity<Void> deleteAcctdat(@PathVariable Long id) {
        log.debug("REST request to delete Acctdat : {}", id);
        acctdatRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
