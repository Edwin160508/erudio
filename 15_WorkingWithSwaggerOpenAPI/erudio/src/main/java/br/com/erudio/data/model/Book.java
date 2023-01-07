package br.com.erudio.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("key")
	private Long id;
	
	@Column(name="launch_date", nullable = false)
	private Calendar launchDate;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(nullable = false)
	private String title;

	@Override
	public int hashCode() {
		return Objects.hash(author, id, launchDate, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Calendar launchDate) {
		this.launchDate = launchDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
