package app.main.dto;

import app.main.entities.BugEntity;
import app.main.enums.Severity;
import java.util.Date;

public class BugResponseDto {
    private Long id;
    private String description;
    private Date deadline;
    private Date createdAt;
    private Severity severity;
    private SoftwareResponseDto software;

    public BugResponseDto() {}
    public BugResponseDto(BugEntity entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.deadline = entity.getDeadline();
        this.createdAt = entity.getCreatedAt();
        this.severity = entity.getSeverity();
        this.software = new SoftwareResponseDto(entity.getSoftware());
    }
    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public Date getDeadline() {
        return deadline;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Severity getSeverity() {
        return severity;
    }
    public SoftwareResponseDto getSoftware() {
        return software;
    }
}
