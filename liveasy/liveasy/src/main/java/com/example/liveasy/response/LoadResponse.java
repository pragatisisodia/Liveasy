package com.example.liveasy.response;

import com.example.liveasy.entity.Load;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadResponse {

    private List<LoadVO> list=new ArrayList<>();



}
