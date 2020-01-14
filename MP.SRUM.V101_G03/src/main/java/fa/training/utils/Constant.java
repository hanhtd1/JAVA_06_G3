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
	public static final String DEFAULT_CLASS_STATUS = "In Comming";
  public static final String DEFAULT_TRAINEE_STATUS = "New";
  public static final String ACTIVE_TRAINEE_STATUS = "Active";
  public static final String DISABLED_TRAINEE_STATUS = "In Active";
  public static final String GRADUATED_TRAINEE_STATUS = "Graduated";
	
	//Message
  public static final String UPDATE_SUCCESS_MESSAGE = "Update successfully!";
  public static final String CREATE_SUCCESS_MESSAGE = "Created successfully!";
  public static final String UPDATE_FAIL_MESSAGE = "Update failed, please try again!";
  public static final String CREATE_FAIL_MESSAGE = "Failed to create, this is already exist!";
  public static final String NOT_FOUND_MESSAGE = "Not found!";
}
