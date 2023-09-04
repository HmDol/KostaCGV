package KostaCGV;

import java.sql.Date;

public class ReviewVo {
	private int reviewnum;
	private String ReviewTitle;
	private String content;
	private String name;
	private Date wdate;
	private int rating;
	private String id;

	public ReviewVo(int reviewnum, String reviewTitle, String content, String name, Date wdate, int rating, String id) {
		this.reviewnum = reviewnum;
		ReviewTitle = reviewTitle;
		this.content = content;
		this.name = name;
		this.wdate = wdate;
		this.rating = rating;
		this.id = id;
	}

	public int getReviewnum() {
		return reviewnum;
	}

	public void setReviewnum(int reviewnum) {
		this.reviewnum = reviewnum;
	}

	public String getReviewTitle() {
		return ReviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		ReviewTitle = reviewTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReviewVo [reviewnum=" + reviewnum + ", ReviewTitle=" + ReviewTitle + ", content=" + content + ", name="
				+ name + ", wdate=" + wdate + ", rating=" + rating + "]";
	}
}
