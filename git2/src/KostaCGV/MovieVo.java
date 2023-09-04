package KostaCGV;

import java.sql.Date;

public class MovieVo {

	private int movicode;
	private String movietitle;
	private Date MovieDate;
	private String StartTime;
	private String story;
	private String genre;

	public MovieVo(int movicode, String movietitle, Date date, String startTime, String story, String genre) {

		this.movicode = movicode;
		this.movietitle = movietitle;
		MovieDate = date;
		StartTime = startTime;
		this.story = story;
		this.genre = genre;
	}

	public int getmovicode() {
		return movicode;
	}

	public void setmovicode(int movicode) {
		this.movicode = movicode;
	}

	public String getMovietitle() {
		return movietitle;
	}

	public void setMovietitle(String movietitle) {
		this.movietitle = movietitle;
	}

	public Date getMovieDate() {
		return MovieDate;
	}

	public void setMovieDate(Date movieDate) {
		MovieDate = movieDate;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie [movicode=" + movicode + ", movietitle=" + movietitle + ", MovieDate=" + MovieDate + ", StartTime="
				+ StartTime + ", story=" + story + ", genre=" + genre + "]";
	}

}