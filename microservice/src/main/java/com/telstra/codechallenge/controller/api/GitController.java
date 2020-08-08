package com.telstra.codechallenge.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.modal.response.GetHottestRepositoryResponseDTO;
import com.telstra.codechallenge.modal.response.GetOldestRepositoryResponseDTO;
import com.telstra.codechallenge.service.GitService;

@RestController
@RequestMapping("v1/git")
public class GitController {

	@Autowired
	private GitService gitService;

	@GetMapping(value = "/hottestRepositories")
	public ResponseEntity<GetHottestRepositoryResponseDTO> getHottestReposotories(
			@RequestParam(value = "count", required = true) Integer count) {

		return gitService.getHottestRepositories(count);
	}

	@GetMapping(value = "/oldestUserAccounts")
	public ResponseEntity<GetOldestRepositoryResponseDTO> getOldestReposotories(
			@RequestParam(value = "count", required = true) Integer count) {

		return gitService.getOldestRepositories(count);
	}

}