package jumpPlusProject2.model;

public class Movie {
	private String title;
	private double totalRating;
	private int numRatings;
	private double avgRating;

	public void addRating(double rating) {
		if(totalRating < 0)
			totalRating = 0;
		totalRating += rating;
		numRatings++;
		avgRating = totalRating / numRatings;
	}
	
	public Movie(String title) {
		this.title = title;
		totalRating = -1; //we know this movie has NO ratings, not just a 0 rating
		numRatings = 0;
		avgRating = 0;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", totalRatings=" + totalRating + ", numRatings=" + numRatings
				+ ", avgRating=" + avgRating + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumRatings() {
		return numRatings;
	}

	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

}
