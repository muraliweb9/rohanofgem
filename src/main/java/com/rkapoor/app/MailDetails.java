package com.rkapoor.app;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MailDetails {
	
	private static final String SUBJECT = "Ofgem Automation Test";

	private static final String BODY = "Hello Ofgem";
	

	private String subject;
	private String body;
	private String time;
	
	public static MailDetails from(String text) {
		
		String[] parts = text.split("\n");
		
		return MailDetails.builder()
			.subject(parts[1].trim())
			.body(parts[3].trim())
			.time(parts[4].trim())
			.build();
	}
	
	public static List<MailDetails> filter(List<MailDetails> mds) {
		
		return mds.stream()
				.filter(md -> SUBJECT.equals(md.getSubject()) && BODY.equals(md.getBody()))
				.collect(Collectors.toList());
		
	}
	
}
