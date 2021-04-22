package com.komsum.geography.web;

import com.komsum.geography.entity.NeighborhoodEntity;
import com.komsum.geography.service.NeighborhoodService;
import com.komsum.geography.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.NeighborhoodCtrl.CTRL)
@RequiredArgsConstructor
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    @RequestMapping(value = "/district/{id}", method = RequestMethod.GET)
    public List<NeighborhoodEntity> getByDistrictId(@PathVariable(value = "id") Integer districtId) {
        return neighborhoodService.findByDistrictId(districtId);
    }
}
