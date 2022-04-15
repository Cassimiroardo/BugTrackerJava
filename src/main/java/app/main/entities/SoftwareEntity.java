package app.main.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SoftwareEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "software")
	private List<BugEntity> bugs;

	public SoftwareEntity() {
	}

	public SoftwareEntity(String title) {
		this.title = title;
	}

	public SoftwareEntity(Long id, String title) {
		this.id = id;
		this.title = title;
	}
}
