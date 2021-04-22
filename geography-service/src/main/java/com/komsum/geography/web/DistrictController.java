package com.komsum.geography.web;

import com.komsum.geography.entity.DistrictEntity;
import com.komsum.geography.service.DistrictService;
import com.komsum.geography.util.constant.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.DistrictCtrl.CTRL)
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public List<DistrictEntity> getByCityId(@PathVariable(value = "id") Integer cityId) {
        return districtService.findByCityId(cityId);
    }
}
