(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0dd60e"],{"80aa":function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",{attrs:{inline:"",model:t.searchForm}},[a("el-form-item",{attrs:{label:"年度","label-width":"70px"}},[a("select-year",{on:{change:t.handleChangeYear}})],1),t._v(" "),a("el-form-item",{attrs:{label:"作业状态","label-width":"80px"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",clearable:""},model:{value:t.searchForm.status,callback:function(e){t.$set(t.searchForm,"status",e)},expression:"searchForm.status"}},t._l(t.statusOptions,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"任务类型","label-width":"80px"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",clearable:""},model:{value:t.searchForm.workCategory,callback:function(e){t.$set(t.searchForm,"workCategory",e)},expression:"searchForm.workCategory"}},t._l(t.workCategory,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:t.search}},[a("i",{staticClass:"el-icon-search"}),t._v("搜索")])],1)],1)],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.productionData}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务名称",prop:"task_name"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务年份",prop:"task_year"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务类型",prop:"task_type"}}),t._v(" "),a("el-table-column",{attrs:{label:"图书名称",prop:"task_class_tag"}}),t._v(" "),a("el-table-column",{attrs:{label:"添印/改版",prop:"task_type_content"}}),t._v(" "),a("el-table-column",{attrs:{label:"下达时间",prop:"task_start_date"}}),t._v(" "),a("el-table-column",{attrs:{label:"进度"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-link",{attrs:{type:"primary",icon:"el-icon-document"},on:{click:function(a){return t.progressThis(e.row)}}},[t._v("查看")])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"状态",prop:"task_state"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-link",{attrs:{type:"primary",icon:"el-icon-s-promotion"},on:{click:function(a){return t.sendThis(e.row)}}},[t._v("发送")])]}}])})],1),t._v(" "),a("pagination",{attrs:{"current-page":t.page.current,"page-size":10,total:t.page.total},on:{"page-change":t.handleCurrentChange}}),t._v(" "),a("el-dialog",{attrs:{title:"人员与进度信息浏览",visible:t.zhituTaskVisible,width:"800px"},on:{"update:visible":function(e){t.zhituTaskVisible=e}}},[a("div",{staticClass:"task-list-container"},[a("el-form",{attrs:{model:t.zhituForm,inline:""}},["制图任务"===t.tasktype?a("el-form-item",{attrs:{label:"产品类型"}},[a("el-radio-group",{on:{change:t.typeChange},model:{value:t.zhituForm.productType,callback:function(e){t.$set(t.zhituForm,"productType",e)},expression:"zhituForm.productType"}},[a("el-radio",{attrs:{label:"纸图",value:"纸图"}}),t._v(" "),a("el-radio",{attrs:{label:"数字图",value:"数字图"}}),t._v(" "),a("el-radio",{attrs:{label:"S57图",value:"S57图"}}),t._v(" "),a("el-radio",{attrs:{label:"EPS",value:"EPS"}}),t._v(" "),a("el-radio",{attrs:{label:"图集插图",value:"图集插图"}})],1)],1):t._e(),t._v(" "),"书表任务"===t.tasktype?a("el-form-item",{attrs:{label:"图书名称"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.zhituForm.bookname,callback:function(e){t.$set(t.zhituForm,"bookname",e)},expression:"zhituForm.bookname"}})],1):t._e(),t._v(" "),"书表任务"===t.tasktype?a("el-form-item",{attrs:{label:"任务内容"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},on:{change:t.contentChange},model:{value:t.zhituForm.taskContent,callback:function(e){t.$set(t.zhituForm,"taskContent",e)},expression:"zhituForm.taskContent"}},t._l(t.taskContentOption,(function(t){return a("el-option",{key:t.distribution_id,attrs:{label:t.distribution_type,value:t.distribution_id}})})),1)],1):t._e(),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.zhituForm.tableData}},[a("el-table-column",{attrs:{label:"产品类型"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.product_type))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"过程"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.user_duty))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"人员"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.user_name))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"工天"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.work_days))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"开始时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.start_date))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"结束时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.end_date))]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"备注"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(t._s(e.row.remark))]}}])})],1)],1)],1)])],1)},n=[],o=a("f0dd"),l=a("6797"),r=a("b199"),i=a("857b"),c=a("18ed"),u=a("3641"),d={components:{SelectYear:c["a"],Pagination:u["a"]},data:function(){return{searchForm:{task_year:"",status:"",workCategory:""},workCategory:[],statusOptions:[],productionData:[{task_name:"任务1",task_type:"制图任务",book_name:"书名1",unit:"单位1",tianyin:"添印",xdsj:"2020-05-30",jcsj:"2020-06-02",status:"进厂"}],page:{current:1,total:1},zhituTaskVisible:!1,tasktype:"制图任务",currentId:"",zhituForm:{productType:"纸图",tableData:[],taskContent:""},taskContentOption:[]}},mounted:function(){this.getTaskState(),this.fetchTableData(),this.getworkCategory()},methods:{getworkCategory:function(){var t=this;l["c"].getTaskType().then((function(e){e.length>0?(console.log(e),t.workCategory=e):t.message({message:e,type:"warning"})}))},getTaskState:function(){var t=this;l["c"].getTaskState({use_type:1}).then((function(e){t.statusOptions=e})).catch((function(e){t.message({message:res,type:"warning"})}))},fetchTableData:function(){var t=this,e=Object(i["e"])({page_number:1,page_size:10});o["a"].selectdepttask(e).then((function(e){200===e.code?(t.productionData=e.data.smallList,t.page.total=e.data.recordCount):t.$message.error("获取数据失败！")})).catch((function(t){console.log(t)}))},search:function(){var t=this,e=Object(i["e"])({page_number:1,page_size:10,task_year:this.searchForm.task_year,task_state:this.searchForm.status});o["a"].selectdepttask(e).then((function(e){200===e.code?(t.productionData=e.data.smallList,t.page.total=e.data.recordCount,t.page.current=1):t.$message.error("获取数据失败！")})).catch((function(t){console.log(t)}))},progressThis:function(t){this.zhituTaskVisible=!0,this.zhituForm.productType="纸图",this.tasktype=t.task_type,this.currentId=t.index_id,"制图任务"===this.tasktype?this.typeChange():(this.zhituForm.bookname=t.task_class_tag,this.taskContent())},typeChange:function(){var t=this;r["g"].select({task_index_id:this.currentId,distribution_type:this.zhituForm.productType}).then((function(e){t.zhituForm.tableData=e.data})).catch((function(t){console.log(t)}))},taskContent:function(){var t=this;r["g"].taskcontent({task_index_id:this.currentId}).then((function(e){t.taskContentOption=e.data,t.zhituForm.taskContent=e.data[0],t.contentChange()})).catch((function(t){console.log(t)}))},contentChange:function(){var t=this;r["g"].select({task_index_id:this.currentId,distribution_type:this.zhituForm.taskContent}).then((function(e){t.zhituForm.tableData=e.data})).catch((function(t){console.log(t)}))},sendThis:function(t){var e=this,a={class_id:t.task_class_id,index_id:t.index_id,task_name:t.task_name,task_state:"RWZJ",task_type:t.task_type};r["h"].updatestate(a).then((function(t){Object(i["i"])(e,t),e.fetchTableData(),e.page.current=1})).catch((function(t){console.log(t)}))},handleChangeYear:function(t){this.searchForm.task_year=t},handleCurrentChange:function(t){var e=this;this.page.current=t;var a=Object(i["e"])({page_number:this.page.current,page_size:10,task_year:this.searchForm.task_year,task_state:this.searchForm.status});o["a"].selectdepttask(a).then((function(t){200===t.code?(e.productionData=t.data.smallList,e.page.total=t.data.recordCount):e.$message.error("获取数据失败！")})).catch((function(t){console.log(t)}))}}},p=d,h=a("5d22"),_=Object(h["a"])(p,s,n,!1,null,"897a7df6",null);e["default"]=_.exports}}]);