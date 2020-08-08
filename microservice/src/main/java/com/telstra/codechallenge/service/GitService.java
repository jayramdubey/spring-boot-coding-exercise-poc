package com.telstra.codechallenge.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telstra.codechallenge.modal.response.GetHottestRepositoryResponseDTO;
import com.telstra.codechallenge.modal.response.GetOldestRepositoryResponseDTO;

@Service
public interface GitService {
	ResponseEntity<GetHottestRepositoryResponseDTO> getHottestRepositories(Integer count);

	ResponseEntity<GetOldestRepositoryResponseDTO>  getOldestRepositories(Integer count);

}
