package com.project_loan.controller;

import com.project_loan.dto.ApplicationDto;
import com.project_loan.dto.ApplicationDto.Request;
import com.project_loan.dto.ApplicationDto.Response;
import com.project_loan.dto.FileDto;
import com.project_loan.dto.ResponseDto;
import com.project_loan.service.ApplicationService;
import com.project_loan.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    private final FileStorageService fileStorageService;
    @PostMapping
    public ResponseDto<Response> create(@RequestBody Request request){
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDto<Response> get(@PathVariable Long applicationId){
        return ok(applicationService.get(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseDto<Response> update(@PathVariable Long applicationId, @RequestBody Request request){
        return ok(applicationService.update(applicationId, request));
    }

    @DeleteMapping("{applicationId}")
    public ResponseDto<Response> delete(@PathVariable Long applicationId){
        applicationService.delete(applicationId);
        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDto<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody ApplicationDto.AcceptTerms request){
        return ok(applicationService.acceptTerms(applicationId, request));
    }

    @PostMapping("/{applicationId}/files")
    public ResponseDto<Void> upload(@PathVariable Long applicationId, MultipartFile file){
        fileStorageService.save(applicationId, file);
        return ok();
    }

    @GetMapping("/{applicationId}/files")
    public ResponseEntity<Resource> download(@PathVariable Long applicationId, @RequestParam(value = "fileName") String fileName){
        Resource file = fileStorageService.load(applicationId, fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file); //content-Disposition 에 attachment 를 주는 경우 Body 에 오는 값을 다운 받으라는 뜻
    }

    @GetMapping("/{applicationId}/files/info")
    public ResponseDto<List<FileDto>> getFileInfo(@PathVariable Long applicationId){
        List<FileDto> fileInfos = fileStorageService.loadAll(applicationId).map(path -> {
            String fileName = path.getFileName().toString();
            return FileDto.builder()
                .name(fileName)
                .url(MvcUriComponentsBuilder.fromMethodName(ApplicationController.class, "download", applicationId, fileName).build().toString())
                .build();
        }).collect(Collectors.toList());

        return ok(fileInfos);
    }

    @DeleteMapping("/{applicationId}/files")
    public ResponseDto<Void> deleteAll(@PathVariable Long applicationId){
        fileStorageService.deleteAll(applicationId);
        return ok();
    }

    @PutMapping("/{applicationId}/contract")
    public ResponseDto<Response> contract(@PathVariable Long applicationId){
        return ok(applicationService.contract(applicationId));
    }
}
