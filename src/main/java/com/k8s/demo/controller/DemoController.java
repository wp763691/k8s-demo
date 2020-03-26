package com.k8s.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.k8s.demo.util.HttpRequestUtil;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser() {
//		String url = "http://k8s-user:8000/user/demo1";
//		String url2 = "http://k8s-user.pro.svc.cluster.local:8000/user/demo2";
		try {
//			String data1 = HttpRequestUtil.sendData("GET", url);
//			String data2 = HttpRequestUtil.sendData("GET", url2);
//			return "v2: " + data1 + " " + data2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "demo-v1";
	}

}
