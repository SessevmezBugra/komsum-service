package com.komsum.geography.web;

import com.komsum.geography.entity.NeighborhoodEntity;
import com.komsum.geography.entity.StreetEntity;
import com.komsum.geography.service.NeighborhoodService;
import com.komsum.geography.service.StreetService;
import com.komsum.geography.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.StreetCtrl.CTRL)
@RequiredArgsConstructor
public class StreetController {

    private final StreetService streetService;

    @RequestMapping(value = "/neighborhood/{id}", method = RequestMethod.GET)
    public List<StreetEntity> getByNeighborhoodId(@PathVariable(value = "id") Integer neighborhoodId) {
        return streetService.findByNeighborhoodId(neighborhoodId);
    }
}
