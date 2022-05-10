package tdtu.spring.models;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "Project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int targetFund;

	@ColumnDefault("0")
	private int currentFund = 0;

	public Project() {
		super();
	}

	public Project(String name, String description, int targetFund) {
		super();
		this.name = name;
		this.description = description;
		this.targetFund = targetFund;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTargetFund() {
		return targetFund;
	}

	public void setTargetFund(int targetFund) {
		this.targetFund = targetFund;
	}

	public int getCurrentFund() {
		return currentFund;
	}

	public void setCurrentFund(int currentFund) {
		this.currentFund = currentFund;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", targetFund=" + targetFund
				+ ", currentFund=" + currentFund + "]";
	}
}
