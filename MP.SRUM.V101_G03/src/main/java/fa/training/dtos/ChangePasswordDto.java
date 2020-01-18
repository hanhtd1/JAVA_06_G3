package fa.training.dtos;

/**
 * @author TrangDM2
 *
 */
public class ChangePasswordDto {
  private String account;
  private String password;
  private String confirmPassword;

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public ChangePasswordDto(String account, String password, String confirmPassword) {
    super();
    this.account = account;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }

  public ChangePasswordDto() {
    super();
  }

  public boolean isPasswordMatch() {
    return password.equals(confirmPassword);
  }

  @Override
  public String toString() {
    return "ChangePasswordDto [account=" + account + ", password=" + password + ", confirmPassword=" + confirmPassword
        + "]";
  }
}
