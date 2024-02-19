package edu.kh.io.pack2.run;

import edu.kh.io.pack2.model.service.ByteService;

public class ByteRun {
	public static void main(String[] args) {
	    ByteService service = new ByteService();
	    

	    
//	    service.fileByteOutput();
//	    service.bufferedFileByteOutput();
	    
	    
//	    service.fileByteInput();
//	    service.bufferedFileByteInput();
		
	    service.fileCopy();
	   
	    
//	    service.objectOutput();
//	    service.objectInput();
	}
}
