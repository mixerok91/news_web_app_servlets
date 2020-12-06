package by.academy.newsapp.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class News implements Serializable{

	private static final long serialVersionUID = -385009162822665170L;

	private int id;
	private String title;
	private LocalDate date;
	private String brief;
	private String text;
	
	public News() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newsTitle) {
		this.title = newsTitle;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate newsDate) {
		this.date = newsDate;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getText() {
		return text;
	}

	public void setText(String content) {
		this.text = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + id;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (id != other.id)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", date=" + date + ", brief=" + brief
				+ ", text=" + text + "]";
	}

}
