package com.telstra.codechallenge.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.modal.external.response.GetHottestRepositoryExternalResponseDTO;
import com.telstra.codechallenge.modal.external.response.GetOldestRepositoryExternalResponseDTO;

@Component
public class GitApiUtility {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Value("${gitapi.search.hottest.url}")
	private String hottestRepositoryUrl;
	@Value("${gitapi.search.oldest.url}")
	private String oldestRepositoryUrl;

	public GetHottestRepositoryExternalResponseDTO getHottestRepositoriesFromGit(String dateRange,String sortBy,String orderBy) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException{
		GetHottestRepositoryExternalResponseDTO response =null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(hottestRepositoryUrl)
		        .queryParam("q", dateRange)
		        .queryParam("sort", sortBy)
		        .queryParam("sort", sortBy).encode();
		
		String url=builder.toUriString();
		
		
		String encodedUrl=java.net.URLDecoder.decode(url, "UTF-8");
		HttpEntity<?> requestHeader = new HttpEntity<>(headers);
		HttpEntity<String> gitresponse = restTemplate.exchange(
				encodedUrl,
		       HttpMethod.GET,
		       requestHeader,
		       String.class);
		response=mapper.readValue(gitresponse.getBody(), GetHottestRepositoryExternalResponseDTO.class);
		
		return response;
		
	}

	public GetOldestRepositoryExternalResponseDTO getOldestRepositoriesFromGit(String followersCount,String sortBy,String orderBy) throws JsonMappingException, JsonProcessingException{
		GetOldestRepositoryExternalResponseDTO response =null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(oldestRepositoryUrl)
		        .queryParam("q", followersCount)
		        .queryParam("sort", sortBy)
		        .queryParam("sort", sortBy).encode();
		
		HttpEntity<?> requestHeader = new HttpEntity<>(headers);
		
		
		HttpEntity<String> gitresponse = restTemplate.exchange(
				builder.toUriString(),
		       HttpMethod.GET,
		       requestHeader,
		       String.class);
		response=mapper.readValue(gitresponse.getBody(), GetOldestRepositoryExternalResponseDTO.class);
		
		return response;
		
	}
}
