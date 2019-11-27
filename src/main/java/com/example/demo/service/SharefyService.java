//package com.example.demo.service;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SharefyService {
//	
//	public void publicarFoto(){
//		
//		  httpclient = new DefaultHttpClient();
//         HttpPost httppost = new HttpPost("https://api.constantcontact.com/v2/library/files");
//
//         httppost.addHeader("Authorization", "Bearer 70e8e17d-e1ed-4b7a-8a8a-40383d74d467");
//         httppost.addHeader("Accept", "application/json");
//         httppost.addHeader("Content-type", "multipart/form-data");
//
//         File fileToUse = new File("/path_to_file/YOLO.jpg"); //e.g. /temp/dinnerplate-special.jpg
//         FileBody data = new FileBody(fileToUse);
//
//         String file_type = "JPG" ;
//         String description = "Oppa Gangnam Style";
//         String folder_id = "-1";
//         String source = "MYCOMPUTER" ;
//
//         MultipartEntity reqEntity = new MultipartEntity();
//         reqEntity.addPart("file_name", new StringBody( fileToUse.getName() ) );
//         reqEntity.addPart("folder_id", new StringBody(folder_id));
//         reqEntity.addPart("description", new StringBody(description));
//         reqEntity.addPart("source", new StringBody(source));
//         reqEntity.addPart("file_type", new StringBody(file_type));
//         reqEntity.addPart("data", data);
//
//         httppost.setEntity(reqEntity);
//
//        HttpResponse response = httpclient.execute(httppost);
//        System.out.println( response ) ;
//
//        HttpEntity resEntity = response.getEntity();
//        System.out.println( resEntity ) ;
//         System.out.println( EntityUtils.toString(resEntity) );
//
//         EntityUtils.consume(resEntity);
//         httpclient.getConnectionManager().shutdown();
// }
//}
//		
//		
////		CloseableHttpClient httpClient = HttpClients.createDefault();
////		HttpPost uploadFile = new HttpPost("...");
////		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
////		builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
////
////		// This attaches the file to the POST:
////		File f = new File("[/path/to/upload]");
////		builder.addBinaryBody(
////		    "file",
////		    new FileInputStream(f),
////		    ContentType.APPLICATION_OCTET_STREAM,
////		    f.getName()
////		);
////
////		HttpEntity multipart = builder.build();
////		uploadFile.setEntity(multipart);
////		CloseableHttpResponse response = httpClient.execute(uploadFile);
////		HttpEntity responseEntity = response.getEntity();
//	}
//
//}