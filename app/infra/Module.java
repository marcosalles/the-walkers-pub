package infra;

import play.api.mvc.Call;

public class Module {

	private Call call;
	private String id;
	private int priority;

	public Module(Call call, String id, int priority) {
		this.call = call;
		this.id = id;
		this.priority = priority;
	}

	public String url() {
		return call.url();
	}
	public String id() {
		return id;
	}
	public int priority() {
		return priority;
	}
}