package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookVO extends RepresentationModel<BookVO>  implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@JsonProperty("key")
	@NotNull
	private Long key;	
	
	@NotBlank
	private String launchDate;
	@NotBlank
	private String author;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private String title;	

	@Override
	public int hashCode() {
		return Objects.hash(author, key, launchDate, price, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(key, other.key)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}


	public Long getKey() {
		return key;
	}


	public void setKey(Long key) {
		this.key = key;
	}


	public String getLaunchDate() {
		return launchDate;
	}


	public void setLaunchDate(String launchDate) {
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
