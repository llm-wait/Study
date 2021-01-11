(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-219eccef"],{1575:function(t,e,a){},"2d50":function(t,e,a){"use strict";a("d651")},"5bd5":function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",{attrs:{inline:"",model:t.searchForm}},[a("el-form-item",{attrs:{label:"年度","label-width":"70px"}},[a("select-year",{on:{change:t.handleChangeYear}})],1),t._v(" "),a("el-form-item",{attrs:{label:"任务状态","label-width":"80px"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",clearable:""},model:{value:t.searchForm.status,callback:function(e){t.$set(t.searchForm,"status",e)},expression:"searchForm.status"}},t._l(t.statusOptions,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"产品类型","label-width":"70px"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",clearable:""},model:{value:t.searchForm.type,callback:function(e){t.$set(t.searchForm,"type",e)},expression:"searchForm.type"}},t._l(t.typeOptions,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1)],1),t._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:t.search}},[t._v("查询")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:t.downLoad}},[t._v("批量导出")])],1)],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{type:"index",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务名称",prop:"task_name"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务年份",prop:"task_year"}}),t._v(" "),a("el-table-column",{attrs:{label:"产品类型",prop:"task_type"}}),t._v(" "),a("el-table-column",{attrs:{label:"下达人",prop:"xdr"}}),t._v(" "),a("el-table-column",{attrs:{label:"下达时间",prop:"task_print_date"}}),t._v(" "),a("el-table-column",{attrs:{label:"任务状态",prop:"task_state"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:"190"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-link",{attrs:{icon:"el-icon-view",type:"primary"},on:{click:function(a){return t.assessThis(e.row)}}},[t._v("质量评估")]),t._v(" "),a("el-link",{attrs:{icon:"el-icon-s-promotion",type:"success"},on:{click:function(a){return t.sendThis(e.row)}}},[t._v("发送")])]}}])})],1),t._v(" "),a("pagination",{attrs:{"current-page":t.page.current,"page-size":10,total:t.page.total},on:{"page-change":t.handleCurrentChange}}),t._v(" "),a("el-dialog",{attrs:{title:"海图质量评估",visible:t.mapVisible,width:"1100px",top:"2vh","before-close":t.beforeMapClose,"close-on-click-modal":!1},on:{"update:visible":function(e){t.mapVisible=e}}},[a("div",{staticClass:"task-list-container"},[a("div",{staticClass:"clearfix"},[a("span",{staticClass:"fl",staticStyle:{width:"80px","font-weight":"bold"}},[t._v("产品类型：")]),t._v(" "),a("el-radio-group",{staticClass:"fl",on:{change:t.typeChange},model:{value:t.distribution_type,callback:function(e){t.distribution_type=e},expression:"distribution_type"}},t._l(t.chartProductType,(function(e){return a("el-radio",{key:e,attrs:{label:e}},[t._v(t._s(e))])})),1),t._v(" "),a("el-button",{staticClass:"fr save-btn",attrs:{type:"primary"},on:{click:t.saveThisMap}},[t._v("保存")])],1),t._v(" "),a("h3",[t._v("基本信息")]),t._v(" "),a("el-form",{attrs:{model:t.mapForm,"label-width":"96px",inline:""}},[a("el-form-item",{staticClass:"w25",attrs:{label:"图号"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.mapForm.map_code,callback:function(e){t.$set(t.mapForm,"map_code",e)},expression:"mapForm.map_code"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"图名"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.mapForm.map_name,callback:function(e){t.$set(t.mapForm,"map_name",e)},expression:"mapForm.map_name"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"工天"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.mapForm.days,callback:function(e){t.$set(t.mapForm,"days",e)},expression:"mapForm.days"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"作业室"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.mapForm.zys,callback:function(e){t.$set(t.mapForm,"zys",e)},expression:"mapForm.zys"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"交验日期"}},[a("el-date-picker",{attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.mapForm.jyrq,callback:function(e){t.$set(t.mapForm,"jyrq",e)},expression:"mapForm.jyrq"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"验收图幅"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.mapForm.ystf,callback:function(e){t.$set(t.mapForm,"ystf",e)},expression:"mapForm.ystf"}},t._l(t.returnOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"图例表"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.mapForm.tlb,callback:function(e){t.$set(t.mapForm,"tlb",e)},expression:"mapForm.tlb"}},t._l(t.returnOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"改成图"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.mapForm.gct,callback:function(e){t.$set(t.mapForm,"gct",e)},expression:"mapForm.gct"}},t._l(t.yesOrNoOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"成图技术修改"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.mapForm.ctjsxg,callback:function(e){t.$set(t.mapForm,"ctjsxg",e)},expression:"mapForm.ctjsxg"}},t._l(t.yesOrNoOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"制图编辑",prop:"ztbj"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",disabled:!0},model:{value:t.mapForm.ztbj,callback:function(e){t.$set(t.mapForm,"ztbj",e)},expression:"mapForm.ztbj"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.label,attrs:{label:t.label,value:t.label}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"作业员",prop:"zyy"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",disabled:!0},model:{value:t.mapForm.zyy,callback:function(e){t.$set(t.mapForm,"zyy",e)},expression:"mapForm.zyy"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.label,attrs:{label:t.label,value:t.label}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"组长",prop:"zz"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择",disabled:!0},model:{value:t.mapForm.zz,callback:function(e){t.$set(t.mapForm,"zz",e)},expression:"mapForm.zz"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.label,attrs:{label:t.label,value:t.label}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"主要负责人"}},[a("el-input",{model:{value:t.mapForm.zyfzr,callback:function(e){t.$set(t.mapForm,"zyfzr",e)},expression:"mapForm.zyfzr"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"次要负责人"}},[a("el-input",{model:{value:t.mapForm.cyfzr,callback:function(e){t.$set(t.mapForm,"cyfzr",e)},expression:"mapForm.cyfzr"}})],1)],1),t._v(" "),a("h3",[t._v("缺陷情况")]),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.mapData}},[a("el-table-column",{attrs:{label:"检查者",prop:"check_oper"}}),t._v(" "),a("el-table-column",{attrs:{label:"严重缺陷",prop:"check_error1"}}),t._v(" "),a("el-table-column",{attrs:{label:"一般缺陷",prop:"check_error3"}}),t._v(" "),a("el-table-column",{attrs:{label:"轻微缺陷",prop:"check_error4"}}),t._v(" "),a("el-table-column",{attrs:{label:"图例表缺陷",prop:"check_maptable"}}),t._v(" "),a("el-table-column",{attrs:{label:"缺陷值（合计）"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.check_error1+e.row.check_error3+e.row.check_error4))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"不能评合格的严重错误",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{directives:[{name:"show",rawName:"v-show",value:!1===e.row.edit2,expression:"scope.row.edit2 === false"}],on:{click:function(a){return t.editrow2(e.row)}}},[t._v(t._s(e.row.check_lost))]),t._v(" "),a("el-input",{directives:[{name:"show",rawName:"v-show",value:!0===e.row.edit2,expression:"scope.row.edit2 === true"}],on:{blur:function(a){return t.saverow2(e.row)}},model:{value:e.row.check_lost,callback:function(a){t.$set(e.row,"check_lost",a)},expression:"scope.row.check_lost"}})]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"只记入本级缺陷"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{directives:[{name:"show",rawName:"v-show",value:!1===e.row.edit1,expression:"scope.row.edit1 === false"}],on:{click:function(a){return t.editrow(e.row)}}},[t._v(t._s(e.row.check_mylevel))]),t._v(" "),a("el-input",{directives:[{name:"show",rawName:"v-show",value:!0===e.row.edit1,expression:"scope.row.edit1 === true"}],on:{blur:function(a){return t.saverow(e.row)}},model:{value:e.row.check_mylevel,callback:function(a){t.$set(e.row,"check_mylevel",a)},expression:"scope.row.check_mylevel"}})]}}])})],1),t._v(" "),a("div",{staticClass:"clearfix"},[a("el-button",{staticClass:"fr calc-btn",attrs:{type:"primary"},on:{click:t.calulateThis}},[t._v("计算")])],1),t._v(" "),a("h3",[t._v("计算结果")]),t._v(" "),a("el-form",{ref:"mapForm2",attrs:{model:t.mapForm2,"label-width":"160px",inline:""}},[a("el-form-item",{staticClass:"w30",attrs:{label:"作业员错漏率（每天）",prop:"compute1"}},[a("el-input",{model:{value:t.mapForm2.compute1,callback:function(e){t.$set(t.mapForm2,"compute1",e)},expression:"mapForm2.compute1"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"校对消灭错漏率(每天)",prop:"compute2"}},[a("el-input",{model:{value:t.mapForm2.compute2,callback:function(e){t.$set(t.mapForm2,"compute2",e)},expression:"mapForm2.compute2"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"审查消灭错漏率",prop:"compute3"}},[a("el-input",{model:{value:t.mapForm2.compute3,callback:function(e){t.$set(t.mapForm2,"compute3",e)},expression:"mapForm2.compute3"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"本幅海图综合质量评定",prop:"compute4"}},[a("el-input",{model:{value:t.mapForm2.compute4,callback:function(e){t.$set(t.mapForm2,"compute4",e)},expression:"mapForm2.compute4"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"校对工作质量评定",prop:"compute5"}},[a("el-input",{model:{value:t.mapForm2.compute5,callback:function(e){t.$set(t.mapForm2,"compute5",e)},expression:"mapForm2.compute5"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"编辑审查工作质量评定",prop:"compute6"}},[a("el-input",{model:{value:t.mapForm2.compute6,callback:function(e){t.$set(t.mapForm2,"compute6",e)},expression:"mapForm2.compute6"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"作业员工作质量评定",prop:"compute7"}},[a("el-input",{model:{value:t.mapForm2.compute7,callback:function(e){t.$set(t.mapForm2,"compute7",e)},expression:"mapForm2.compute7"}})],1)],1)],1)]),t._v(" "),a("el-dialog",{attrs:{title:"书表质量评估",visible:t.bookVisible,width:"1100px",top:"2vh","before-close":t.beforeBookClose,"close-on-click-modal":!1},on:{"update:visible":function(e){t.bookVisible=e}}},[a("div",{staticClass:"task-list-container"},[a("div",{staticClass:"clearfix"},[a("span",{staticClass:"fl section-title"},[t._v("章节")]),t._v(" "),a("el-select",{staticClass:"fl",attrs:{size:"small",placeholder:"请选择",prop:"section"},on:{change:t.sectionChange},model:{value:t.bookForm.section,callback:function(e){t.$set(t.bookForm,"section",e)},expression:"bookForm.section"}},t._l(t.sectionsOptions,(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1),t._v(" "),a("el-button",{staticClass:"save-btn fr",attrs:{type:"primary"},on:{click:t.saveThisBook}},[t._v("保存")])],1),t._v(" "),a("h3",[t._v("基本信息")]),t._v(" "),a("el-form",{attrs:{model:t.bookForm,"label-width":"96px",inline:""}},[a("el-form-item",{staticClass:"w25",attrs:{label:"书号",prop:"book_code"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.bookForm.book_code,callback:function(e){t.$set(t.bookForm,"book_code",e)},expression:"bookForm.book_code"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"书名",prop:"book_name"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.bookForm.book_name,callback:function(e){t.$set(t.bookForm,"book_name",e)},expression:"bookForm.book_name"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"工天",prop:"days"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.bookForm.days,callback:function(e){t.$set(t.bookForm,"days",e)},expression:"bookForm.days"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"作业室",prop:"zys"}},[a("el-input",{attrs:{disabled:!0},model:{value:t.bookForm.zys,callback:function(e){t.$set(t.bookForm,"zys",e)},expression:"bookForm.zys"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"交验日期",prop:"jyrq"}},[a("el-date-picker",{attrs:{type:"date",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",placeholder:"选择日期"},model:{value:t.bookForm.jyrq,callback:function(e){t.$set(t.bookForm,"jyrq",e)},expression:"bookForm.jyrq"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"验收图幅",prop:"ystf"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.ystf,callback:function(e){t.$set(t.bookForm,"ystf",e)},expression:"bookForm.ystf"}},t._l(t.returnOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"图例表",prop:"tlb"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.tlb,callback:function(e){t.$set(t.bookForm,"tlb",e)},expression:"bookForm.tlb"}},t._l(t.returnOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"改成图",prop:"gct"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.gct,callback:function(e){t.$set(t.bookForm,"gct",e)},expression:"bookForm.gct"}},t._l(t.yesOrNoOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"成图技术修改",prop:"ctjsxg"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.ctjsxg,callback:function(e){t.$set(t.bookForm,"ctjsxg",e)},expression:"bookForm.ctjsxg"}},t._l(t.yesOrNoOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"制图编辑",prop:"ztbj"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.ztbj,callback:function(e){t.$set(t.bookForm,"ztbj",e)},expression:"bookForm.ztbj"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"作业员",prop:"zyy"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.zyy,callback:function(e){t.$set(t.bookForm,"zyy",e)},expression:"bookForm.zyy"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"组长",prop:"zz"}},[a("el-select",{attrs:{size:"small",placeholder:"请选择"},model:{value:t.bookForm.zz,callback:function(e){t.$set(t.bookForm,"zz",e)},expression:"bookForm.zz"}},t._l(t.peopleOptions,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})})),1)],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"主要负责人",prop:"zyfzr"}},[a("el-input",{model:{value:t.bookForm.zyfzr,callback:function(e){t.$set(t.bookForm,"zyfzr",e)},expression:"bookForm.zyfzr"}})],1),t._v(" "),a("el-form-item",{staticClass:"w25",attrs:{label:"次要负责人",prop:"cyfzr"}},[a("el-input",{model:{value:t.bookForm.cyfzr,callback:function(e){t.$set(t.bookForm,"cyfzr",e)},expression:"bookForm.cyfzr"}})],1)],1),t._v(" "),a("h3",[t._v("缺陷情况")]),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.bookData}},[a("el-table-column",{attrs:{label:"检查者",prop:"check_oper"}}),t._v(" "),a("el-table-column",{attrs:{label:"严重缺陷",prop:"check_error1"}}),t._v(" "),a("el-table-column",{attrs:{label:"较重缺陷",prop:"check_error2"}}),t._v(" "),a("el-table-column",{attrs:{label:"一般缺陷",prop:"check_error3"}}),t._v(" "),a("el-table-column",{attrs:{label:"轻微缺陷",prop:"check_error4"}}),t._v(" "),a("el-table-column",{attrs:{label:"图例表缺陷",prop:"check_maptable"}}),t._v(" "),a("el-table-column",{attrs:{label:"缺陷值（合计）",width:"130"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.check_error1+e.row.check_error2+e.row.check_error3+e.row.check_error4))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"不能评合格的严重错误",width:"160"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{directives:[{name:"show",rawName:"v-show",value:!1===e.row.edit2,expression:"scope.row.edit2 === false"}],on:{click:function(a){return t.editrow2(e.row)}}},[t._v(t._s(e.row.check_lost))]),t._v(" "),a("el-input",{directives:[{name:"show",rawName:"v-show",value:!0===e.row.edit2,expression:"scope.row.edit2 === true"}],on:{blur:function(a){return t.saverow2(e.row)}},model:{value:e.row.check_lost,callback:function(a){t.$set(e.row,"check_lost",a)},expression:"scope.row.check_lost"}})]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"只记入本级缺陷",width:"130"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{directives:[{name:"show",rawName:"v-show",value:!1===e.row.edit1,expression:"scope.row.edit1 === false"}],on:{click:function(a){return t.editrow(e.row)}}},[t._v(t._s(e.row.check_mylevel))]),t._v(" "),a("el-input",{directives:[{name:"show",rawName:"v-show",value:!0===e.row.edit1,expression:"scope.row.edit1 === true"}],on:{blur:function(a){return t.saverow(e.row)}},model:{value:e.row.check_mylevel,callback:function(a){t.$set(e.row,"check_mylevel",a)},expression:"scope.row.check_mylevel"}})]}}])})],1),t._v(" "),a("div",{staticClass:"clearfix"},[a("el-button",{staticClass:"fr calc-btn",attrs:{type:"primary"},on:{click:t.calulateThis}},[t._v("计算")])],1),t._v(" "),a("h3",[t._v("计算结果")]),t._v(" "),a("el-form",{ref:"bookForm2",attrs:{model:t.bookForm2,"label-width":"160px",inline:""}},[a("el-form-item",{staticClass:"w30",attrs:{label:"作业员错漏率（每天）",prop:"compute1"}},[a("el-input",{model:{value:t.bookForm2.compute1,callback:function(e){t.$set(t.bookForm2,"compute1",e)},expression:"bookForm2.compute1"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"校对消灭错漏率(每天)",prop:"compute2"}},[a("el-input",{model:{value:t.bookForm2.compute2,callback:function(e){t.$set(t.bookForm2,"compute2",e)},expression:"bookForm2.compute2"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"审查消灭错漏率",prop:"compute3"}},[a("el-input",{model:{value:t.bookForm2.compute3,callback:function(e){t.$set(t.bookForm2,"compute3",e)},expression:"bookForm2.compute3"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"本图书综合质量评定",prop:"compute4"}},[a("el-input",{model:{value:t.bookForm2.compute4,callback:function(e){t.$set(t.bookForm2,"compute4",e)},expression:"bookForm2.compute4"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"校对工作质量评定",prop:"compute5"}},[a("el-input",{model:{value:t.bookForm2.compute5,callback:function(e){t.$set(t.bookForm2,"compute5",e)},expression:"bookForm2.compute5"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"编辑审查工作质量评定",prop:"compute6"}},[a("el-input",{model:{value:t.bookForm2.compute6,callback:function(e){t.$set(t.bookForm2,"compute6",e)},expression:"bookForm2.compute6"}})],1),t._v(" "),a("el-form-item",{staticClass:"w30",attrs:{label:"作业员工作质量评定",prop:"compute7"}},[a("el-input",{model:{value:t.bookForm2.compute7,callback:function(e){t.$set(t.bookForm2,"compute7",e)},expression:"bookForm2.compute7"}})],1)],1)],1)])],1)},r=[],l=a("6797"),s=a("4cf2"),c=a("b199"),i=a("857b"),n=a("18ed"),m=a("3641"),p={components:{SelectYear:n["a"],Pagination:m["a"]},data:function(){return{chartProductType:[],searchForm:{task_year:"",status:"",type:""},statusOptions:[],tableData:[],page:{current:1,total:1},mapVisible:!1,distribution_type:"纸图",mapForm:{map_code:"",map_name:"",days:"",zys:"",jyrq:"",ystf:"",tlb:"",gct:"",ctjsxg:"",ztbj:"",zyy:"",zz:"",zyfzr:"",cyfzr:""},returnOptions:[{value:"退回",label:"退回"},{value:"未退回",label:"未退回"}],yesOrNoOptions:[{value:"是",label:"是"},{value:"否",label:"否"}],mapCheckId:"",bookCheckId:"",currentTaskId:"",currentType:"",mapData:[],mapForm2:{compute1:"",compute2:"",compute3:"",compute4:"",compute5:"",compute6:"",compute7:""},bookVisible:!1,sectionsOptions:[],bookForm:{book_code:"",book_name:"",days:"",zys:"",jyrq:"",ystf:"",tlb:"",gct:"",ctjsxg:"",ztbj:"",zyy:"",zz:"",zyfzr:"",cyfzr:""},bookData:[],bookForm2:{compute1:"",compute2:"",compute3:"",compute4:"",compute5:"",compute6:"",compute7:""},multipleSelection:[],yzcwOptions:[{value:1,label:"是"},{value:0,label:"否"}],typeOptions:["海图任务","应急保障任务","书表任务"],peopleOptions:[]}},computed:{userInfo:function(){return this.$store.getters.userInfo}},mounted:function(){this.getStatusOptions(),this.fetchTableData(),this.getPeoples(),Object(i["f"])(this)},methods:{fetchTableData:function(){var t=this,e={dept_id:this.userInfo.dept_id,page_number:this.page.current,page_size:10};s["a"].getList(e).then((function(e){t.page.total=e.data.recordCount,t.tableData=e.data.smallList})).catch((function(t){console.log(t)}))},getStatusOptions:function(){var t=this,e={use_type:1};l["c"].getTaskState(e).then((function(e){t.statusOptions=e})).catch((function(t){console.log(t)}))},getPeoples:function(){var t=this;c["h"].selectdeptuser().then((function(e){for(var a=e.data.length,o=0;o<a;o++)t.peopleOptions.push({label:e.data[o].user_name,value:e.data[o].user_id})})).catch((function(t){console.log(t)}))},search:function(){var t=this,e={dept_id:this.userInfo.dept_id,page_number:1,page_size:20,task_year:this.searchForm.task_year,status:this.searchForm.status};this.page.current=1,s["a"].getList(e).then((function(e){t.page.total=e.data.recordCount,t.tableData=e.data.smallList})).catch((function(t){console.log(t)}))},assessThis:function(t){var e=this;if(this.currentTaskId=t.index_id,"制图任务"===t.task_type||"应急保障任务"===t.task_type){this.mapVisible=!0,this.currentType="海图",this.$nextTick((function(){e.distribution_type="纸图",void 0!==e.$refs["mapForm"]&&e.$refs["mapForm"].resetFields(),void 0!==e.$refs["mapForm2"]&&e.$refs["mapForm2"].resetFields()}));var a={chart_type:this.distribution_type,dept_id:this.userInfo.dept_id,task_index_id:t.index_id};s["a"].getmapinfor(a).then((function(t){e.$nextTick((function(){t.data&&(e.mapForm.map_code=t.data.chart_book_code?t.data.chart_book_code:"",e.mapForm.map_name=t.data.chart_book_name?t.data.chart_book_name:"",e.mapForm.zys=t.data.dept_name?t.data.dept_name:"",e.mapForm.jyrq=t.data.start_check_date?t.data.start_check_date:"",e.mapForm.ystf=t.data.chart_book_back?t.data.chart_book_back:"",e.mapForm.tlb=t.data.chart_pdf_back?t.data.chart_pdf_back:"",e.mapForm.gct=t.data.chart_map_edit?t.data.chart_map_edit:"",e.mapForm.ctjsxg=t.data.chart_edit?t.data.chart_edit:"",e.mapForm.ztbj=t.data.chart_editor?t.data.chart_editor:"",e.mapForm.zyy=t.data.chart_maker?t.data.chart_maker:"",e.mapForm.zz=t.data.chart_leader?t.data.chart_leader:"",e.mapForm.zyfzr=t.data.chart_manager?t.data.chart_manager:"",e.mapForm.cyfzr=t.data.chart_manager1?t.data.chart_manager1:"",e.mapCheckId=t.data.check_index_id,e.mapForm.dept_id=t.data.dept_id?t.data.dept_id:"")}))})).then((function(){s["a"].getAllWorkDaysById({indexID:t.index_id,chart_type:e.distribution_type}).then((function(t){e.$nextTick((function(){null===t.data?e.mapForm.days="":e.mapForm.days=t.data}))}))})).then((function(){var a={task_id:t.index_id};s["a"].getmaperrorlist(a).then((function(t){for(var a=t.data.length,o=0;o<a;o++)t.data[o].edit1=!1,t.data[o].edit2=!1;e.mapData=t.data})).catch((function(t){console.log(t)}))}))}else if("书表任务"===t.task_type){this.bookVisible=!0,this.currentType="书表",this.$nextTick((function(){void 0!==e.$refs["bookForm"]&&e.$refs["bookForm"].resetFields(),void 0!==e.$refs["bookForm2"]&&e.$refs["bookForm2"].resetFields()}));var o={task_index_id:t.index_id};c["g"].taskcontent(o).then((function(t){e.sectionsOptions=t.data})).then((function(){var a={dept_id:e.userInfo.dept_id,task_index_id:t.index_id};s["a"].getmapinfor(a).then((function(t){e.$nextTick((function(){e.bookForm.map_code=t.data.chart_book_code?t.data.chart_book_code:"",e.bookForm.map_name=t.data.chart_book_name?t.data.chart_book_name:"",e.bookForm.zys=t.data.dept_name?t.data.dept_name:"",e.bookForm.jyrq=t.data.start_check_date?t.data.start_check_date:"",e.bookForm.ystf=t.data.chart_book_back?t.data.chart_book_back:"",e.bookForm.tlb=t.data.chart_pdf_back?t.data.chart_pdf_back:"",e.bookForm.gct=t.data.chart_map_edit?t.data.chart_map_edit:"",e.bookForm.ctjsxg=t.data.chart_edit?t.data.chart_edit:"",e.bookForm.ztbj=t.data.chart_editor?t.data.chart_editor:"",e.bookForm.zyy=t.data.chart_maker?t.data.chart_maker:"",e.bookForm.zz=t.data.chart_leader?t.data.chart_leader:"",e.bookForm.zyfzr=t.data.chart_manager?t.data.chart_manager:"",e.bookForm.cyfzr=t.data.chart_manager1?t.data.chart_manager1:"",e.bookCheckId=t.data.check_index_id,e.bookForm.dept_id=t.data.dept_id?t.data.dept_id:""}))})).then((function(){s["a"].getAllWorkDaysById({indexID:t.index_id,chart_type:""}).then((function(t){e.$nextTick((function(){null===t.data?e.bookForm.days="":e.bookForm.days=t.data}))}))})).then((function(){var a={task_id:t.index_id};s["a"].getmaperrorlist(a).then((function(t){e.bookData=t.data})).catch((function(t){console.log(t)}))}))})).catch((function(t){console.log(t)}))}},editrow:function(t){t.edit1=!0},saverow:function(t){t.edit1=!1},editrow2:function(t){t.edit2=!0},saverow2:function(t){t.edit2=!1},calulateThis:function(){var t=this;if("海图"===this.currentType){var e={map_code:this.mapForm.map_code,task_id:this.currentTaskId,TaskChartType:this.distribution_type};s["a"].calculate(e).then((function(e){t.$nextTick((function(){t.mapForm2.compute1=e.data.zyclv,t.mapForm2.compute2=e.data.jdxmcll,t.mapForm2.compute3=e.data.scxmcll,t.mapForm2.compute4=e.data.htzhzlpd,t.mapForm2.compute5=e.data.jdgzzzpd,t.mapForm2.compute6=e.data.bjscpd,t.mapForm2.compute7=e.data.zyyzlpd}))})).catch((function(t){console.log(t)}))}else{var a={map_code:this.bookForm.map_code,task_id:this.currentTaskId};s["a"].calculate(a).then((function(e){t.$nextTick((function(){t.bookForm2.compute1=e.data.zyclv,t.bookForm2.compute2=e.data.jdxmcll,t.bookForm2.compute3=e.data.scxmcll,t.bookForm2.compute4=e.data.htzhzlpd,t.bookForm2.compute5=e.data.jdgzzzpd,t.bookForm2.compute6=e.data.bjscpd,t.bookForm2.compute7=e.data.zyyzlpd}))})).catch((function(t){console.log(t)}))}},sendThis:function(t){var e=this,a={class_id:t.task_class_id,index_id:t.index_id,task_name:t.task_name,task_state:"ZY",task_type:t.task_type};s["a"].updatestate(a).then((function(t){Object(i["i"])(e,t),e.fetchTableData()})).catch((function(t){console.log(t)}))},handleChangeYear:function(t){this.searchForm.task_year=t},handleCurrentChange:function(t){var e=this;this.page.current=t;var a={dept_id:this.userInfo.dept_id,page_number:t,page_size:10};s["a"].getList(a).then((function(t){e.page.total=t.data.recordCount,e.tableData=t.data.smallList})).catch((function(t){console.log(t)}))},handleSelectionChange:function(t){this.multipleSelection=t,console.log(t)},saveThisMap:function(){var t=this;console.log("ssss");var e={chart_book_back:this.mapForm.ystf,chart_book_code:this.mapForm.map_code,chart_book_name:this.mapForm.map_name,chart_edit:this.mapForm.ctjsxg,chart_manager:this.mapForm.zyfzr,chart_manager1:this.mapForm.cyfzr,chart_map_edit:this.mapForm.gct,chart_pdf_back:this.mapForm.tlb,check_index_id:this.mapCheckId,dept_id:this.mapForm.dept_id,dept_name:this.mapForm.zys,start_check_date:this.formatDate(this.mapForm.jyrq),task_index_id:this.currentTaskId,chart_editor:this.mapForm.ztbj,chart_maker:this.mapForm.zyy,chart_leader:this.mapForm.zz};console.log(e),s["a"].update(e).then((function(e){Object(i["i"])(t,e)}))},saveThisBook:function(){var t=this,e={chart_book_back:this.bookForm.ystf,chart_book_code:this.bookForm.map_code,chart_book_name:this.bookForm.map_name,chart_edit:this.bookForm.ctjsxg,chart_manager:this.bookForm.zyfzr,chart_manager1:this.bookForm.cyfzr,chart_map_edit:this.bookForm.gct,chart_pdf_back:this.bookForm.tlb,check_index_id:this.bookCheckId,dept_id:this.bookForm.dept_id,dept_name:this.bookForm.zys,start_check_date:this.formatDate(this.bookForm.jyrq),task_index_id:this.currentTaskId,chart_editor:this.bookForm.ztbj,chart_maker:this.bookForm.zyy,chart_leader:this.bookForm.zz};s["a"].update(e).then((function(e){Object(i["i"])(t,e)}))},formatDate:function(t){if(""!==t&&null!==t){var e=new Date(t),a=e.getFullYear(),o=e.getMonth(),r=e.getDate(),l=a+"-"+(o+1>=10?o+1:"0"+(o+1))+"-"+(r+1<10?"0"+r:r);return l}return""},typeChange:function(){var t=this,e={chart_type:this.distribution_type,dept_id:this.userInfo.dept_id,task_index_id:this.currentTaskId};s["a"].getmapinfor(e).then((function(e){t.$nextTick((function(){t.mapForm.map_code=e.data.chart_book_code?e.data.chart_book_code:"",t.mapForm.map_name=e.data.chart_book_name?e.data.chart_book_name:"",t.mapForm.zys=e.data.dept_name?e.data.dept_name:"",t.mapForm.jyrq=e.data.start_check_date?e.data.start_check_date:"",t.mapForm.ystf=e.data.chart_book_back?e.data.chart_book_back:"",t.mapForm.tlb=e.data.chart_pdf_back?e.data.chart_pdf_back:"",t.mapForm.gct=e.data.chart_map_edit?e.data.chart_map_edit:"",t.mapForm.ctjsxg=e.data.chart_edit?e.data.chart_edit:"",t.mapForm.ztbj=e.data.chart_editor?e.data.chart_editor:"",t.mapForm.zyy=e.data.chart_maker?e.data.chart_maker:"",t.mapForm.zz=e.data.chart_leader?e.data.chart_leader:"",t.mapForm.zyfzr=e.data.chart_manager?e.data.chart_manager:"",t.mapForm.cyfzr=e.data.chart_manager1?e.data.chart_manager1:"",t.mapCheckId=e.data.check_index_id,t.mapForm.dept_id=e.data.dept_id?e.data.dept_id:""}))})),s["a"].getAllWorkDaysById({indexID:e.task_index_id,chart_type:this.distribution_type}).then((function(e){t.$nextTick((function(){null===e.data?t.mapForm.days="":t.mapForm.days=e.data}))}))},sectionChange:function(){var t=this,e={chart_type:this.bookForm.section,dept_id:this.userInfo.dept_id,task_index_id:this.currentTaskId};s["a"].getmapinfor(e).then((function(e){t.$nextTick((function(){t.bookForm.map_code=e.data.chart_book_code?e.data.chart_book_code:"",t.bookForm.map_name=e.data.chart_book_name?e.data.chart_book_name:"",t.bookForm.zys=e.data.dept_name?e.data.dept_name:"",t.bookForm.jyrq=e.data.start_check_date?e.data.start_check_date:"",t.bookForm.ystf=e.data.chart_book_back?e.data.chart_book_back:"",t.bookForm.tlb=e.data.chart_pdf_back?e.data.chart_pdf_back:"",t.bookForm.gct=e.data.chart_map_edit?e.data.chart_map_edit:"",t.bookForm.ctjsxg=e.data.chart_edit?e.data.chart_edit:"",t.bookForm.ztbj=e.data.chart_editor?e.data.chart_editor:"",t.bookForm.zyy=e.data.chart_maker?e.data.chart_maker:"",t.bookForm.zz=e.data.chart_leader?e.data.chart_leader:"",t.bookForm.zyfzr=e.data.chart_manager?e.data.chart_manager:"",t.bookForm.cyfzr=e.data.chart_manager1?e.data.chart_manager1:"",t.bookCheckId=e.data.check_index_id,t.bookForm.dept_id=e.data.dept_id?e.data.dept_id:""}))})),s["a"].getAllWorkDaysById({indexID:e.task_index_id,chart_type:""}).then((function(e){t.$nextTick((function(){null===e.data?t.bookForm.days="":t.bookForm.days=e.data}))}))},beforeMapClose:function(){this.mapVisible=!1,this.fetchTableData(),this.page.current=1},beforeBookClose:function(){this.bookVisible=!1,this.fetchTableData(),this.page.current=1},multipleData:function(){return this.multipleSelection.map((function(t){return t.index_id})).join(",")},downLoad:function(){if(0==this.multipleSelection.length)return!1;s["a"].downLoadExcel({indexId:this.multipleData()}).then((function(t){Object(i["c"])(t,"任务质量评估")}))}}},d=p,u=(a("93ae"),a("2d50"),a("5d22")),_=Object(u["a"])(d,o,r,!1,null,"90cd9f66",null);e["default"]=_.exports},"93ae":function(t,e,a){"use strict";a("1575")},d651:function(t,e,a){}}]);