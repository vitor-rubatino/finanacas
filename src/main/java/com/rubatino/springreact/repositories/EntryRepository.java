package com.rubatino.springreact.repositories;

import com.rubatino.springreact.entities.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
