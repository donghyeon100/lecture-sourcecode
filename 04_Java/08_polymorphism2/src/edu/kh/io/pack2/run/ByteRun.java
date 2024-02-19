package edu.kh.io.pack2.run;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ByteRun {
	public static void main(String[] args) {
	    /*
	     * File 클래스 주요 메소드
	     * 1. listFiles()    : 디렉터리 내의 모든 File 객체를 File[] 배열로 반환
	     * 2. getName()      : 이름 반환
	     * 3. getParent()    : 저장된 디렉터리 반환
	     * 4. getPath()      : getParent() + getName()
	     * 5. lastModified() : 최종수정일을 long 타입으로 반환
	     * 6. length()       : 파일 크기를 long 타입으로 반환 (바이트 단위)
	     * 7. isDirectory()  : 디렉터리이면 true 반환
	     * 8. isFile()       : 파일이면 true 반환 
	     */
	    
	    // JAVA_HOME 을 File 객체로 생성
	    File javaHome = new File("\\Program Files\\Java\\jdk-17");
	    
	    // JAVA_HOME 에 있는 모든 디렉터리와 파일을 File 객체로 가져오기
	    File[] files = javaHome.listFiles();
	    
	    // 하나씩 확인
	    for(File file : files) {
	      
	      StringBuilder builder = new StringBuilder();
	      
	      // 이름
	      builder.append(String.format("%-20s", file.getName().substring(0, Math.min(file.getName().length(), 20))));
	      
	      // 최종수정일
	      String lastModified = new SimpleDateFormat("yyyy-MM-dd a h:mm").format(file.lastModified());
	      builder.append(String.format("%-20s", lastModified));
	      
	      // 유형
	      builder.append(String.format("%-10s", file.isDirectory() ? "파일 폴더" : "파일"));
	      
	      // 크기
	      long byteSize = file.isDirectory() ? 0 : file.length();
	      long kbSize = (byteSize / 1024) + (byteSize % 1024 != 0 ? 1 : 0);
	      if(byteSize != 0)
	        builder.append(String.format("%10s", new DecimalFormat("#,##0").format(kbSize) + "KB"));
	      
	      // 출력
	      System.out.println(builder.toString());
	      
	    }
	}
}
