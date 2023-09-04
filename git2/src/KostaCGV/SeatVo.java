package KostaCGV;

import java.sql.Date;

public class SeatVo {

	private String seatcode;
	private String seatname;
	private int isres;
	private String moviecode;
	

	public SeatVo() {

	}



	public SeatVo(String seatcode, String seatname, int isres, String moviecode) {
		super();
		this.seatcode = seatcode;
		this.seatname = seatname;
		this.isres = isres;
		this.moviecode = moviecode;

	}

	public String getSeatcode() {
		return seatcode;
	}

	public void setSeatcode(String seatcode) {
		this.seatcode = seatcode;
	}

	public String getSeatname() {
		return seatname;
	}

	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}

	public int getIsres() {
		return isres;
	}

	public void setIsres(int isres) {
		this.isres = isres;
	}

	public String getMoviecode() {
		return moviecode;
	}

	public void setMoviecode(String moviecode) {
		this.moviecode = moviecode;
	}

	@Override
	public String toString() {
		return "CinemaVo [seatcode=" + seatcode + ", seatname=" + seatname + ", isres=" + isres + ", moviecode="
				+ moviecode + "]";
	}

}
