package com.study.erum.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.study.erum.model.User;
import com.study.erum.service.FileService;
import com.study.erum.service.UserService;

@Controller
public class FileUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;
    @RequestMapping("/")
    public String uploadForm() {
        return "form";
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
    private void insertAndSaveFile(String username, String originalFilename, MultipartFile file) throws IOException {
        String filename = saveFile(file);
        String webPath = generateWebPath(filename);
        File fileInfo = new File();
        fileInfo.setUsername(username);
        fileInfo.setFilename(originalFilename); // 원래 파일명 설정
        fileInfo.setFilepath(webPath); // 웹 경로 설정
        fileService.insertFile(fileInfo);
    }

    @PostMapping("/addUserAndFile")
    public String addUserAndFile(@RequestParam("username") String username, 
                                 @RequestParam("content") String content,
                                 @RequestParam("file") MultipartFile[] files) throws UnsupportedEncodingException {
        // 사용자 정보 삽입
        User user = new User();
        user.setUsername(username);
        user.setContent(content);
        userService.insertUser(user);

        // 파일 정보 삽입
        int fileCount = Math.min(files.length, 3); // 최대 3개의 파일 업로드
        for (int i = 0; i < fileCount; i++) {
            MultipartFile file = files[i];
            String originalFilename = file.getOriginalFilename();
            try {
                insertAndSaveFile(username, originalFilename, file);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 저장 실패 처리
            }
        }

        return "redirect:/result?username=" + URLEncoder.encode(username, "UTF-8");
    }
    

    @GetMapping("/uploaded-images/{filename:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(Paths.get("C:/upload/" + filename));
    }


    @GetMapping("/result")
    public String showResult(@RequestParam("username") String username, Model model) {
        // 사용자 정보 및 파일 정보 조회
        User user = userService.getUserByUsername(username);
        List<File> files = fileService.getFilesByUsername(username);

        // 모델에 데이터 추가
        model.addAttribute("user", user);
        model.addAttribute("files", files);
        
        return "result"; // 결과 페이지로 이동
    }
}