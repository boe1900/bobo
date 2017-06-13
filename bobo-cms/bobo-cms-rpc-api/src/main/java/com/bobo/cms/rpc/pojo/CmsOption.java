package com.bobo.cms.rpc.pojo;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 配置信息表，用来保存网站的所有配置信息。
 * </p>
 *
 * @author huabo
 * @since 2017-06-13
 */
@TableName("cms_option")
public class CmsOption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 配置KEY
     */
	@TableField("option_key")
	private String optionKey;
    /**
     * 配置内容
     */
	@TableField("option_value")
	private String optionValue;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOptionKey() {
		return optionKey;
	}

	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

}
