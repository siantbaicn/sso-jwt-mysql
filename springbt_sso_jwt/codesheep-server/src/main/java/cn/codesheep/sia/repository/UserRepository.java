package cn.codesheep.sia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.codesheep.sia.bean.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,String> {

	UserInfo findByLoginUserAndLoginPwd(String userNameAES, String pwdAES);

}