//package io.kidan.inlet.factory;
//
//import io.kidan.guardian.entity.Dataset;
//import io.kidan.inlet.entity.Submission;
//import io.kidan.nexus.entity.User;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@Component
//public class InletFactory {
//
//    // Submission Object Builder
//    public Submission buildSubmissionObject () {
//        String fileName = inputFile.getOriginalFilename();
//        String filePath = path + fileName;
//        User user = userAuthService.AuthenticatedUser().orElseThrow(
//                ()-> new RuntimeException("User not found")
//        );
//        Dataset dataset = datasetService.findDatasetById(submission.getDataset().getId()) ;
//
//        submission.setDataset(dataset);
//        submission.setFileName(fileName);
//        submission.setFilePath(filePath);
//        submission.setUser(user);
//    }
//
//}
