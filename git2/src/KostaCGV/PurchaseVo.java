package KostaCGV;

import java.sql.Date;

public class PurchaseVo {

	private String id;
	private String snack;
	private int amount;

	public PurchaseVo() {

	}

	public PurchaseVo(String id, String snack, int amount) {

		this.id = id;
		this.snack = snack;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSnack() {
		return snack;
	}

	public void setSnack(String snack) {
		this.snack = snack;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
