package app.main.dto;

import java.util.Date;

public class BugDto {
	private String description;
	private Date deadline;
	private int severity;
	private String imageUrl;
	
	public BugDto() {}
	
	public BugDto(
			String description,
			Date deadline,
			int severity,
			String imageUrl
			) {
		this.description = description;
		this.deadline = deadline;
		this.severity = severity;
		this.imageUrl = imageUrl;
	}
	
	public boolean isValid() {
		if(this.description.isEmpty())
			return false;
		if(this.severity > 3 || this.severity < 0)
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public int getSeverity() {
		return severity;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
