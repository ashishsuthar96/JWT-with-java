package com.relevel.test.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInput {
	
	    private Integer userId;
	    
	    @NotEmpty(message = "Name is mandatory")
	    private String name;
	    
	    @Size(min = 8, message = "password must be minimum 8 character long")
	    private String password;
	    
	    @Email(message = "please enter a valid email")
	    @NotEmpty(message = "email is required")
	    private String email;
	    
	    @Pattern(regexp = "(0|91)?[6-9]\\d{9}", message = "please enter valid mobile number")
	    private String phoneNumber;
	    
	    @Min(value = 5, message = "age must be between 5-100")
	    @Max(value = 100,message = "age must be between 5-100")
	    private int age;
	    
	    private String gender;
	    
	    private String address;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
	    

}
