package std.javajunit5;

public class Study {
	private StudyStatus status;
	private int limit;
	private String name;

	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit 은 0보다 커야한다");
		}

		this.limit = limit;
	}

	public String getName() {
		return name;
	}

	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		return "Study{" +
			"status=" + status +
			", limit=" + limit +
			", name='" + name + '\'' +
			'}';
	}
}
