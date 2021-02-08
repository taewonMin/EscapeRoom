package common.file.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import vo.BoardFileVO;

public interface AtchFileService {
	
	/**
	 * 썸네일용 첨부파일 정보저장
	 * @param fileVO
	 * @return
	 * @throws Exception
	 */
	public BoardFileVO saveAtchFile(FileItem item, int boardNo) throws Exception;
	
	/**
	 * 첨부파일 목록 조회
	 * @param fileVO
	 * @return
	 * @throws SQLException
	 */
	public List<BoardFileVO> getAtchFileList(int boardGrNo) throws SQLException;
	
	/**
	 * 첨부파일 세부정보 조회 메서드
	 * @param fileVO
	 * @return
	 * @throws SQLException
	 */
	public BoardFileVO getAtchFileDetail(BoardFileVO boardFileVO) throws SQLException;
	
	/**
	 * 게시판 번호를 이용하여 해당 파일정보 조회
	 * @param boardNo
	 * @return
	 */
	public BoardFileVO eventFileSearch(int boardNo) throws SQLException;
	
	/**
	 * 수정할 첨부파일 정보 저장
	 * @param item
	 * @param boardNo
	 * @return
	 */
	public BoardFileVO uploadSaveAtchFile(FileItem item, int boardNo) throws Exception;
	
	/**
	 * 보드넘버를 받아 해당 보드파일 정보 삭제
	 * @param boardNo
	 * @return
	 */
	public int deleteFile(int boardNo);
	
	/**
	 * 상세이미지 파일 정보저장
	 * @param item
	 * @param boardNo
	 * @return
	 */
//	public BoardFileVO saveDetailFile(FileItem item, int boardNo) throws Exception;
	
}
