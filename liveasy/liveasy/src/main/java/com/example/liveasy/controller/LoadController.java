package com.example.liveasy.controller;


import com.example.liveasy.request.LoadVORequest;
import com.example.liveasy.response.LoadResponse;
import com.example.liveasy.response.LoadVO;
import com.example.liveasy.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping()
    public ResponseEntity<String>addLoad(@RequestBody LoadVORequest load)
    {
        loadService.addLoad(load);
        return ResponseEntity.ok("loads details added successfully ");
    }



    @GetMapping("")
    public ResponseEntity<LoadResponse> getLoadsByShipperId(@RequestParam("shipperId") UUID shipperId) {
        LoadResponse response=loadService.getLoadsByShipperId(shipperId);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadVO>getLoadById(@PathVariable int loadId)
    {
        LoadVO loadVo=loadService.getLoadById(loadId);
        return new ResponseEntity<>(loadVo,HttpStatus.OK);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<String>updateLoad(@PathVariable int loadId,@RequestBody LoadVORequest request)
    {
        boolean marked=loadService.updateLoad(loadId,request);
        if(marked)
        {
            return ResponseEntity.ok("Load Successfully updated");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<String>deleteLoadById(@PathVariable int loadId)
    {
        loadService.deleteLoadById(loadId);
        return ResponseEntity.ok("load Succesfully deleted");
    }




}
