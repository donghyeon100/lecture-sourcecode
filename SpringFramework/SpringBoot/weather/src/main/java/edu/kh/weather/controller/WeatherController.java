package edu.kh.weather.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WeatherController {
	
//	@Autowired
//	private RestTemplate restTemplate;
	
//	private final String serviceKey = "VAnmAF7hu0kgMMh9/XpGXQQiBohl1+EZzdMhrIi/YtM5qrXECcSUitkVSHTixeqN6yX/EOv9jVeojFVnEGC5Nw==";
	private final String serviceKey = "VAnmAF7hu0kgMMh9%2FXpGXQQiBohl1%2BEZzdMhrIi%2FYtM5qrXECcSUitkVSHTixeqN6yX%2FEOv9jVeojFVnEGC5Nw%3D%3D";
	
	
	private final Map<String, String> coordinateList = new HashMap<String, String>();
	
	public WeatherController() {
		coordinateList.put("서울", "60/127");
		coordinateList.put("경기", "60/120");
		coordinateList.put("인천", "55/38");
		coordinateList.put("제주", "52/127");
		coordinateList.put("세종", "66/103");
		coordinateList.put("광주", "58/74");
		coordinateList.put("대구", "89/90");
		coordinateList.put("대전", "67/100");
		coordinateList.put("부산", "76");
		coordinateList.put("울산", "102/84");
		coordinateList.put("강원", "73/134");
		coordinateList.put("경북", "89/91");
		coordinateList.put("경남", "91/77");
		coordinateList.put("전북", "63/89");
		coordinateList.put("전남", "51/67");
		coordinateList.put("충북", "69/107");
		coordinateList.put("충남", "68/100");
	}

	
	@GetMapping("ex1")
	public String ex1() {
		return "ex1";
	}
	
	
	@GetMapping("ex2")
	public String ex2(@RequestParam(value="regionName", required=false, defaultValue="서울") String regionName, Model model)  {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
		
		// 현재 시간 얻어와 baseDate, baseTime가공
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/HH/mm");
		String[] arr = (sdf.format(new Date())).split("/");
		
		String baseDate = arr[0];
		
		int hour = Integer.parseInt(arr[1]);
		int minute  = Integer.parseInt(arr[2]);
		
		// 매시간 30분에 생성되고 10분마다 최신 정보로 업데이트(기온, 습도, 바람)
		String baseTime = "";
		if(minute <= 45) { 
			if(hour == 0) hour = 24;
			
			baseTime = String.format("%02d30", hour-1);
		}else {
			
			baseTime = String.format("%02d30", hour );
		}
		
		String[] coordinate = (coordinateList.get(regionName)).split("/");
		String nx = coordinate[0];
		String ny = coordinate[1];
		
		
		log.debug("regionName : " + regionName);
		log.debug("baseDate : " + baseDate);
		log.debug("baseTime : " + baseTime);
		log.debug("nx : " + nx);
		log.debug("ny : " + ny);
		
		
 		try {
		    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
		    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
		    urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		    urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일 발표*/
		    urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*06시30분 발표(30분 단위)*/
		    urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점 X 좌표값*/
		    urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점 Y 좌표값*/
		    
		    log.debug("urlBuilder.toString() : " + urlBuilder.toString());
		    
		    URL url = new URL(urlBuilder.toString());
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Content-type", "application/json");
		    System.out.println("Response code: " + conn.getResponseCode());
		    
		    BufferedReader rd;
		    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    } else {
		        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		    }
		    
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = rd.readLine()) != null) {
		        sb.append(line);
		    }
		    rd.close();
		    conn.disconnect();
		    
		    String result = sb.toString();
		    log.debug(result);
		    
		    // --------------------------------------------
		    
		    JSONObject j1 = new JSONObject(result);
		    
		    String response = j1.getString("response");

	        // response 로 부터 body 찾기
	        JSONObject j2 = new JSONObject(response);
	        String body = j2.getString("body");

	        // body 로 부터 items 찾기
	        JSONObject j3 = new JSONObject(body);
	        String items = j3.getString("items");
	        
	        JSONObject j4 = new JSONObject(items);
	        String item = j4.getString("item");
	        
	        log.debug(item);
		    
		    ObjectMapper objectMapper = new ObjectMapper();
	        List<Map<String, Object>> list = objectMapper.readValue(item, List.class);

	        log.debug(list.toString());
	        
	        Map<String, Object> weatherMap = new HashMap<>();
	        
	        
	        weatherMap.put("fcstDate", list.get(0).get("fcstDate"));
	        weatherMap.put("fcstTime", list.get(0).get("fcstTime"));
	        
	        for(Map<String, Object> map : list){
	            if(map.get("fcstDate").equals(weatherMap.get("fcstDate")) &&
            		map.get("fcstTime").equals(weatherMap.get("fcstTime"))){

	            	weatherMap.put((String)map.get("category"), map.get("fcstValue"));
	            }
	        }
	        
	        String sky = null;
	        switch((String)weatherMap.get("SKY")){
	        case "1": sky = "맑음";    	break;
	        case "3": sky = "구름많음"; break;
	        case "4": sky = "흐림"; 	break;
	        }
	        
	        
	        String pty = null;
	        switch((String)weatherMap.get("PTY")){
	        case "0": pty = "없음";    	break;
	        case "1": pty = "비";    	break;
	        case "2": pty = "비/눈";    	break;
	        case "3": pty = "눈";    	break;
	        case "5": pty = "빗방울"; break;
	        case "6": pty = "빗방울눈날림"; 	break;
	        case "7": pty = "눈날림"; 	break;
	        }
	        
	        weatherMap.put("SKY", sky);
	        weatherMap.put("PTY", pty);
	        
		    log.debug(weatherMap.toString());
		    
		    model.addAttribute("weatherMap", weatherMap);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ex2";
	}
	
}
