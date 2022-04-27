package app.main.dto;

public class UpdateBugDto {
    private int severity;
    public UpdateBugDto() {}
    public UpdateBugDto(int severity) {
        this.severity = severity;
    }
    public boolean isValid() {
        return this.severity >= 1 && this.severity <= 3;
    }
    public int getSeverity() {
        return severity;
    }
}
