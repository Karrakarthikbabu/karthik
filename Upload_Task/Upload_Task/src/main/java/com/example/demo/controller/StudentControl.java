package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Students;
import com.example.demo.repo.StdRepo;
import com.example.demo.serv.Stdserv;

@RestController
@RequestMapping("/api")
public class StudentControl {

	@Autowired
	private StdRepo repoo;
	@Autowired
	Stdserv sr;

	@PostMapping("/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile[] file) throws IOException {
		for (MultipartFile f : file) {
			Students student = new Students();
			student.setId(4);
			student.setEmail("karthikk@gmail.com");
			student.setName("karti");
			student.setResume(f.getBytes());
			repoo.save(student);
		}

		return ResponseEntity.ok("File uploaded successfully ");
	}

	@GetMapping("/download/{sid}")
	public ResponseEntity<Resource> download(@PathVariable("sid") int sid) {
		Optional<Students> findById = repoo.findById(sid);
		if (findById.isPresent()) {
			Students upload = findById.get();
			ByteArrayResource resource = new ByteArrayResource(upload.getResume());
			return ResponseEntity.ok().contentLength(upload.getResume().length)
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + upload.getFileName() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.badRequest().build();

		}
	}

	@GetMapping("/all")
	public ResponseEntity<Page<Students>> getAllEmployees(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "19") int size) {
		Page<Students> employees = sr.getPaginationPagebypage(18, size);
		return ResponseEntity.ok(employees);
	}

	

	@GetMapping("/sorting")
	public List<Students> getUsersSortedByLastName(@RequestParam(defaultValue = "ASC") String direction) {
		Sort.Direction sortDirection = Sort.Direction.fromString(direction);
		Sort sort = Sort.by(sortDirection, "name");
		System.out.println("dskfs");
		return sr.findAll();
	
	}

	 @GetMapping("/filter")
	    public ResponseEntity<List<Students>> getFilteredProducts(
	            @RequestParam(required = false) String name,
	            @RequestParam(required = false) String mail) {
	        List<Students> Std;
	        if (name != null && mail != null) {
	            Std = Stdserv.getStudentsByNameAndMail(name, mail);
	        } else if (name != null) {
	            Std = Stdserv.getStudentsByName(name);
	        } else if (mail != null) {
	            Std = Stdserv.getStudentsByMail(mail);
	        } else {
	            Std = Stdserv.getAllStd();
	        }
	        return ResponseEntity.ok().body(Std);
}
}
