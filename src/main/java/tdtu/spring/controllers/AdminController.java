package tdtu.spring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tdtu.spring.models.Account;
import tdtu.spring.models.Project;
import tdtu.spring.services.AccountService;
import tdtu.spring.services.DonationService;
import tdtu.spring.services.ProjectService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private DonationService donationService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("")
	public String showHome(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);

		Page<Project> projectPage = projectService.findPaginatedProject(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("projectPage", projectPage);

		int totalPages = projectPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "admin/home";
	}

	@GetMapping("/projects")
	public String showManageProject(Model model) {
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects", projects);
		return "admin/manage-project";
	}

	@GetMapping("/accounts")
	public String showManageAccount(Model model) {
		List<Account> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		return "admin/manage-account";
	}

	@GetMapping("/aprrove/{projectId}")
	public String approveProject(@PathVariable(name = "projectId") int projectId) {
		System.out.println(projectId);

		projectService.updateStatus(projectId, "Running");

		return "redirect:/admin/projects";
	}
	
	@PostMapping("/add-account")
	public String addAccount(@ModelAttribute("account") Account account) {
		String name = account.getName();
		String username = account.getUsername();
		String password = passwordEncoder.encode(account.getPassword());
		String role = "user&admin";// account.getRole();

		Account newAccount = new Account(name, username, password, role);
		accountService.save(newAccount);

		return "redirect:/admin/accounts";
	}
	
  @GetMapping("/donations")
  public String showDonationList(Model model) {
  	model.addAttribute("donations", donationService.findAll());
  	return "admin/manage-donation";
  }
	
}
