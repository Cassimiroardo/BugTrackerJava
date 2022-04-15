package app.main.dto;

public class SoftwareDto {
	private String title;
	public SoftwareDto() {}
	public SoftwareDto(String title) {
		this.title = title;
	}
	public boolean isValid() {
		if(this.title.isEmpty())
			return false;
		return true;
	}
	public String getTitle() {
		return title;
	}
}
