package fa.training.utils;

public class Constant {
	public static final int PAGE_SIZE = 5;

	//Roles
  public static final String TRAINEE = "ROLE_TRAINEE";
  public static final String ADMIN = "ROLE_ADMIN";
  public static final String TRAINER = "ROLE_TRAINER";
  
  //Password
  public static final String DEFAULT_PASSWORD = "123456789";
	
	//Status
	public static final String CLASS_DEFAULT_STATUS = "In Comming";
  public static final String TRAINEE_DEFAULT_STATUS = "New";
  public static final String TRAINEE_ACTIVE_STATUS = "Active";
  public static final String TRAINEE_DISABLED_STATUS = "In Active";
  public static final String TRAINEE_GRADUATED_STATUS = "Graduated";
  public static final String SUBJECT_DEFAULT_STATUS = "New";
  public static final String SUBJECT_ACTIVE_STATUS = "Active";
	
	//Message
  public static final String UPDATE_SUCCESS_MESSAGE = "Update successfully!";
  public static final String UPDATE_FAIL_MESSAGE = "Update failed, please try again!";
  public static final String CREATE_SUCCESS_MESSAGE = "Created successfully!";
  public static final String CREATE_FAIL_MESSAGE = "Failed to create, this is already exist!";
  public static final String NOT_FOUND_MESSAGE = "Not found!";

	public static final String CLAZZ_CATEGORY = "Java";

	public static final String TRAINEE_SEARCH_ALL = "All";

	public static final String CATEGORY = "Category";
	public static final String CLAZZ = "Clazz";
	public static final String STATUS = "Status";
	public static final String CATEGORY_CLAZZ = "CategoryAndClazz";
	public static final String CATEGORY_STATUS = "CategoryAndStatus";
	public static final String CLAZZ_STATUS = "ClazzAndStatus";
	public static final String CATEGORY_CLAZZ_STATUS = "CategoryAndClazzAndStatus";
  public static final Integer FIRST_PAGE = 0;
	

}
