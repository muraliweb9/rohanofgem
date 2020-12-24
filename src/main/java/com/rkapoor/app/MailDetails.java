package com.rkapoor.app;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
	private Boolean ofgemEmail;
	
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
				.filter(md -> md.getOfgemEmail())
				.collect(Collectors.toList());
		
	}
	
	public Boolean getOfgemEmail() {
		return SUBJECT.equals(getSubject()) && BODY.equals(getBody());
	}
	
	public static MailDetails latestEmail(List<MailDetails> mds) {
		List<MailDetails> filtered = filter(mds);
		Set<MailDetails> sortedMailDetails = new TreeSet<>(new MailDetailsComparator());
		sortedMailDetails.addAll(filtered);
		return filtered.get(0);
	}
}

class MailDetailsComparator implements Comparator<MailDetails> {

	@Override
	public int compare(MailDetails o1, MailDetails o2) {
		String time1 = o1.getTime();
		String time2 = o2.getTime();
		return time1.compareTo(time2);
	}
	
}
