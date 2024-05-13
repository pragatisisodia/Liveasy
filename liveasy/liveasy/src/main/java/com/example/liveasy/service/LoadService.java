package com.example.liveasy.service;


import com.example.liveasy.entity.Load;
import com.example.liveasy.repository.LoadRepository;
import com.example.liveasy.request.LoadVORequest;
import com.example.liveasy.response.LoadResponse;
import com.example.liveasy.response.LoadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;
    public void addLoad(LoadVORequest load) {
        Load payload=new Load();
        payload.setLoadingPoint(load.getLoadingPoint());
        payload.setShipperId(UUID.randomUUID());








        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        payload.setDate(today); // No need to parse, use the LocalDate directly


        payload.setUnloadingPoint(load.getUnloadingPoint());
        payload.setComment(load.getComment());
        payload.setWeight(load.getWeight());
        payload.setProductType(load.getProductType());
        payload.setNoOfTrucks(load.getNoOfTrucks());
        payload.setTruckType(load.getTruckType());
        loadRepository.save(payload);


    }


    public LoadVO getLoadById(int loadId) {
        Optional<Load> load=loadRepository.findById(loadId);
        return buildLoadVo(load);
    }

   private LoadVO buildLoadVo(Optional<Load> load) {
        LoadVO loadVo=new LoadVO();
        loadVo.setLoadingPoint(load.get().getLoadingPoint());
        loadVo.setComment(load.get().getComment());
        loadVo.setWeight(load.get().getWeight());
        loadVo.setTruckType(load.get().getTruckType());
        loadVo.setProductType(load.get().getProductType());
        loadVo.setUnloadingPoint(load.get().getUnloadingPoint());
        loadVo.setNoOfTrucks(load.get().getNoOfTrucks());
        loadVo.setLoadId(load.get().getLoadId());
        loadVo.setDate(load.get().getDate());
        loadVo.setShipperId(load.get().getShipperId());
        return loadVo;


    }

    public void deleteLoadById(int loadId) {
        loadRepository.deleteById(loadId);
    }

    public boolean updateLoad(int loadId, LoadVORequest request) {

        Optional<Load> load=loadRepository.findById(loadId);
        if(load.isPresent())
        {
            LocalDate date= LocalDate.now();
            Load output=load.get();
            output.setDate(date);
            output.setLoadingPoint(request.getLoadingPoint());
            output.setUnloadingPoint(request.getUnloadingPoint());
            output.setProductType(request.getProductType());
            output.setProductType(request.getProductType());
            output.setTruckType(request.getTruckType());
            output.setNoOfTrucks(request.getNoOfTrucks());
            output.setWeight(request.getWeight());
            output.setComment(request.getComment());
            loadRepository.save(output);
            return true;
        }
        else
        {
            return false;
        }


    }

    public LoadResponse getLoadsByShipperId(UUID shipperId) {
        List<Load> load=loadRepository.findByShipperId(shipperId);
      //  System.out.println(load);
        List<LoadVO> loads=buildLoadResponse(load);
      //  return buildLoadResponse(load);
        return new LoadResponse(loads);


    }

    private List<LoadVO> buildLoadResponse(List<Load> load) {
        List<LoadVO> response=new ArrayList<>();
        for(Load l:load)
        {
            LoadVO loadVo=new LoadVO();
            loadVo.setLoadingPoint(l.getLoadingPoint());
            loadVo.setComment(l.getComment());
            loadVo.setWeight(l.getWeight());
            loadVo.setTruckType(l.getTruckType());
            loadVo.setUnloadingPoint(l.getUnloadingPoint());
            loadVo.setProductType(l.getProductType());
            loadVo.setNoOfTrucks(l.getNoOfTrucks());
            loadVo.setDate(l.getDate());
            loadVo.setLoadId(l.getLoadId());
            loadVo.setShipperId(l.getShipperId());
            response.add(loadVo);
          //  System.out.println(loadVo);
        }
        return response;
    }


}
