package com.springboot.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * mybatis.generator 分页自定义插件  自定义查询的字段
 * @program: alibbb
 *
 * @description:
 *
 * @author: Mr.Guan
 *
 * @Mail: GuanWeiMail@163.com
 *
 * @create: 2018-04-10 16:10
 **/
public class MyBatisCustomPlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 为每个Example类添加limit和offset属性已经set、get方法
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();
        PrimitiveTypeWrapper stringWrapper = FullyQualifiedJavaType.getStringInstance().getPrimitiveTypeWrapper();
        FullyQualifiedJavaType stringInstance = FullyQualifiedJavaType.getStringInstance();
        //分页相关start
        Field limit = new Field();
        limit.setName("limit");
        limit.setVisibility(JavaVisibility.PRIVATE);
        limit.setType(integerWrapper);
        topLevelClass.addField(limit);

        Method setLimit = new Method();
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.setName("setLimit");
        setLimit.addParameter(new Parameter(integerWrapper, "limit"));
        setLimit.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setLimit);

        Method getLimit = new Method();
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(integerWrapper);
        getLimit.setName("getLimit");
        getLimit.addBodyLine("return limit;");
        topLevelClass.addMethod(getLimit);

        Field offset = new Field();
        offset.setName("offset");
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("setOffset");
        setOffset.addParameter(new Parameter(integerWrapper, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        Method getOffset = new Method();
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setReturnType(integerWrapper);
        getOffset.setName("getOffset");
        getOffset.addBodyLine("return offset;");
        topLevelClass.addMethod(getOffset);
        //分页相关end


//
        Field customField = new Field();
        customField.setName("customField");
        customField.setVisibility(JavaVisibility.PRIVATE);
        customField.setType(stringInstance);
        topLevelClass.addField(customField);

        Method setCustomField = new Method();
        setCustomField.setVisibility(JavaVisibility.PUBLIC);
        setCustomField.setName("setCustomField");
        setCustomField.addParameter(new Parameter(stringInstance, "customField"));
        setCustomField.addBodyLine("this.customField = customField;");
        topLevelClass.addMethod(setCustomField);

        Method getCustomField = new Method();
        getCustomField.setVisibility(JavaVisibility.PUBLIC);
        getCustomField.setReturnType(stringInstance);
        getCustomField.setName("getCustomField");
        getCustomField.addBodyLine("return customField;");
        topLevelClass.addMethod(getCustomField);



        return true;
    }

    /**
     * 为Mapper.xml的selectByExample添加limit
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {

        XmlElement ifLimitNotNullElement = new XmlElement("if");
        ifLimitNotNullElement.addAttribute(new Attribute("test", "limit != null"));

        XmlElement ifOffsetNotNullElement = new XmlElement("if");
        ifOffsetNotNullElement.addAttribute(new Attribute("test", "offset != null"));
        ifOffsetNotNullElement.addElement(new TextElement("limit ${offset*limit}, ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNotNullElement);

        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "offset == null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNullElement);

        element.addElement(ifLimitNotNullElement);

        //删除 <include refid="Base_Column_List" />
        element.getElements().remove(3);

        //增加自定义字段
        XmlElement ifCustomFieldNotNullElement = new XmlElement("if");
        ifCustomFieldNotNullElement.addAttribute(new Attribute("test", "customField != null"));
        ifCustomFieldNotNullElement.addElement(new TextElement(" ${customField} "));

        XmlElement ifCustomFieldNullElement = new XmlElement("if");
        ifCustomFieldNullElement.addAttribute(new Attribute("test", "customField == null"));
        ifCustomFieldNullElement.addElement(new TextElement(" <include refid=\"Base_Column_List\" /> "));


//        for(int i = )

        //添加到from之前
        element.addElement(3,ifCustomFieldNotNullElement);
        element.addElement(4, ifCustomFieldNullElement);
//        element.
        return true;
    }

}
