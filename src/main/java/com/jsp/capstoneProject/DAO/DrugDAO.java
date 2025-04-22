package com.jsp.capstoneProject.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.capstoneProject.entity.Drug;
import com.jsp.capstoneProject.repo.DrugRepo;

@Repository
public class DrugDAO 
{
	@Autowired
	DrugRepo drugRepo;
	
	public Drug saveDrug(Drug drug)
	{
		return drugRepo.save(drug);	
	}
	
	public Drug updateDrug(Drug drug)
	{
		Optional<Drug> op= drugRepo.findById(drug.getId());
		if(op!=null)
		{
			Drug db= op.get();
			if(drug.getCompany()==null)
			{
				drug.setCompany(db.getCompany());
			}
			if(drug.getName()==null)
			{
				drug.setName(db.getName());
			}
			if(drug.getPrice()==0)
			{
				drug.setPrice(db.getPrice());
			}
			if(drug.getQuality()==0)
			{
				drug.setQuality(db.getQuality());
			}
			if(drug.getRating()==0)
			{
				drug.setRating(db.getRating());
			}
			if(drug.getType()==null)
			{
				drug.setType(db.getType());
			}
			return drugRepo.save(drug);
		}
		return null;
	}
	
	public Drug fetchDrugById(int id)
	{
		Optional<Drug>op= drugRepo.findById(id);
		if (op.isPresent()) 
		{
			Drug db= op.get();
			return db;
		}
		return null;
	}
	
	public Drug deleteDrug(int id)
	{
		Optional<Drug>op= drugRepo.findById(id);
		if (op.isPresent()) 
		{
			Drug db= op.get();
			drugRepo.delete(db);
			return db;
		}
		return null;
	}
	public Drug fetchByName(String name)
	{
		Optional<Drug> op= drugRepo.findByName(name);
		if(op!=null)
		{
			Drug db= op.get();
			return db;
		}
		return null;
	}

	public List<Drug> fetchAllDrugs() 
	{
		return drugRepo.findAll();
	}
}
