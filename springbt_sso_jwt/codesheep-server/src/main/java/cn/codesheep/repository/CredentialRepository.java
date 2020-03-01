package cn.codesheep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.codesheep.bean.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    Credentials findByName(String name);
}
