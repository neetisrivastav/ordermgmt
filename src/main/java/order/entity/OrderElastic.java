//package order.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.stereotype.Component;
//import org.springframework.data.annotation.Id;
//
//@Component
//
////Elastic search annotation.
//@Document(indexName= "my_index", type= "ordermgmt")
//public class OrderElastic implements Serializable {
////	@Id
//	private Long id;
//	private Date deleteDate;
//	private Integer orderAmount;
//	private Date dueDate;
//	private Date updateDate;
//	private String orderDiscriptions;
//	private String orderStatus;
//	private Date draftDate;
//	private Date readyDate;
//	private Date inProgressDate;
//	private Date completionDate;
//	private String currency;
//	
//	
//	public OrderElastic() {
//		super();
//	}
//	public OrderElastic(Long id, Date deleteDate, Integer orderAmount, Date dueDate, Date updateDate,
//			String orderDiscriptions, String orderStatus, Date draftDate, Date readyDate, Date inProgressDate,
//			Date completionDate, String currency) {
//		super();
//		this.id = id;
//		this.deleteDate = deleteDate;
//		this.orderAmount = orderAmount;
//		this.dueDate = dueDate;
//		this.updateDate = updateDate;
//		this.orderDiscriptions = orderDiscriptions;
//		this.orderStatus = orderStatus;
//		this.draftDate = draftDate;
//		this.readyDate = readyDate;
//		this.inProgressDate = inProgressDate;
//		this.completionDate = completionDate;
//		this.currency = currency;
//	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public Date getDeleteDate() {
//		return deleteDate;
//	}
//	public void setDeleteDate(Date deleteDate) {
//		this.deleteDate = deleteDate;
//	}
//	public Integer getOrderAmount() {
//		return orderAmount;
//	}
//	public void setOrderAmount(Integer orderAmount) {
//		this.orderAmount = orderAmount;
//	}
//	public Date getDueDate() {
//		return dueDate;
//	}
//	public void setDueDate(Date dueDate) {
//		this.dueDate = dueDate;
//	}
//	public Date getUpdateDate() {
//		return updateDate;
//	}
//	public void setUpdateDate(Date updateDate) {
//		this.updateDate = updateDate;
//	}
//	public String getOrderDiscriptions() {
//		return orderDiscriptions;
//	}
//	public void setOrderDiscriptions(String orderDiscriptions) {
//		this.orderDiscriptions = orderDiscriptions;
//	}
//	public String getOrderStatus() {
//		return orderStatus;
//	}
//	public void setOrderStatus(String orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//	public Date getDraftDate() {
//		return draftDate;
//	}
//	public void setDraftDate(Date draftDate) {
//		this.draftDate = draftDate;
//	}
//	public Date getReadyDate() {
//		return readyDate;
//	}
//	public void setReadyDate(Date readyDate) {
//		this.readyDate = readyDate;
//	}
//	public Date getInProgressDate() {
//		return inProgressDate;
//	}
//	public void setInProgressDate(Date inProgressDate) {
//		this.inProgressDate = inProgressDate;
//	}
//	public Date getCompletionDate() {
//		return completionDate;
//	}
//	public void setCompletionDate(Date completionDate) {
//		this.completionDate = completionDate;
//	}
//	public String getCurrency() {
//		return currency;
//	}
//	public void setCurrency(String currency) {
//		this.currency = currency;
//	}
//	
//	
//
//}
