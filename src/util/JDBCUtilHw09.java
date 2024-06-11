package util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import vo.BoardVO;

public class JDBCUtilHw09 {
	
	private static JDBCUtilHw09 instance = null;

	private JDBCUtilHw09() {
	} 
	
	public static JDBCUtilHw09 getInstance() {
		if(instance == null) 
			instance = new JDBCUtilHw09();
		return instance;
	}
	

	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "pc11_5";
	private String password = "java";
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		// 자원 반납
		if (rs != null) try {rs.close();} catch (SQLException e) {}
		if (pstmt != null) try {pstmt.close();} catch (SQLException e) {}
		if (stmt != null) try {stmt.close();} catch (SQLException e) {}
		if (conn != null) try {conn.close();} catch (SQLException e) {}		
	}

	
	public void delete(String sql, BoardVO bv) {
		 
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setBigDecimal(1, bv.getNo());
			
			cnt = pstmt.executeUpdate();
			printResult(cnt);
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println();
			System.out.println("DB연결이 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			close(conn, stmt, pstmt, rs);
		}

	}
	
	public void update(String sql, BoardVO bv) {
		 
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, bv.getTitle());
			pstmt.setNString(2, bv.getWritter());
			pstmt.setNString(3, bv.getContent());
			pstmt.setBigDecimal(4, bv.getNo());
			
			cnt = pstmt.executeUpdate();
			printResult(cnt);
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println();
			System.out.println("DB연결이 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			close(conn, stmt, pstmt, rs);
		}

	}
	
	public void insert(String sql, BoardVO bv) {
		 
		int cnt = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setNString(1, bv.getTitle());
			pstmt.setNString(2, bv.getWritter());
			pstmt.setNString(3, bv.getContent());
			
			cnt = pstmt.executeUpdate();
			printResult(cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println();
//			System.out.println("DB연결이 실패하거나, SQL문이 틀렸습니다.");
//			System.out.print("사유 : " + e.getMessage());
		} finally {
			close(conn, stmt, pstmt, rs);
		}

	}
	
	
	public List<BoardVO> searchSelectList(String sql, BoardVO bv) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);

			if (bv.getDate() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				String date = bv.getDate().format(formatter);
				pstmt.setNString(1, date);

			} else if (bv.getWritter() != null) {
				pstmt.setNString(1, bv.getWritter());
			}  else if (bv.getTitle() != null) {
				pstmt.setNString(1, bv.getTitle());
			}  else if (bv.getContent() != null) {
				pstmt.setNString(1, bv.getContent());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				BigDecimal no = rs.getBigDecimal("BOARD_NO");
				String title = rs.getNString("BOARD_TITLE");
				String writer = rs.getNString("BOARD_WRITER");
				Timestamp stamp =  rs.getTimestamp("BOARD_DATE");
				LocalDateTime date = rs.getTimestamp("BOARD_DATE").toLocalDateTime();
				String content = rs.getNString("BOARD_CONTENT");
			
				BoardVO boradVo = new BoardVO();
				boradVo.setNo(no);
				boradVo.setTitle(title);
				boradVo.setWriter(writer);
				boradVo.setDate(date);
				boradVo.setContent(content);
				
				list.add(boradVo);
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println();
			System.out.println("DB연결이 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}


	// executeQuery() => select
	public List<BoardVO> selectList(String sql, List<Object> page) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);

			for (int i=0; i<page.size(); i++) {
				pstmt.setObject(i+1, page.get(i));
			} 
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				BigDecimal no = rs.getBigDecimal("BOARD_NO");
				String title = rs.getNString("BOARD_TITLE");
				String writer = rs.getNString("BOARD_WRITER");
				LocalDateTime date = rs.getTimestamp("BOARD_DATE").toLocalDateTime();
				String content = rs.getNString("BOARD_CONTENT");
			
				BoardVO bv = new BoardVO();
				bv.setNo(no);
				bv.setTitle(title);
				bv.setWriter(writer);
				bv.setDate(date);
				bv.setContent(content);
				
				list.add(bv);
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println();
			System.out.println("DB연결이 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		} finally {
			close(conn, stmt, pstmt, rs);
		}
		
		return list;
	}
	
	
	public void printResult (int cnt) {
		System.out.println();
		if (cnt > 0) {
			System.out.println("(*°▽°*) 성공! (*°▽°*)");
		} else {
			System.out.println("(°ロ°) 실패! (°ロ°)");
		}
	}

	
}
