package com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.service;

import com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.entity.Pharmacy;
import com.biz.fastcampus.pharmacyrecommendation.api.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    // self invocation test <start>

    public void bar(List<Pharmacy> pharmacyList) {
        log.info("bar CurrentTransactionName: " + TransactionSynchronizationManager.getCurrentTransactionName());
        foo(pharmacyList);
    }

    @Transactional
    public void foo(List<Pharmacy> pharmacyList) {
        log.info("foo CurrentTransactionName: " + TransactionSynchronizationManager.getCurrentTransactionName());
        pharmacyList.forEach(pharmacy -> {
            pharmacyRepository.save(pharmacy);
            throw new RuntimeException("error"); // 예외 발생
        });
    }

    // self invocation test <end>

    // read only
    public void startReadOnlyMethod(Long id) {
        pharmacyRepository.findById(id).ifPresent(pharmacy ->
                pharmacy.changePharmacyAddress("서울특별시 광진구"));
    }

    @Transactional(readOnly = true)
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Transactional
    public void updateAddress(Long id, String address) {
        Pharmacy entity = this.pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);

    }

    public void updateAddressWithoutTransactional(Long id, String address) {
        Pharmacy entity = this.pharmacyRepository.findById(id).orElse(null);

        if (Objects.isNull(entity)) {
            log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
            return;
        }

        entity.changePharmacyAddress(address);

    }

}
