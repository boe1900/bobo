package com.bobo.cms.admin.controller;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.kisso.common.SSOProperties;
import com.bobo.common.base.BaseController;
import com.bobo.common.util.PropertiesFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台controller
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台控制器", description = "后台管理")
public class ManageController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);

	/**
	 * 后台首页
	 * @return
	 */
	@ApiOperation(value = "后台首页")
	@Permission(action = Action.Skip)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		SSOProperties prop = SSOConfig.getSSOProperties();

		return redirectTo(prop.get("sso.defined.serverIndexUrl")+"?appName="+prop.get("sso.defined.clientName"));
	}

}