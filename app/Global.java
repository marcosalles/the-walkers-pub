

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.Session;
import play.mvc.SimpleResult;
import controllers.routes;

public class Global extends GlobalSettings {

	// Add filtered urls
	private static final List<String> nonSavedUrls = Arrays.asList(
			routes.UserController.signUp().url(),
			routes.UserController.login().url()
	);

	@Override
	@SuppressWarnings("rawtypes")
	public Action onRequest(Request request, Method method) {
		return new Action.Simple() {
			@Override
			public Promise<SimpleResult> call(Context context) throws Throwable {
				Request actionRequest = context.request();
				Session session = context.session();
				String currentUrl = actionRequest.path();
				if (!nonSavedUrls.contains(currentUrl)) {
					String previousUrl = previousUrl(session);
					session.put("previousUrl", previousUrl);
					session.put("currentUrl", currentUrl);
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