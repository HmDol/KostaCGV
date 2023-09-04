package KostaCGV;

import java.sql.Date;

public class MarketVo {

	private String snack;
	private int price;
	private int amount;

	public MarketVo() {

	}

	public MarketVo(String snack, int price, int amount) {

		this.snack = snack;
		this.price = price;
		this.amount = amount;
	}

	public String getSnack() {
		return snack;
	}

	public void setSnack(String snack) {
		this.snack = snack;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
