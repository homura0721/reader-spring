package cn.edu.scujcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Comment implements Serializable{
	private static final long serialVersionUID = 9025921434272724054L;
	
	private String content;//评论内容
	private String commentAuthor;//评论作者
	private int star;//点赞数
	private String facorites; //收藏夹
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dt = LocalDateTime.now();//评论时间
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentAuthor() {
		return commentAuthor;
	}
	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getFacorites() {
		return facorites;
	}
	public void setFacorites(String facorites) {
		this.facorites = facorites;
	}
	public LocalDateTime getDt() {
		return dt;
	}
	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentAuthor == null) ? 0 : commentAuthor.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dt == null) ? 0 : dt.hashCode());
		result = prime * result + ((facorites == null) ? 0 : facorites.hashCode());
		result = prime * result + star;
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
		Comment other = (Comment) obj;
		if (commentAuthor == null) {
			if (other.commentAuthor != null)
				return false;
		} else if (!commentAuthor.equals(other.commentAuthor))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dt == null) {
			if (other.dt != null)
				return false;
		} else if (!dt.equals(other.dt))
			return false;
		if (facorites == null) {
			if (other.facorites != null)
				return false;
		} else if (!facorites.equals(other.facorites))
			return false;
		if (star != other.star)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comment [content=" + content + ", commentAuthor=" + commentAuthor + ", star=" + star + ", facorites="
				+ facorites + ", dt=" + dt + "]";
	}
	
	
}
