package app.main.dto;

import app.main.entities.SoftwareEntity;

public class SoftwareResponseDto {
    private Long id;
    private String title;

    public SoftwareResponseDto(SoftwareEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
