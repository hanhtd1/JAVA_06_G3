package fa.training.utils;

public interface Constant {

	// Regex
	public static final String REGEX_PHONE_NUMBER = "((09|03|07|08|05)+([0-9]{8})\\b)";
	public static final String REGEX_NAME = "^[A-Z]+(([',. -][A-Z ])?[a-zA-Z]*)*{2,}$";

	public static final int PAGE_SIZE = 5;

	// Roles
	public static final String TRAINEE = "ROLE_TRAINEE";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String TRAINER = "ROLE_TRAINER";

	// Password
	public static final String DEFAULT_PASSWORD = "123456789";

	// ID
	public static final Integer DEFAULT_ID = 0;

	// Status
	public static final String CLASS_DEFAULT_STATUS = "In Coming";
	public static final String CLASS_ACTIVE_STATUS = "Active";
	public static final String USER_DEFAULT_STATUS = "New";
	public static final String TRAINEE_DEFAULT_STATUS = "New";
	public static final String TRAINEE_ACTIVE_STATUS = "Active";
	public static final String TRAINEE_DISABLED_STATUS = "In Active";
	public static final String TRAINEE_GRADUATED_STATUS = "Graduated";
	public static final String SUBJECT_DEFAULT_STATUS = "New";
	public static final String SUBJECT_ACTIVE_STATUS = "Active";
	public static final String SUBJECT_DISABLED_STATUS = "In Active";

	// Message
	public static final String UPDATE_SUCCESS_MESSAGE = "Update successfully!";
	public static final String UPDATE_FAIL_MESSAGE = "Update failed, please try again!";
	public static final String CREATE_SUCCESS_MESSAGE = "Created successfully!";
	public static final String CREATE_FAIL_MESSAGE = "Failed to create, this is already exist!";
	public static final String NOT_FOUND_MESSAGE = "Not found!";

	public static final String VALID_NAME_MESSAGE = "Please enter valid name format!";
	public static final String VALID_PHONE_MESSAGE = "Please enter valid phone number format! (ex: 09xxxx-xxxx)";

	// Other
	public static final String CLAZZ_CATEGORY = "Java";
	public static final String TRAINEE_SEARCH_ALL = "All";
	public static final String CATEGORY = "CATEGORY";
	public static final String CLAZZ = "CLAZZ";
	public static final String STATUS = "STATUS";
	public static final String CATEGORY_CLAZZ = "CATEGORY_CLAZZ";
	public static final String CATEGORY_STATUS = "CATEGORY_STATUS";
	public static final String CLAZZ_STATUS = "CLAZZ_STATUS";
	public static final String CATEGORY_CLAZZ_STATUS = "CATEGORY_CLAZZ_STATUS";
	public static final Integer FIRST_PAGE = 0;

	public static final int USER_ID_DEFAULT = 2;
	public static final String FIRST_PAGE_STRING = "1";

	public static final String CLAZZ_CONTENT_SEARCH_DEFAULT = "";
	public static final String CLAZZ_STATUS_DEFAULT = "All";
	public static final String CLAZZ_PAGE_DEFAULT = "1";
	public static final String CLAZZ_SEARCH_BY_TRAINER_ID = "CLAZZ_SEARCH_BY_TRAINER_ID";
	public static final String CLAZZ_NAME_AND_CATEGORY = "CLAZZ_NAME_AND_CATEGORY";
	public static final String CLAZZ_NAME_AND_CATEGORY_AND_STATUS = "CLAZZ_NAME_AND_CATEGORY_AND_STATUS";

	public static final int FIRST_RESULT = 0;

  public static final String UPDATE_ACTION = "UPDATE";
  public static final String CREATE_ACTION = "CREATE";
}
