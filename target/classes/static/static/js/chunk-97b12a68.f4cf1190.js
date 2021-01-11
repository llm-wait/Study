(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-97b12a68"],{"18ed":function(t,e,a){"use strict";var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-select",{staticStyle:{width:"100%"},attrs:{multiple:t.multiple,filterable:"",placeholder:"请选择年度",clearable:"",size:"small"},on:{change:t.handleChange},model:{value:t.year,callback:function(e){t.year=e},expression:"year"}},t._l(t.list,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1)},n=[],s=a("b199"),c={props:{multiple:{type:Boolean,default:!1},current:{type:String,default:""}},data:function(){return{year:"",list:[]}},mounted:function(){this.getData(),this.current&&(this.year=this.current)},methods:{getData:function(){var t=this;s["h"].selectyear().then((function(e){t.list=e.data}))},handleChange:function(t){this.$emit("change",t)}}},o=c,u=a("5d22"),l=Object(u["a"])(o,r,n,!1,null,"55eaebee",null);e["a"]=l.exports},"28ce":function(t,e,a){},3641:function(t,e,a){"use strict";var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-wapper"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pageSize,layout:"total, prev, pager, next",total:t.total,background:""},on:{"update:currentPage":function(e){t.currentPage=e},"update:current-page":function(e){t.currentPage=e},"current-change":t.handleCurrentChange}})],1)},n=[],s=(a("e680"),{props:{currentPage:{type:Number,default:1},pageSize:{type:Number,default:50},total:{type:Number,default:1}},data:function(){return{}},methods:{handleCurrentChange:function(t){this.$emit("page-change",t)}}}),c=s,o=(a("c2e2"),a("5d22")),u=Object(o["a"])(c,r,n,!1,null,"3cd17cbd",null);e["a"]=u.exports},"69b0":function(t,e){t.exports=Object.is||function(t,e){return t===e?0!==t||1/t===1/e:t!=t&&e!=e}},a270:function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-row",[a("el-form",{attrs:{inline:"",model:t.search.data,size:"small"}},[a("el-form-item",{attrs:{label:"周期"}},[a("el-select",{attrs:{placeholder:"全部"},on:{change:t.cycleChange},model:{value:t.search.cycle,callback:function(e){t.$set(t.search,"cycle",e)},expression:"search.cycle"}},[a("el-option",{attrs:{value:"年度"}}),t._v(" "),a("el-option",{attrs:{value:"季度"}})],1)],1),t._v(" "),""!==t.search.cycle?a("el-form-item",{attrs:{label:"年度"}},[a("select-year",{on:{change:t.handleChangeYear}})],1):t._e(),t._v(" "),"季度"===t.search.cycle?a("el-form-item",{attrs:{label:"季度",clearable:""}},[a("el-select",{model:{value:t.search.data.quarter,callback:function(e){t.$set(t.search.data,"quarter",e)},expression:"search.data.quarter"}},[a("el-option",{attrs:{value:"1"}}),t._v(" "),a("el-option",{attrs:{value:"2"}}),t._v(" "),a("el-option",{attrs:{value:"3"}}),t._v(" "),a("el-option",{attrs:{value:"4"}})],1)],1):t._e(),t._v(" "),a("el-form-item",{attrs:{label:"作业室"}},[a("el-cascader",{staticStyle:{width:"100%"},attrs:{options:t.options.dept,"show-all-levels":!1,props:{label:"dept_name",value:"dept_id",children:"childDepts",checkStrictly:!0},clearable:""},on:{change:t.handleChangeDept},model:{value:t.search.dept_pid,callback:function(e){t.$set(t.search,"dept_pid",e)},expression:"search.dept_pid"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"姓名"}},[a("el-select",{attrs:{clearable:""},model:{value:t.search.data.user_name,callback:function(e){t.$set(t.search.data,"user_name",e)},expression:"search.data.user_name"}},t._l(t.options.deptUser,(function(t){return a("el-option",{key:t.label})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"工作质量"}},[a("el-select",{attrs:{clearable:""},model:{value:t.search.data.performance_evaluation,callback:function(e){t.$set(t.search.data,"performance_evaluation",e)},expression:"search.data.performance_evaluation"}},[a("el-option",{attrs:{label:"优秀",value:"1"}}),t._v(" "),a("el-option",{attrs:{label:"良好",value:"2"}}),t._v(" "),a("el-option",{attrs:{label:"合格",value:"3"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"产品类型"}},[a("el-select",{attrs:{clearable:""},on:{change:t.produceTypeChange},model:{value:t.search.data.product_type,callback:function(e){t.$set(t.search.data,"product_type",e)},expression:"search.data.product_type"}},[a("el-option",{attrs:{value:"海图"}}),t._v(" "),a("el-option",{attrs:{value:"书表"}}),t._v(" "),a("el-option",{attrs:{value:"图集"}}),t._v(" "),a("el-option",{attrs:{value:"整理测绘"}})],1),t._v(" "),a("el-select",{attrs:{clearable:""},model:{value:t.search.data.pictureType,callback:function(e){t.$set(t.search.data,"pictureType",e)},expression:"search.data.pictureType"}},t._l(t.pictureTypeOptions,(function(t){return a("el-option",{key:t,attrs:{value:t}})})),1)],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.handleQuery}},[t._v("统计")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.handleQuery}},[t._v("发布")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:t.checkOut}},[t._v("导出")])],1)],1)],1),t._v(" "),a("el-row",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.table.data}},[a("el-table-column",{attrs:{label:"序号",type:"index",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{label:"姓名",prop:"user_name"}}),t._v(" "),a("el-table-column",{attrs:{label:"图号",prop:"product_code"}}),t._v(" "),a("el-table-column",{attrs:{label:"类型",prop:"product_type"}}),t._v(" "),a("el-table-column",{attrs:{label:"工天",prop:"user_workdays"}}),t._v(" "),a("el-table-column",{attrs:{label:"工天因数",prop:"workdays_factor"}}),t._v(" "),a("el-table-column",{attrs:{label:"消灭错漏率（%）",prop:"remove_error_rate"}}),t._v(" "),a("el-table-column",{attrs:{label:"标准成绩",prop:"standard_score"}}),t._v(" "),a("el-table-column",{attrs:{label:"质量评定",prop:"performance_evaluation"}})],1),t._v(" "),a("pagination",{attrs:{"current-page":t.search.data.page_number,"page-size":t.search.data.page_size,total:t.table.page.total},on:{"page-change":t.handleCurrentChange}})],1)],1)},n=[],s=(a("d91d"),a("6797")),c=(a("b199"),a("ac67"),a("1bc7"),a("32ea"),a("28f8")),o=a("b775"),u=a("da71");function l(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,r)}return a}function d(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?l(Object(a),!0).forEach((function(e){Object(c["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):l(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var i={checkOutPersonalAssessment:function(t){return Object(o["a"])(d({url:"/personal/assessment/checkOutPersonalAssessment",method:"get",params:t},u["a"]))},selectNameByStation:function(t){return Object(o["a"])({url:"/personal/assessment/selectNameByStation",method:"get",params:t})},personalSelect:function(t){return Object(o["a"])({url:"/personal/assessment/personalSelect",method:"get",params:t})}},p=a("18ed"),h=a("857b"),m=a("3641"),k={components:{SelectYear:p["a"],Pagination:m["a"]},data:function(){return{search:{cycle:"",dept_id:"",data:{task_year:"",quarter:"",performance_evaluation:"1",product_type:"海图",pictureType:"数字图"}},options:{dept:[],deptUser:[]},ssgcOptions:["项目论证","实施方案","经费预算评审","中期评审","验收大纲评审","试运行","项目验收","项目鉴定"],table:{data:[{}],page:{}},dialog:{add:{visible:!1,ssgcOptions:["项目论证","实施方案","经费预算评审","中期评审","验收大纲评审","试运行","项目验收","项目鉴定"],form:{}}}}},computed:{pictureTypeOptions:function(){var t=["数字图","纸质图","S57图"],e=["原创航海资料","改版航海资料","法规","汇编"];return this.search.data.product_type?"海图"===this.search.data.product_type?t:e:[]},userInfo:function(){return this.$store.getters.userInfo}},mounted:function(){this.getOptions(),this.getTableData()},methods:{cycleChange:function(t){t||(this.search.data.task_year="",this.search.data.quarter="")},produceTypeChange:function(){this.search.data.pictureType=""},getOptions:function(){var t=this;s["b"].getDeptTreeList().then((function(e){t.options.dept=[e.data]}));var e={dept_id:this.userInfo.dept_id,dept_name:this.userInfo.dept_name};i.selectNameByStation(e).then((function(t){console.log(t)}))},getUserOptions:function(t){var e=this;console.log(t),i.personalSelect({dept_id:t}).then((function(t){e.options.deptUser=t.data}))},handleChangeYear:function(t){this.search.data.task_year=t},handleCurrentChange:function(t){this.search.data.page_number=t},getTableData:function(){var t=this;i.personalSelect(this.search.data).then((function(e){t.table.data=e.data}))},handleQuery:function(){this.getTableData()},handleChangeDept:function(t){var e=t[t.length-1];this.getUserOptions(e)},checkOut:function(){i.checkOutPersonalAssessment(this.search.data).then((function(t){Object(h["c"])(t,"个人工作质量评估")}))}}},b=k,f=a("5d22"),g=Object(f["a"])(b,r,n,!1,null,null,null);e["default"]=g.exports},b199:function(t,e,a){"use strict";a.d(e,"i",(function(){return u})),a.d(e,"g",(function(){return l})),a.d(e,"j",(function(){return d})),a.d(e,"c",(function(){return i})),a.d(e,"d",(function(){return p})),a.d(e,"e",(function(){return h})),a.d(e,"f",(function(){return m})),a.d(e,"h",(function(){return k})),a.d(e,"b",(function(){return b})),a.d(e,"a",(function(){return f}));a("ac67"),a("1bc7"),a("32ea");var r=a("28f8"),n=a("b775"),s=a("da71");function c(t,e){var a=Object.keys(t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(t);e&&(r=r.filter((function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable}))),a.push.apply(a,r)}return a}function o(t){for(var e=1;e<arguments.length;e++){var a=null!=arguments[e]?arguments[e]:{};e%2?c(Object(a),!0).forEach((function(e){Object(r["a"])(t,e,a[e])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(a)):c(Object(a)).forEach((function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(a,e))}))}return t}var u={add:function(t,e){return Object(n["a"])({url:"/task/taskinfo/add",method:"post",params:t,data:e})},deletebyid:function(){return Object(n["a"])({url:"/task/taskinfo/deletebyid",method:"get"})},deletebyindex:function(){return Object(n["a"])({url:"/task/taskinfo/deletebyindex",method:"get"})},getbyid:function(t){return Object(n["a"])({url:"/task/taskinfo/getbyid",method:"get",params:t})},select:function(){return Object(n["a"])({url:"/task/taskinfo/select",method:"get"})},update:function(t){return Object(n["a"])({url:"/task/taskinfo/update",method:"post",data:t})}},l={add:function(t){return Object(n["a"])({url:"/task/taskdistribution/add",method:"post",data:t})},delete:function(t){return Object(n["a"])({url:"/task/taskdistribution/delete",method:"get",params:t})},selectbyid:function(){return Object(n["a"])({url:"/task/taskdistribution/selectbyid",method:"get"})},update:function(t){return Object(n["a"])({url:"/task/taskdistribution/update",method:"post",data:t})},updateend:function(){return Object(n["a"])({url:"/task/taskdistribution/updateend",method:"get"})},updatestart:function(){return Object(n["a"])({url:"/task/taskdistribution/updatestart",method:"get"})},taskcontent:function(t){return Object(n["a"])({url:"/task/taskdistribution/taskcontent",method:"get",params:t})},select:function(t){return Object(n["a"])({url:"task/taskdistribution/selectTaskSchedulePersonnel",method:"get",params:t})}},d={add:function(t){return Object(n["a"])({url:"/task/taskother/add",method:"post",data:t})},addbylist:function(){return Object(n["a"])({url:"/task/taskother/addbylist",method:"post"})},delete:function(){return Object(n["a"])({url:"/task/taskother/delete",method:"get"})},select:function(){return Object(n["a"])({url:"/task/taskother/select",method:"get"})},selectbyid:function(){return Object(n["a"])({url:"/task/taskother/selectbyid",method:"get"})},update:function(){return Object(n["a"])({url:"/task/taskother/update",method:"post"})},selectcode:function(){return Object(n["a"])({url:"/task/taskother/selectcode",method:"get"})}},i={add:function(t){return Object(n["a"])({url:"/task/taskcheck/add",method:"post",data:t})},delete:function(){return Object(n["a"])({url:"/task/taskcheck/delete",method:"get"})},select:function(){return Object(n["a"])({url:"/task/taskcheck/select",method:"get"})},selectbyid:function(t){return Object(n["a"])({url:"/task/taskcheck/selectbyid",method:"get",params:t})},update:function(){return Object(n["a"])({url:"/task/taskcheck/update",method:"post"})},updateend:function(){return Object(n["a"])({url:"/task/taskcheck/updateend",method:"post"})},getTaskCheckByDisID:function(t){return Object(n["a"])({url:"/task/taskcheck/getTaskCheckByDisID",method:"get",params:t})},updataTaskCheckByDisID:function(t){return Object(n["a"])({url:"/task/taskcheck/updataTaskCheckByDisID",method:"post",data:t})}},p={add:function(t){return Object(n["a"])({url:"/task/taskclass/add",method:"get",params:t})},delete:function(t){return Object(n["a"])({url:"/task/taskclass/delete",method:"get",params:t})},fieldbyid:function(t){return Object(n["a"])({url:"/task/taskclass/fieldbyid",method:"get",params:t})},getchildmax:function(){return Object(n["a"])({url:"/task/taskclass/getchildmax",method:"get"})},getdepttree:function(){return Object(n["a"])({url:"/task/taskclass/getdepttree",method:"get"})},gettree:function(t){return Object(n["a"])({url:"/task/taskclass/gettree",method:"get",params:t})},gettreeEdit:function(t){return Object(n["a"])({url:"/task/taskclass/gettreeEdit",method:"get",params:t})},nameisexist:function(){return Object(n["a"])({url:"/task/taskclass/nameisexist",method:"get"})},select:function(){return Object(n["a"])({url:"/task/taskclass/select",method:"get"})},selectbyid:function(){return Object(n["a"])({url:"/task/taskclass/selectbyid",method:"get"})},update:function(t){return Object(n["a"])({url:"/task/taskclass/update",method:"get",params:t})}},h={add:function(t){return Object(n["a"])({url:"/task/taskcommon/add",method:"post",data:t})},addbylist:function(){return Object(n["a"])({url:"/task/taskcommon/addbylist",method:"post"})},delete:function(t){return Object(n["a"])({url:"/task/taskcommon/delete",method:"get",params:t})},select:function(t){return Object(n["a"])({url:"/task/taskcommon/select",method:"get",params:t})},selectbyid:function(){return Object(n["a"])({url:"/task/taskcommon/selectbyid",method:"get"})},update:function(t){return Object(n["a"])({url:"/task/taskcommon/update",method:"post",data:t})}},m={adddeptlist:function(t,e){return Object(n["a"])({url:"/task/taskdept/adddeptlist",method:"post",params:t,data:e})},addlist:function(t){return Object(n["a"])({url:"/task/taskdept/addlist",method:"post",data:t})},addone:function(){return Object(n["a"])({url:"/task/taskdept/addone",method:"post"})},deletebyid:function(){return Object(n["a"])({url:"/task/taskdept/deletebyid",method:"get"})},deletebyindexid:function(){return Object(n["a"])({url:"/task/taskdept/deletebyindexid",method:"get"})},select:function(){return Object(n["a"])({url:"/task/taskdept/select",method:"get"})},update:function(){return Object(n["a"])({url:"/task/taskdept/update",method:"post"})},updatelist:function(){return Object(n["a"])({url:"/task/taskdept/updatelist",method:"post"})}},k={addlist:function(){return Object(n["a"])({url:"/task/taskindex/addlist",method:"post"})},addone:function(){return Object(n["a"])({url:"/task/taskindex/addone",method:"post"})},bookdeptlist:function(){return Object(n["a"])({url:"/task/taskindex/bookdeptlist",method:"get"})},booklist:function(){return Object(n["a"])({url:"/task/taskindex/booklist",method:"get"})},checklist:function(t){return Object(n["a"])({url:"/task/taskindex/pageCheckList",method:"get",params:t})},checklistdetail:function(t){return Object(n["a"])({url:"/task/taskindex/checklistdetail",method:"get",params:t})},checklistexport:function(t){return Object(n["a"])(o({url:"/task/taskindex/checklistexport",method:"get",params:t},s["a"]))},delete:function(t){return Object(n["a"])({url:"/task/taskindex/delete",method:"get",params:t})},mapdeptlist:function(){return Object(n["a"])({url:"/task/taskindex/mapdeptlist",method:"get"})},deptchecklist:function(t){return Object(n["a"])({url:"/task/taskindex/deptchecklist",method:"get",params:t})},getchecktaskresult:function(t){return Object(n["a"])({url:"/task/taskindex/getchecktaskresult",method:"get",params:t})},maplist:function(){return Object(n["a"])({url:"/task/taskindex/maplist",method:"get"})},othersdeptlist:function(){return Object(n["a"])({url:"/task/taskindex/othersdeptlist",method:"get"})},otherslist:function(){return Object(n["a"])({url:"/task/taskindex/otherslist",method:"get"})},select:function(){return Object(n["a"])({url:"/task/taskindex/select",method:"get"})},selectbyid:function(){return Object(n["a"])({url:"/task/taskindex/selectbyid",method:"get"})},selectchecktask:function(t){return Object(n["a"])({url:"/task/taskindex/selectchecktask",method:"get",params:t})},selectchecktaskexport:function(t){return Object(n["a"])(o({url:"/task/taskindex/selectchecktaskexport",method:"get",params:t},s["a"]))},selectdepttask:function(t){return Object(n["a"])({url:"/task/taskindex/selectdepttask",method:"get",params:t})},selectdeptuser:function(t){return Object(n["a"])({url:"/task/taskindex/selectdeptuser",method:"get",params:t})},selectyear:function(){return Object(n["a"])({url:"/task/taskindex/selectyear",method:"get"})},update:function(){return Object(n["a"])({url:"/task/taskindex/update",method:"post"})},updateclass:function(t){return Object(n["a"])({url:"/task/taskindex/updateclass",method:"get",params:t})},updatedata:function(){return Object(n["a"])({url:"/task/taskindex/updatedata",method:"get"})},updaterate:function(){return Object(n["a"])({url:"/task/taskindex/updaterate",method:"get"})},updatestate:function(t){return Object(n["a"])({url:"/task/taskindex/updatestate",method:"get",params:t})},updatestatelist:function(t){return Object(n["a"])({url:"/task/taskindex/updatestatelist",method:"get",params:t})},updateyear:function(){return Object(n["a"])({url:"/task/taskindex/updateyear",method:"get"})},updateyearlist:function(t){return Object(n["a"])({url:"/task/taskindex/updateyearlist",method:"get",params:t})},checkTaskInfo:function(t){return Object(n["a"])({url:"/task/taskindex/checkInfo",method:"get",params:t})},selectTasklist:function(t){return Object(n["a"])({url:"/task/taskindex/selectTasklist",method:"get",params:t})},addTask:function(t){return Object(n["a"])({url:"/task/taskindex/addTask",method:"post",data:t})},updateTaskName:function(t){return Object(n["a"])({url:"/task/taskindex/updateTaskName",method:"post",params:t})}},b={add:function(t,e,a){return Object(n["a"])(o({url:"/task/projecttemplate/add",method:"post",params:t,data:e},a))},dataimport:function(t){return Object(n["a"])({url:"/task/projecttemplate/dataimport",method:"post",params:t})},deletebyid:function(t){return Object(n["a"])({url:"/task/projecttemplate/deletebyid",method:"get",params:t})},filedownload:function(t){return Object(n["a"])(o({url:"/task/projecttemplate/filedownload",method:"get",params:t},s["a"]))},select:function(t){return Object(n["a"])({url:"/task/projecttemplate/select",method:"get",params:t})},selectbyid:function(t){return Object(n["a"])({url:"/task/projecttemplate/selectbyid",method:"get",params:t})},update:function(t,e,a){return Object(n["a"])(o({url:"/task/projecttemplate/update",method:"post",params:t,data:e},a))},upfile:function(t){return Object(n["a"])({url:"/task/projecttemplate/upfile",method:"post",params:t})}},f={add:function(t,e){return Object(n["a"])(o({url:"/task/projectfiles/add",method:"get",params:t},e))},deletebyid:function(t){return Object(n["a"])({url:"/task/projectfiles/delete",method:"get",params:t})},filedownload:function(t){return Object(n["a"])(o({url:"/task/projectfiles/download",method:"get",params:t},s["a"]))},select:function(t){return Object(n["a"])({url:"/task/projectfiles/select",method:"get",params:t})},selectbyid:function(t){return Object(n["a"])({url:"/task/projectfiles/selectbyid",method:"get",params:t})},update:function(t){return Object(n["a"])({url:"/task/projectfiles/update",method:"post",data:t})},upfile:function(t){return Object(n["a"])({url:"/task/projectfiles/upfile",method:"post",params:t})}}},c2e2:function(t,e,a){"use strict";a("28ce")},d91d:function(t,e,a){"use strict";var r=a("a86f"),n=a("69b0"),s=a("f417");a("c46f")("search",1,(function(t,e,a,c){return[function(a){var r=t(this),n=void 0==a?void 0:a[e];return void 0!==n?n.call(a,r):new RegExp(a)[e](String(r))},function(t){var e=c(a,t,this);if(e.done)return e.value;var o=r(t),u=String(this),l=o.lastIndex;n(l,0)||(o.lastIndex=0);var d=s(o,u);return n(o.lastIndex,l)||(o.lastIndex=l),null===d?-1:d.index}]}))}}]);