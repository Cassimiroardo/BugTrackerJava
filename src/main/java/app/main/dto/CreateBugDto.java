package app.main.dto;

import java.util.Date;

public class CreateBugDto {
	private String description;
	private Date deadline;
	private int severity;
	
	public CreateBugDto() {}
	
	public CreateBugDto(
			String description,
			Date deadline,
			int severity
			) {
		this.description = description;
		this.deadline = deadline;
		this.severity = severity;
	}
	
	public boolean isValid() {
		if(this.description.isEmpty())
			return false;
		return this.severity <= 2 && this.severity >= 0;
	}
	public String getDescription() { return description; }
	public Date getDeadline() { return deadline; }
	public int getSeverity() { return severity; }
}
