package app.main.entities;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import app.main.enums.Severity;

@Entity
public class BugEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	@Column(nullable = true)
	private Date deadline;
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	@Column(nullable = false)
	private Severity severity;
	@Column
	private String imageUrl;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private SoftwareEntity software;

	public BugEntity() {}
	
	public BugEntity(
				Long id,
				String description,
				Date deadline,
				Date createdAt,
				Severity severity,
				String imageUrl
			) {
		this.id = id;
		this.description = description;
		this.deadline = deadline;
		this.createdAt = createdAt;
		this.severity = severity;
		this.imageUrl = imageUrl;
	}
	
	public BugEntity(
				String description,
				Date deadline,
				Severity severity,
				String imageUrl
			) {
		this.description =  description;
		this.deadline = deadline;
		this.severity = severity;
		this.imageUrl = imageUrl;
	}

	public void setSoftware(SoftwareEntity software) { this.software = software; }
	public void setSeverity(Severity severity) { this.severity = severity; }
}
