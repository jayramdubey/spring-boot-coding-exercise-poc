package com.telstra.codechallenge.modal.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown= true)
public class HottestItems {
	@JsonProperty("html_url")
	private String htmlUrl;
	
	@JsonProperty("watchers_count")
	private String watchersCount;
	
	@JsonProperty("language")
	private String language;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("name")
	private String name;
}
