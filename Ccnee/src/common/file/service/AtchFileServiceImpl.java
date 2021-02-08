package common.file.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import common.file.dao.AtchFileDaoImpl;
import common.file.dao.AtchFileDao;
import common.util.FileUploadRequestWrapper;
import vo.BoardFileVO;

public class AtchFileServiceImpl implements AtchFileService{
	
	private static AtchFileService fileService;
	private AtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static AtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	@Override
	public BoardFileVO saveAtchFile(FileItem item, int boardNo) throws Exception {
		
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		// 전체경로를 제외한 파일명만 추출하기
		String boardFileOrigin = new File(item.getName()).getName();
		
//		long fileSize = item.getSize(); // 사이즈 없어서 불필요
		String boardFileName = "";
		String boardStreCours = "";
		
		// images파일에 업로드할때 UUID 뒤에 붙일 확장명 추출
		String dotExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf("."));
		
		
		File storeFile = null;
		
		do {
			boardFileName = UUID.randomUUID().toString().replace("-", "");
			
			
			boardStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY
					+ File.separator + boardFileName + dotExtsn;
			
			storeFile = new File(boardStreCours);
		}while(storeFile.exists()); // 파일명이 중복안될때까지...
		
		// 확장명 추출
		String boardFileExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf(".") + 1);
		
		item.write(storeFile); // 업로드 파일 저장
		
		// 파일 저장 서비스 호출
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setBoardFileName(boardFileName);
		boardFileVO.setBoardFileOrigin(boardFileOrigin);
		boardFileVO.setBoardStreCours(boardStreCours);
		boardFileVO.setBoardFileExtsn(boardFileExtsn);
		boardFileVO.setBoardNo(boardNo);
		
		fileDao.insertAtchFile(boardFileVO); // 세부파일정보 저장
		
		item.delete(); // 임시 업로드 파일 삭제하기
		
		return boardFileVO;
	}

	@Override
	public List<BoardFileVO> getAtchFileList(int boardGrNo) throws SQLException {
		
		return fileDao.getAtchFileList(boardGrNo);
	}

	@Override
	public BoardFileVO getAtchFileDetail(BoardFileVO boardFileVO) throws SQLException {
	
		return fileDao.getAtchFileDetail(boardFileVO);
	}

	@Override
	public BoardFileVO eventFileSearch(int boardNo) throws SQLException {
		
		
		return fileDao.eventFileSearch(boardNo);
	}

	@Override
	public BoardFileVO uploadSaveAtchFile(FileItem item, int boardNo) throws Exception {
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		// 전체경로를 제외한 파일명만 추출하기
		String boardFileOrigin = new File(item.getName()).getName();
		
//		long fileSize = item.getSize(); // 사이즈 없어서 불필요
		String boardFileName = "";
		String boardStreCours = "";
		
		// images파일에 업로드할때 UUID 뒤에 붙일 확장명 추출
		String dotExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf("."));
		
		
		File storeFile = null;
		
		do {
			boardFileName = UUID.randomUUID().toString().replace("-", "");
			
			
			boardStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY
					+ File.separator + boardFileName + dotExtsn;
			
			storeFile = new File(boardStreCours);
		}while(storeFile.exists()); // 파일명이 중복안될때까지...
		
		// 확장명 추출
		String boardFileExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf(".") + 1);
		
		item.write(storeFile); // 업로드 파일 저장
		
		// 파일 저장 서비스 호출
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setBoardFileName(boardFileName);
		boardFileVO.setBoardFileOrigin(boardFileOrigin);
		boardFileVO.setBoardStreCours(boardStreCours);
		boardFileVO.setBoardFileExtsn(boardFileExtsn);
		boardFileVO.setBoardNo(boardNo);
		
		fileDao.updateAtchFile(boardFileVO); // 세부파일정보 저장
		
		item.delete(); // 임시 업로드 파일 삭제하기
		
		return boardFileVO;
	}

	@Override
	public int deleteFile(int boardNo) {
		
		int cnt = 0;
		
		try {
			cnt = fileDao.deleteFile(boardNo);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 파일 삭제중 예외발생");
		}
		
		return cnt;
	}

//	@Override
//	public BoardFileVO saveDetailFile(FileItem item, int boardNo) throws Exception {
//		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
//		
//		if(!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//		
//		// 전체경로를 제외한 파일명만 추출하기
//		String boardFileOrigin = new File(item.getName()).getName();
//		
////		long fileSize = item.getSize(); // 사이즈 없어서 불필요
//		String boardFileName = "";
//		String boardStreCours = "";
//		
//		// images파일에 업로드할때 UUID 뒤에 붙일 확장명 추출
//		String dotExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
//					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf("."));
//		
//		
//		File storeFile = null;
//		
//		do {
//			boardFileName = UUID.randomUUID().toString().replace("-", "");
//			
//			
//			boardStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY
//					+ File.separator + boardFileName + dotExtsn;
//			
//			storeFile = new File(boardStreCours);
//		}while(storeFile.exists()); // 파일명이 중복안될때까지...
//		
//		// 확장명 추출
//		String boardFileExtsn = boardFileOrigin.lastIndexOf(".") < 0 ?
//					"" : boardFileOrigin.substring(boardFileOrigin.lastIndexOf(".") + 1);
//		
//		item.write(storeFile); // 업로드 파일 저장
//		
//		// 파일 저장 서비스 호출
//		BoardFileVO boardFileVO = new BoardFileVO();
//		boardFileVO.setBoardFileName(boardFileName);
//		boardFileVO.setBoardFileOrigin(boardFileOrigin);
//		boardFileVO.setBoardStreCours(boardStreCours);
//		boardFileVO.setBoardFileExtsn(boardFileExtsn);
//		boardFileVO.setBoardNo(boardNo);
//		
//		fileDao.insertDetailFile(boardFileVO); // 세부파일정보 저장
//		
//		item.delete(); // 임시 업로드 파일 삭제하기
//		
//		return boardFileVO;
//	}
	
	
	
}
