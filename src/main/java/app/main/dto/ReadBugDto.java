package app.main.dto;

public class ReadBugDto {
    private int severity;
    public ReadBugDto() {}
    public ReadBugDto(int severity) {
        this.severity = severity;
    }
    public boolean isValid() {
        return this.severity >= 1 && this.severity <= 3;
    }
    public int getSeverity() {
        return severity;
    }
}
