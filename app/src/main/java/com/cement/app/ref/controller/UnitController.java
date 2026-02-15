package com.cement.app.ref.controller;

import com.cement.app.ref.entity.Unit;
import com.cement.app.ref.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public List<Unit> getAllUnits() {
        return unitService.getAllActiveUnits();
    }
}