package com.example.sino.model.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.sino.utils.DateConverter;
import com.example.sino.utils.PictureBytesConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "sino_table")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Long serverUserId;
    private String name;
    private String family;
    private String mobileNo;
    private String username;
    private String password;
    private String bisPassword;
    @TypeConverters({DateConverter.class})
    private Date expireDate;
    private Integer loginStatus;
    @TypeConverters({DateConverter.class})
    private Date lastLoginDate;
    private String companyName;
    private String picturePathUrl;
    private Boolean autoLogin = false;
    private Boolean loginIs = false;
    @TypeConverters({PictureBytesConverter.class})
    private List<Integer> pictureBytes;

    public User() {
    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        if (in.readByte() == 0) {
            serverUserId = null;
        } else {
            serverUserId = in.readLong();
        }
        name = in.readString();
        family = in.readString();
        mobileNo = in.readString();
        username = in.readString();
        password = in.readString();
        bisPassword = in.readString();
        if (in.readByte() == 0) {
            loginStatus = null;
        } else {
            loginStatus = in.readInt();
        }
        companyName = in.readString();
        picturePathUrl = in.readString();
        byte tmpAutoLogin = in.readByte();
        autoLogin = tmpAutoLogin == 0 ? null : tmpAutoLogin == 1;
        byte tmpLoginIs = in.readByte();
        loginIs = tmpLoginIs == 0 ? null : tmpLoginIs == 1;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerUserId() {
        return serverUserId;
    }

    public void setServerUserId(Long serverUserId) {
        this.serverUserId = serverUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBisPassword() {
        return bisPassword;
    }

    public void setBisPassword(String bisPassword) {
        this.bisPassword = bisPassword;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPicturePathUrl() {
        return picturePathUrl;
    }

    public void setPicturePathUrl(String picturePathUrl) {
        this.picturePathUrl = picturePathUrl;
    }

    public Boolean getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(Boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public Boolean getLoginIs() {
        return loginIs;
    }

    public void setLoginIs(Boolean loginIs) {
        this.loginIs = loginIs;
    }

    public List<Integer> getPictureBytes() {
        return pictureBytes;
    }

    public void setPictureBytes(List<Integer> pictureBytes) {
        this.pictureBytes = pictureBytes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        if (serverUserId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(serverUserId);
        }
        parcel.writeString(name);
        parcel.writeString(family);
        parcel.writeString(mobileNo);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(bisPassword);
        if (loginStatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(loginStatus);
        }
        parcel.writeString(companyName);
        parcel.writeString(picturePathUrl);
        parcel.writeByte((byte) (autoLogin == null ? 0 : autoLogin ? 1 : 2));
        parcel.writeByte((byte) (loginIs == null ? 0 : loginIs ? 1 : 2));
    }
}
