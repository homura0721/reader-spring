package cn.edu.scujcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Book implements Serializable{
	private static final long serialVersionUID = 8113986652205866086L;
	
	private String id;
	private String title;
	private String author;
	private String body;   //书的正文， txt、epub 格式
	private String blurb; //书的简介 introduction blurb
	private String tag1; // 书的标签1
	private String tag2; // 书的标签2
	private String tag3; // 书的标签3
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateTime = LocalDateTime.now();//更新时间
	private String cover;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((blurb == null) ? 0 : blurb.hashCode());
		result = prime * result + ((tag1 == null) ? 0 : tag1.hashCode());
		result = prime * result + ((tag2 == null) ? 0 : tag2.hashCode());
		result = prime * result + ((tag3 == null) ? 0 : tag3.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (blurb == null) {
			if (other.blurb != null)
				return false;
		} else if (!blurb.equals(other.blurb))
			return false;
		if (tag1 == null) {
			if (other.tag1 != null)
				return false;
		} else if (!tag1.equals(other.tag1))
			return false;
		if (tag2 == null) {
			if (other.tag2 != null)
				return false;
		} else if (!tag2.equals(other.tag2))
			return false;
		if (tag3 == null) {
			if (other.tag3 != null)
				return false;
		} else if (!tag3.equals(other.tag3))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", body=" + body + ", blurb="
				+ blurb + ", tag1=" + tag1 + ", tag2=" + tag2 + ", tag3=" + tag3 + ", updateTime=" + updateTime
				+ ", cover=" + cover + "]";
	}
	

}
