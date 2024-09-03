package me.bdh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private String folderPath = "D:/uploadFiles/test/";
	
	
	public int save(List<MultipartFile> selectFile) throws IllegalStateException, IOException{
		
		for(MultipartFile file : selectFile){
			
			// 파일명 변경
			String rename = fileRename( file.getOriginalFilename() );
			
			// 파일을 지정된 경로에 저장
			file.transferTo(new File(folderPath + rename));
			
		}
		
		// 위 for에서 오류가 발생하지 않았다면 모든 파일이 저장됨 ==> 저장된 파일 개수 반환
		// 위에서 오류가 발생했다면 예외가 던져져 retuen 구문이 수행되지 않음
		return selectFile.size();
	}

	
	
    /** 파일명 변경 함수
     * @param fileName
     * @return
     */
    private String fileRename(String fileName) {
        String uuid = UUID.randomUUID().toString();
        String extension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }
        return fileName + "_" + uuid + extension;
    }
}
