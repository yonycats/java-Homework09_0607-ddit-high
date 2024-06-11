package service;

import java.util.List;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;

	private static IBoardService boardService = new BoardServiceImpl();
	
	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	 
	public static IBoardService getInstance() {
		return boardService;
	}

	
	
	@Override
	public List<BoardVO> printAllSv(List<Object> param) {
		return boardDao.printAll(param );
	}

	@Override
	public void insertBoardSv(BoardVO bv) {
		boardDao.insertBoard(bv);
	}

	@Override
	public void updateBoardSv(BoardVO bv) {
		boardDao.updateBoard(bv);
	}

	@Override
	public void deleteBoardSv(BoardVO bv) {
		boardDao.deleteBoard(bv);
	}

	@Override
	public List<BoardVO> dateSearchBoardSv(BoardVO bv) {
		return boardDao.dateSearchBoard(bv);
	}

	@Override
	public List<BoardVO> writerSearchBoardSv(BoardVO bv) {
		return boardDao.writerSearchBoard(bv);
	}

	@Override
	public List<BoardVO> titleSearchBoardSv(BoardVO bv) {
		return boardDao.titleSearchBoard(bv);
	}

	@Override
	public List<BoardVO> contentSearchBoardSv(BoardVO bv) {
		return boardDao.contentSearchBoard(bv);
	}

}
