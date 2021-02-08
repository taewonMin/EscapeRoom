package board.cmt.service;

import java.util.List;

import vo.CmtVO;

public interface ICmtService {

	
	List<CmtVO> listCmt(int boardNo);

	int insertCmt(CmtVO cv);

	int deleteCmt(int cmtNo);

	int updateCmt(CmtVO cmtVO);
	

}
