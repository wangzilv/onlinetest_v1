<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.baidu.ueditor.ActionEnter" %>
<%@ page import="org.springframework.web.multipart.MultipartHttpServletRequest" %>
<%@ page import="org.springframework.web.multipart.MultipartResolver" %>
<%@ page import="org.springframework.web.multipart.commons.CommonsMultipartResolver" %>
<%@ page import="org.springframework.web.multipart.commons.CommonsMultipartFile" %>
<%@ page import="com.ai.newsale.product.controller.CreateProductController" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");

	String rootPath = application.getRealPath( "/" );
	String actionType = request.getParameter("action");
	if(actionType.equals("uploadimage") || actionType.equals("uploadfile"))
	{
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getMultiFileMap().get("upfile").get(0);

		String fileId = "";
		String upfileName = "";
		String thirdCategoryCode = "";
		HttpSession httpSession = request.getSession();
		thirdCategoryCode = (String) httpSession.getAttribute("thirdCategoryCode");
		CreateProductController createProductController = new CreateProductController();
		String  result = createProductController.uploadImg(multipartFile,thirdCategoryCode,"3",request);
		JSONObject jsonResult = JSON.parseObject(result);
		System.out.print(jsonResult);
		JSONArray busiInfoArray = (JSONArray) jsonResult.get("BUSI_INFO");
		JSONObject busiInfoObj =  (JSONObject) busiInfoArray.get(0);
		String resultCode = (String) busiInfoObj.get("RESULT_CODE");
		if(!resultCode.equals("0")){
			out.write(new ActionEnter(request, rootPath).exec());
		}
		JSONObject node = new JSONObject();
		node.put("url",(String) busiInfoObj.get("FILE_PATH"));
		node.put("size", String.valueOf(multipartFile.getSize()));
		node.put("type", multipartFile.getContentType());
		node.put("state","SUCCESS");
		out.write(node.toJSONString());
	}
	else
	{
		out.write(new ActionEnter(request, rootPath).exec());
	}
	
%>