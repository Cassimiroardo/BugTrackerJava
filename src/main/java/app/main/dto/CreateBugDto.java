package app.main.dto;

import java.util.Date;

public class CreateBugDto {
	private String description;
	private Date deadline;
	private int severity;
	private String imageUrl;
	
	public CreateBugDto() {}
	
	public CreateBugDto(
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
		return this.severity <= 2 && this.severity >= 0;
	}

	public String getDescription() { return description; }

	public Date getDeadline() { return deadline; }

	public int getSeverity() { return severity; }

	public String getImageUrl() { return imageUrl; }
}
