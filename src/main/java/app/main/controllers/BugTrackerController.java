package app.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.main.dto.BugDto;
import app.main.dto.SoftwareDto;
import app.main.entities.BugEntity;
import app.main.entities.SoftwareEntity;
import app.main.enums.Severity;
import app.main.repositories.BugRepository;
import app.main.repositories.SoftwareRepository;

@RestController
@RequestMapping
public class BugTrackerController {
	
	@Autowired
	private SoftwareRepository softwareRepository;
	
	@Autowired
	private BugRepository bugRepository;
	
	@PostMapping(path = "/software")
	public @ResponseBody ResponseEntity<SoftwareEntity> saveSoftware(
				@RequestBody(required = true) SoftwareDto softwareDto
			) {
		if(!softwareDto.isValid())
			return null;
		
		SoftwareEntity software = new SoftwareEntity(softwareDto.getTitle());
		SoftwareEntity created = softwareRepository.save(software);
		
		return new ResponseEntity<SoftwareEntity>(created, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/software/{softwareId}/bug")
	public @ResponseBody ResponseEntity<BugEntity> saveBug(
				@RequestBody(required = true) BugDto bugDto,
				@PathVariable(name = "softwareId", required = true) String softwareId
			) {
		if(softwareId.isEmpty() || !bugDto.isValid())
			return null;
		
		Severity bugSeverity = Severity.valueOf(bugDto.getSeverity()).get();
		
		BugEntity bug = new BugEntity(
				bugDto.getDescription(),
				bugDto.getDeadline(),
				bugSeverity
			);
		
		SoftwareEntity software = softwareRepository.findById(Long.parseLong(softwareId)).get();
		
		if(software == null)
			return null;
		
		bug.setSoftware(software);
		
		BugEntity created = bugRepository.save(bug);
		return new ResponseEntity<BugEntity>(created, HttpStatus.CREATED);
	}
}
