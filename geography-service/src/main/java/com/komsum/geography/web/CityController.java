package com.komsum.geography.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.service.CityService;
import com.komsum.geography.util.constant.ApiPaths;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.CityCtrl.CTRL)
@RequiredArgsConstructor
public class CityController implements SecuredRestController{
//
    private final CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CityEntity>> getAllCity(){
        return ResponseEntity.ok(cityService.findAll());
    }
}
