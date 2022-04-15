package app.main.controllers;

import app.main.dto.PatchBugDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import app.main.dto.CreateBugDto;
import app.main.dto.CreateSoftwareDto;
import app.main.entities.BugEntity;
import app.main.entities.SoftwareEntity;
import app.main.enums.Severity;
import app.main.repositories.BugRepository;
import app.main.repositories.SoftwareRepository;

@RestController
public class BugTrackerController {
	@Autowired
	private SoftwareRepository softwareRepository;
	
	@Autowired
	private BugRepository bugRepository;
	
	@PostMapping(path = "/software", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SoftwareEntity> createSoftware(
				@RequestBody(required = true) @NotNull CreateSoftwareDto softwareDto
			) {
		if(!softwareDto.isValid())
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		SoftwareEntity software = new SoftwareEntity(softwareDto.getTitle());
		SoftwareEntity created = softwareRepository.save(software);
		return new ResponseEntity<SoftwareEntity>(created, null, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/software/{softwareId}/bug", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugEntity> createBug(
				@RequestBody(required = true) @NotNull CreateBugDto createBugDto,
				@PathVariable(name = "softwareId", required = true) @NotNull String softwareId
			) {
		if(softwareId.isEmpty() || !createBugDto.isValid())
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		Severity bugSeverity = Severity.valueOf(createBugDto.getSeverity()).get();
		
		BugEntity bug = new BugEntity(
				createBugDto.getDescription(),
				createBugDto.getDeadline(),
				bugSeverity,
				createBugDto.getImageUrl()
			);
		
		SoftwareEntity software = softwareRepository.findById(Long.parseLong(softwareId)).get();
		
		if(software == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		bug.setSoftware(software);
		
		BugEntity created = bugRepository.save(bug);
		return new ResponseEntity<BugEntity>(created, null, HttpStatus.CREATED);
	}

	@GetMapping(path = "/software", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SoftwareEntity[]> readSoftware() {
		return null;
	}

	@GetMapping(path = "/bug/{bugId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugEntity> readBug() {
		return null;
	}

	@PatchMapping(path = "/bug/{bugId}/severity", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BugEntity> updateBug(
				@RequestBody(required = true) @NotNull PatchBugDto patchBugDto,
				@PathVariable(name = "bugId", required = true) @NotNull String bugId
			) {
		if(bugId.isEmpty() || !patchBugDto.isValid())
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		BugEntity bug = this.bugRepository.findById(Long.parseLong(bugId)).get();
		if(bug == null)
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		Severity bugSeverity = Severity.valueOf(patchBugDto.getSeverity()).get();
		bug.setSeverity(bugSeverity);
		this.bugRepository.save(bug);
		return new ResponseEntity<BugEntity>(bug, null, HttpStatus.OK);
	}
}
