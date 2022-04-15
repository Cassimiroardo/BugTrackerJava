package app.main.dto;

public class CreateSoftwareDto {
	private String title;
	public CreateSoftwareDto() {}
	public CreateSoftwareDto(String title) { this.title = title; }
	public boolean isValid() {
		return !this.title.isEmpty();
	}
	public String getTitle() { return title; }
}
