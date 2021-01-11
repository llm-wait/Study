package com.startest.wm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @公司 北京星天科技
 * @作者 杨世凯
 * @创建时间 2020-05-25-14:33
 * @描述 数据工作单
 */
@ApiModel("数据工作单")
public class wm_form_data {
   @ApiModelProperty("ID")
   private String book_data_id;
   @ApiModelProperty("任务索引ID")
   private String task_index_id;
   @ApiModelProperty("军审号")
   private String book_jcode;
   @ApiModelProperty("正书名")
   private String book_name;
   @ApiModelProperty("副书名及说明文字（包括分卷册书名）")
   private String book_describe;
   @ApiModelProperty("版次")
   private String book_edition;
   @ApiModelProperty("印次")
   private String book_impression;
   @ApiModelProperty("责任编辑")
   private String book_edit;
   @ApiModelProperty("出版日期")
   private String book_publish;
   @ApiModelProperty("印数（册、张）")
   private String book_impression_count;

   public String getBook_data_id() {
      return book_data_id;
   }

   public void setBook_data_id(String book_data_id) {
      this.book_data_id = book_data_id;
   }

   public String getTask_index_id() {
      return task_index_id;
   }

   public void setTask_index_id(String task_index_id) {
      this.task_index_id = task_index_id;
   }

   public String getBook_jcode() {
      return book_jcode;
   }

   public void setBook_jcode(String book_jcode) {
      this.book_jcode = book_jcode;
   }

   public String getBook_name() {
      return book_name;
   }

   public void setBook_name(String book_name) {
      this.book_name = book_name;
   }

   public String getBook_describe() {
      return book_describe;
   }

   public void setBook_describe(String book_describe) {
      this.book_describe = book_describe;
   }

   public String getBook_edition() {
      return book_edition;
   }

   public void setBook_edition(String book_edition) {
      this.book_edition = book_edition;
   }

   public String getBook_impression() {
      return book_impression;
   }

   public void setBook_impression(String book_impression) {
      this.book_impression = book_impression;
   }

   public String getBook_edit() {
      return book_edit;
   }

   public void setBook_edit(String book_edit) {
      this.book_edit = book_edit;
   }

   public String getBook_publish() {
      return book_publish;
   }

   public void setBook_publish(String book_publish) {
      this.book_publish = book_publish;
   }

   public String getBook_impression_count() {
      return book_impression_count;
   }

   public void setBook_impression_count(String book_impression_count) {
      this.book_impression_count = book_impression_count;
   }
}
