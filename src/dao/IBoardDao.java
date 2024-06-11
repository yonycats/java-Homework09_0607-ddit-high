package dao;

import java.util.List;
import vo.BoardVO;

public interface IBoardDao {

	/**
	 * List에 담긴 게시물 정보를 전부 출력하기 위한 메서드
	 * @return 모든 게시물 정보를 담은 List객체
	 */
	public List<BoardVO> printAll(List<Object> param);
	
	/**
	 * List에 담겨진 게시물 정보를 DB에 insert하기 위한 메서드
	 * @param bv 게시물 정보를 담은 List 객체
	 * @return DB작업이 성공하면 1, 실패하면 0 반환됨
	 */
	public void insertBoard(BoardVO bv);
	
	/**
	 * List에 담겨진 게시물 정보를 DB에 update하기 위한 메서드
	 * @param bv 게시물 정보를 담은 List 객체
	 * @return DB작업이 성공하면 1, 실패하면 0 반환됨
	 */
	public void updateBoard(BoardVO bv);
	
	/**
	 * 해당 게시물 번호에 해당하는 게시물을 삭제하기 위한 메서드
	 * @param 삭제할 BOARD_NO를 확인하기 위한 게시물 번호가 담긴 List 객체
	 * @return DB작업이 성공하면 1, 실패하면 0 반환됨
	 */
	public void deleteBoard(BoardVO bv);
	
	/**
	 * List에 담긴 날짜와 관련된 게시물을 찾기 위한 메서드
	 * @param 검색하길 원하는 게시물의 날짜 정보가 담긴 List 객체
	 * @return 해당 글이 존재하면  true, 존재하지 않으면 false 리턴함
	 */
	public List<BoardVO> dateSearchBoard(BoardVO bv);
	
	/**
	 * List에 담긴 작성자와 관련된 게시물을 찾기 위한 메서드
	 * @param 검색하길 원하는 게시물의 작성자 정보가 담긴 List 객체
	 * @return 해당 글이 존재하면  true, 존재하지 않으면 false 리턴함
	 */
	public List<BoardVO> writerSearchBoard(BoardVO bv);
	
	/**
	 * List에 담긴 제목과 관련된 게시물을 찾기 위한 메서드
	 * @param 검색하길 원하는 게시물의 제목 정보가 담긴 List 객체
	 * @return 해당 글이 존재하면  true, 존재하지 않으면 false 리턴함
	 */
	public List<BoardVO> titleSearchBoard(BoardVO bv);
	
	/**
	 * List에 담긴 내용과 관련된 게시물을 찾기 위한 메서드
	 * @param 검색하길 원하는 게시물의 내용 정보가 담긴 List 객체
	 * @return 해당 글이 존재하면  true, 존재하지 않으면 false 리턴함
	 */
	public List<BoardVO> contentSearchBoard(BoardVO bv);
	
}
