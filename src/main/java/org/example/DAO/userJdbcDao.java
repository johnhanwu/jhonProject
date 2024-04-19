package org.example.DAO;

import org.example.common.io.ResourceBuild;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.core.io.ResourceLoader;
import javax.sql.DataSource;
import java.util.List;


@Repository
public class userJdbcDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ResourceLoader resourceLoader;

    public userJdbcDao(DataSource dataSource, ResourceLoader resourceLoader) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.resourceLoader = resourceLoader;
    }
@Autowired
    private JdbcTemplate jdbcTemplate;
@Autowired
    private ResourceBuild resourceBuild;


public void createUser(User user){
String sql="INSERT INTO user (first_name,last_name,age,address,height,weight)" +
        " VALUES (?, ?,?,?,?,?)";
    jdbcTemplate.update(sql,user.getFirstName(),
                            user.getLastName(),
                            user.getAge(),
                            user.getAddress(),
                            user.getHeight(),
                            user.getWeight());
}
    public List<User> getAllUsers() {
        String sql =resourceBuild.sqlPath("classpath:sql-template/usercrud/findAll.sql") ;
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getInt("height"),
                        rs.getInt("weight")
                ));

    }
    // 更新（Update）操作
    public void updateUser(User user,int id) {
        String sql = "UPDATE user SET first_name = ?,last_name=?age=?,address=?,height=?,weight=?" +
                " WHERE id = ?";
        jdbcTemplate.update(sql, user.getFirstName(),
                                    user.getLastName(),
                                    user.getAge(),
                                    user.getAddress(),
                                    user.getHeight(),
                                    user.getWeight(),
                                    user.getId());
    }

    // 刪除（Delete）操作
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }




}
