package cn.secondKill.entity;

/**
 * Created by dell on 2017/7/22.
 * 用户model
 */
public class HumanModel {

    private int humanId;

    private String humanName;

    private String gender;

    private String userPhone;

    private String password;

    public int getHumanId() {
        return humanId;
    }

    public void setHumanId(int humanId) {
        this.humanId = humanId;
    }

    public String getHumanName() {
        return humanName;
    }

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "HumanModel{" +
                "humanId=" + humanId +
                ", humanName='" + humanName + '\'' +
                ", gender='" + gender + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
