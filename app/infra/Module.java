package infra;

import play.api.mvc.Call;

public class Module {

	private Call call;
	private String id;

	public Module(Call call, String id) {
		this.call = call;
		this.id = id;
	}

	public Call getCall() {
		return call;
	}

	public String getId() {
		return id;
	}
}