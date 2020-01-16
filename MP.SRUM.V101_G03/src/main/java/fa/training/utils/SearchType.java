package fa.training.utils;

public class SearchType {
	public static String searchType(String category, String clazz, String status) {
		System.out.println("clazz : " + clazz);
		if (!category.equals(Constant.TRAINEE_SEARCH_ALL) && clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.CATEGORY;
		} else if (category.equals(Constant.TRAINEE_SEARCH_ALL) && !clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.CLAZZ;
		} else if (category.equals(Constant.TRAINEE_SEARCH_ALL) && clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& !status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.STATUS;
		} else if (!category.equals(Constant.TRAINEE_SEARCH_ALL) && !clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.CATEGORY_CLAZZ;
		} else if (!category.equals(Constant.TRAINEE_SEARCH_ALL) && clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& !status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.CATEGORY_STATUS;
		} else if (category.equals(Constant.TRAINEE_SEARCH_ALL) && !clazz.equals(Constant.TRAINEE_SEARCH_ALL)
				&& !status.equals(Constant.TRAINEE_SEARCH_ALL)) {
			return Constant.CLAZZ_STATUS;
		} else {
			return Constant.CATEGORY_CLAZZ_STATUS;
		}
	}

	public static String clazzSearchType(final String contentSearch, final String status) {
		if (contentSearch.equals(Constant.CLAZZ_CONTENT_SEARCH_DEFAULT)
				&& status.equals(Constant.CLAZZ_STATUS_DEFAULT)) {
			return Constant.CLAZZ_SEARCH_BY_TRAINER_ID;
		} else if (!contentSearch.equals(Constant.CLAZZ_CONTENT_SEARCH_DEFAULT)
				&& status.equals(Constant.CLAZZ_STATUS_DEFAULT)) {
			return Constant.CLAZZ_NAME_AND_CATEGORY;
		} else if (contentSearch.equals(Constant.CLAZZ_CONTENT_SEARCH_DEFAULT)
				&& !status.equals(Constant.CLAZZ_STATUS_DEFAULT)) {
			return Constant.STATUS;
		} else {
			return Constant.CLAZZ_NAME_AND_CATEGORY_AND_STATUS;
		}
	}
}
