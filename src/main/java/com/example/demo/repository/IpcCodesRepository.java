package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.IpcCodes;;

public interface IpcCodesRepository extends JpaRepository <IpcCodes, String> {

}
