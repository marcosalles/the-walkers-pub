package infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.data.validation.ValidationError;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

import daos.UserDao;

public abstract class Validator {

	protected List<ValidationError> errors;

	protected Validator() {
		this.errors = new ArrayList<ValidationError>();
	}

	protected abstract void validate();

	public Validator validated() {
		validate();
		return this;
	}

	public boolean isValid() {
		return this.errors.size() == 0;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	protected boolean stringNotLongEnough(String string, int size) {
		return string.length() < size;
	}

	public JsonNode errorsAsJson() {
		Map<String, String> map = new HashMap<String, String>();
		for (ValidationError error : errors) {
			map.put(error.key(), error.message());
		}
		return Json.toJson(UserDao.list());
	}
}
