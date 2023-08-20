package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.entity.VerificationToken;

@Repository
public interface VerficationTokenRepository extends JpaRepository<VerificationToken, Long>
{

}
