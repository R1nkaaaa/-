package pojo;

public class Page {
	private Integer startPage;
	private Integer pageSize;
	private Integer total;
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Page(Integer startPage, Integer pageSize, Integer total) {
		super();
		this.startPage = startPage;
		this.pageSize = pageSize;
		this.total = total;
	}
	
}
