package com.example.demo.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Students;
import com.example.demo.repo.StdRepo;

@Service

public class Stdserv {
	@Autowired

	private StdRepo ur;

	public Page<Students> getPaginationPagebypage(int pageNum, int pageSize) {
		PageRequest of = PageRequest.of(pageNum - 1, pageSize);
		Page<Students> all = ur.findAll(of);
		return all;

	}

	public List<Students> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Students> getStudentsByNameAndMail(String name, String mail) {
		
		return null;
	}

	public static List<Students> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Students> getStudentsByMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Students> getAllStd() {
		// TODO Auto-generated method stub
		return null;
	}
  
		

}
