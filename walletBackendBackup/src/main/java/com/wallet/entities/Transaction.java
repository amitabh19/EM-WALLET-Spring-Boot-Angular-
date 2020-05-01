package com.wallet.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "idt_Sequence")
	@SequenceGenerator(name = "idt_Sequence", sequenceName = "IDt_SEQ")
	private long id;
	private String txnId;
	private double amount;
	private LocalDateTime tTime;

	private String tType;

	private long senderId;
	private long recieverId;
	private double updatedPersonBal;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String txnId, double amount, LocalDateTime tTime, String tType, long senderId, long recieverId,
			double updatedPersonBal) {
		super();
		this.txnId = txnId;
		this.amount = amount;
		this.tTime = tTime;
		this.tType = tType;
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.updatedPersonBal = updatedPersonBal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime gettTime() {
		return tTime;
	}

	public void settTime(LocalDateTime tTime) {
		this.tTime = tTime;
	}

	public String gettType() {
		return tType;
	}

	public void settType(String tType) {
		this.tType = tType;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(long recieverId) {
		this.recieverId = recieverId;
	}

	public double getUpdatedPersonBal() {
		return updatedPersonBal;
	}

	public void setUpdatedPersonBal(double updatedPersonBal) {
		this.updatedPersonBal = updatedPersonBal;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", txnId=" + txnId + ", amount=" + amount + ", tTime=" + tTime + ", tType="
				+ tType + ", senderId=" + senderId + ", recieverId=" + recieverId + ", updatedPersonBal="
				+ updatedPersonBal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (recieverId ^ (recieverId >>> 32));
		result = prime * result + (int) (senderId ^ (senderId >>> 32));
		result = prime * result + ((tTime == null) ? 0 : tTime.hashCode());
		result = prime * result + ((tType == null) ? 0 : tType.hashCode());
		result = prime * result + ((txnId == null) ? 0 : txnId.hashCode());
		temp = Double.doubleToLongBits(updatedPersonBal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Transaction))
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (recieverId != other.recieverId)
			return false;
		if (senderId != other.senderId)
			return false;
		if (tTime == null) {
			if (other.tTime != null)
				return false;
		} else if (!tTime.equals(other.tTime))
			return false;
		if (tType == null) {
			if (other.tType != null)
				return false;
		} else if (!tType.equals(other.tType))
			return false;
		if (txnId == null) {
			if (other.txnId != null)
				return false;
		} else if (!txnId.equals(other.txnId))
			return false;
		if (Double.doubleToLongBits(updatedPersonBal) != Double.doubleToLongBits(other.updatedPersonBal))
			return false;
		return true;
	}

}
