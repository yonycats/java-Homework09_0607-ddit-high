package dao;

import java.util.List;
import util.JDBCUtilHw09;
import vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	private static IBoardDao boardDao = new BoardDaoImpl();
	
	private BoardDaoImpl() {
	}
	
	public static IBoardDao getInstance() {
		return boardDao;
	}

	JDBCUtilHw09 jdbc = JDBCUtilHw09.getInstance();
	
	
	
	@Override
	public List<BoardVO> printAll(List<Object> param) {
		String sql = " SELECT *\r\n" + 
				 " FROM (SELECT ROWNUM RN, B.*\r\n" + 
				 "      FROM (SELECT BOARD_NO, BOARD_DATE, BOARD_WRITER, BOARD_TITLE, BOARD_CONTENT\r\n" + 
				 "            FROM JDBC_BOARD ORDER BY BOARD_DATE) B)\r\n" + 
				 " WHERE RN BETWEEN ? AND ?";
		
		return jdbc.selectList(sql, param);
	}

	@Override
	public void insertBoard(BoardVO bv) {
		String sql = " INSERT INTO JDBC_BOARD (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT)\r\n" + 
				" VALUES ( (SELECT NVL(MAX(BOARD_NO),0)+1 FROM JDBC_BOARD) , ?, ?, SYSDATE, ?)";
		
		jdbc.insert(sql, bv);
	}

	@Override
	public void updateBoard(BoardVO bv) {
		String sql = " UPDATE JDBC_BOARD\r\n" + 
					 " SET BOARD_TITLE = ?, BOARD_WRITER = ?, \r\n" + 
					 "     BOARD_CONTENT = ?, BOARD_DATE = SYSDATE\r\n" + 
					 " WHERE BOARD_NO = ?";
		
		jdbc.update(sql, bv);
	}

	@Override
	public void deleteBoard(BoardVO bv) {
		String sql = " DELETE FROM JDBC_BOARD\r\n" + 
					 " WHERE BOARD_NO = ?";
		
		jdbc.delete(sql, bv);
	}

	@Override
	public List<BoardVO> dateSearchBoard(BoardVO bv) {
		String sql = " SELECT BOARD_NO, BOARD_DATE, BOARD_WRITER, BOARD_TITLE, BOARD_CONTENT\n" + 
				 " FROM JDBC_BOARD\n" + 
				 " WHERE BOARD_DATE LIKE TO_DATE(?, 'YYYY-MM-DD')\n" + 
				 " ORDER BY BOARD_DATE";
		
		return jdbc.searchSelectList(sql, bv);
	}

	@Override
	public List<BoardVO> writerSearchBoard(BoardVO bv) {
		String sql = "SELECT BOARD_NO, BOARD_DATE, BOARD_WRITER, BOARD_TITLE, BOARD_CONTENT\n" + 
				 "FROM JDBC_BOARD\n" + 
				 "WHERE BOARD_WRITER LIKE ?\n" + 
				 "ORDER BY BOARD_DATE";
		
		return jdbc.searchSelectList(sql, bv);
	}

	@Override
	public List<BoardVO> titleSearchBoard(BoardVO bv) {
		String sql = "SELECT BOARD_NO, BOARD_DATE, BOARD_WRITER, BOARD_TITLE, BOARD_CONTENT\n" + 
				 "FROM JDBC_BOARD\n" + 
				 "WHERE BOARD_TITLE LIKE ?\n" + 
				 "ORDER BY BOARD_DATE";
		
		return jdbc.searchSelectList(sql, bv);
	}

	@Override
	public List<BoardVO> contentSearchBoard(BoardVO bv) {
		String sql = "SELECT BOARD_NO, BOARD_DATE, BOARD_WRITER, BOARD_TITLE, BOARD_CONTENT\n" + 
				 "FROM JDBC_BOARD\n" + 
				 "WHERE BOARD_CONTENT LIKE ?\n" + 
				 "ORDER BY BOARD_DATE";

		return jdbc.searchSelectList(sql, bv);
	}

}
