package com.komsum.geography.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.geography.entity.StreetEntity;
import com.komsum.geography.service.StreetService;
import com.komsum.geography.util.constant.ApiPaths;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.StreetCtrl.CTRL)
@RequiredArgsConstructor
public class StreetController {

    private final StreetService streetService;

    @RequestMapping(value = "/neighborhood/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<StreetEntity>> getByNeighborhoodId(@PathVariable(value = "id") Integer neighborhoodId) {
        return ResponseEntity.ok(streetService.findByNeighborhoodId(neighborhoodId));
    }
    
    @RequestMapping(value = "/in", method = RequestMethod.POST)
    public ResponseEntity<List<StreetEntity>> getByStreetIdIn(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok(streetService.findByStreetIdIn(ids));
    }
}
