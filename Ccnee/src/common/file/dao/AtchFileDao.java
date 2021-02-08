package common.file.dao;

import java.sql.SQLException;
import java.util.List;

import vo.BoardFileVO;

public interface AtchFileDao {
	
	/**
	 * 첨부파일 정보저장
	 * @param boardFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFile(BoardFileVO boardFileVO) throws SQLException;
	
	/**
	 * 첨부파일 목록 조회
	 * @param fileVO
	 * @return
	 * @throws SQLException
	 */
	public List<BoardFileVO> getAtchFileList(int boardGrNo) throws SQLException;
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param boardFileVO
	 * @return
	 * @throws SQLException
	 */
	public BoardFileVO getAtchFileDetail(BoardFileVO boardFileVO) throws SQLException;
	
	/**
	 * 게시판 번호를 조회하여 해당 파일정보 조회
	 * @param boardNo
	 * @return
	 */
	public BoardFileVO eventFileSearch(int boardNo) throws SQLException;
	
	/**
	 * 수정할 첨부파일 정보 저장
	 * @param boardFileVO
	 * @throws SQLException
	 */
	public int updateAtchFile(BoardFileVO boardFileVO) throws SQLException;
	
	/**
	 * 보드 파일 정보 삭제
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int deleteFile(int boardNo)throws SQLException;
	
	/**
	 * 상세파일 이미지 DB에 저장
	 * @param boardFileVO
	 */
//	public int insertDetailFile(BoardFileVO boardFileVO) throws SQLException;
	
}
