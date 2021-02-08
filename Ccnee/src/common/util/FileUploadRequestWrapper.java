package common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * FileUpload API를 사용하는 HttpServletRequestWrapper 클래스
 * (HttpServletRequest에 기반한 API를 사용하면서 멀티파트처리 기능이 추가되었음.)
 */
public class FileUploadRequestWrapper extends HttpServletRequestWrapper {
    public static final String UPLOAD_DIRECTORY = "D:\\A_TeachingMaterial\\4.MiddleProject\\workspace2\\CCNEE\\WebContent\\resources\\images";		// 업로드 경로 설정
    private boolean multipart = false;													// 멀티파트여부

    private Map<String, String[]> parameterMap;											// 폼필드(파라미터) 데이터를 담기 위한 맵
    private Map<String, Object> fileItemMap;											// fileItem객체를 담기위한 맵

    /**
     * 생성자
     * @param request
     * @throws FileUploadException
     */
    public FileUploadRequestWrapper(HttpServletRequest request)
    throws FileUploadException{
        this(request, -1, -1, -1, null);
    }

    /**
     * 생성자
     * @param request
     * @param memoryThreshold 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
     * @param fileSizeMax 파일 1개당 최대크기
     * @param maxRequestSize 요청 파일 최대 크기
     * @param repositoryPath 임시파일 저장경로
     * @throws FileUploadException
     */
    public FileUploadRequestWrapper(HttpServletRequest request,
        int memoryThreshold, long fileSizeMax, long maxRequestSize, String repositoryPath) throws FileUploadException {
        super(request);

        parsing(request, memoryThreshold, fileSizeMax, maxRequestSize, repositoryPath);
    }

    /**
     * 멀티파트 데이터를 파싱하여 두개의 맵에 나누어 담는다.
     * @param request
     * @param memoryThreshold 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
     * @param fileSizeMax 파일 1개당 최대 크기
     * @param maxRequestSize 요청 파일 최대 크기
     * @param repositoryPath 임시파일 저장경로
     * @throws FileUploadException
     */
    private void parsing(HttpServletRequest request,
        int memoryThreshold, long fileSizeMax, long maxRequestSize, String repositoryPath) throws FileUploadException {

        if (ServletFileUpload.isMultipartContent(request)) {
            multipart = true; // 멀티파트임을 설정함.

            parameterMap = new HashMap<>();
            fileItemMap = new HashMap<>();

            DiskFileItemFactory factory = new DiskFileItemFactory();
            if (memoryThreshold != -1) {
            	factory.setSizeThreshold(memoryThreshold);
            }
            if (repositoryPath != null) {// 저장 경로가 설정되지 않았으면...
            	factory.setRepository(new File(repositoryPath));
            }else {
            	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            }

            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setFileSizeMax(fileSizeMax);
            servletFileUpload.setSizeMax(maxRequestSize);

            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (int i = 0 ; i < list.size() ; i++) {
                FileItem fileItem = (FileItem) list.get(i);
                String name = fileItem.getFieldName(); // 필드명 가져오기

                if (fileItem.isFormField()) { // 폼필드인 경우...
                    String value = "";
					try {
						// 폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환을 해주어야 한다.
						//value = new String(fileItem.getString().getBytes("8859_1"), "UTF-8");
						value = fileItem.getString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
                    String[] values = (String[]) parameterMap.get(name);
                    if (values == null) { // 처음 만드는 경우...
                        values = new String[] { value };
                    } else { // 기존에 이미 존재하는 경우...
                        String[] tempValues = new String[values.length + 1];
                        System.arraycopy(values, 0, tempValues, 0, 1);
                        tempValues[tempValues.length - 1] = value;
                        values = tempValues;
                    }
                    parameterMap.put(name, values);
                } else { // 파일인 경우...
                    fileItemMap.put(name, fileItem);
                }
            }
            addTo(); // 현재 객체를 속성값으로 설정한다.(래퍼클래스 여부 체크를 위해 설정)
        }
    }

    public boolean isMultipartContent() {
        return multipart;
    }

    public String getParameter(String name) {
        if (multipart) {
            String[] values = (String[])parameterMap.get(name);
            if (values == null) return null;
            return values[0];
        } else
            return super.getParameter(name);
    }

    public String[] getParameterValues(String name) {
        if (multipart)
            return (String[])parameterMap.get(name);
        else
            return super.getParameterValues(name);
    }

    public Enumeration<String> getParameterNames() {
        if (multipart) {
            return new Enumeration<String>() {
                Iterator<String> iter = parameterMap.keySet().iterator();

                public boolean hasMoreElements() {
                    return iter.hasNext();
                }
                public String nextElement() {
                    return iter.next();
                }
            };
        } else {
            return super.getParameterNames();
        }
    }

    public Map<String, String[]> getParameterMap() {
        if (multipart)
            return parameterMap;
        else
            return super.getParameterMap();
    }

    public Map<String, Object> getFileItemMap() {
    	 if (multipart)
             return fileItemMap;
         else
             return null;
    }

    public FileItem getFileItem(String name) {
        if (multipart)
            return (FileItem) fileItemMap.get(name);
        else
            return null;
    }

    /**
     * fileItemMap에 존재하는 FileItem을 삭제한다.
     */
    public void delete() {
        if (multipart) {
            Iterator<Object> fileItemIter = fileItemMap.values().iterator();
            while( fileItemIter.hasNext()) {
                FileItem fileItem = (FileItem)fileItemIter.next();
                fileItem.delete();
            }
        }
    }

    /**
     * request객체에 속성값으로 현재 래퍼객체(FileUploadRequestWrapper)를 등록함.
     * (멀티파트인 경우에만 호출됨.)
     */
    public void addTo() {
      super.setAttribute(FileUploadRequestWrapper.class.getName(), this);
    }


    /**
     * 래퍼클래스 여부를 체크함.
     * @param request 요청객체
     * @return true 이면 래퍼클래스, false 이면 래퍼클래스 아님.
     */
    public static boolean hasWrapper(HttpServletRequest request) {
    	if (FileUploadRequestWrapper.getFrom(request) == null) {
    		return false;
    	} else {
    		return true;
    	}
    }

    /**
     * request객체에 존재하는 FileUploadRequestWrapper객체를 가져옴
     * @param request 객체
     * @return 존재하면 FileUploadRequestWrapper 객체, 없으면 null 리턴함.
     */
    public static FileUploadRequestWrapper
                  getFrom(HttpServletRequest request) {
        return (FileUploadRequestWrapper)
            request.getAttribute(FileUploadRequestWrapper.class.getName());
    }

}
