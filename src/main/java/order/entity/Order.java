package order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
@Entity
@Table(name = "orderdetails")
public class Order implements Serializable{
	
	public Order() {
		super();
	}
	public Order(Integer orderAmount,Date dueDate,String orderDiscriptions,Date draftDate,String currency) {
		super();
		this.orderAmount = orderAmount;
		this.dueDate = dueDate;
		this.orderDiscriptions = orderDiscriptions;
		this.draftDate = draftDate;
		this.currency = currency;
	}
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Date deleteDate;
	@Column
	@NotNull(message = "order amount can not be null")
	@Min(value = 1,message = "order can not be less than one")
	@NumberFormat(style = Style.NUMBER)
	private Integer orderAmount;
	@Column
	@NotNull
	private Date dueDate;
	@Column
	private Date updateDate;
	@Column
	@NotNull(message = "order description can not be null")
	@NotEmpty(message = "order description can not be empty")
	private String orderDiscriptions;
	@Column
	private String orderStatus;
	@Column
	private Date draftDate;
	@Column 
	private Date readyDate;
	@Column 
	private Date inProgressDate;
	@Column
	private Date completionDate;
	@Column 
	private Date overdueDate;
	
	@Column
	@Size(min = 3, max = 3,message = "invalid currency, must be of 3 characters only")
	@NotNull(message = "currency can not be null")
	@NotEmpty(message = "currency can not be empty")
	private String currency;
	
	
	
	
	public Date getInProgressDate() {
		return inProgressDate;
	}
	public void setInProgressDate(Date inProgressDate) {
		this.inProgressDate = inProgressDate;
	}
	public Date getDraftDate() {
		return draftDate;
	}
	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}
	public Date getReadyDate() {
		return readyDate;
	}
	public void setReadyDate(Date readyDate) {
		this.readyDate = readyDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public Date getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getOrderDiscriptions() {
		return orderDiscriptions;
	}
	public void setOrderDiscriptions(String orderDiscriptions) {
		this.orderDiscriptions = orderDiscriptions;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	

}
