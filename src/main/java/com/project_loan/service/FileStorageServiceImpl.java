package com.project_loan.service;

import com.project_loan.exception.BaseException;
import com.project_loan.exception.ResultType;
import com.project_loan.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService{

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private final ApplicationRepository applicationRepository;
    @Override
    public void save(Long applicationId, MultipartFile file) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        try {
            String applicationPath = uploadPath.concat("/" + applicationId);
            Path directoryPath = Path.of(applicationPath); //URI 변환 경로 반환
            if(!Files.exists(directoryPath)){ //exists()파일 또는 폴더가 존재하는지 확인
                Files.createDirectory(directoryPath);
            }

            Files.copy(file.getInputStream(), Paths.get(applicationPath).resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING); // source, target, option(target 파일이 존재하면 덮어 쓴다.) file -> target 에 복사한다. 파일이 존재하더라도 덮어쓴다.
        } catch (Exception e){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public Resource load(Long applicationId, String fileName) {

        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        try {
            String applicationPath = uploadPath.concat("/" + applicationId);

            Path file = Paths.get(applicationPath).resolve(fileName);

            UrlResource resource = new UrlResource(file.toUri()); //웹 브라우저에서 사용하는 형식의 문자열 변환

            if (resource.isReadable() || resource.exists()){
                return resource;
            }else {
                throw new BaseException(ResultType.NOT_EXIST);
            }
        } catch (Exception e){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public Stream<Path> loadAll(Long applicationId) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        try{
            String applicationPath = uploadPath.concat("/" + applicationId);
            return Files.walk(Paths.get(applicationPath),1).filter(path -> !path.equals(Paths.get(applicationPath))); //해당 경로 하위폴더 탐색 가능 디렉토리 같이 조회 안되게 filter 적용
        } catch (Exception e){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }

    @Override
    public void deleteAll(Long applicationId) {
        if(!isPresentApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        String applicationPath = uploadPath.concat("/" + applicationId);
        FileSystemUtils.deleteRecursively(Paths.get(applicationPath).toFile()); //디렉토리 삭제
    }

    private boolean isPresentApplication(Long applicationId){
       return applicationRepository.findById(applicationId).isPresent();
    }
}
