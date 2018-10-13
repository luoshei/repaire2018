package org.repaire.service;

import org.repaire.pojo.BxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Luo on 2018/5/14.
 */
public interface IUploadService {
    public BxResult uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
