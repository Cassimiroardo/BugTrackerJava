package app.main.controllers;

import app.main.dto.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.main.entities.BugEntity;
import app.main.entities.SoftwareEntity;
import app.main.enums.Severity;
import app.main.repositories.BugRepository;
import app.main.repositories.SoftwareRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BugTrackerController {
	@Autowired
	private SoftwareRepository softwareRepository;
	
	@Autowired
	private BugRepository bugRepository;
	
	@PostMapping(path = "/softwares")
	public ResponseEntity<SoftwareResponseDto> createSoftware(
				@RequestBody(required = true) @NotNull CreateSoftwareDto softwareDto
			) {
		if(!softwareDto.isValid())
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		SoftwareEntity software = new SoftwareEntity(softwareDto.getTitle());
		SoftwareEntity created = softwareRepository.save(software);
		SoftwareResponseDto response = new SoftwareResponseDto(created);
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@PostMapping(path = "/softwares/{softwareId}/bugs")
	public ResponseEntity<BugResponseDto> createBug(
				@RequestBody(required = true) @NotNull CreateBugDto createBugDto,
				@PathVariable(name = "softwareId", required = true) @NotNull String softwareId
			) {
		if(softwareId.isEmpty() || !createBugDto.isValid())
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		
		Severity bugSeverity = Severity.valueOf(createBugDto.getSeverity()).get();
		
		BugEntity bug = new BugEntity(
				createBugDto.getDescription(),
				createBugDto.getDeadline(),
				bugSeverity
			);
		
		SoftwareEntity software = softwareRepository.findById(Long.parseLong(softwareId)).get();
		
		if(software == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		bug.setSoftware(software);
		
		BugEntity created = bugRepository.save(bug);
		BugResponseDto response = new BugResponseDto(created);
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping(path = "/bugs/{bugId}")
	public ResponseEntity<BugResponseDto> readBugById(
			@PathVariable(name = "bugId", required = false) @NotNull Long bugId
	) {
		if(bugId == null)
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		List<BugEntity> bugs = this.bugRepository.findAllById(bugId);
		List<BugResponseDto> response = bugs.stream().map(bug -> new BugResponseDto(bug)).collect(Collectors.toList());
		if(response.get(0) == null)
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity(response.get(0), HttpStatus.FOUND);
	}

	@GetMapping(path = "/bugs")
	public ResponseEntity<List<BugResponseDto>> readBug() {
		List<BugEntity> bugs = this.bugRepository.findAll();
		List<BugResponseDto> response = bugs.stream().map(bug -> new BugResponseDto(bug)).collect(Collectors.toList());
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping(path = "/softwares/{idSoftware}/bugs")
	public ResponseEntity<List<BugResponseDto>> readBug(
			@RequestBody(required = false) @Nullable ReadBugDto readBugDto,
			@PathVariable(name = "idSoftware", required = true) @NotNull Long idSoftware
	) {
		if(idSoftware == null)
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

		if(readBugDto != null && readBugDto.isValid()){
			List<BugEntity> bugs = this.bugRepository.findByIdSoftwareWhereSeverity(idSoftware, readBugDto.getSeverity());
			List<BugResponseDto> response = bugs.stream().map(bug -> new BugResponseDto(bug)).collect(Collectors.toList());
			return new ResponseEntity(response, HttpStatus.FOUND);
		}

		List<BugEntity> bugs = this.bugRepository.findBySoftwareId(idSoftware);
		List<BugResponseDto> response = bugs.stream().map(bug -> new BugResponseDto(bug)).collect(Collectors.toList());
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PatchMapping(path = "/bugs/{bugId}/severity")
	public ResponseEntity<BugEntity> updateBug(
				@RequestBody(required = true) @NotNull UpdateBugDto updateBugDto,
				@PathVariable(name = "bugId", required = true) @NotNull String bugId
			) {
		if(bugId.isEmpty() || !updateBugDto.isValid())
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		BugEntity bug = this.bugRepository.findById(Long.parseLong(bugId)).get();
		if(bug == null)
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		Severity bugSeverity = Severity.valueOf(updateBugDto.getSeverity()).get();
		bug.setSeverity(bugSeverity);
		this.bugRepository.save(bug);
		BugResponseDto response = new BugResponseDto(bug);
		return new ResponseEntity(response, HttpStatus.OK);
	}
}
