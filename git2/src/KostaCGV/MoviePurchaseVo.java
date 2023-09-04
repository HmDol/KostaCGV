package KostaCGV;

import java.sql.Date;

public class MoviePurchaseVo {
	private int payCode;
	private String payMethod;
	private Date payDate;
	private String memId;
	private String seatCode;
	private int movieCode;
	
	public MoviePurchaseVo() {}

	
	
	public MoviePurchaseVo(int payCode, String payMethod, Date payDate, String memId, String seatCode, int movieCode) {
		super();
		this.payCode = payCode;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.memId = memId;
		this.seatCode = seatCode;
		this.movieCode = movieCode;
	}



	public int getPayCode() {
		return payCode;
	}

	public void setPayCode(int payCode) {
		this.payCode = payCode;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}
	

	
	
	
}