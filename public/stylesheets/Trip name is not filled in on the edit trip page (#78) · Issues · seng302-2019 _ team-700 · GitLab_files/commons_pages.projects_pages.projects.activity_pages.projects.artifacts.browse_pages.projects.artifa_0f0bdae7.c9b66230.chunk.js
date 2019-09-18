(this.webpackJsonp=this.webpackJsonp||[]).push([[2],{196:function(e,t,n){"use strict";var r=n(0),i=n.n(r),o=n(33),s=n(76),a=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();var c=function(){function e(t){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e),this.projectSelectInput=i()(t),this.newItemBtn=i()(".new-project-item-link"),this.resourceType=this.newItemBtn.data("type"),this.resourceLabel=this.newItemBtn.data("label"),this.formattedText=this.deriveTextVariants(),this.groupId=this.projectSelectInput.data("groupId"),this.bindEvents(),this.initLocalStorage()}return a(e,[{key:"bindEvents",value:function(){var e=this;this.projectSelectInput.siblings(".new-project-item-select-button").on("click",function(t){return e.openDropdown(t)}),this.newItemBtn.on("click",function(t){e.getProjectFromLocalStorage()||(t.preventDefault(),e.openDropdown(t))}),this.projectSelectInput.on("change",function(){return e.selectProject()})}},{key:"initLocalStorage",value:function(){s.a.isLocalStorageAccessSafe()&&(this.localStorageKey=["group",this.groupId,this.formattedText.localStorageItemType,"recent-project"].join("-"),this.setBtnTextFromLocalStorage())}},{key:"openDropdown",value:function(e){i()(e.currentTarget).siblings(".project-item-select").select2("open")}},{key:"selectProject",value:function(){var e=JSON.parse(this.projectSelectInput.val()),t={url:e.url+"/"+this.projectSelectInput.data("relativePath"),name:e.name};this.setNewItemBtnAttributes(t),this.setProjectInLocalStorage(t)}},{key:"setBtnTextFromLocalStorage",value:function(){var e=this.getProjectFromLocalStorage();this.setNewItemBtnAttributes(e)}},{key:"setNewItemBtnAttributes",value:function(e){e?(this.newItemBtn.attr("href",e.url),this.newItemBtn.text(this.formattedText.defaultTextPrefix+" in "+e.name)):this.newItemBtn.text("Select project to create "+this.formattedText.presetTextSuffix)}},{key:"getProjectFromLocalStorage",value:function(){var e=localStorage.getItem(this.localStorageKey);return JSON.parse(e)}},{key:"setProjectInLocalStorage",value:function(e){var t=JSON.stringify(e);localStorage.setItem(this.localStorageKey,t)}},{key:"deriveTextVariants",value:function(){var e=this.resourceLabel;return{localStorageItemType:"new-"+this.resourceType.split("_").join("-").slice(0,-1),defaultTextPrefix:e,presetTextSuffix:this.resourceType.split("_").join(" ").slice(0,-1)}}}]),e}();function l(){i()(".ajax-project-select").each(function(e,t){var n,r,s=i()(t).data("simpleFilter")||!1;return this.groupId=i()(t).data("groupId"),this.includeGroups=i()(t).data("includeGroups"),this.allProjects=i()(t).data("allProjects")||!1,this.orderBy=i()(t).data("orderBy")||"id",this.withIssuesEnabled=i()(t).data("withIssuesEnabled"),this.withMergeRequestsEnabled=i()(t).data("withMergeRequestsEnabled"),n="Search for project",this.includeGroups&&(n+=" or group"),i()(t).select2({placeholder:n,minimumInputLength:0,query:(r=this,function(e){var t,n;return t=function(t){var n;return n={results:t},e.callback(n)},n=r.includeGroups?function(n){var r;return r=function(e){var r;return r=e.concat(n),t(r)},o.a.groups(e.term,{},r)}:t,r.groupId?o.a.groupProjects(r.groupId,e.term,{with_issues_enabled:r.withIssuesEnabled,with_merge_requests_enabled:r.withMergeRequestsEnabled},n):o.a.projects(e.term,{order_by:r.orderBy,with_issues_enabled:r.withIssuesEnabled,with_merge_requests_enabled:r.withMergeRequestsEnabled,membership:!r.allProjects},n)}),id:function(e){return s?e.id:JSON.stringify({name:e.name,url:e.web_url})},text:function(e){return e.name_with_namespace||e.name},dropdownCssClass:"ajax-project-dropdown"}),s?t:new c(t)})}n.d(t,"a",function(){return l})},453:function(e,t,n){"use strict";var r=n(1),i={components:{LoadingIcon:n(27).a},props:{isDisabled:{type:Boolean,required:!1,default:!1},isLoading:{type:Boolean,required:!1,default:!1},toggleText:{type:String,required:!1,default:Object(r.a)("Select")}}},o=n(2),s=Object(o.a)(i,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("button",{staticClass:"dropdown-menu-toggle dropdown-menu-full-width",attrs:{disabled:e.isDisabled||e.isLoading,type:"button","data-toggle":"dropdown","aria-expanded":"false"}},[n("loading-icon",{directives:[{name:"show",rawName:"v-show",value:e.isLoading,expression:"isLoading"}],attrs:{inline:!0}}),e._v(" "),[e.$slots.default?e._t("default"):n("span",{staticClass:"dropdown-toggle-text"},[e._v("\n      "+e._s(e.toggleText)+"\n    ")])],e._v(" "),n("span",{directives:[{name:"show",rawName:"v-show",value:!e.isLoading,expression:"!isLoading"}],staticClass:"dropdown-toggle-icon"},[n("i",{staticClass:"fa fa-chevron-down",attrs:{"aria-hidden":"true","data-hidden":"true"}})])],2)},[],!1,null,null,null);t.a=s.exports},522:function(e,t,n){"use strict";var r={};n.r(r),n.d(r,"setProject",function(){return w}),n.d(r,"setZone",function(){return y}),n.d(r,"setMachineType",function(){return _}),n.d(r,"setIsValidatingProjectBilling",function(){return O}),n.d(r,"fetchProjects",function(){return E}),n.d(r,"validateProjectBilling",function(){return I}),n.d(r,"fetchZones",function(){return S}),n.d(r,"fetchMachineTypes",function(){return T}),n.d(r,"default",function(){return P});var i={};n.r(i),n.d(i,"hasProject",function(){return C}),n.d(i,"hasZone",function(){return x}),n.d(i,"hasMachineType",function(){return k});var o,s=n(8),a=n(7),c=n(3),l=n.n(c),u=n(1),d=n(14),h=n(27),p={props:{placeholderText:{type:String,required:!0,default:Object(u.a)("Search")}},data:function(){return{searchQuery:this.value}},watch:{searchQuery:function(e){this.$emit("input",e)}}},f=n(2),g=Object(f.a)(p,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"dropdown-input"},[n("input",{directives:[{name:"model",rawName:"v-model",value:e.searchQuery,expression:"searchQuery"}],staticClass:"dropdown-input-field",attrs:{placeholder:e.placeholderText,type:"search",autocomplete:"off"},domProps:{value:e.searchQuery},on:{input:function(t){t.target.composing||(e.searchQuery=t.target.value)}}}),e._v(" "),n("i",{staticClass:"fa fa-search dropdown-input-search",attrs:{"aria-hidden":"true","data-hidden":"true"}}),e._v(" "),n("i",{staticClass:"fa fa-times dropdown-input-clear js-dropdown-input-clear",attrs:{"aria-hidden":"true","data-hidden":"true",role:"button"}})])},[],!1,null,null,null).exports,m={props:{name:{type:String,required:!0},value:{type:[Number,String],required:!0}}},b=Object(f.a)(m,function(){var e=this.$createElement;return(this._self._c||e)("input",{attrs:{name:this.name,type:"hidden"},domProps:{value:this.value}})},[],!1,null,null,null).exports,j=n(453),v=function(e){var t=e.resource,n=e.params,r=e.commit,i=e.mutation,o=e.payloadKey;return new Promise(function(e,s){return t.list(n).then(function(t){var n=t.result;r(i,n[o]),e()},function(e){s(e)})})},w=function(e,t){(0,e.commit)("SET_PROJECT",t)},y=function(e,t){(0,e.commit)("SET_ZONE",t)},_=function(e,t){(0,e.commit)("SET_MACHINE_TYPE",t)},O=function(e,t){(0,e.commit)("SET_IS_VALIDATING_PROJECT_BILLING",t)},E=function(e){var t=e.commit;return v({resource:gapi.client.cloudresourcemanager.projects,params:{},commit:t,mutation:"SET_PROJECTS",payloadKey:"projects"})},I=function(e){var t=e.dispatch,n=e.commit,r=e.state;return new Promise(function(e,i){var o=gapi.client.cloudbilling.projects.getBillingInfo({name:"projects/"+r.selectedProject.projectId});return n("SET_ZONE",""),n("SET_MACHINE_TYPE",""),o.then(function(r){var i=r.result.billingEnabled;n("SET_PROJECT_BILLING_STATUS",!!i),t("setIsValidatingProjectBilling",!1),e()},function(e){t("setIsValidatingProjectBilling",!1),i(e)})})},S=function(e){var t=e.commit,n=e.state;return v({resource:gapi.client.compute.zones,params:{project:n.selectedProject.projectId},commit:t,mutation:"SET_ZONES",payloadKey:"items"})},T=function(e){var t=e.commit,n=e.state;return v({resource:gapi.client.compute.machineTypes,params:{project:n.selectedProject.projectId,zone:n.selectedZone},commit:t,mutation:"SET_MACHINE_TYPES",payloadKey:"items"})},P=function(){},C=function(e){return!!e.selectedProject.projectId},x=function(e){return!!e.selectedZone},k=function(e){return!!e.selectedMachineType};function B(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var L=(B(o={},"SET_PROJECT",function(e,t){Object.assign(e,{selectedProject:t})}),B(o,"SET_IS_VALIDATING_PROJECT_BILLING",function(e,t){Object.assign(e,{isValidatingProjectBilling:t})}),B(o,"SET_PROJECT_BILLING_STATUS",function(e,t){Object.assign(e,{projectHasBillingEnabled:t})}),B(o,"SET_ZONE",function(e,t){Object.assign(e,{selectedZone:t})}),B(o,"SET_MACHINE_TYPE",function(e,t){Object.assign(e,{selectedMachineType:t})}),B(o,"SET_PROJECTS",function(e,t){Object.assign(e,{projects:t})}),B(o,"SET_ZONES",function(e,t){Object.assign(e,{zones:t})}),B(o,"SET_MACHINE_TYPES",function(e,t){Object.assign(e,{machineTypes:t})}),o);s.default.use(d.a);var N={store:new d.a.Store({actions:r,getters:i,mutations:L,state:{selectedProject:{projectId:"",name:""},selectedZone:"",selectedMachineType:"",isValidatingProjectBilling:null,projectHasBillingEnabled:null,projects:[],zones:[],machineTypes:[]}}),components:{LoadingIcon:h.a,DropdownButton:j.a,DropdownSearchInput:g,DropdownHiddenInput:b},props:{fieldId:{type:String,required:!0},fieldName:{type:String,required:!0},defaultValue:{type:String,required:!1,default:""}},data:function(){return{isLoading:!1,hasErrors:!1,searchQuery:"",gapiError:""}},computed:{results:function(){var e=this;return this.items?this.items.filter(function(t){return t.name.toLowerCase().indexOf(e.searchQuery)>-1}):[]}},methods:{fetchSuccessHandler:function(){var e=this;if(this.defaultValue){var t=l.a.find(this.items,function(t){return t.name===e.defaultValue});t&&this.setItem(t.name)}this.isLoading=!1,this.hasErrors=!1},fetchFailureHandler:function(e){this.isLoading=!1,this.hasErrors=!0,e.result&&e.result.error&&(this.gapiError=e.result.error.message)}}},D=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},H={name:"GkeProjectIdDropdown",mixins:[N],props:{docsUrl:{type:String,required:!0}},computed:D({},Object(d.d)(["selectedProject","isValidatingProjectBilling","projectHasBillingEnabled"]),Object(d.d)({items:"projects"}),Object(d.c)(["hasProject"]),{hasOneProject:function(){return this.items&&1===this.items.length},isDisabled:function(){return this.isLoading||this.isValidatingProjectBilling||this.items&&this.items.length<2},toggleText:function(){return this.isValidatingProjectBilling?Object(u.e)("ClusterIntegration|Validating project billing status"):this.isLoading?Object(u.e)("ClusterIntegration|Fetching projects"):this.hasProject?this.selectedProject.name:this.items?Object(u.e)("ClusterIntegration|Select project"):Object(u.e)("ClusterIntegration|No projects found")},helpText:function(){var e=void 0;return this.hasErrors?this.errorMessage:(this.items||(e="ClusterIntegration|We were unable to fetch any projects. Ensure that you have a project on %{docsLinkStart}Google Cloud Platform%{docsLinkEnd}."),e=this.items&&this.items.length?"ClusterIntegration|To use a new project, first create one on %{docsLinkStart}Google Cloud Platform%{docsLinkEnd}.":"ClusterIntegration|To create a cluster, first create a project on %{docsLinkStart}Google Cloud Platform%{docsLinkEnd}.",Object(u.f)(Object(u.e)(e),{docsLinkEnd:'&nbsp;<i class="fa fa-external-link" aria-hidden="true"></i></a>',docsLinkStart:'<a href="'+l.a.escape(this.docsUrl)+'" target="_blank" rel="noopener noreferrer">'},!1))},errorMessage:function(){return this.projectHasBillingEnabled?Object(u.f)(Object(u.e)("ClusterIntegration|An error occured while trying to fetch your projects: %{error}"),{error:this.gapiError}):this.gapiError?Object(u.e)("ClusterIntegration|We could not verify that one of your projects on GCP has billing enabled. Please try again."):Object(u.f)(Object(u.e)('This project does not have billing enabled. To create a cluster, <a href=%{linkToBilling} target="_blank" rel="noopener noreferrer">enable billing <i class="fa fa-external-link" aria-hidden="true"></i></a> and try again.'),{linkToBilling:"https://console.cloud.google.com/freetrial?utm_campaign=2018_cpanel&utm_source=gitlab&utm_medium=referral"},!1)}}),watch:{selectedProject:function(){this.setIsValidatingProjectBilling(!0),this.validateProjectBilling().then(this.validateProjectBillingSuccessHandler).catch(this.validateProjectBillingFailureHandler)}},created:function(){this.isLoading=!0,this.fetchProjects().then(this.fetchSuccessHandler).catch(this.fetchFailureHandler)},methods:D({},Object(d.b)(["fetchProjects","setIsValidatingProjectBilling","validateProjectBilling"]),Object(d.b)({setItem:"setProject"}),{fetchSuccessHandler:function(){var e=this;if(this.defaultValue){var t=l.a.find(this.items,function(t){return t.projectId===e.defaultValue});t&&this.setItem(t)}else 1===this.items.length&&this.setItem(this.items[0]);this.isLoading=!1,this.hasErrors=!1},validateProjectBillingSuccessHandler:function(){this.hasErrors=!this.projectHasBillingEnabled},validateProjectBillingFailureHandler:function(e){this.hasErrors=!0,this.gapiError=e.result?e.result.error.message:e}})},M=Object(f.a)(H,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{staticClass:"js-gcp-project-id-dropdown dropdown"},[n("dropdown-hidden-input",{attrs:{name:e.fieldName,value:e.selectedProject.projectId}}),e._v(" "),n("dropdown-button",{class:{"border-danger":e.hasErrors,"read-only":e.hasOneProject},attrs:{"is-disabled":e.isDisabled,"is-loading":e.isLoading,"toggle-text":e.toggleText}}),e._v(" "),n("div",{staticClass:"dropdown-menu dropdown-select"},[n("dropdown-search-input",{attrs:{"placeholder-text":e.s__("ClusterIntegration|Search projects")},model:{value:e.searchQuery,callback:function(t){e.searchQuery=t},expression:"searchQuery"}}),e._v(" "),n("div",{staticClass:"dropdown-content"},[n("ul",[n("li",{directives:[{name:"show",rawName:"v-show",value:!e.results.length,expression:"!results.length"}]},[n("span",{staticClass:"menu-item"},[e._v("\n              "+e._s(e.s__("ClusterIntegration|No projects matched your search"))+"\n            ")])]),e._v(" "),e._l(e.results,function(t){return n("li",{key:t.project_number},[n("button",{attrs:{type:"button"},on:{click:function(n){n.preventDefault(),e.setItem(t)}}},[e._v("\n              "+e._s(t.name)+"\n            ")])])})],2)]),e._v(" "),n("div",{staticClass:"dropdown-loading"},[n("loading-icon")],1)],1)],1),e._v(" "),n("span",{staticClass:"form-text",class:{"text-danger":e.hasErrors,"text-muted":!e.hasErrors},domProps:{innerHTML:e._s(e.helpText)}})])},[],!1,null,null,null).exports,Z=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},V={name:"GkeZoneDropdown",mixins:[N],computed:Z({},Object(d.d)(["selectedProject","selectedZone","projects","isValidatingProjectBilling","projectHasBillingEnabled"]),Object(d.d)({items:"zones"}),{isDisabled:function(){return this.isLoading||this.isValidatingProjectBilling||!this.projectHasBillingEnabled},toggleText:function(){return this.isLoading?Object(u.e)("ClusterIntegration|Fetching zones"):this.selectedZone?this.selectedZone:this.projectHasBillingEnabled?Object(u.e)("ClusterIntegration|Select zone"):Object(u.e)("ClusterIntegration|Select project to choose zone")},errorMessage:function(){return Object(u.f)(Object(u.e)("ClusterIntegration|An error occured while trying to fetch project zones: %{error}"),{error:this.gapiError})}}),watch:{isValidatingProjectBilling:function(e){this.hasErrors=!1,!e&&this.projectHasBillingEnabled&&(this.isLoading=!0,this.fetchZones().then(this.fetchSuccessHandler).catch(this.fetchFailureHandler))}},methods:Z({},Object(d.b)(["fetchZones"]),Object(d.b)({setItem:"setZone"}))},A=Object(f.a)(V,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{staticClass:"js-gcp-zone-dropdown dropdown"},[n("dropdown-hidden-input",{attrs:{name:e.fieldName,value:e.selectedZone}}),e._v(" "),n("dropdown-button",{class:{"border-danger":e.hasErrors},attrs:{"is-disabled":e.isDisabled,"is-loading":e.isLoading,"toggle-text":e.toggleText}}),e._v(" "),n("div",{staticClass:"dropdown-menu dropdown-select"},[n("dropdown-search-input",{attrs:{"placeholder-text":e.s__("ClusterIntegration|Search zones")},model:{value:e.searchQuery,callback:function(t){e.searchQuery=t},expression:"searchQuery"}}),e._v(" "),n("div",{staticClass:"dropdown-content"},[n("ul",[n("li",{directives:[{name:"show",rawName:"v-show",value:!e.results.length,expression:"!results.length"}]},[n("span",{staticClass:"menu-item"},[e._v("\n              "+e._s(e.s__("ClusterIntegration|No zones matched your search"))+"\n            ")])]),e._v(" "),e._l(e.results,function(t){return n("li",{key:t.id},[n("button",{attrs:{type:"button"},on:{click:function(n){n.preventDefault(),e.setItem(t.name)}}},[e._v("\n              "+e._s(t.name)+"\n            ")])])})],2)]),e._v(" "),n("div",{staticClass:"dropdown-loading"},[n("loading-icon")],1)],1)],1),e._v(" "),e.hasErrors?n("span",{staticClass:"form-text",class:{"text-danger":e.hasErrors,"text-muted":!e.hasErrors}},[e._v("\n    "+e._s(e.errorMessage)+"\n  ")]):e._e()])},[],!1,null,null,null).exports,q=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},z={name:"GkeMachineTypeDropdown",mixins:[N],computed:q({},Object(d.d)(["isValidatingProjectBilling","projectHasBillingEnabled","selectedZone","selectedMachineType"]),Object(d.d)({items:"machineTypes"}),Object(d.c)(["hasZone","hasMachineType"]),{allDropdownsSelected:function(){return this.projectHasBillingEnabled&&this.hasZone&&this.hasMachineType},isDisabled:function(){return this.isLoading||this.isValidatingProjectBilling||!this.projectHasBillingEnabled||!this.hasZone},toggleText:function(){return this.isLoading?Object(u.e)("ClusterIntegration|Fetching machine types"):this.selectedMachineType?this.selectedMachineType:this.projectHasBillingEnabled||this.hasZone?this.hasZone?Object(u.e)("ClusterIntegration|Select machine type"):Object(u.e)("ClusterIntegration|Select zone to choose machine type"):Object(u.e)("ClusterIntegration|Select project and zone to choose machine type")},errorMessage:function(){return Object(u.f)(Object(u.e)("ClusterIntegration|An error occured while trying to fetch zone machine types: %{error}"),{error:this.gapiError})}}),watch:{selectedZone:function(){this.hasErrors=!1,this.hasZone&&(this.isLoading=!0,this.fetchMachineTypes().then(this.fetchSuccessHandler).catch(this.fetchFailureHandler))},selectedMachineType:function(){this.enableSubmit()}},methods:q({},Object(d.b)(["fetchMachineTypes"]),Object(d.b)({setItem:"setMachineType"}),{enableSubmit:function(){if(this.allDropdownsSelected){var e=document.querySelector(".js-gke-cluster-creation-submit");e&&e.removeAttribute("disabled")}}})},G=Object(f.a)(z,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("div",{staticClass:"js-gcp-machine-type-dropdown dropdown"},[n("dropdown-hidden-input",{attrs:{name:e.fieldName,value:e.selectedMachineType}}),e._v(" "),n("dropdown-button",{class:{"border-danger":e.hasErrors},attrs:{"is-disabled":e.isDisabled,"is-loading":e.isLoading,"toggle-text":e.toggleText}}),e._v(" "),n("div",{staticClass:"dropdown-menu dropdown-select"},[n("dropdown-search-input",{attrs:{"placeholder-text":e.s__("ClusterIntegration|Search machine types")},model:{value:e.searchQuery,callback:function(t){e.searchQuery=t},expression:"searchQuery"}}),e._v(" "),n("div",{staticClass:"dropdown-content"},[n("ul",[n("li",{directives:[{name:"show",rawName:"v-show",value:!e.results.length,expression:"!results.length"}]},[n("span",{staticClass:"menu-item"},[e._v("\n              "+e._s(e.s__("ClusterIntegration|No machine types matched your search"))+"\n            ")])]),e._v(" "),e._l(e.results,function(t){return n("li",{key:t.id},[n("button",{attrs:{type:"button"},on:{click:function(n){n.preventDefault(),e.setItem(t.name)}}},[e._v("\n              "+e._s(t.name)+"\n            ")])])})],2)]),e._v(" "),n("div",{staticClass:"dropdown-loading"},[n("loading-icon")],1)],1)],1),e._v(" "),e.hasErrors?n("span",{staticClass:"form-text",class:{"text-danger":e.hasErrors,"text-muted":!e.hasErrors}},[e._v("\n    "+e._s(e.errorMessage)+"\n  ")]):e._e()])},[],!1,null,null,null).exports,R=Object(u.e)("ClusterIntegration|An error occurred when trying to contact the Google Cloud API. Please try again later."),F=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e};var Q=function(e,t,n){var r=arguments.length>3&&void 0!==arguments[3]?arguments[3]:{},i=document.querySelector(e);if(!i)return!1;var o,a,c,l=i.querySelector("input");return new s.default({el:i,components:(o={},a=n,c=t,a in o?Object.defineProperty(o,a,{value:c,enumerable:!0,configurable:!0,writable:!0}):o[a]=c,o),render:function(e){return e(n,{props:F({fieldName:l.getAttribute("name"),fieldId:l.getAttribute("id"),defaultValue:l.value},r)})}})},J=function(){Object(a.a)(R)},$=function(){var e=document.querySelector(".js-gke-cluster-creation");return!!e&&gapi.client.init({discoveryDocs:["https://www.googleapis.com/discovery/v1/apis/cloudbilling/v1/rest","https://www.googleapis.com/discovery/v1/apis/cloudresourcemanager/v1/rest","https://www.googleapis.com/discovery/v1/apis/compute/v1/rest"]}).then(function(){gapi.client.setToken({access_token:e.dataset.token}),function(){var e=".js-gcp-project-id-dropdown-entry-point",t=document.querySelector(e);Q(e,M,"gke-project-id-dropdown",{docsUrl:t.dataset.docsurl})}(),Q(".js-gcp-zone-dropdown-entry-point",A,"gke-zone-dropdown"),Q(".js-gcp-machine-type-dropdown-entry-point",G,"gke-machine-type-dropdown")}).catch(J)};t.a=function(){return gapi?gapi.load("client",$):(J(),!1)}},526:function(e,t,n){"use strict";var r=n(0),i=n.n(r),o=n(39),s=n.n(o),a=n(1),c=n(21),l=n(5),u=n(7),d=n(196),h=function(){function e(e,t){for(var n=0;n<t.length;n++){var r=t[n];r.enumerable=r.enumerable||!1,r.configurable=!0,"value"in r&&(r.writable=!0),Object.defineProperty(e,r.key,r)}}return function(t,n,r){return n&&e(t.prototype,n),r&&e(t,r),t}}();var p=function(){function e(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,e);var t=i()("ul.clone-options-dropdown"),n=i()("#project_clone"),r=i()("a.clone-dropdown-btn span"),o=r.text().trim();o.length>0&&i()("a:contains('"+o+"')",t).addClass("is-active"),i()("a",t).on("click",function(e){var o=i()(e.currentTarget),s=o.attr("href"),a=o.find(".dropdown-menu-inner-title").text();return e.preventDefault(),i()(".is-active",t).not(o).removeClass("is-active"),o.toggleClass("is-active"),n.val(s),r.text(a),i()(".clone").text(s)}),e.initRefSwitcher(),i()(".project-refs-select").on("change",function(){return i()(this).parents("form").submit()}),i()(".hide-no-ssh-message").on("click",function(e){return s.a.set("hide_no_ssh_message","false"),i()(this).parents(".no-ssh-key-message").remove(),e.preventDefault()}),i()(".hide-no-password-message").on("click",function(e){return s.a.set("hide_no_password_message","false"),i()(this).parents(".no-password-message").remove(),e.preventDefault()}),e.projectSelectDropdown()}return h(e,null,[{key:"projectSelectDropdown",value:function(){Object(d.a)(),i()(".project-item-select").on("click",function(t){return e.changeProject(i()(t.currentTarget).val())})}},{key:"changeProject",value:function(e){return window.location=e}},{key:"initRefSwitcher",value:function(){var e=document.createElement("li"),t=document.createElement("a");return t.href="#",i()(".js-project-refs-dropdown").each(function(){var n,r;return n=i()(this),r=n.data("selected"),n.glDropdown({data:function(e,t){l.a.get(n.data("refsUrl"),{params:{ref:n.data("ref"),search:e}}).then(function(e){var n=e.data;return t(n)}).catch(function(){return Object(u.a)(Object(a.a)("An error occurred while getting projects"))})},selectable:!0,filterable:!0,filterRemote:!0,filterByText:!0,inputFieldName:n.data("inputFieldName"),fieldName:n.data("fieldName"),renderRow:function(n){var i=e.cloneNode(!1);if(null!=n.header)i.className="dropdown-header",i.textContent=n.header;else{var o=t.cloneNode(!1);n===r&&(o.className="is-active"),o.textContent=n,o.dataset.ref=n,i.appendChild(o)}return i},id:function(e,t){return t.attr("data-ref")},toggleLabel:function(e,t){return t.text().trim()},clicked:function(e){if(e.e.preventDefault(),i()('input[name="ref"]').length){var t=n.closest("form"),r=n.data("visit"),o=!!r||r,s=t.attr("action"),a=-1===s.indexOf("?")?"?":"&";o&&Object(c.g)(""+s+a+t.serialize())}}})})}}]),e}();t.a=p},527:function(e,t,n){"use strict";n.d(t,"a",function(){return c});var r=n(0),i=n.n(r),o=n(5),s=n(1),a=n(7);function c(){var e=document.querySelector(".gcp-signup-offer");if(e){var t=e.getElementsByClassName("close")[0],n=t.dataset,r=n.dismissEndpoint,c=n.featureId;t.addEventListener("click",function(){o.a.post(r,{feature_name:c}).then(function(){i()(e).alert("close")}).catch(function(){Object(a.a)(Object(s.a)("An error occurred while dismissing the alert. Refresh the page and try again."))})})}}},57:function(e,t,n){"use strict";n.r(t);var r=n(527),i=n(522),o=n(526),s=n(88);document.addEventListener("DOMContentLoaded",function(){var e=document.body.dataset.page;["projects:clusters:new","projects:clusters:create_gcp","projects:clusters:create_user"].indexOf(e)>-1&&(Object(r.a)(),Object(i.a)()),new o.a,new s.a})},88:function(e,t,n){"use strict";var r=n(29),i=n.n(r),o=n(75),s=n(208);var a=function(e){function t(){!function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}(this,t);var e=function(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}(this,(t.__proto__||Object.getPrototypeOf(t)).call(this));return i.a.bind("g p",function(){return Object(o.a)(".shortcuts-project")}),i.a.bind("g v",function(){return Object(o.a)(".shortcuts-project-activity")}),i.a.bind("g f",function(){return Object(o.a)(".shortcuts-tree")}),i.a.bind("g c",function(){return Object(o.a)(".shortcuts-commits")}),i.a.bind("g j",function(){return Object(o.a)(".shortcuts-builds")}),i.a.bind("g n",function(){return Object(o.a)(".shortcuts-network")}),i.a.bind("g d",function(){return Object(o.a)(".shortcuts-repository-charts")}),i.a.bind("g i",function(){return Object(o.a)(".shortcuts-issues")}),i.a.bind("g b",function(){return Object(o.a)(".shortcuts-issue-boards")}),i.a.bind("g m",function(){return Object(o.a)(".shortcuts-merge_requests")}),i.a.bind("g w",function(){return Object(o.a)(".shortcuts-wiki")}),i.a.bind("g s",function(){return Object(o.a)(".shortcuts-snippets")}),i.a.bind("g k",function(){return Object(o.a)(".shortcuts-kubernetes")}),i.a.bind("g e",function(){return Object(o.a)(".shortcuts-environments")}),i.a.bind("g l",function(){return Object(o.a)(".shortcuts-metrics")}),i.a.bind("i",function(){return Object(o.a)(".shortcuts-new-issue")}),e.enabledHelp.push(".hidden-shortcut.project"),e}return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}(t,s["a"]),t}();t.a=a}}]);
//# sourceMappingURL=commons~pages.projects~pages.projects.activity~pages.projects.artifacts.browse~pages.projects.artifa~0f0bdae7.c9b66230.chunk.js.map