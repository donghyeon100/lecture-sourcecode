package me.bdh.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.bdh.service.TestService;

@RestController
@RequiredArgsConstructor
public class TestController {

	private TestService service;
}
