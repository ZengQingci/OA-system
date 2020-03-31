<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 个人信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">工号</div>
                        <div class="col-md-4">${sessionScope.loginUser.sn}</div>
                        <div class="col-md-2">姓名</div>
                        <div class="col-md-4">${sessionScope.loginUser.name}</div>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">所属部门</div>
                        <div class="col-md-4">${sessionScope.loginUser.department.name}</div>
                        <div class="col-md-2">职务</div>
                        <div class="col-md-4">${sessionScope.loginUser.post}</div>
                    </div>
                    <div class="panel-footer text-right">
                        <a type="button" class="button" href="/department/list"> 返回 </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"/>