
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/buildings/"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home Admin</a>
                </li>
                <li class="active">Danh sách toàn nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="font-family: 'Times New Roman', Times, serif;">
            <div class="page-header">
                <h1>
                    Danh sách toàn nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <!-- Main page -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <span class="widget-toolbar">

										<a href="#" data-action="reload">
											<i class="ace-icon fa fa-refresh"></i>
										</a>

										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>

										<a href="#" data-action="close">
											<i class="ace-icon fa fa-times"></i>
										</a>
									</span>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="/admin/building-list" id="listForm" method="GET">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div>
                                                    <label>Tên tòa nhà</label>
                                                    <form:input class="form-control" path="name" placeholder="Tên tòa nhà..." />
<%--                                                    <input class="form-control" type="text" id="name" name="name" value="${modelSearch.name}"--%>
<%--                                                           placeholder="Tên tòa nhà..." />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-6">
                                                <div>
                                                    <label>Diện tích sàn</label>
                                                    <form:input class="form-control" path="floorArea"/>
<%--                                                    <input class="form-control" type="number" id="floorArea" name="floorArea"--%>
<%--                                                           value="${modelSearch.floorArea}" />--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <div>
                                                    <label>Chọn quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">--Chọn quận--</form:option>
                                                        <form:options items="${districtCode}"></form:options>
                                                    </form:select>
                                                </div>
                                            </div>

                                            <div class="col-xs-5">
                                                <div>
                                                    <label>Phường</label>
                                                    <form:input class="form-control" path="ward"/>
<%--                                                    <input class="form-control" type="text" id="ward" name="ward"--%>
<%--                                                           value="${modelSearch.ward}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-5">
                                                <div>
                                                    <label>Đường</label>
                                                    <form:input class="form-control" path="street"/>
<%--                                                    <input class="form-control" type="text" id="street" name="street"--%>
<%--                                                           value="${modelSearch.street}" />--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"/>
<%--                                                    <input class="form-control" type="number" id="numberOfBasement" name="numberOfBasement" value="${modelSearch.numberOfBasement}"--%>
<%--                                                           placeholder="" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Hướng</label>
                                                    <form:input class="form-control" path="direction"/>
<%--                                                    <input class="form-control" type="text" id="direction" name="direction"--%>
<%--                                                           value="${modelSearch.direction}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-4">
                                                <div>
                                                    <label>Hạng</label>
                                                    <form:input class="form-control" path="level"/>
<%--                                                    <input class="form-control" type="number" id="level" name="level"--%>
<%--                                                           value="${modelSearch.level}" />--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"/>
<%--                                                    <input class="form-control" type="number" id="areaFrom" name="areaFrom" value="${modelSearch.areaFrom}"--%>
<%--                                                           placeholder="" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
<%--                                                    <input class="form-control" type="text" id="areaTo" name="areaTo"--%>
<%--                                                           value="${modelSearch.areaTo}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
<%--                                                    <input class="form-control" type="number" id="rentPriceFrom" name="rentPriceFrom"--%>
<%--                                                           value="${modelSearch.rentPriceFrom}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-3">
                                                <div>
                                                    <label>Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
<%--                                                    <input class="form-control" type="number" name="rentPriceTo" id="rentPriceTo"--%>
<%--                                                           value="${modelSearch.rentPriceTo}" />--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">

                                            <div class="col-xs-5">
                                                <div>
                                                    <label>Tên quản lí</label>
                                                    <form:input class="form-control" path="managerName"/>
<%--                                                    <input class="form-control" type="text" id="managerName" name="managerName"--%>
<%--                                                           value="${modelSearch.managerName}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-5">
                                                <div>
                                                    <label>SĐT quản lí</label>
                                                    <form:input class="form-control" path="managerPhone"/>
