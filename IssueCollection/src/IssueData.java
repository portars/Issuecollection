public class IssueData {
	/**
	 * 
	 */
	private String keyword;
	private String content;
	private String link;
	private String imageurl;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	public IssueData(String keyword,String content,String link,String imageurl)
	{
		this.keyword=keyword;
		this.content=content;
		this.link=link;
		this.imageurl=imageurl;
	}
	public void println(){
		System.out.println(keyword+"/"+content+"/"+link+"/"+imageurl);
//		System.out.println(imageurl);
	}
}
