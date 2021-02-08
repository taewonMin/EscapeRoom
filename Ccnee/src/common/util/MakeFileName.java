package common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vo.BoardFileVO;


public class MakeFileName {
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}
	
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		String[] uuidFileName = fileName.split(delimiter);
		return uuidFileName[uuidFileName.length - 1];
	}
	
	
	public static List<BoardFileVO> parseFileNameFromAttaches(List<BoardFileVO> attachList, String delimiter) {

		List<BoardFileVO> renamedAttachList = new ArrayList<BoardFileVO>();
		
		if(attachList!=null) {
			for (BoardFileVO attach : attachList) {
				String fileName = attach.getBoardFileName(); // DB상의 fileName
				fileName = parseFileNameFromUUID(fileName, delimiter); // uuid가 제거된
																		// fileName
				attach.setBoardFileName(fileName);
	
				renamedAttachList.add(attach);
			}
		}
		return renamedAttachList;
	}
}
