package com.jhcm.appdirect.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhcm.appdirect.backend.model.EventLog;

public interface EventLogRepository extends JpaRepository<EventLog, Long> {

}
