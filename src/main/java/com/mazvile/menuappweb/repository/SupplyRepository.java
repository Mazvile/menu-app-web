package com.mazvile.menuappweb.repository;

import com.mazvile.menuappweb.repository.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, UUID> {


}
