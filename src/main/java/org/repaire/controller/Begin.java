package org.repaire.controller;

import org.repaire.mapper.RepaireappMapper;
import org.repaire.pojo.RepaireappUnion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Luo on 2018/4/27.
 */
@Controller
public class Begin {

    @Autowired
    private RepaireappMapper mapper;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "hello world";
    }


}