<%--                                                    <input class="form-control" type="text" id="managerPhone" name="managerPhone"--%>
<%--                                                           value="${modelSearch.managerPhone}" />--%>
                                                </div>
                                            </div>

                                            <div class="col-xs-2">
                                                <div>
                                                    <label>Chọn nhân viên</label>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="">--Chọn nhân viên--</form:option>
                                                        <form:options items="${staffs}"></form:options>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                               <form:checkboxes path="typeCode" items="${typeCode}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <button class="btn btn-info" type="button" id="btnSearch">
                                                    <i class="ace-icon glyphicon glyphicon-search"></i>
                                                    Tìm kiếm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </form:form>
                            </div>
                        </div>
                        <div class="pull-right">
                            <a href="/admin/building-edit">
                                <button class="btn btn-app btn-success btn-xs" title="Thêm tòa nhà">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                         fill="currentColor" class="bi bi-building-fill-add" viewBox="0 0 16 16">
                                        <path
                                                d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0" />
                                        <path
                                                d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-app btn-danger btn-xs" title="Xóa tòa nhà"
                                    id="btnDeleteBuilding">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                     fill="currentColor" class="bi bi-building-fill-dash" viewBox="0 0 16 16">
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="hr hr-30 dotted hr-double"></div>
            <!-- Table -->
            <div class="row">
                <div class="col-xs-12">
                    <table id="buildingList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lí</th>
                            <th>Số điện thoại quản lí</th>
                            <th>D.tích sàn</th>
                            <th>D.tích trống</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí MG</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${listBuilding}" var="it">
                         <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${it.id}">
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td>${it.name}</td>
                            <td>${it.address}</td>
                            <td>${it.numberOfBasement}</td>
                            <td>${it.managerName}</td>
                            <td>${it.managerPhone}</td>
                            <td>${it.floorArea}</td>
                            <td>${it.emptyArea}</td>
                            <td>${it.rentPrice}</td>
                            <td>${it.serviceFee}</td>
                            <td>${it.brokerageFee}</td>

                            <td>
                                <div class="hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-success" title="Giao toà nhà"
                                            onclick="assignmentBuilding(${it.id})">
                                        <i class="ace-icon fa fa-check bigger-120"></i>
                                    </button>

                                    <a href="/admin/building-edit-${it.id}">
                                    <button class="btn btn-xs btn-info" title="Sửa thông tin">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </button>
                                    </a>
                                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"
                                            onclick="deleteBuilding(${it.id})">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </button>

                                </div>
                            </td>
                        </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>

</div><!-- /.main-content -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table id="staffList" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace" value="">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
<%--                    <tr>--%>
<%--                        <td class="center">--%>
<%--                            <label class="pos-rel">--%>
<%--                                <input type="checkbox" class="ace" value="1">--%>
<%--                                <span class="lbl"></span>--%>
<%--                            </label>--%>
<%--                        </td>--%>

<%--                        <td class="center">Nguyễn Văn A</td>--%>
<%--                    </tr>--%>

                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        console.log($('#buildingId').val())

        loadStaffs(buildingId);
    }

    function loadStaffs(buildingId){
        $.ajax({
            type: "GET",
            url: "${buildingAPI}" + buildingId + "/staffs",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" class="check-box-element" value=' + item.staffId + ' id=checkbox_' +  item.staffId + ' ' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</tr>'
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.log("Success");
            },
            error: function (response) {
                console.log("Failed");
                console.log(response);
                alert("Failed")
            }
        });
    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs
        assignBuilding(data);
    })

    function assignBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}staffs",
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json",
            success: function (response) {
                console.log("Success");
                alert(response)
            },
            error: function (response) {
                console.log("Failed");
                console.log(response);
                alert("Failed")
            }
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data["buildingIds"] = buildingIds;
        deleteBuildings(data);
    })


    function deleteBuilding(buildingId) {
        var data = {};
        data["buildingIds"] = buildingId;
        deleteBuildings(data);
    }


    function deleteBuildings(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}" + data['buildingIds'],
            // data: JSON.stringify(data),
            // dataType: "json",
            contentType: "application/json",
            success: function () {
                console.log("Success");
                alert("Delete Success")
            },
            error: function () {
                console.log("Failed");
                console.log();
                alert("Delete Failed")
            }
        });
    }

    $('#btnSearch').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>
