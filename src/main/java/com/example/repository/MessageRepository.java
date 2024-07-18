package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query("FROM Message WHERE messageId =:messageId")
	public Message getMessageById(@Param("messageId") int messageId);

    @Modifying
    @Query("DELETE FROM Message WHERE messageId=:messageId")
    public int deleteById(@Param("messageId") int messageId);

    @Query("FROM Message WHERE postedBy =:postedBy")
    public List<Message> getMessagesForUser(@Param("postedBy") int postedBy);

    @Modifying
    @Query("UPDATE Message SET messageText =:messageText WHERE messageId=:messageId")
    public Message updateMessageText(@Param("messageId") int messageId, @Param("messageText") String messageText);
}

// public Account login(String username, String password){
//     Connection connection = ConnectionUtil.getConnection();
//     try{
//         //get
//         String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
//         PreparedStatement preparedStatement=connection.prepareStatement(sql);
//         preparedStatement.setString(1,username);
//         preparedStatement.setString(2,password);

//         ResultSet rs =preparedStatement.executeQuery();
//         while(rs.next()){
//             Account account = new Account(
//                 rs.getInt("account_id"),
//                 rs.getString("username"),
//                 rs.getString("password"));
//             return account;

//         }
//     }catch(SQLException e){
//         System.out.println(e.getMessage());
//     }
//     return null;
// }
// public Account login(String username, String password){

//     if(accountDAO!=null){
//         return accountDAO.login(username,password);

//     }
//     else{
//         return null;
//     }
// }

// @PostMapping(value="/login")
// public Account login(@RequestBody Account account){
//     return account;
// }
