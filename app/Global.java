

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.GlobalSettings;
import play.Logger;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.Session;
import play.mvc.SimpleResult;
import controllers.routes;

public class Global extends GlobalSettings {

	// Add filtered urls
	private static final List<String> EXCEPTIONS = Arrays.asList(
			routes.MainController.todo().url(),
			routes.UserController.signUp().url(),
			routes.UserController.login().url()
	);
	private static final String CONTENT = "/content";

	@Override
	@SuppressWarnings("rawtypes")
	public Action onRequest(Request request, Method method) {
		return new Action.Simple() {
			@Override
			public Promise<SimpleResult> call(Context context) throws Throwable {
				Request request = context.request();
				String currentUrl = request.path();
				if (!currentUrl.startsWith(CONTENT)) {
					Map<String,String> map = new HashMap<String,String>();
					map.put("remoteAddress", request.remoteAddress());
					map.put("uri", String.format("%s - %s", request.method(), currentUrl));
					Logger.info(Json.toJson(map).toString());
					Session session = context.session();
					if (!EXCEPTIONS.contains(currentUrl) && request.method().equalsIgnoreCase("get")) {
						String previousUrl = previousUrl(session);
						session.put("previousUrl", previousUrl);
						session.put("currentUrl", currentUrl);
					}
				}
				return delegate.call(context);
			}

			private String previousUrl(Session session) {
				String previous = session.get("currentUrl");
				if (previous == null) {
					previous = routes.MainController.home().url();
				}
				return previous;
			}
		};
	}
}