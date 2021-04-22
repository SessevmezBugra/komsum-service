package com.komsum.geography.web;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.service.CityService;
import com.komsum.geography.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CityCtrl.CTRL)
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

//    @RequestMapping(method = RequestMethod.GET)
//    public List<CityEntity> getAllCity(){
//        return cityService.findAll();
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllCity(){
        return "Test";
    }
}
