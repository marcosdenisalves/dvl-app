package com.dvlcube.app.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dvlcube.app.jpa.repo.JobRepository;
import com.dvlcube.app.manager.data.JobBean;

@RestController
@RequestMapping("${dvl.rest.prefix}/jobs")
public class JobService {

	@Autowired
	private JobRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<JobBean>> findAll() {
		List<JobBean> list = repo.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<JobBean> findById(@PathVariable Long id) {
		Optional<JobBean> obj = repo.findById(id);
		return ResponseEntity.ok().body(obj.orElse(null));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody JobBean obj) {
		obj.setId(null);
		obj = repo.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody JobBean obj, @PathVariable Long id) {
		obj.setId(id);
		if (obj.getId() != null) {
			obj = repo.save(obj);
			return ResponseEntity.noContent().build();
		}
		return insert(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
