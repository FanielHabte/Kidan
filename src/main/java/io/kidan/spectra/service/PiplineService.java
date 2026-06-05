package io.kidan.spectra.service;

import io.kidan.spectra.entity.PipelineRun;
import io.kidan.spectra.repository.PiplineRepository;
import org.springframework.stereotype.Service;

@Service
public class PiplineService {
    private final PiplineRepository piplineRepository;

    public PiplineService(PiplineRepository piplineRepository) {
        this.piplineRepository = piplineRepository;
    }

    public void savePiplineRun(PipelineRun pipelineRun){
        piplineRepository.save(pipelineRun);
    }

}
