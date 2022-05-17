package tdtu.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdtu.spring.models.Donation;
import tdtu.spring.models.Donation;
import tdtu.spring.repositories.DonationRepository;

@Service
@Transactional
@Component
public class DonationService {

	@Autowired
	@Lazy
	private DonationRepository repo;

	public List<Donation> findAll() {
		return repo.findAll();
	}

	public List<Donation> findByAccountId(int id) {
		return repo.findByAccountId(id);
	}

	public Donation save(Donation donation) {
		return repo.save(donation);
	}

	public Donation get(int id) {
		return repo.findById(id).get();
	}
	
	public int sumAmount(int id) {
		return repo.getTotalAmountByProjectId(id);
	}
	
	public int sumAmount(int projectId, int accountId) {
		return repo.getTotalAmountByProjectIdAndAccountId(projectId, accountId);
	}
	
	public int countDonation(int id) {
		return repo.countByProjectId(id);
	}

}
