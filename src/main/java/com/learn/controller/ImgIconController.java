package com.learn.controller;

import com.learn.service.ThumbNailService;
import com.learn.service.UploadImgService;
import com.learn.utils.ImgList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ImgIconController {
    //使用Autowired时，该业务类需要声明为@service，此时xml中不用其它的配置
    @Autowired
    private UploadImgService upload;
    @Autowired
    private ThumbNailService thumbnail;

    //文件上传并生成缩略图
    @RequestMapping(value="/thumb",method= RequestMethod.POST)
    public String GenerateImage(@RequestParam("image") CommonsMultipartFile file, HttpServletRequest request) throws IOException
    {
        //根据相对路径获取绝对路径，图片上传后位于元数据中
        String realUploadPath=request.getSession().getServletContext().getRealPath("/")+"images";

        //获取上传后原图的相对地址
        String imageUrl=upload.uploadImg(file, realUploadPath);

        //获取生成的缩略图的相对地址
        String thumbImageUrl=thumbnail.generateThumbnail(file, realUploadPath);
        return "redirect:/images";
    }


    //显示所有图片
    @RequestMapping(value="/images",method=RequestMethod.GET)
    public ModelAndView showImages(HttpServletRequest request, HttpServletResponse response)
    {
        //根据相对路径获取绝对路径，图片上传后位于元数据中
        List<String> rawImagesList=new ArrayList<String>();
        String realUploadPath=request.getSession().getServletContext().getRealPath("/")+"images";
        rawImagesList= ImgList.printFile(realUploadPath+"/rawImages");

        ModelAndView mv=new ModelAndView();
        mv.addObject("imageList", rawImagesList);
        mv.setViewName("images");
        return mv;
    }

    //文件下载
    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String path=request.getSession().getServletContext().getRealPath("/")+"/images/rawImages/";
        String fileName=request.getParameter("filename");
        File file=new File(path+fileName);
        if(file.exists()){
            //设置MIME类型
            response.setContentType("application/octet-stream");
            //或者为response.setContentType("application/x-msdownload");

            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题
            response.addHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(), "ISO-8859-1"));

            InputStream inputStream=new FileInputStream(file);
            ServletOutputStream outputStream=response.getOutputStream();
            byte[] bs=new byte[1024];
            while((inputStream.read(bs)>0)){
                outputStream.write(bs);
            }
            outputStream.close();
            inputStream.close();
        }
    }
}
