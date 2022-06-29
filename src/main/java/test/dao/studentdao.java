package test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import test.deans.student;

public class studentdao {
	
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(student s) {
		String sql="insert into student(fname,lname,email,password)values('"+s.getFname()+"','"+s.getLname()+"','"+s.getEmail()+"','"+s.getPassword()+"')";
		return template.update(sql);
	}
	
	public int update(student s) {
		String sql="update student set fname='"+s.getFname()+"',lname='"+s.getLname()+"',email='"+s.getEmail()+"',password='"+s.getPassword()+"' where id="+s.getId()+"";
		return template.update(sql);
	}
	
	public student login(String email, String password) {
		String sql = "select * from student where email=? and password=?";
		
		try {
			student s = template.queryForObject(sql, new Object[] {email,password}, new RowMapper<student>() {

				public student mapRow(ResultSet rs, int rowNum) throws SQLException {
					student s = new student();
					
					s.setId(rs.getInt("id"));
					s.setFname(rs.getString("fname"));
					s.setLname(rs.getString(3));
					s.setEmail(rs.getString(4));
					s.setPassword(rs.getString(5));
					return s;
				}});
			return s;
		}
		catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	public List<student> getallstudents(){
		return template.query("select * from student", new RowMapper<student>() {

			public student mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				student s = new student();
				s.setId(rs.getInt(1));
				s.setFname(rs.getString(2));
				s.setLname(rs.getString(3));
				s.setEmail(rs.getString(4));
				s.setPassword(rs.getString(5));
				
				return s;
			}});
	}
	
	public int delete(int id) {
		String sql="delete from student where id="+id;
		return template.update(sql);
	}
	
	public student getstudent(int id) {
		String sql="select * from student where id=?";
		
		return template.queryForObject(sql, new Object[] {id},new BeanPropertyRowMapper<student>(student.class));
	}
	
}
