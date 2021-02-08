package board.item.service;

import java.io.File;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.item.dao.ItemDao;
import board.item.dao.ItemDaoImpl;
import common.util.FileUploadRequestWrapper;
import common.util.SqlMapClientFactory;
import vo.ItemVO;
import vo.PagingVO;

public class ItemServiceImpl implements ItemService{
	private ItemDao itemDao;
	
	private SqlMapClient smc;
	private static ItemService itemService;
	
	private ItemServiceImpl() {
		itemDao = ItemDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ItemService getInstance() {
		if(itemService == null) {
			itemService = new ItemServiceImpl();
		}
		return itemService;
	}
	

	@Override
	public int insertItemBoard(ItemVO item, FileItem fileItem) {
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String itemFileOrigin = new File(fileItem.getName()).getName(); 
		
		String itemFileName = ""; //저장할 파일명. 오리지널 파일명으로만 저장하면 중복되니깐
		String itemStreCours = ""; //파일 경로 정보. => 모든 데이터를 디비에 저장할거라서
		
		String dotExtsn = itemFileOrigin.lastIndexOf(".") < 0 ?
				"" : itemFileOrigin.substring(itemFileOrigin.lastIndexOf("."));
		
		File storeFile = null;
		
		do {
			itemFileName = UUID.randomUUID().toString().replace("-", ""); //유효아이디
			
			itemStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY
					+ File.separator + itemFileName + dotExtsn;
			
			storeFile = new File(itemStreCours); //while문의 조건으로 중복체크하기 위해
			
		} while(storeFile.exists()); //파일명이 중복되지 않을 때 까지. 중복되는 순간 빠져나옴
		
		//확장명 추출
		String itemFileExtsn = itemFileOrigin.lastIndexOf(".") < 0 ? "" : itemFileOrigin.substring(itemFileOrigin.lastIndexOf(".")+1);
		
		try {
			fileItem.write(storeFile); //업로드 파일을 저장하는 기능을 처리
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		//파일 저장 서비스 호출. (디비에 저장 시작)
		item.setItemFileName(itemFileName);
		item.setItemStreCours(itemStreCours);
		
		int cnt=0;
		try {
			cnt = itemDao.insertItemBoard(item); //세부파일 정보 저장
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		fileItem.delete(); //옵션으로 있는 메서드임. 임시 업로드 파일 삭제하기
		
		return cnt;
		
	}
	
	@Override
	public List<ItemVO> displayItemBoardList(PagingVO pv) {
		List<ItemVO> itemList = Collections.emptyList();
		try {
			itemList = itemDao.displayItemBoardList(pv);
			for(ItemVO item : itemList) {
				//itemFileDao.getItemAtchFileList(item.getItemNo());
			}
			
		} catch(SQLException ex) {
			throw new RuntimeException("전체 아이템 조회 중 예외발생",ex);
			
		}
		return itemList;
	}
	
	@Override
	public List<ItemVO> displayItemActYpagingBoardList(PagingVO pv) {
		List<ItemVO> itemList = Collections.emptyList();
		
		try {
			itemList = itemDao.displayItemActYpagingBoardList(pv);
			
		} catch(SQLException ex) {
			throw new RuntimeException("act=y인 아이템 조회 중 예외발생",ex);
			
		}
		return itemList;
	}
	
	@Override
	public List<ItemVO> displayItemBoardList(ItemVO item) {
		List<ItemVO> itemList = Collections.emptyList();
		
		try {
			itemList = itemDao.displayItemBoardList(item);
			
		} catch(SQLException ex) {
			throw new RuntimeException("전체 아이템 조회 중 예외발생",ex);
			
		}
		return itemList;
	}

	@Override
	public int updateItemBoard(ItemVO item, FileItem fileItem) {
		
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String itemFileOrigin = new File(fileItem.getName()).getName(); 
		
		String itemFileName = ""; //저장할 파일명. 오리지널 파일명으로만 저장하면 중복되니깐
		String itemStreCours = ""; //파일 경로 정보. => 모든 데이터를 디비에 저장할거라서
		
		String dotExtsn = itemFileOrigin.lastIndexOf(".") < 0 ?
				"" : itemFileOrigin.substring(itemFileOrigin.lastIndexOf("."));
		
		File storeFile = null;
		
		do {
			itemFileName = UUID.randomUUID().toString().replace("-", ""); //유효아이디
			
			itemStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY
					+ File.separator + itemFileName + dotExtsn;
			
			storeFile = new File(itemStreCours); //while문의 조건으로 중복체크하기 위해
			
		} while(storeFile.exists()); //파일명이 중복되지 않을 때 까지. 중복되는 순간 빠져나옴
		
		//확장명 추출
		String itemFileExtsn = itemFileOrigin.lastIndexOf(".") < 0 ? "" : itemFileOrigin.substring(itemFileOrigin.lastIndexOf(".")+1);
		
		try {
			fileItem.write(storeFile); //업로드 파일을 저장하는 기능을 처리
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		
		//파일 저장 서비스 호출. (디비에 저장 시작)
		item.setItemFileName(itemFileName);
		item.setItemStreCours(itemStreCours);
		
		int cnt=0;
		try {
			cnt = itemDao.updateItemBoard(item);
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
		fileItem.delete(); //옵션으로 있는 메서드임. 임시 업로드 파일 삭제하기
		
		return cnt;
	}

	@Override
	public int deleteItemBoard(int itemNo) {
		int cnt = 0;
		
		try {
			cnt = itemDao.deleteItemBoard(itemNo);
			
		} catch(SQLException e) {
			throw new RuntimeException("글 삭제 중 예외발생",e);
		}
		return cnt;
	}
	
	@Override
	public List<ItemVO> searchItemBoard(ItemVO item) {
		List<ItemVO> itemSearchList = Collections.emptyList();
		
		try {
			itemSearchList = itemDao.searchItemBoard(item);
			
		}catch(SQLException e) {
			throw new RuntimeException("아이템 검색 중 예외발생",e);
		}
		return itemSearchList;
	}

	@Override
	public int getItemAllCount() {
		int cnt=0;
		
		try {
			cnt = itemDao.getItemAllCount();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("전체 아이템 개수 조회 중 예외발생",e);
		}
		
		return cnt;
	}

	@Override
	public int getItemActYcount() {
		int cnt=0;
		
		try {
			cnt = itemDao.getItemActYcount();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("act=y인 아이템 개수 조회 중 예외발생",e);
		}
		
		return cnt;
	}
	
	@Override
	public ItemVO getItem(int itemNo) {
		ItemVO getItem;
		
		try {
			getItem = itemDao.getItem(itemNo);
			
		}catch(SQLException e) {
			throw new RuntimeException("아이템 선택 중 예외발생",e);
		}
		return getItem;
	}

	@Override
	public int getsearchItemAllCount(ItemVO item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItemVO> getItemList() {
		
		List<ItemVO> itemList = Collections.emptyList();
		
		try {
			itemList = itemDao.getItemList();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return itemList;
	}



	
}
