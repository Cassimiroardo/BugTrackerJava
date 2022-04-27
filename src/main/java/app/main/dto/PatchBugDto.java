package app.main.dto;

public class PatchBugDto {
    private int severity;
    public PatchBugDto() {}
    public PatchBugDto(int severity) {
        this.severity = severity;
    }
    public boolean isValid() {
        return this.severity >= 1 && this.severity <= 3;
    }
    public int getSeverity() {
        return severity;
    }
}
