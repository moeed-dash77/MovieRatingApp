package dbadapter;

public class Rating {
	private String uid;
	private String mid;
	private int rating;
	private String comment;
	
	public Rating(String uid, String mid, int rating, String comment) {
		this.uid = uid;
		this.mid = mid;
		this.rating = rating;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Rating [Movie Title=" + mid + ", Your UserName=" + uid + ", Your Rating="
				+ rating + ", Your comment=" + comment + "]";
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String username) {
		this.uid = username;
	}
	public String getMid() {
		return mid;
	}
	public void setTitle(String title) {
		this.mid = title;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}