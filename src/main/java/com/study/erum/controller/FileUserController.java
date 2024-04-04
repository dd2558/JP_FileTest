package com.study.erum.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.erum.model.File;
import com.study.erum.model.TripInfo;
import com.study.erum.model.User;
import com.study.erum.service.FileService;
import com.study.erum.service.TripInfoService;
import com.study.erum.service.UserService;

@Controller
public class FileUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripInfoService tripInfoService;
    
    @Autowired
    private FileService fileService;
    @RequestMapping("/")
    public String uploadForm() {
        return "form";
    }

    @PostMapping("/addUserAndFile")
    public String addUserAndFile(@RequestParam("username") String username,
                                  @RequestParam("place_name") String place_name,
                                  @RequestParam("hashtag") String hashtag,
                                  @RequestParam("description") String description,
                                  @RequestParam("address") String address,
                                  @RequestParam("rating") int rating,
                                  @RequestParam("author") String author,
                                  @RequestParam("phone_number") String phoneNumber,
                                  @RequestParam("sns_url") String snsUrl,
                                  @RequestParam("other_info") String otherInfo,
                                  @RequestParam("file") MultipartFile[] files,
            Model model                      
    		) throws UnsupportedEncodingException {
        
        // 사용자 정보 삽입
        User user = new User();
        user.setUsername(username);
        userService.insertUser(user);
        System.out.println(user);
        
        // 여행 정보 삽입
        TripInfo tripInfo = new TripInfo();
        tripInfo.setPlace_name(place_name); 
        tripInfo.setHashtag(hashtag);
        tripInfo.setDescription(description);
        tripInfo.setAddress(address);
        tripInfo.setRating(rating);
        tripInfo.setAuthor(author);
        tripInfo.setPhoneNumber(phoneNumber);
        tripInfo.setSnsUrl(snsUrl);
        tripInfo.setOtherInfo(otherInfo);
        tripInfoService.insertTripInfo(tripInfo);
        System.out.println(tripInfo);
        
        // 파일 정보 삽입
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            try {
                insertAndSaveFile(username, originalFilename, place_name, file, hashtag, model);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 저장 실패 처리
            }
        }

        return "redirect:/result?hashtag=" + URLEncoder.encode(hashtag, "UTF-8");



    }



 // 파일 확장자를 확인하고 지정하는 메서드
    private String getFileExtension(String originalFilename) {
        if (originalFilename != null && !originalFilename.isEmpty()) {
            int lastIndex = originalFilename.lastIndexOf('.');
            if (lastIndex != -1 && lastIndex < originalFilename.length() - 1) {
                return originalFilename.substring(lastIndex);
            }
        }
        return ""; // 확장자가 없는 경우 빈 문자열 반환
    }

    // 파일명을 시간 형식으로 생성하는 메서드 (확장자 포함)
    private String generateFilenameWithExtension(String originalFilename) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTimeStamp = dateFormat.format(new Date());
        String extension = getFileExtension(originalFilename);
        return currentTimeStamp + extension;
    }

    // 웹 경로 생성 메서드
    private String generateWebPath(String filename) {
        return "/uploaded-images/" + filename;
    }

    // 파일을 저장하고 파일명을 반환하는 메서드
    private String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = generateFilenameWithExtension(originalFilename); // 파일명에 확장자 추가
        String filepath = "C:/upload/" + filename;
        file.transferTo(new java.io.File(filepath));
        return filename;
    }

    // 파일 정보 삽입 및 저장 메서드
 // 파일 정보 삽입 및 저장 메서드
    private String insertAndSaveFile(String username, String originalFilename, String place_name, MultipartFile file, String hashtag, Model model) throws IOException {
        String filename = saveFile(file);
        String webPath = generateWebPath(filename);
        File fileInfo = new File();
        fileInfo.setUsername(username);
        fileInfo.setPlace_name(place_name); // 여기서 placeName을 설정
        fileInfo.setFilename(originalFilename); // 원래 파일명 설정
        fileInfo.setFilepath(webPath); // 웹 경로 설정
        fileInfo.setHashtag(hashtag); // 웹 경로 설정
        fileService.insertFile(fileInfo);
        System.out.println(fileInfo);
        
        Map<String, String> tripInfoMap = new HashMap<>();
        tripInfoMap.put("place_name", place_name);
        tripInfoMap.put("hashtag", hashtag);
        // 이러한 형식으로 다른 필요한 정보도 추가할 수 있음
        
        // 맵을 모델에 추가하여 리다이렉트할 때 함께 전달
        model.addAttribute("tripInfoPost", tripInfoMap);
        
        return hashtag; // 해시태그를 리턴
    }



   
    

    @GetMapping("/uploaded-images/{filename:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(Paths.get("C:/upload/" + filename));
    }


    @RequestMapping("/result")
    public String showResult(@RequestParam("hashtag") String hashtag,  Model model) { // @RequestParam("place_name")String place_name
        System.out.println("hashtag: " + hashtag); // hashtag 출력하여 확인
        // 여행 정보 및 파일 정보 조회
        TripInfo tripInfo = tripInfoService.getTripInfoByHashTag(hashtag);  //해쉬태그 get 메서드 서비스/ 서비스 임플 / 매퍼 모두 작성
        if (tripInfo == null) {
            System.out.println("No trip info found for place: " + hashtag);
        }
        //List<File> files = fileService.getFilesByPlaceName(place_name);  // 이미지 파일은 여행지명과 연관있으므로 가만히 냅둔다
        List<File> files = fileService.getFilesByHashTag(hashtag);  // 이미지 파일은 여행지명과 연관있으므로 가만히 냅둔다

        // 모델에 데이터 추가
        model.addAttribute("tripInfo", tripInfo);
        System.out.println("tripInfo왜null이냐"+tripInfo);
        System.out.println("files:"+files);
        model.addAttribute("files", files);
        return "result"; // 결과 페이지로 이동
    }
    
    
 

}